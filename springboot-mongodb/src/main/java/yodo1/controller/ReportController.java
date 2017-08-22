package yodo1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.Map;

import yodo1.domain.Report;
import yodo1.service.ReportService;

//@SpringBootApplication
@RestController
@RequestMapping("/report")
public class ReportController {
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;

    @RequestMapping(method = RequestMethod.POST,value = "/create")
    public Map<String, Object> createReport(@RequestBody Map<String, Object> reportMap) {
        logger.info("createReport");
        Report report = reportService.createReport(reportMap);

        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put("message", "Report created successfully");
        response.put("report", report);

        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{reportTitle}")
    public Report getReportDetails(@PathVariable("reportTitle") String title) {
        logger.info("getReportDetails");
        return reportService.getReportDetails(title);
    }
}
