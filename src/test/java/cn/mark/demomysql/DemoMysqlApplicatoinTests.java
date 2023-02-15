package cn.mark.demomysql;


import cn.mark.demomysql.model.Book;
import cn.mark.demomysql.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoMysqlApplicatoinTests {

    @Autowired
    BookService bookService;

    @Test
    //感觉多线程没效果
    public void testBook() throws Exception {
        long start = System.currentTimeMillis();
        Callable callable=()->{
            Book book=new Book();
            List<Book> books = bookService.queryListBook(book);
            return books;
        };

        FutureTask futureTask=new FutureTask(callable);
        new Thread(futureTask).start();
        List<Book> o = (List<Book>) futureTask.get();
        o.forEach(book1 -> {
            System.out.println(book1);
        });

        long end = System.currentTimeMillis();

        System.out.println("消耗时间====="+(end-start)+"ms");

    }

    @Test
    public  void testdiaoyong(){

        long start = System.currentTimeMillis();
        Date date = new Date();
        long time = date.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = dateFormat.format(time);
        System.out.println(format);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("测试："+i);
                }
            }
        }).start();
        long end = System.currentTimeMillis();
        System.out.println("时间:"+(end-start));


    }
}
