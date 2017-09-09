package com.haizhi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "hbrain_news", type = "signal")

/**
 * Created by admin on 2017/7/1.
 */
public class ItemNews {

    @Id
    private String id;
    private String articleSection;
    private String headline;
    private String website;
    private String datePublished;
    private String referenceUrl;


    @Override
    public String toString() {
        return "ItemNews{" +
                "id='" + id + "'" +
                "website='" + website + '\'' +
                ", articleSection='" + articleSection + '\'' +
                ", headline='" + headline + '\'' +
                ", datePublished='" + datePublished + '\'' +
                '}';
    }

    public String getWebsite() {
        return website;
    }

    public ItemNews setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getArticleSection() {
        return articleSection;
    }

    public void setArticleSection(String articleSection) {
        this.articleSection = articleSection;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getHeadline() {

        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getReferenceUrl() {
        return referenceUrl;
    }

    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }
}
