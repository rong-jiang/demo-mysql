package cn.mark.demomysql;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class test {
    //    java 将 list 字符串用逗号隔开拼接字符串的多种方法
    //   然后再将逗号拼接的字符串转义为SQL中IN的条件
//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("aaa");
//        list.add("bbb");
//        list.add("ccc");
//
//        String result = list.stream().map(String::valueOf).collect(Collectors.joining(","));
//        System.out.println("数组："+result);
//        getInSql(result.split(","));
//
//    }

    private static String getInSql(String[] array) {
        String inSql = "";
        if (null != array && array.length > 0) {
            for (String str : array) {
                inSql += "'" + str + "',";
            }
            inSql = inSql.substring(0, inSql.length() - 1);
           // inSql = " (" + inSql + ") ";//in如果带有括号 可以将其去掉括号
        }
        System.out.println("拆分："+inSql);
        return inSql;
    }

    /* public static void main(String[] args) {
        int x ,p,i,s=0;
        for (x=2; x<5; x++){
            for (p=i=1;i<x;i++){
                p*=x;
            }
            s+=p;
        }
        System.out.println("P"+s);
    }*/

    /*public static void main(String[] args) {
        System.out.println(test(5));

    }
    public static int test(int n){
        try{
            n++;
            System.out.println("try"+(n/0));
            return n;
        }catch (Exception e){
            n++;
            System.out.println("catch"+n);
        }finally {
            n++;
            System.out.println("fin"+n);
        }
        return n;
    }*/


    //java中关于数组去重 方法一：用Set集合进行去重
    public static void main(String[] args) {
        String[] test = {"A","B","B","B","D","E","E","E","A"};
        List<String> result = new ArrayList<>();
        Set<String> settest=new HashSet<String>();
        for(int i=1;i<test.length;i++){
            result.add(test[i]);
        }
        settest.addAll(result);
        System.out.println(settest);
    }
}

