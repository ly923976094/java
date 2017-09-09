package com.haizhi.web;


import com.haizhi.dao.BiddingPriceDao;
import com.haizhi.dao.ItemPriceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on 2017/6/14.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BiddingPriceController {
    @Autowired
    private BiddingPriceDao biddingPriceDao;

    @Autowired
    private ItemPriceDao itemPriceDao;

    //中标页面筛选条件动态生成
    @RequestMapping(value = "/hbrain/biddingCondition")
    @ResponseBody
    public Object biddingCondition(final String category, final String orderedItem) {
        HashMap<String, Object> conditionMap = new HashMap<String, Object>();
        final HashMap<String, String> paramMap = new HashMap<String, String>() {
            {
                put("category", category != null?category:"中药");
            }
        };
        if(orderedItem == null) {
            List<HashMap<String, String>> itemOfferedList = biddingPriceDao.selectBiddingItemByCategory(paramMap);
            conditionMap.put("orderedItemCondition", itemOfferedList);
            if (itemOfferedList.size() > 0) {
                final String orderedItem_new = itemOfferedList.get(0).get("orderedItem");
                paramMap.put("orderedItem", orderedItem_new);

                ArrayList<HashMap<String, String>> itemConditionList = biddingPriceDao.selectBiddingCondition(paramMap);
                conditionMap.put("itemConditionCondition", itemConditionList);
                if(itemConditionList.size() > 0){
                    final String biddingCondition = itemConditionList.get(0).get("itemCondition");
                    paramMap.put("itemCondition", biddingCondition);

                }
                ArrayList<HashMap<String, String>> customerList = biddingPriceDao.selectBiddingCustomer(paramMap);
                conditionMap.put("customerCondition", customerList);
            }else{
                conditionMap.put("orderedItemCondition", itemOfferedList);
                conditionMap.put("customerCondition", itemOfferedList);
            }
        }else{
            paramMap.put("orderedItem", orderedItem);

            ArrayList<HashMap<String, String>> itemConditionList = biddingPriceDao.selectBiddingCondition(paramMap);
            conditionMap.put("itemConditionCondition", itemConditionList);
            if(itemConditionList.size() > 0){
                final String biddingCondition = itemConditionList.get(0).get("itemCondition");
                paramMap.put("itemCondition", biddingCondition);

            }
            ArrayList<HashMap<String, String>> customerList = biddingPriceDao.selectBiddingCustomer(paramMap);
            conditionMap.put("customerCondition", customerList);

        }
        return  conditionMap;
    }


    //历史中标数据查询
    @RequestMapping(value = "/hbrain/historyBiddingQuery")
    @ResponseBody
    public Object historyBiddingQuery(final String category,final String orderedItem,final String itemCondition, final String customer, final String pageNum) {
        System.out.println("category = " + category + "   " + "  itemCondition = " + itemCondition + "  customer = " + customer);

        final HashMap<String, Object> paramMap = new HashMap<String, Object>() {
            {
                put("category", category);
                put("orderedItem", orderedItem);
                put("itemCondition", itemCondition);
                put("customer", customer);
                put("pageStart", (Integer.parseInt(pageNum) > 1 ?((Integer.parseInt(pageNum) - 1) * 5 + 1):0));
            }
        };
        HashMap<String, Object> orderMap = new HashMap<String, Object>();
        HashMap<String, String> historyBiddingCount = biddingPriceDao.historyBiddingCountQuery(paramMap);
        List<HashMap<String, Object>> historyBiddingInfo = biddingPriceDao.historyBiddingQuery(paramMap);
        orderMap.put("historyBiddingInfo", historyBiddingInfo);
        orderMap.put("historyBiddingCount", historyBiddingCount);
        return  orderMap;
    }


    //物料市场价格，历史中标价格，预估价格数据查询
    @RequestMapping(value = "/hbrain/marketHistoryForecastQuery")
    @ResponseBody
    public Object marketHistoryForecastQuery(final String category,final String orderedItem,final String itemCondition, final String customer) {
        final HashMap<String, Object> paramMap = new HashMap<String, Object>() {
            {
                put("category", category);
                put("orderedItem", orderedItem);
                put("itemCondition", itemCondition);
                put("customer", customer);
            }
        };
        HashMap<String, Object> priceMap = new HashMap<String, Object>();
        List<HashMap<String, Object>> priceList = biddingPriceDao.marketHistoryForecastQuery(paramMap);

        String[] marketPrice;
        String[] biddingPrice;
        String[] lassoPrice;
        Date[] date;

        if (priceList.size() > 0) {
            marketPrice = new String[priceList.size()];
            biddingPrice = new String[priceList.size()];
            lassoPrice = new String[priceList.size()];
            date = new Date[priceList.size()];

            for (int i = 0; i < priceList.size(); i++) {
                HashMap<String, Object> dataMap = priceList.get(i);
                marketPrice[i] = dataMap.get("m_price")  != null?dataMap.get("m_price").toString():"";
                biddingPrice[i] = dataMap.get("price")  != null?dataMap.get("price").toString():"";
                lassoPrice[i] = dataMap.get("lasso")  != null?dataMap.get("lasso").toString():"";
                date[i] = dataMap.get("orderDate")  != null?(new Date(((Timestamp) dataMap.get("orderDate")).getTime())):null;
            }
        }else{
            priceList = biddingPriceDao.historyBiddingQueryAll(paramMap);
            marketPrice = new String[priceList.size()];
            biddingPrice = new String[priceList.size()];
            lassoPrice = new String[priceList.size()];
            date = new Date[priceList.size()];
            if(priceList.size() > 0) {
                for (int i = 0; i < priceList.size(); i++) {
                    HashMap<String, Object> dataMap = priceList.get(i);
                    biddingPrice[i] = dataMap.get("price") != null ? dataMap.get("price").toString() : "";
                    date[i] = dataMap.get("orderDate") != null ? (new Date(((Timestamp) dataMap.get("orderDate")).getTime())) : null;
                }
            }
        }
        priceMap.put("biddingPrice", biddingPrice);
        priceMap.put("marketPrice", marketPrice);
        priceMap.put("lassoPrice", lassoPrice);
        priceMap.put("date", date);
        return  priceMap;
    }

    //历史中标预估记录
    @RequestMapping(value = "/hbrain/historyBiddingForecastQuery")
    @ResponseBody
    public Object historyBiddingForecastQuery() {
        HashMap<String, Object> orderMap = new HashMap<String, Object>();
        List<HashMap<String, Object>> historyBiddingInfo = biddingPriceDao.historyBiddingForecastQuery();
        orderMap.put("historyBiddingInfo", historyBiddingInfo);
        return  orderMap;
    }
}
