package com.agroerp.modules.reports.service;

import com.agroerp.modules.reports.dto.ReportSummaryResponse;
import java.time.LocalDate;

public interface ReportService {
    ReportSummaryResponse getDateRangeReport(
            LocalDate fromDate, LocalDate toDate);
}