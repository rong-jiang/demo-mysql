package cn.mark.demomysql.ulit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class test {
    //冒泡排序
   /* public static void main(String[] args) {
        int[] sum = {25, 65, 5, 10, 80};
        int[] add = add(sum);
        for (int i = 0; i < add.length; i++) {
            System.out.println(add[i]);
        }
        System.out.println(Arrays.toString(add(sum)));
    }

    public static int[] add(int[] sum) {
        int t = 0;
        for (int i = 0; i < sum.length - 1; i++) {
            for (int j = 0; j < sum.length - 1 - i; j++) {
                if (sum[j + 1] < sum[j]) {
                    t = sum[j];
                    sum[j] = sum[j + 1];
                    sum[j + 1] = t;
                }
            }
        }
        return sum;
    }*/

    //排序
    public static void main(String[] args) {
        int t = 0;
        int[] sum = {25, 65, 5, 10, 80};
        //插入排序(快（2倍）)
        for (int i =1; i < sum.length; i++) {//从第二个开始 每次拿一个
            System.out.println("第"+i+"次");
            for (int j = i; j>0 ; j--) {//找到一个合适的位子插进去（当前位子和前面的那些数字比较）
                if (sum[j]<sum[j-1]){
                    t=sum[j];
                    sum[j]=sum[j-1];
                    sum[j-1]=t;

                }
                System.out.println(Arrays.toString(sum));
            }
        }
        //冒泡排序(慢)
//        for (int i = 0; i < sum.length - 1; i++) {
//            for (int j = 0; j < sum.length - 1 - i; j++) {
//                if (sum[j + 1] < sum[j]) {
//                    t = sum[j];
//                    sum[j] = sum[j + 1];
//                    sum[j + 1] = t;
//                }
//            }
//        }
        System.out.println("=========================");
        System.out.println(Arrays.toString((sum)));

    /*Scanner scanner=new Scanner(System.in);
    System.out.println("请输入名字:");
    String s = scanner.next();
    Scanner scanner2=new Scanner(System.in);
    System.out.println("请输入性别:");
    String s2 = scanner.next();
    Scanner scanner3=new Scanner(System.in);
    System.out.println("请输入地址:");
    String s3 = scanner.next();
    System.out.println("个人信息:"+"\n"+"姓名:"+s+"\n"+"性别:"+s2+"\n"+"地址:"+s3);
*/
        List list = new ArrayList();
        list.add(1);
        list.add(12);
        list.add(13);
        list.add(14);
        //将 lambda 表达式传递给 forEach
        list.forEach(li -> {
            System.out.println(li);
        });
        //可去掉{}
//    list.forEach(li ->
//        System.out.println(li)
//    )

    /*String str = "Holler";

    byte[] bytes = str.getBytes();

    for (int i = 0; i < bytes.length; i++) {
        System.out.println(bytes[i]);
    }*/

        //前缀自增自减法(++a,--a): 先进行自增或者自减运算，再进行表达式运算。
        //后缀自增自减法(a++,a--): 先进行表达式运算，再进行自增或者自减运算
        int a = 10;
        int b = 20;
        int c = 25;
        int d = 25;
        System.out.println("a+=b" + (a += b));
//    System.out.println("a + b = " + (a + b) );
//    System.out.println("a - b = " + (a - b) );
//    System.out.println("a * b = " + (a * b) );
//    System.out.println("b / a = " + (b / a) );
//    System.out.println("b % a = " + (b % a) );
//    System.out.println("c % a = " + (c % a) );
        System.out.println("a++   = " + (a++));
        System.out.println("a--   = " + (a--));
        System.out.println("b--   = " + (b++));
        System.out.println("b--   = " + (b--));
        // 查看  d++ 与 ++d 的不同
        System.out.println("d++   = " + (d++));
        System.out.println("++d   = " + (++d));

        String name = "张三";
        String names = "张三";
        System.out.println(name = names);
        System.out.println(name.equals(names));

        int x, p, i, s = 0;
        for (x = 2; x < 5; x++) {
            for (p = i = 1; i < x; i++) {
                p *= x;
            }
            s += p;
        }
        System.out.println("P" + s);

        System.out.println("==================");
        String s1 = "abc";
        String s2 = "";
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder append = stringBuilder.append(s1).append("def").append("jgk");
        System.out.println(append);
        s2= String.valueOf(append);
        System.out.println(s2);

        testdemo1 testdemo1=new testdemo1();
        System.out.println(testdemo1.testd());

    }


}
