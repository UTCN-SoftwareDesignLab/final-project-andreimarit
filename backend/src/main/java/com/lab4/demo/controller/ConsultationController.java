package com.lab4.demo.controller;

import com.lab4.demo.dto.ConsultationDTO;
import com.lab4.demo.sending.EmailSenderService;
import com.lab4.demo.service.ConsultationService;
import com.lab4.demo.sms.SMSService;
import com.lab4.demo.util.report.ReportServiceFactory;
import com.lab4.demo.util.report.ReportType;
import com.twilio.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RequestMapping(CONSULTATIONS)
@RestController
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;
    private final EmailSenderService sendEmailService;
    private final SMSService smsService;
    private final SimpMessagingTemplate webSocket;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<ConsultationDTO> findAll(){
        return consultationService.findAll();
    }

    @PostMapping
    public ConsultationDTO create(@RequestBody ConsultationDTO consultationDTO){
        return consultationService.create(consultationDTO);
    }

    @PatchMapping(ID)
    public ConsultationDTO edit(@RequestBody ConsultationDTO consultationDTO, @PathVariable Long id){
        return consultationService.edit(id, consultationDTO);
    }

    @GetMapping(EMAILSEND)
    public int sendEmail() throws Exception {
        System.out.println("entered in email");
        sendEmailService.sendEmail();
        System.out.println("sent the Email");
        return 1;
    }

    @PostMapping(SMSSEND)
    public int sendSMS() {
        try {
            smsService.send();
        }
        catch (ApiException e){
            webSocket.convertAndSend("/consultation/sms", getTimeStamp() + ": Error sending the SMS: "+e.getMessage());
            throw e;
        }
        webSocket.convertAndSend("/consultation/sms", getTimeStamp() + ": SMS has been sent!");

        return 1;
    }

//    @RequestMapping(value = "/smscallback", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public void smsCallback(@RequestBody MultiValueMap<String, String> map) {
//        smsService.receive(map);
//        webSocket.convertAndSend("/consultation/sms", getTimeStamp() + ": Twilio has made a callback request! Here are the contents: "+map.toString());
//    }

    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
    }

    @GetMapping(EXPORT_REPORT)
    public String exportReport(@PathVariable ReportType type) throws IOException {

        return reportServiceFactory.getReportService(type).export();
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id) {
        consultationService.deleteById(id);
    }

}
