package com.haizhi.service;

/**
 * Created by admin on 2017/7/2.
 */
        import com.haizhi.entity.ItemNews;
        import com.haizhi.entity.NewsQuery;
        import com.haizhi.repository.ItemNewsRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.PageRequest;
        import org.springframework.stereotype.Service;

        import java.util.List;

/**
 * Created by patterncat on 2016-01-28.
 */
@Service
public class ItemNewsService {

    @Autowired
    ItemNewsRepository itemNewsRepository;

    public Page<ItemNews> findNews(NewsQuery query, final PageRequest pageRequest) {
        return itemNewsRepository.findNews(query, pageRequest);
    }
}


