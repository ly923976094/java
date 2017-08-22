package yodo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import yodo1.domain.Report;
import yodo1.repository.ReportRepository;

import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    public Report createReport(Map<String, Object> reportMap) {
        Report report = new Report(reportMap.get("date").toString(),
                reportMap.get("title").toString(),
                reportMap.get("content").toString());

        repository.save(report);
        return report;
    }

    @Cacheable(value = "reportcache", keyGenerator = "wiselyKeyGenerator")
    public Report getReportDetails(String title) {
        System.out.println("无缓存的时候调用这里---数据库查询, title=" + title);
        return repository.findByTitle(title);
    }
}
