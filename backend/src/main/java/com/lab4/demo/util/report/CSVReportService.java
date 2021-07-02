package com.lab4.demo.util.report;

import com.lab4.demo.repo.PatientRepository;
import com.lab4.demo.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import static com.lab4.demo.util.report.ReportType.CSV;

@RequiredArgsConstructor
@Service
public class CSVReportService implements ReportService {

    private final PatientRepository patientRepository;

    @Override
    public String export() throws IOException {

        System.out.println("has entered in CSV to create");
        try{
            List<Patient> patients = patientRepository.findAll().stream().collect(Collectors.toList());

            PrintWriter writer = new PrintWriter("myCSV.csv", "UTF-8");
            for (Patient patient : patients) {
                writer.println(patient);
            }
            writer.close();
        }
        catch ( FileNotFoundException | UnsupportedEncodingException e){
            System.out.println(e);
        }


        return "I am a CSV reporter.";
    }

    @Override
    public ReportType getType() {
        return CSV;
    }
}
