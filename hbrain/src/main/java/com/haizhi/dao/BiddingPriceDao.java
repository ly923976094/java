package com.haizhi.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface BiddingPriceDao {

    //查询物料历史中标信息总数
    @Select("<script>select  count(*) as count from " +
            "record_bidding where 1=1" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='orderedItem != null'>" +
            " and hbrainCategory = #{orderedItem}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='customer != null'>" +
            " and customer = #{customer}" +
            "</if>" +
            "</script>")
    HashMap<String, String> historyBiddingCountQuery(HashMap<String, Object> paramMap);


    //查询物料历史中标信息,带翻页，每页5条
    @Select("<script>select seller,unitText, totalUnit, totalPrice, price, orderDate from record_bidding where 1 = 1" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='orderedItem != null'>" +
            " and hbrainCategory = #{orderedItem}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='customer != null'>" +
            " and customer = #{customer}" +
            "</if>" +
            " order by orderDate desc" +
            "<if test='pageStart != null'>" +
            " limit #{pageStart}, 5" +
            "</if>" +
            "</script>")
    List<HashMap<String, Object>> historyBiddingQuery(HashMap<String, Object> paramMap);



    //查询物料所有的历史中标信息
    @Select("<script>select seller, totalUnit, totalPrice, price, orderDate from record_bidding where 1 = 1" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='orderedItem != null'>" +
            " and hbrainCategory = #{orderedItem}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='customer != null'>" +
            " and customer = #{customer}" +
            "</if>" +
            " order by orderDate asc" +
            "</script>")
    List<HashMap<String, Object>> historyBiddingQueryAll(HashMap<String, Object> paramMap);



//------------------------------------------------------筛选条件start----------------------

    //根据分类名称查找该分类下"firstLetter"开头的物料名称
    @Select("<script>" +
            "select distinct orderedItem from bidding_condition where category = #{category}" +
            "</script>")
    ArrayList<HashMap<String, String>> selectBiddingItemByCategory(HashMap<String, String> paramMap);



    //根据以上检索条件检索查找中标物料规格
    @Select("<script>select  distinct itemCondition from bidding_condition where " +
            " category = #{category}" +
            " and orderedItem = #{orderedItem}"+
            "</script>")
    ArrayList<HashMap<String, String>> selectBiddingCondition(HashMap<String, String> paramMap);


    //根据以上检索条件检索查找中标物料使用企业
    @Select("<script>select  distinct customer from bidding_condition where " +
            " category = #{category}" +
            " and orderedItem = #{orderedItem}"+
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "</script>")
    ArrayList<HashMap<String, String>> selectBiddingCustomer(HashMap<String, String> paramMap);


    //查询物料对应的历史中标信息，市场价格信息，中标预估信息
    @Select("<script>select orderDate, price, m_price, lasso from bidding_price_fitting where 1 = 1" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='orderedItem != null'>" +
            " and hbrainCategory = #{orderedItem}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='customer != null'>" +
            " and customer = #{customer}" +
            "</if>" +
            " order by orderDate asc" +
            "</script>")
    List<HashMap<String, Object>> marketHistoryForecastQuery(HashMap<String, Object> paramMap);


    //查询物料历史中标预估记录信息
    @Select("<script>select orderDate, hbrainCategory, lasso, status, customer, itemCondition from record_bidding_forecasting where forecast_flag = '1'" +
            " order by orderDate desc" +
            " limit 0, 3" +
            "</script>")
    List<HashMap<String, Object>> historyBiddingForecastQuery();

//    @Select("INSERT INTO user(name, age) VALUES (#{name}, #{age})")
//    void insert(User user);
}