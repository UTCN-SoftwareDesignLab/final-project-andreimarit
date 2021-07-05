package com.lab4.demo.controller;

import com.lab4.demo.dto.ProductDTO;
import com.lab4.demo.service.ProductService;
import com.lab4.demo.util.report.ReportServiceFactory;
import com.lab4.demo.util.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(PRODUCT)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ReportServiceFactory reportServiceFactory;


    @GetMapping
    public List<ProductDTO> allProducts() {
        return productService.findAll();
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO product) {
        return productService.create(product);
    }

    @PatchMapping(ID)
    public ProductDTO edit(@RequestBody ProductDTO product, @PathVariable Long id) {
        return productService.edit(product, id);
    }

    //
    @GetMapping(EXPORT_REPORT)
    public String exportReport(@PathVariable ReportType type) throws IOException {
        return reportServiceFactory.getReportService(type).export();
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }

//    @GetMapping(TAG)
//    public List<ProductDTO>  findNAG(@RequestBody String name, @RequestBody String author, @RequestBody String genre){
//        return productService.findByNAG(name, author, genre);
//    }
//
//    @PostMapping(ID + SELL)
//    public ResponseEntity<?> sell(@PathVariable Long id, @PathVariable int quantity){
//        if(productService.sold(id, quantity)) return ResponseEntity.ok(new MessageResponse("The selling was made!"));
//        else return ResponseEntity.badRequest().body(new MessageResponse("Something wrong with the selling: Error!"));
//    }


}
