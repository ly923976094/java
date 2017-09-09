package com.haizhi.dao;


import com.haizhi.entity.ItemPrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface ItemPriceDao {

    //查询物料市场价格的称量单位及货币单位
    @Select("<script>select  unitText, priceCurrency from item_price where 1=1" +
            "<if test='statedIn != null'>" +
            " and statedIn = #{statedIn}" +
            "</if>" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='itemOffered != null'>" +
            " and itemOffered = #{itemOffered}" +
            "</if>" +
            "<if test='fromLocation != null'>" +
            " and fromLocation = #{fromLocation}" +
            "</if>" +
            "<if test='availableAtOrFrom != null'>" +
            " and availableAtOrFrom = #{availableAtOrFrom}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='fromDate != null'>" +
            " and priceValidUntil > str_to_date(#{fromDate},'%Y-%m-%d')" +
            "</if>" +
            " limit 0,1" +
            "</script>")
    List<HashMap<String, Object>> priceUnitQuery(HashMap<String, String> paramMap);


    //查询物料市场价格(以日为单位)
    @Select("<script>select price, DATE_FORMAT(priceValidUntil,'%Y-%m-%d') as priceValidUntil from item_price where priceValidUntil > str_to_date('2014-01-01','%Y-%m-%d')" +
            "<if test='statedIn != null'>" +
            " and statedIn = #{statedIn}" +
            "</if>" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='itemOffered != null'>" +
            " and itemOffered = #{itemOffered}" +
            "</if>" +
            "<if test='fromLocation != null'>" +
            " and fromLocation = #{fromLocation}" +
            "</if>" +
            "<if test='availableAtOrFrom != null'>" +
            " and availableAtOrFrom = #{availableAtOrFrom}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='fromDate != null'>" +
            " and priceValidUntil >= str_to_date(#{fromDate},'%Y-%m-%d')" +
            "</if>" +
            " order by priceValidUntil asc" +
            "</script>")
    List<ItemPrice> priceQuery(HashMap<String, String> paramMap);



    //查询物料市场价格(以月为单位)
    @Select("<script>select AVG(price) as price, DATE_FORMAT(priceValidUntil,'%Y-%m') as priceValidUntil from item_price where priceValidUntil > str_to_date('2014-01-01','%Y-%m-%d') " +
            "<if test='statedIn != null'>" +
            " and statedIn = #{statedIn}" +
            "</if>" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='itemOffered != null'>" +
            " and itemOffered = #{itemOffered}" +
            "</if>" +
            "<if test='fromLocation != null'>" +
            " and fromLocation = #{fromLocation}" +
            "</if>" +
            "<if test='availableAtOrFrom != null'>" +
            " and availableAtOrFrom = #{availableAtOrFrom}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='fromDate != null'>" +
            " and priceValidUntil >= str_to_date(#{fromDate},'%Y-%m-%d')" +
            "</if>" +
            " group by priceValidUntil " +
            " order by priceValidUntil asc" +

            "</script>")
    List<ItemPrice> priceQueryByMonth(HashMap<String, String> paramMap);



//    select a.year,a.price as orderPrice,b.price as marketPrice from (select DATE_FORMAT(orderDate,'%Y') as year,  AVG(price) as price,SUM(totalUnit) as total from record_order where orderDate > str_to_date('2014-01-01','%Y-%m-%d')  group by year ) a
//    join (select DATE_FORMAT(priceValidUntil,'%Y') as year,  AVG(price) as price from item_price where priceValidUntil > str_to_date('2014-01-01','%Y-%m-%d') group by year) b
//    on  a.year = b.year
//物料市场价与采购量采购价关系图
    @Select("<script>select a.year,a.price as orderPrice,b.price as marketPrice, total from " +
            "(select DATE_FORMAT(orderDate,'%Y') as year,  AVG(price) as price,SUM(totalUnit) as total from record_order where orderDate > str_to_date('2014-01-01','%Y-%m-%d')" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='itemOffered != null'>" +
            " and hbrainCategory = #{itemOffered}" +
            "</if>" +
            "<if test='fromLocation != null'>" +
            " and fromLocation = #{fromLocation}" +
            "</if>" +
            "<if test='availableAtOrFrom != null'>" +
            " and availableAtOrFrom = #{availableAtOrFrom}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemConditionOut = #{itemCondition}" +
            "</if>" +
            "    group by year) a  join " +
            "(select DATE_FORMAT(priceValidUntil,'%Y') as year,  AVG(price) as price from item_price where priceValidUntil > str_to_date('2014-01-01','%Y-%m-%d')" +
            "<if test='statedIn != null'>" +
            " and statedIn = #{statedIn}" +
            "</if>" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='itemOffered != null'>" +
            " and itemOffered = #{itemOffered}" +
            "</if>" +
            "<if test='fromLocation != null'>" +
            " and fromLocation = #{fromLocation}" +
            "</if>" +
            "<if test='availableAtOrFrom != null'>" +
            " and availableAtOrFrom = #{availableAtOrFrom}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "   group by year) b" +
            " on  a.year = b.year" +
            "</script>")
    List<HashMap<String, Object>> priceCompare(HashMap<String, String> paramMap);








    //查询物料历史采购信息，按页查询
    @Select("<script>select orderedItem, itemCondition, seller, customer,totalUnit, unitText, totalPrice, price, orderDate from " +
            "record_order where hbrainCategory = #{itemOffered}" +
            " order by record_order.orderDate desc" +
            "<if test='pageStart != null'>" +
            " limit #{pageStart}, 5" +
            "</if>" +
            "</script>")
    List<HashMap<String, Object>> historyOrderQuery(HashMap<String, Object> paramMap);

    //查询物料历史采购信息总数
    @Select("<script>select  count(*) as count from " +
            "record_order where orderedItem = #{itemOffered}" +
            " order by record_order.orderDate desc" +
            "</script>")
    HashMap<String, String> historyOrderCountQuery(HashMap<String, Object> paramMap);


//------------------------------------------------------筛选条件start----------------------

    //根据分类名称查找该分类下"firstLetter"开头的物料名称
    @Select("<script>" +
            "select distinct itemOffered from screening_condition where category = #{category} and firstLetter = #{firstLetter}" +
            "</script>")
    ArrayList<HashMap<String, String>> selectItemOfferedByCategory(HashMap<String, String> paramMap);

    //根据分类名称,物料名称首字母，物料名称查找产地
    @Select("<script>" +
            "select distinct fromLocation from screening_condition where category = #{category} and firstLetter = #{firstLetter} and itemOffered = #{itemOffered}" +
            "</script>")
    ArrayList<HashMap<String, String>> selectFromLocationByItemOffered(HashMap<String, String> paramMap);

    //根据分类名称,物料名称首字母，物料名称查找产地后，如果有产地，根据产地查找市场
    @Select("<script>" +
            "select distinct availableAtOrFrom from screening_condition where category = #{category} and firstLetter = #{firstLetter}" +
            " and itemOffered = #{itemOffered} and fromLocation = #{fromLocation}" +
            "</script>")
    ArrayList<HashMap<String, String>> selectAvailableByFromLocation(HashMap<String, String> paramMap);

    //根据分类名称,物料名称首字母，物料名称查找产地后，如果没有产地，根据同样的条件去查找市场
    @Select("<script>" +
            "select distinct availableAtOrFrom from screening_condition where category = #{category} and firstLetter = #{firstLetter} and itemOffered = #{itemOffered}" +
            "</script>")
    ArrayList<HashMap<String, String>> selectAvailableByItemOffered(HashMap<String, String> paramMap);
//----------
//    //根据分类名称,物料名称首字母，物料名称查找产地后，如果有产地，根据产地查找市场
//    @Select("<script>" +
//            "select distinct availableAtOrFrom from screening_condition where category = #{category} and firstLetter = #{firstLetter}" +
//            " and itemOffered = #{itemOffered} and fromLocation = #{fromLocation}" +
//            "</script>")


    //根据以上检索条件检索查找规格(会存在产地为空或者市场为空的情况)
    @Select("<script>select  distinct itemCondition from screening_condition where " +
            " category = #{category}" +
            " and itemOffered = #{itemOffered}"+
            "<if test='fromLocation != null'>" +
            " and fromLocation = #{fromLocation}" +
            "</if>" +
            "<if test='availableAtOrFrom != null'>" +
            " and availableAtOrFrom = #{availableAtOrFrom}" +
            "</if>" +
            "</script>")
    ArrayList<HashMap<String, String>> selectItemCondition(HashMap<String, String> paramMap);

//------------------------------------------------------筛选条件end----------------------

    //根据物料名称子字符串查找怕模糊匹配的物料名称列表用于自动补全提示
    @Select("<script>SELECT distinct itemOffered, category, firstLetter FROM screening_condition where itemOffered in ( SELECT distinct itemOffered FROM screening_condition GROUP BY itemOffered) " +
            "<if test='itemOffered != null'>" +
            " and itemOffered like #{itemOffered}" +
            "</if>" +
            "</script>")
    List<HashMap<String, String>> itemAutoCompleted(HashMap<String, String> paramMap);

//    @Select("INSERT INTO user(name, age) VALUES (#{name}, #{age})")
//    void insert(User user);




    //查询物料市场价格变化，最近的两天数据
    @Select("<script>select price, priceValidUntil from item_price where priceValidUntil &gt; str_to_date('2014-01-01','%Y-%m-%d')" +
            "<if test='statedIn != null'>" +
            " and statedIn = #{statedIn}" +
            "</if>" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='itemOffered != null'>" +
            " and itemOffered = #{itemOffered}" +
            "</if>" +
            "<if test='fromLocation != null'>" +
            " and fromLocation = #{fromLocation}" +
            "</if>" +
            "<if test='availableAtOrFrom != null'>" +
            " and availableAtOrFrom = #{availableAtOrFrom}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='fromDate != null'>" +
            " and priceValidUntil > str_to_date(#{fromDate},'%Y-%m-%d')" +
            "</if>" +
            " order by priceValidUntil desc limit 0,2" +
            "</script>")
    List<HashMap<String, Object>> priceDayChange(HashMap<String, String> paramMap);



    //查询物料市场价格变化，最近的两月数据
    @Select("<script>select (select AVG(price) from item_price where date_sub(curdate(), INTERVAL 30 DAY) &lt;= date(priceValidUntil) " +
            "<if test='statedIn != null'>" +
            " and statedIn = #{statedIn}" +
            "</if>" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='itemOffered != null'>" +
            " and itemOffered = #{itemOffered}" +
            "</if>" +
            "<if test='fromLocation != null'>" +
            " and fromLocation = #{fromLocation}" +
            "</if>" +
            "<if test='availableAtOrFrom != null'>" +
            " and availableAtOrFrom = #{availableAtOrFrom}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='fromDate != null'>" +
            " and priceValidUntil &gt; str_to_date(#{fromDate},'%Y-%m-%d')" +
            "</if>" + ") as thisMonthPrice ," +
            " (select AVG(price) from item_price where date_sub(curdate(), INTERVAL 60 DAY) &lt;= date(priceValidUntil) and date(priceValidUntil) &lt;= date_sub(curdate(), INTERVAL 30 DAY) " +
            "<if test='statedIn != null'>" +
            " and statedIn = #{statedIn}" +
            "</if>" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='itemOffered != null'>" +
            " and itemOffered = #{itemOffered}" +
            "</if>" +
            "<if test='fromLocation != null'>" +
            " and fromLocation = #{fromLocation}" +
            "</if>" +
            "<if test='availableAtOrFrom != null'>" +
            " and availableAtOrFrom = #{availableAtOrFrom}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='fromDate != null'>" +
            " and priceValidUntil &gt; str_to_date(#{fromDate},'%Y-%m-%d')" +
            "</if>" +
            ") as lastMonthPrice" +

            "</script>")
    List<HashMap<String, Object>> priceMonthChange(HashMap<String, String> paramMap);






    //查询物料市场价格变化，最近的两季度数据
    @Select("<script>select (select AVG(price) from item_price where date_sub(curdate(), INTERVAL 90 DAY) &lt;= date(priceValidUntil) " +
            "<if test='statedIn != null'>" +
            " and statedIn = #{statedIn}" +
            "</if>" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='itemOffered != null'>" +
            " and itemOffered = #{itemOffered}" +
            "</if>" +
            "<if test='fromLocation != null'>" +
            " and fromLocation = #{fromLocation}" +
            "</if>" +
            "<if test='availableAtOrFrom != null'>" +
            " and availableAtOrFrom = #{availableAtOrFrom}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='fromDate != null'>" +
            " and priceValidUntil &gt; str_to_date(#{fromDate},'%Y-%m-%d')" +
            "</if>" + ") as thisQuaterPrice ," +
            " (select AVG(price) from item_price where date_sub(curdate(), INTERVAL 180 DAY) &lt;= date(priceValidUntil) and date(priceValidUntil) &lt;= date_sub(curdate(), INTERVAL 90 DAY) " +
            "<if test='statedIn != null'>" +
            " and statedIn = #{statedIn}" +
            "</if>" +
            "<if test='category != null'>" +
            " and category = #{category}" +
            "</if>" +
            "<if test='itemOffered != null'>" +
            " and itemOffered = #{itemOffered}" +
            "</if>" +
            "<if test='fromLocation != null'>" +
            " and fromLocation = #{fromLocation}" +
            "</if>" +
            "<if test='availableAtOrFrom != null'>" +
            " and availableAtOrFrom = #{availableAtOrFrom}" +
            "</if>" +
            "<if test='itemCondition != null'>" +
            " and itemCondition = #{itemCondition}" +
            "</if>" +
            "<if test='fromDate != null'>" +
            " and priceValidUntil &gt; str_to_date(#{fromDate},'%Y-%m-%d')" +
            "</if>" +
            ") as lastQuaterPrice" +

            "</script>")
    List<HashMap<String, Object>> priceQuaterChange(HashMap<String, String> paramMap);




//        @Select("<script>select a as b from item_price where date_sub(curdate(), INTERVAL 60 DAY) &lt;= 1" +
//           // "(select AVG(price) from item_price where date_sub(curdate(), INTERVAL 60 DAY) <= date(priceValidUntil) and date(priceValidUntil) <= date_sub(curdate(), INTERVAL 30 DAY)) as bb" +
//            "</script>")
//        List<HashMap<String, Object>> priceMonthChange(HashMap<String, String> paramMap);

}
