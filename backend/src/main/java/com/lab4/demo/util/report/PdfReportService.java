package com.lab4.demo.util.report;

import com.lab4.demo.model.Consultation;
import com.lab4.demo.repo.ConsultationRepository;
import com.lab4.demo.repo.PatientRepository;
import com.lab4.demo.model.Patient;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.lab4.demo.util.report.ReportType.PDF;

@RequiredArgsConstructor
@Service
public class PdfReportService implements ReportService {


//    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;

    @Override
    public String export() throws IOException {

        System.out.println("entered in PDF to create");
        try {
            Date now = new Date();
            List<Consultation> consultations = consultationRepository.findAll().stream().filter(consultation -> consultation.getId() != 0 ).collect(Collectors.toList());

            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            contentStream.beginText();
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 700);
            for (Consultation consultation : consultations) {
                contentStream.showText(consultation.getId().toString() + "   ");
                contentStream.showText("Patient: " + consultation.getPatient().getName() + "     ");
                contentStream.showText("Doctor: " + consultation.getDoctor().getUsername() + "     ");
                contentStream.showText("Date" + consultation.getDate());

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
