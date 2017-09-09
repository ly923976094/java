package com.haizhi.repository;

import com.haizhi.entity.ItemNews;
import com.haizhi.entity.NewsQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by admin on 2017/7/1.
 */
public interface ItemNewsRepository extends ElasticsearchRepository<ItemNews, String> {
    Page<ItemNews> findNews(NewsQuery query, final PageRequest pageRequest);
}
