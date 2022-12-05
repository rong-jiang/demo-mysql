package cn.mark.demomysql.ulit;


import cn.mark.demomysql.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class test02Thread implements Runnable{

    //胜利者
    private String winner;

    @Override
    public void run() {

        for (int i = 0; i <= 100; i++) {

            //模拟兔子消息
            if (Thread.currentThread().getName().equals("兔子") && i%10==0){

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //判断比赛是的结束
            boolean flag=gameOver(i);
            //如果比赛结束了,就停止程序
            if (flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"---->跑了"+i+"步");
        }
    }

    //判断是否完成比赛
    private boolean gameOver(int steps){
        //判断是否有胜利者
        if (winner!=null){//已经存在胜利者了
            return true;
        }{
            if (steps>=100){
                winner=Thread.currentThread().getName();
                System.out.println("Winner is"+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        test02Thread thread=new test02Thread();
        new Thread(thread,"兔子").start();
        new Thread(thread,"乌龟").start();
    }
}
