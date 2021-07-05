package com.lab4.demo.util.report;

import com.lab4.demo.model.Cart;
import com.lab4.demo.model.Product;
import com.lab4.demo.repo.CartRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.lab4.demo.util.report.ReportType.PDF;

@RequiredArgsConstructor
@Service
public class PdfReportService implements ReportService {


//    private final PatientRepository patientRepository;
    private final CartRepository cartRepository;


    @Override
    public String export() throws IOException {

        System.out.println("entered in PDF to create");
        try {
            Date now = new Date();
            List<Cart> carts = cartRepository.findAll().stream().filter(consultation -> consultation.getId() != 0 ).collect(Collectors.toList());

            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.beginText();
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 700);
            for (Cart cart : carts) {
                contentStream.showText(cart.getId().toString() + "   ");
                contentStream.showText("Client: " + cart.getClient().getUsername() + "     ");
                List<Product> products = cart.getProducts();
                contentStream.showText("Products bought: ");
                for (Product product : products) {
                    contentStream.showText(product.getName() + " ");
                }
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.close();
            doc.save("myPDF_Final.pdf");
            System.out.println("pdf was created");
            doc.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
        return "I am a PDF reporter.";
    }


    @Override
    public ReportType getType() {
        return PDF;
    }


}
