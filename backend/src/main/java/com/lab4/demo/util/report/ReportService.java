package com.lab4.demo.util.report;

import java.io.IOException;

public interface ReportService {
    String export() throws IOException;

    ReportType getType();
}
