package com.haizhi.entity;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Created by wanghy on 2017/7/3.
 */
public class NewsQuery {
    private final String articleSection;
    private final String itemOffered;
    private final String website;
    private final String province;
    private final String startDate;

    public NewsQuery(String articleSection, String itemOffered, String website, String province, String startDate) {
        this.articleSection = articleSection;
        this.itemOffered = itemOffered;
        this.website = website;
        this.province = province;
        this.startDate = startDate;
    }

    public QueryBuilder toQueryBuilder() {
        List<QueryBuilder> qbs = new ArrayList<QueryBuilder>();
        if (StringUtils.isNotEmpty(articleSection)) {
            qbs.add(articleSectionQueryBuilder());
        }
        if (StringUtils.isNotEmpty(itemOffered)) {
            qbs.add(itemOfferedQueryBuilder());
        }
        if (StringUtils.isNotEmpty(website)) {
            qbs.add(websiteQueryBuilder());
        }
        if (StringUtils.isNotEmpty(province)) {
            qbs.add(provinceQueryBuilder());
        }
        if (StringUtils.isNotEmpty(startDate)) {
            qbs.add(monthNumQueryBuilder());
        }
        if (qbs.isEmpty()) {
            return matchAllQuery();
        } else if (qbs.size() == 1) {
            return qbs.get(0);
        } else { //multi query param
            BoolQueryBuilder compoundQueryBuilder = boolQuery();
            for (QueryBuilder qb: qbs) {
                compoundQueryBuilder.must(qb);
            }
            return compoundQueryBuilder;
        }
    }

    private QueryBuilder articleSectionQueryBuilder() {
        return termQuery("articleSection", articleSection);
    }
    private QueryBuilder itemOfferedQueryBuilder() {
        return matchPhraseQuery("articleBody", itemOffered);
    }
    private QueryBuilder websiteQueryBuilder() {
        return termQuery("website", website);
    }
    private QueryBuilder provinceQueryBuilder() {
        return matchPhraseQuery("headline", province);
    }
    private QueryBuilder monthNumQueryBuilder() {
        return rangeQuery("datePublished").from(startDate);
    }
}
