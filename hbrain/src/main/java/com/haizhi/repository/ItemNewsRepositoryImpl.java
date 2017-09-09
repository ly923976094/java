package com.haizhi.repository;

import com.haizhi.entity.ItemNews;
import com.haizhi.entity.NewsQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

/**
 * Created by wanghy on 2017/7/3.
 */
public class ItemNewsRepositoryImpl implements ItemNewsRepositoryCustom{
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    private Log log = LogFactory.getLog(ItemNewsRepositoryImpl.class);

    @Override
    public Page<ItemNews> findNews(NewsQuery query, PageRequest pageRequest) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageRequest)
                .withQuery(query.toQueryBuilder())
                .withSort(new FieldSortBuilder("datePublished").order(SortOrder.DESC))
                .build();
        log.debug("findNews: " + searchQuery.getQuery().toString());
        return elasticsearchTemplate.queryForPage(searchQuery,ItemNews.class);     }
}
