package chapter8;

import java.util.*;
import static java.util.Collections.sort;

/**
 * Created by Administrator on 2017/7/25.
 */
public class ArraysDemo {
    public static void main(String[] args) {
        /**
         * Arrays 框架
         *
         */

        /**
         * 重点List,asList(数组)将数组转成集合
         */

        String[] arr = {"abc", "hahha", "xixi"};
        List<String> list = Arrays.asList(arr);
//        list.add("123");
        System.out.println(list);

        /**
         * 集合转成数组
         */
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("1");
        sort(list1);
        list1.toArray();
        System.out.println();

        /**
         * map entry
         */

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1,"1");
        map.put(2,"2");
        for(Map.Entry<Integer,String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }



    }
}
