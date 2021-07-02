//package com.lab4.demo.util.report;
//
//import com.lab4.demo.repo.PatientRepository;
//import com.lab4.demo.model.Patient;
//import lombok.RequiredArgsConstructor;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.export.JRPdfExporter;
//import net.sf.jasperreports.export.SimpleExporterInput;
//import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
//import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
//import net.sf.jasperreports.export.SimplePdfReportConfiguration;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static com.lab4.demo.util.report.ReportType.PDF2;
//
//@RequiredArgsConstructor
//@Service
//public class JasperPDFReportService implements ReportService {
//
//
//    private final PatientRepository patientRepository;
//
//    @Override
//    public String export() {
//
//        try {
//            List<Patient> patients = patientRepository.findAll().stream().filter(patient -> patient.getQuantity() == 0).collect(Collectors.toList());
//
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(patients);
//
//            Map<String, Object> parameters = new HashMap<>();
//            parameters.put("createdBy","Andrei");
//
//
//            //SOURCE FILE ??????????????????????
//            JasperPrint jasperPrint = JasperFillManager.fillReport("JapserPDF.txt", parameters, dataSource);
//
//            //probably not needed until next comm...is another tutorial
//            JRPdfExporter exporter = new JRPdfExporter();
//
//            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//            exporter.setExporterOutput(
//              new SimpleOutputStreamExporterOutput("patientJasperReport.pdf"));
//
//            SimplePdfReportConfiguration reportConfig
//              = new SimplePdfReportConfiguration();
//            reportConfig.setSizePageToContent(true);
//            reportConfig.setForceLineBreakPolicy(false);
//
//            SimplePdfExporterConfiguration exportConfig
//              = new SimplePdfExporterConfiguration();
//            exportConfig.setMetadataAuthor("Andrei");
//            exportConfig.setEncrypted(true);
//            exportConfig.setAllowedPermissionsHint("PRINTING");
//
//            exporter.setConfiguration(reportConfig);
//            exporter.setConfiguration(exportConfig);
//
//            exporter.exportReport();
//
//
//           // JasperExportManager.exportReportToPdfFile(jasperPrint, "jasperPDF.pdf");
//        }
//        catch (JRException e){
//            System.out.println(e);
//        }
//
//
//        return "I am Jasper PDF reporter!";
//    }
//
//    @Override
//    public ReportType getType() {
//        return PDF2;
//    }
//}
