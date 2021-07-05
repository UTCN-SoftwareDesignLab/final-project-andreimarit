package com.lab4.demo.controller;

import com.lab4.demo.dto.CartDTO;
import com.lab4.demo.dto.DiscountDTO;
import com.lab4.demo.sending.EmailSenderService;
import com.lab4.demo.service.DiscountService;
import com.lab4.demo.util.report.ReportServiceFactory;
import com.lab4.demo.util.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(DISCOUNT)
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;
    private final ReportServiceFactory reportServiceFactory;
    private final EmailSenderService emailSenderService;

    @GetMapping
    public List<DiscountDTO> allDiscounts() {
        return discountService.findAll();
    }

    @GetMapping(ID)
    public List<DiscountDTO> allDiscountsClient(@PathVariable Long id){return discountService.findAllByClient(id);}

    @PostMapping
    public DiscountDTO create(@RequestBody DiscountDTO discount) {
        return discountService.create(discount);
    }

    @PatchMapping(ID)
    public DiscountDTO edit(@RequestBody DiscountDTO discount, @PathVariable Long id) {
        return discountService.edit(discount, id);
    }
    //
    @GetMapping(EXPORT_REPORT)
    public String exportReport(@PathVariable ReportType type) throws IOException {
        return reportServiceFactory.getReportService(type).export();
    }

    @GetMapping(EMAILSEND)
    public void sendEmail() throws Exception {
        System.out.println("entered in email");
        emailSenderService.sendEmail();
        System.out.println("sent the Email");
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id){
        discountService.delete(id);
    }

//    @GetMapping(TAG)
//    public List<DiscountDTO>  findNAG(@RequestBody String name, @RequestBody String author, @RequestBody String genre){
//        return discountService.findByNAG(name, author, genre);
//    }
//
//    @PostMapping(ID + SELL)
//    public ResponseEntity<?> sell(@PathVariable Long id, @PathVariable int quantity){
//        if(discountService.sold(id, quantity)) return ResponseEntity.ok(new MessageResponse("The selling was made!"));
//        else return ResponseEntity.badRequest().body(new MessageResponse("Something wrong with the selling: Error!"));
//    }


}
