package cn.mark.demomysql.ulit;


import java.util.*;

public class testdemo1 {

    public static void main(String[] args) {
        List list=new ArrayList();
        list.add(1);
        list.add(12);
        list.add(12);
        list.add(5);
        list.add(14);

        list.forEach(ls ->{
            System.out.println(ls);
        });

        System.out.println("========================");

        Set set=new HashSet();
        set.addAll(list);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }

        System.out.println("============================");

        Set set1=new HashSet();
        set1.add(1);
        set1.add(5);
        set1.add(5);
        set1.add(6);

        System.out.println(set1);
        System.out.println("========================");
        for (Object o : set1) {
            System.out.println(o);
        }

        System.out.println("集合转数组");
        Object[] objects = list.toArray();
//        Object[] objects = list.toArray(new Integer[list.size()]);
        for (Object object : objects) {
            System.out.println(object);
        }

        System.out.println("=========================");
        System.out.println("数组装集合");


        String [] a ={"张三","李四","王五"};

        List<String> list1 = Arrays.asList(a);
        list1.forEach(lista ->{
            System.out.println(lista);
        });
        //list1.add("超六");会报错
        //如果想要操作add、remove等操作，则需要：
        List<String> list2=new LinkedList<>();
        list2.add("超六");
        list2.forEach(lista ->{
            System.out.println(lista);
        });


    }



}
