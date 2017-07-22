package chapter7;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13.
 * hashTable  内部结构是哈希表，是同步的，不能为null作为键和值
 *    properties 用来存储键值对型的配置文件
 * hashMap 内部是表结构，不是同步的，null 能为键和值
 * TreeMap 内部是二叉树，不是同步的，可以对Map集合中的键进行排序
 *
 */
class Student{
    private String name;
    private  int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
public class MapDemo
{
    public static void main(String[] args){

        Map<Integer,String> map = new HashMap<Integer, String>();
        method(map);

        HashMap<Student, Integer> hm = new HashMap<Student, Integer>();
        hm.put(new Student("liyang",21),1);
        hm.put(new Student("liyang2",21),2);
        hm.put(new Student("liyang",21),1);

        Iterator<Student> it = hm.keySet().iterator();
        while(it.hasNext()){
            Student s = it.next();
            Integer value = hm.get(s);
            System.out.println(s.getName() + ":" + s.getAge() + "---" + value);
        }


    }
    public static void method(Map<Integer, String> map){
        map.put(8, "wangcai");
        map.put(8,"xiaiqiang");

        System.out.println(map);
    }
}
