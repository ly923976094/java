package com.haizhi.web;


import com.haizhi.dao.BiddingPriceDao;
import com.haizhi.dao.ItemNewsDao;
import com.haizhi.dao.ItemPriceDao;
import com.haizhi.entity.ItemNews;
import com.haizhi.entity.NewsQuery;
import com.haizhi.service.ItemNewsService;
import com.haizhi.utils.HbrainUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 * Created by admin on 2017/6/14.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ItemNewsController {
    @Autowired
    private BiddingPriceDao biddingPriceDao;

    @Autowired
    private ItemPriceDao itemPriceDao;

    @Autowired
    private ItemNewsDao itemNewsDao;

    @Autowired
    private ItemNewsService itemNewsService;


    //新闻事件查询
    @RequestMapping(value = "/hbrain/newsEventQuery")
    @ResponseBody
    public Object newsEventQuery(final String itemOffered) {
        final HashMap<String, Object> paramMap = new HashMap<String, Object>() {
            {
                put("itemOffered", itemOffered);
            }
        };
        List<HashMap<String, Object>> newsEventList = itemNewsDao.newsEventQuery(paramMap);
        return  newsEventList;
    }


    //新闻检索条件生成
    @RequestMapping(value = "/hbrain/newsCondition")
    @ResponseBody
    public Object newsCondition(final String itemOffered) {
        HashMap<String, Object> conditionMap = new HashMap<>();
        String[] provinceCondition = {"北京","上海","天津","重庆","广东","江苏","山东","浙江","河北","辽宁","四川","河南","湖北","湖南","福建","安徽","内蒙古","陕西","江西","广西","黑龙江","吉林","云南","贵州","山西","新疆","甘肃","海南","宁夏","青海","西藏","香港","澳门","台湾"};
        String[] articleSection = {"行业聚焦","自然灾害","政策法规","供应商动态"};
        String[] source = {"药通网","中药材天地网", "赛柏蓝", "中国铝业网", "中华商务网", "大宗新闻网", "盖德化工网", "中华人民共和国商务部", "医药界",  "中药产业观察网", "国家中药材流通追溯体系"};
        String[] category = {"包材","化工","农副","原料药","中药"};
    HashMap<String, Object> categoryItemMap = new HashMap<>();
        for(String categoryStr: category){
            final HashMap<String, Object> paramMap = new HashMap<String, Object>() {
                {
                    put("category", categoryStr);
                }
            };
            List<HashMap<String, Object>> itemList = itemNewsDao.newsItemConditionQuery(paramMap);
            categoryItemMap.put(categoryStr, itemList);
        }
        conditionMap.put("provinceCondition",provinceCondition);
        conditionMap.put("articleSection",articleSection);
        conditionMap.put("source",source);
        conditionMap.put("category",categoryItemMap);

        return  conditionMap;
    }


    //物料相关新闻查询
    @RequestMapping(value = "/hbrain/newsQuery")
    @ResponseBody
    public Object newsQuery(final String articleSection, final String website, final String itemOffered,
                            final String province, final String monthNum, final Integer pageNum, final Integer size) {

        NewsQuery query = new NewsQuery(articleSection, itemOffered, website, province, HbrainUtils.getQueryFromDAte(monthNum));
        Page<ItemNews> posts  = itemNewsService.findNews(query, getPageRequest(pageNum, size));
        return  posts;
    }


    private static PageRequest getPageRequest(final Integer pageNum, final Integer size) {
        return new PageRequest(pageNum != null ? pageNum : 0, size != null ? size : 10);
    }

}
