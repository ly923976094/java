package yodo1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import yodo1.domain.Report;

import java.util.List;

public interface ReportRepository extends MongoRepository<Report, String> {
    Report findByTitle(String title);
    List<Report> findByDate(String date);
}
