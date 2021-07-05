package com.lab4.demo.util.report;

import com.lab4.demo.model.Discount;
import com.lab4.demo.model.Product;
import com.lab4.demo.repo.DiscountRepository;
import com.lab4.demo.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import static com.lab4.demo.util.report.ReportType.CSV;
import static com.lab4.demo.util.report.ReportType.CSV2;

@RequiredArgsConstructor
@Service
public class CSV2ReportService implements ReportService {

    private final DiscountRepository discountRepository;

    @Override
    public String export() throws IOException {

        System.out.println("has entered in CSV to create");
        try{
            List<Discount> discounts = discountRepository.findAll().stream().collect(Collectors.toList());

            PrintWriter writer = new PrintWriter("myCSV2.csv", "UTF-8");
            for (Discount discount : discounts) {
                writer.println(discount);
            }
            writer.close();
        }
        catch ( FileNotFoundException | UnsupportedEncodingException e){
            System.out.println(e);
        }


        return "I am a CSV2 reporter.";
    }

    @Override
    public ReportType getType() {
        return CSV2;
    }
}
