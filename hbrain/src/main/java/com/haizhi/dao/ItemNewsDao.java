package com.haizhi.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface ItemNewsDao {

    //查询物料相关新闻事件
    @Select("<script>select headline, location,datePublished, DATE_FORMAT(datePublished,'%Y-%m') as yearMonth, trend,   referenceUrl" +
            " from news_event where product = #{itemOffered}" +
            " order by datePublished desc" +
            "</script>")
    List<HashMap<String, Object>> newsEventQuery(HashMap<String, Object> paramMap);



    //新闻物料检索条件查询
    @Select("<script>select itemOffered" +
            " from news_itemOffered where category = #{category}" +
            "</script>")
    List<HashMap<String, Object>> newsItemConditionQuery(HashMap<String, Object> paramMap);
}
