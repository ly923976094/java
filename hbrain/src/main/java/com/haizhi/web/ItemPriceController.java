package com.haizhi.web;


import com.haizhi.dao.ItemPriceDao;
import com.haizhi.entity.ItemPrice;
import com.haizhi.utils.HbrainUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by admin on 2017/6/14.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ItemPriceController {

    private Log log = LogFactory.getLog(ItemPriceController.class);

    @Autowired
    private ItemPriceDao itemPriceDao;

//根据物料检索条件查询物料价格信息
    @RequestMapping(value = "/hbrain/itemPrice")
    @ResponseBody
    public Object itemPrice(final String category, @RequestParam("itemOffered")  final String itemOffered, final String fromLocation, final String availableAtOrFrom, final String itemCondition, final String fromDate, final String monthNum, final String isBymonth) {


        final HashMap<String, String> paramMap = new HashMap<String, String>() {
            {
                put("category", category);
                put("itemOffered", itemOffered);
                put("fromLocation", fromLocation);
                put("availableAtOrFrom", availableAtOrFrom);
                put("itemCondition", itemCondition);
                put("fromDate", HbrainUtils.getQueryFromDAte(fromDate, monthNum));
            }
        };

        log.debug(String.format("Query item price: params: %s", paramMap.toString()));

        //query price
        List<ItemPrice> yaotongPriceList = null;
        List<ItemPrice> zyctdPriceList = null;

        if("1".equals(isBymonth)) {
            if ("中药".equals(category)) {
                paramMap.put("statedIn", "www.yt1998.com");
                yaotongPriceList = itemPriceDao.priceQueryByMonth(paramMap);
                paramMap.put("statedIn", "www.zyctd.com");
                zyctdPriceList = itemPriceDao.priceQueryByMonth(paramMap);
            } else {
                yaotongPriceList = itemPriceDao.priceQueryByMonth(paramMap);
                zyctdPriceList = new ArrayList<ItemPrice>();
            }

        } else {
            if ("中药".equals(category)) {
                paramMap.put("statedIn", "www.yt1998.com");
                yaotongPriceList = itemPriceDao.priceQuery(paramMap);
                paramMap.put("statedIn", "www.zyctd.com");
                zyctdPriceList = itemPriceDao.priceQuery(paramMap);
            } else {
                yaotongPriceList = itemPriceDao.priceQuery(paramMap);
                zyctdPriceList = new ArrayList<ItemPrice>();
            }
        }

        //construct response
        HashMap<String, Object> priceMap = new HashMap<String, Object>();
        List<String> dates = new ArrayList<String>();
        List<String> yaotongPrices = new ArrayList<String>();
        List<String> zyctdPrices = new ArrayList<String>();
        String theDate = minDate(yaotongPriceList, zyctdPriceList);
        String currentDate = HbrainUtils.currentDate();
        int i =0, j = 0;
        while((i < yaotongPriceList.size() || j < zyctdPriceList.size()) && theDate.compareTo(currentDate) < 0) {
            dates.add(theDate);
            int ytPriceIndex = getPriceIndex(yaotongPriceList, i, theDate);
            if (ytPriceIndex >= 0) {
                yaotongPrices.add(yaotongPriceList.get(ytPriceIndex).getPrice());
                i = ytPriceIndex + 1;
            } else {
                i = -1 * ytPriceIndex;
                yaotongPrices.add("");
            }
            int zyPriceIndex = getPriceIndex(zyctdPriceList, j, theDate);
            if (zyPriceIndex >= 0) {
                zyctdPrices.add(zyctdPriceList.get(zyPriceIndex).getPrice());
                j = zyPriceIndex + 1;
            } else {
                j = -1 * zyPriceIndex;
                zyctdPrices.add("");
            }
            theDate = "1".equals(isBymonth) ? HbrainUtils.nextMonth(theDate) : HbrainUtils.nextDay(theDate);
        }
        paramMap.put("statedIn", "www.yt1998.com");
        List<HashMap<String, Object>> priceUnit = itemPriceDao.priceUnitQuery(paramMap);
        priceMap.put("unitText", priceUnit.isEmpty() ? "" : priceUnit.get(0).get("unitText"));
        priceMap.put("priceCurrency", priceUnit.isEmpty() ? "" : priceUnit.get(0).get("priceCurrency").toString());
        priceMap.put("dateArray", dates.toArray());
        priceMap.put("yaotongDatePrice", yaotongPrices.toArray());
        priceMap.put("zyctdDatePrice", zyctdPrices.toArray());
        return  priceMap;
    }

    private String minDate(List<ItemPrice> yaotongPriceList, List<ItemPrice> zyctdPriceList) {
        String ytDate = CollectionUtils.isEmpty(yaotongPriceList) ? null : yaotongPriceList.get(0).getPriceValidUntil();
        String zyDate = CollectionUtils.isEmpty(zyctdPriceList) ? null : zyctdPriceList.get(0).getPriceValidUntil();
        if (StringUtils.isNotEmpty(ytDate) && StringUtils.isNotEmpty(zyDate)) {
            return ytDate.compareTo(zyDate) <=0 ? ytDate : zyDate;
        } else {
            return StringUtils.isNotEmpty(ytDate) ? ytDate : (StringUtils.isNotEmpty(zyDate) ? zyDate : null);
        }
    }

    private int getPriceIndex(List<ItemPrice> priceList, int index, String theDate) {
        while (priceList != null && index < priceList.size()) {
            ItemPrice price = priceList.get(index);
            if (theDate.equals(price.getPriceValidUntil())) {
                return index;
            } else if (theDate.compareTo(price.getPriceValidUntil()) > 0){
                index++;
            } else {
                return -1 * index; //return next search position
            }
        }
        return -1 * Math.max(1, index); //return next search position
    }

//历史采购记录查询
    @RequestMapping(value = "/hbrain/historyOrderQuery")
    @ResponseBody
    public Object historyOrderQuery(final String itemOffered, final String pageNum) {
        HashMap<String, Object> orderMap = new HashMap<String, Object>();
        final HashMap<String, Object> paramMap = new HashMap<String, Object>() {
            {
                put("itemOffered", itemOffered);
                put("pageStart", (Integer.parseInt(pageNum) > 1 ?((Integer.parseInt(pageNum) - 1) * 5 + 1):0));
            }
        };
        List<HashMap<String, Object>> historyOrderInfo = itemPriceDao.historyOrderQuery(paramMap);
        HashMap<String, String> historyOrderCount = itemPriceDao.historyOrderCountQuery(paramMap);
        orderMap.put("historyOrderInfo", historyOrderInfo);
        orderMap.put("historyOrderCount", historyOrderCount);
        return orderMap;
    }


    //初次加载页面时默认检索条件生成
    @RequestMapping(value = "/hbrain/firstRedirectCondition")
    @ResponseBody
            public Object firstRedirectCondition(final String category, final String firstLetter, final String itemOffered, final String fromLocation, final String availableAtOrFrom) {
        HashMap<String, Object> conditionMap = new HashMap<String, Object>();
        final HashMap<String, String> paramMap = new HashMap<String, String>() {
            {
                put("firstLetter", firstLetter != null?firstLetter:"A");
                put("category", category != null?category:"中药");
            }
        };
        if(itemOffered == null) {
            List<HashMap<String, String>> itemOfferedList = itemPriceDao.selectItemOfferedByCategory(paramMap);
            conditionMap.put("itemOfferedCondition", itemOfferedList);
            if (itemOfferedList.size() > 0) {
                final String itemOffered_new = itemOfferedList.get(0).get("itemOffered");
                paramMap.put("itemOffered", itemOffered_new);
                ArrayList<HashMap<String, String>> fromLocationList = itemPriceDao.selectFromLocationByItemOffered(paramMap);
                conditionMap.put("fromLocationCondition", fromLocationList);
                ArrayList<HashMap<String, String>> availableList = new ArrayList<HashMap<String, String>>();
                if (fromLocationList.size() > 0) {

                    final String fromLocation_new = fromLocationList.get(0).get("fromLocation");
                    paramMap.put("fromLocation", fromLocation_new);
                    availableList = itemPriceDao.selectAvailableByFromLocation(paramMap);
                } else {
                    availableList = itemPriceDao.selectAvailableByItemOffered(paramMap);
                }
                conditionMap.put("availableCondition", availableList);
                if (availableList.size() > 0) {
                    final String availableAtOrFrom_new = availableList.get(0).get("availableAtOrFrom");
                    paramMap.put("availableAtOrFrom", availableAtOrFrom_new);
                }
                ArrayList<HashMap<String, String>> itemConditionList = itemPriceDao.selectItemCondition(paramMap);
                    conditionMap.put("itemConditionCondition", itemConditionList);
            }else{
                conditionMap.put("fromLocationCondition", itemOfferedList);
                conditionMap.put("availableCondition", itemOfferedList);
                conditionMap.put("itemConditionCondition", itemOfferedList);
            }
        }else
            if(fromLocation == null && availableAtOrFrom == null){
                paramMap.put("itemOffered", itemOffered);
                ArrayList<HashMap<String, String>> fromLocationList = itemPriceDao.selectFromLocationByItemOffered(paramMap);
                ArrayList<HashMap<String, String>> availableList = new ArrayList<HashMap<String, String>>();
                conditionMap.put("fromLocationCondition", fromLocationList);
                if (fromLocationList.size() > 0) {
                    final String fromLocation_new = fromLocationList.get(0).get("fromLocation");
                    paramMap.put("fromLocation", fromLocation_new);
                    availableList = itemPriceDao.selectAvailableByFromLocation(paramMap);
                } else {
                    availableList = itemPriceDao.selectAvailableByItemOffered(paramMap);
                }
                conditionMap.put("availableCondition", availableList);
                if (availableList.size() > 0) {
                    final String availableAtOrFrom_new = availableList.get(0).get("availableAtOrFrom");
                    paramMap.put("availableAtOrFrom", availableAtOrFrom_new);
                }
                ArrayList<HashMap<String, String>> itemConditionList = itemPriceDao.selectItemCondition(paramMap);
                conditionMap.put("itemConditionCondition", itemConditionList);
            }else
                if(fromLocation != null && availableAtOrFrom == null){
                    paramMap.put("itemOffered", itemOffered);
                    paramMap.put("fromLocation", fromLocation);
                    ArrayList<HashMap<String, String>> availableList = itemPriceDao.selectAvailableByFromLocation(paramMap);
                    conditionMap.put("availableCondition", availableList);
                    if(availableList.size() > 0){
                        final String availableAtOrFrom_new = availableList.get(0).get("availableAtOrFrom");
                        paramMap.put("availableAtOrFrom", availableAtOrFrom_new);
                        ArrayList<HashMap<String, String>> itemConditionList = itemPriceDao.selectItemCondition(paramMap);
                        conditionMap.put("itemConditionCondition", itemConditionList);
                    }
                }
            else
            if(availableAtOrFrom != null){
                paramMap.put("itemOffered", itemOffered);
                paramMap.put("fromLocation", fromLocation);
                ArrayList<HashMap<String, String>> itemConditionList = itemPriceDao.selectItemCondition(paramMap);
                    conditionMap.put("itemConditionCondition", itemConditionList);
            }
        //System.out.println("category = " + category + "   " + "itemOffered = " + itemOffered);
        return  conditionMap;
    }


//输入框物料名称自动补全
    @RequestMapping(value = "/hbrain/itemAutoCompleted")
    @ResponseBody
        public Object itemAutoCompleted(final String itemOffered) {
        final HashMap<String, String> paramMap = new HashMap<String, String>() {
            {
                put("itemOffered", itemOffered.trim() != null && !"".equals(itemOffered.trim())?("%" + itemOffered.trim() + "%"):"");
            }
        };
        return  itemPriceDao.itemAutoCompleted(paramMap);
    }
//点击自动补全选项后该物料对应筛选条件的生成
    @RequestMapping(value = "/hbrain/inputItemOfferedQuery")
    @ResponseBody
    public Object inputItemOfferedQuery(final String category,final String firstLetter, final String itemOffered) {
        final HashMap<String, String> paramMap = new HashMap<String, String>() {
            {
                put("category", category);
                put("firstLetter", firstLetter);
                put("itemOffered", itemOffered);
            }

        };
        HashMap<String, Object> conditionMap = new HashMap<String, Object>();
        List<HashMap<String, String>> itemOfferedList = itemPriceDao.selectItemOfferedByCategory(paramMap);

        conditionMap.put("itemOfferedCondition", itemOfferedList);
        if (itemOfferedList.size() > 0) {
            ArrayList<HashMap<String, String>> fromLocationList = itemPriceDao.selectFromLocationByItemOffered(paramMap);
            conditionMap.put("fromLocationCondition", fromLocationList);
            ArrayList<HashMap<String, String>> availableList = new ArrayList<HashMap<String, String>>();
            if (fromLocationList.size() > 0) {

                final String fromLocation_new = fromLocationList.get(0).get("fromLocation");
                paramMap.put("fromLocation", fromLocation_new);
                availableList = itemPriceDao.selectAvailableByFromLocation(paramMap);
            } else {
                availableList = itemPriceDao.selectAvailableByItemOffered(paramMap);
            }
            conditionMap.put("availableCondition", availableList);
            if (availableList.size() > 0) {
                final String availableAtOrFrom_new = availableList.get(0).get("availableAtOrFrom");
                paramMap.put("availableAtOrFrom", availableAtOrFrom_new);
            }
            ArrayList<HashMap<String, String>> itemConditionList = itemPriceDao.selectItemCondition(paramMap);
            conditionMap.put("itemConditionCondition", itemConditionList);
        }


        return  conditionMap;
    }


    //物料市场价与采购量采购价关系图
    @RequestMapping(value = "/hbrain/priceCompare")
    @ResponseBody
    public Object priceCompare(final String category, final String itemOffered, final String fromLocation, final String availableAtOrFrom, final String itemCondition) {
        final HashMap<String, String> paramMap = new HashMap<String, String>() {
            {
                put("statedIn", "www.yt1998.com");
                put("category", category);
                put("itemOffered", itemOffered);
                put("fromLocation", fromLocation);
                put("availableAtOrFrom", availableAtOrFrom);
                put("itemCondition", itemCondition);
            }
        };
        List<HashMap<String, Object>> priceCompareList = itemPriceDao.priceCompare(paramMap);

        Double[] orderPrice = new Double[priceCompareList.size()];
        Double[] marketPrice = new Double[priceCompareList.size()];
        String[] year = new String[priceCompareList.size()];
        String[] total = new String[priceCompareList.size()];
        HashMap<String, Object> priceCompareMap = new HashMap<String, Object>();
        if (priceCompareList.size() > 0) {
            for (int i = 0; i < priceCompareList.size(); i++) {
                HashMap<String, Object> priceMap = priceCompareList.get(i);
                orderPrice[i] = Double.parseDouble(priceMap.get("orderPrice").toString());
                marketPrice[i] = Double.parseDouble(priceMap.get("marketPrice").toString());
                year[i] = priceMap.get("year").toString();
                total[i] = priceMap.get("total").toString();
            }
            priceCompareMap.put("orderPrice", orderPrice);
            priceCompareMap.put("marketPrice", marketPrice);
            priceCompareMap.put("year", year);
            priceCompareMap.put("total", total);
            priceCompareMap.put("totalUnit", "千克");
            List<HashMap<String, Object>> yaotongList = itemPriceDao.priceUnitQuery(paramMap);
            priceCompareMap.put("unitText", yaotongList.get(0).get("unitText"));
            priceCompareMap.put("priceCurrency", yaotongList.get(0).get("priceCurrency").toString());
        }

//        priceMap.put("zyctdPrice", zyctdList);
        return  priceCompareMap;
    }




    //价格变化信息查询
    @RequestMapping(value = "/hbrain/priceChangeInfo")
    @ResponseBody
    public Object priceChangeInfo(final String category, final String itemOffered, final String fromLocation, final String availableAtOrFrom, final String itemCondition) {
        DecimalFormat df   = new DecimalFormat("######0.00");
        final HashMap<String, String> paramMap = new HashMap<String, String>() {
            {
                put("category", category);
                put("itemOffered", itemOffered);
                put("fromLocation", fromLocation);
                put("availableAtOrFrom", availableAtOrFrom);
                put("itemCondition", itemCondition);
            }
        };
        if("中药".equals(category)){
            paramMap.put("statedIn", "www.yt1998.com");
        }
        HashMap<String, Object> priceChangeMap = new HashMap<String, Object>();
        String todayPrice = "未获取";
        List<HashMap<String, Object>> yaotongDayList = itemPriceDao.priceDayChange(paramMap);
        String dayChangeRatio = "未获取";
        priceChangeMap.put("todayDate", "未获取");
        if(yaotongDayList.size() == 2){
            try {
                todayPrice = yaotongDayList.get(1).get("price").toString();
                priceChangeMap.put("todayDate",yaotongDayList.get(1).get("priceValidUntil"));
                dayChangeRatio = (Double.parseDouble(yaotongDayList.get(1).get("price").toString()) - Double.parseDouble(yaotongDayList.get(0).get("price").toString()))/Double.parseDouble(yaotongDayList.get(1).get("price").toString()) + "";
//                dayChangeRatio = "未获取".equals(dayChangeRatio)?dayChangeRatio:df.format(dayChangeRatio);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        priceChangeMap.put("todayPrice", todayPrice);
        priceChangeMap.put("dayChangeRatio", dayChangeRatio);

        List<HashMap<String, Object>> yaotongMonthList = itemPriceDao.priceMonthChange(paramMap);
        String monthChangeRatio = "未获取";
        if(yaotongMonthList.size() == 1){
            try {
                monthChangeRatio = (Double.parseDouble(yaotongMonthList.get(0).get("thisMonthPrice").toString()) - Double.parseDouble(yaotongMonthList.get(0).get("lastMonthPrice").toString()))/Double.parseDouble(yaotongMonthList.get(0).get("lastMonthPrice").toString()) + "";
//                monthChangeRatio = "未获取".equals(monthChangeRatio)?monthChangeRatio:df.format(monthChangeRatio);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        priceChangeMap.put("monthChangeRatio", monthChangeRatio);

        List<HashMap<String, Object>> yaotongQuaterList = itemPriceDao.priceQuaterChange(paramMap);
        String quaterChangeRatio = "未获取";

        if(yaotongMonthList.size() == 1){
            try {
                quaterChangeRatio = (Double.parseDouble(yaotongQuaterList.get(0).get("thisQuaterPrice").toString()) - Double.parseDouble(yaotongQuaterList.get(0).get("lastQuaterPrice").toString()))/Double.parseDouble(yaotongQuaterList.get(0).get("lastQuaterPrice").toString()) + "";
//                quaterChangeRatio = "未获取".equals(quaterChangeRatio)?quaterChangeRatio:df.format(quaterChangeRatio);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        priceChangeMap.put("quaterChangeRatio", quaterChangeRatio);
        return priceChangeMap;
    }
}
