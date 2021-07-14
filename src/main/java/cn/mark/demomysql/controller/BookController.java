package cn.mark.demomysql.controller;

import cn.mark.demomysql.model.Book;
import cn.mark.demomysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(){
        return "Hello, World!";
    }

    @RequestMapping(value="/queryBook",method = RequestMethod.GET)
    @ResponseBody
    public Book queryBook(@RequestParam("id") int id){
//        Book book=new Book();
//        book.setId(1);
        return bookService.queryList(id);
    }

    @RequestMapping(value="/queryList",method = RequestMethod.GET)
    @ResponseBody
    public List<Book> queryList (@RequestParam("id") int id,@RequestParam("age") int age) throws Exception {
        return bookService.listBook(id,age);
    }

    @RequestMapping(value="/queryListAge",method = RequestMethod.GET)
    public List<Book> queryListAge (@RequestParam("age") int age) throws Exception {
        System.out.println("热加载..................");
        System.out.println("成功了...........");
        System.out.println("结束了.......");
        System.out.println("debug调式.....");
        System.out.println("怎么怎么快了快来了有点.....");
        System.out.println("调教查看分支.........");
        System.out.println("再次查看分支合并..........");

        return bookService.listBookAge(age);
    }

    @RequestMapping(value="/insertSelective",method = RequestMethod.POST)
    public List<Book> insertSelective (@RequestBody List<Book> book){
        return bookService.insertSelective(book);
    }

    public static void main(String[] args) throws Exception {
        Thread thread0 = new Thread(()-> {
            try {
                System.out.println(new Date() + "\t" + Thread.currentThread().getName() + "\t太困了，让我睡10秒，中间有事叫我，zZZ。。。");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(new Date() + "\t" + Thread.currentThread().getName() + "\t被叫醒了，又要继续干活了");
            }
        });
        thread0.start();

        // 这里睡眠只是为了保证先让上面的那个线程先执行
        Thread.sleep(2000);

        new Thread(()-> {
            System.out.println(new Date() + "\t" + Thread.currentThread().getName() + "\t醒醒，醒醒，别睡了，起来干活了！！！");
            // 无需获取锁就可以调用interrupt
            thread0.interrupt();
        }).start();
    }
}
