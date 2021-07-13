package cn.mark.demomysql.service.imp;

import cn.mark.demomysql.mapper.BookMapper;
import cn.mark.demomysql.model.Book;
import cn.mark.demomysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
//public class BookServiceImpl extends ServiceImpl<BookMapper,Book> implements BookService {
public class BookServiceImpl  implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public int insert(Book record) {
        return 0;
    }

    @Override
    public List<Book> insertSelective(List<Book> record) {
        for (Book book : record) {
            book.setAge(30);
            bookMapper.insertSelective(book);
        }
        return record;
    }

    @Cacheable(cacheNames = "bookRedis",key = "'t_book_'+#id")
    @Override
    public Book queryList(int id) {
//        return bookMapper.selectById(id);
        return null;
    }

    @Override
//    ,condition = "'select * from t_book where id ='+#id+'and age ='+#age"
    @Cacheable(cacheNames = "bookRedisIdAge",key = "'t_book_'+#id+'_'+#age")
    public List<Book> listBook(int id, int age) {
        Book book=new Book();
        book.setId(id);
        book.setAge(age);
        return bookMapper.listBook(book);
    }

    @Override
//    ,condition = "'select * from t_book where id ='+#id+'and age ='+#age"
    @Cacheable(value = "bookRedisAge")
    public List<Book> listBookAge(int age) {
        Book book=new Book();
        book.setAge(age);
        return bookMapper.listBook(book);
    }


}
