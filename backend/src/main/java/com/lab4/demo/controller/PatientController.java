package com.lab4.demo.controller;

import com.lab4.demo.dto.PatientDTO;
import com.lab4.demo.service.PatientService;
import com.lab4.demo.util.report.ReportServiceFactory;
import com.lab4.demo.util.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(PATIENT)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final ReportServiceFactory reportServiceFactory;


    @GetMapping
    public List<PatientDTO> allPatients() {
        return patientService.findAll();
    }

    @PostMapping
    public PatientDTO create(@RequestBody PatientDTO patient) {
        return patientService.create(patient);
    }

    @PatchMapping(ID)
    public PatientDTO edit(@RequestBody PatientDTO patient, @PathVariable Long id) {
        return patientService.edit(patient, id);
    }
//
    @GetMapping(EXPORT_REPORT)
    public String exportReport(@PathVariable ReportType type) throws IOException {
        return reportServiceFactory.getReportService(type).export();
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id){
        patientService.delete(id);
    }

//    @GetMapping(TAG)
//    public List<PatientDTO>  findNAG(@RequestBody String name, @RequestBody String author, @RequestBody String genre){
//        return patientService.findByNAG(name, author, genre);
//    }
//
//    @PostMapping(ID + SELL)
//    public ResponseEntity<?> sell(@PathVariable Long id, @PathVariable int quantity){
//        if(patientService.sold(id, quantity)) return ResponseEntity.ok(new MessageResponse("The selling was made!"));
//        else return ResponseEntity.badRequest().body(new MessageResponse("Something wrong with the selling: Error!"));
//    }


}
