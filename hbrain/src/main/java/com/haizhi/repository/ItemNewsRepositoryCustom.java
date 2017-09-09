package com.haizhi.repository;

import com.haizhi.entity.ItemNews;
import com.haizhi.entity.NewsQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by wanghy on 2017/7/3.
 */
public interface ItemNewsRepositoryCustom {
    Page<ItemNews> findNews(NewsQuery query, final PageRequest pageRequest);
}
