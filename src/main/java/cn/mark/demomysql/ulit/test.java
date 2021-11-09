package cn.mark.demomysql.ulit;

import java.util.Arrays;

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

//冒泡排序
public static void main(String[] args) {
        int t=0;
        int[] sum ={25,65,5,10,80};
        for (int i = 0; i < sum.length-1; i++){
            for (int j = 0; j < sum.length-1-i; j++){
                if (sum[j+1]<sum[j]){
                    t=sum[j];
                    sum[j]=sum[j+1];
                    sum[j+1]=t;
                }
            }
        }
    System.out.println(Arrays.toString((sum)));
    }

}
