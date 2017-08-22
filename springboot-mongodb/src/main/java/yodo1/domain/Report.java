package yodo1.domain;

import org.springframework.data.annotation.Id;

public class Report {
    @Id
    private String id;

    private String date;
    private String content;
    private String title;

    public Report() {

    }

    public Report(String date, String title, String content) {
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dateStr) {
        this.date = dateStr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("Report[id=%s, date='%s', content='%s', title='%s']", id, date, content, title);
    }

}
