package cn.mark.demomysql.service.imp;

import cn.mark.demomysql.mapper.BookMapper;
import cn.mark.demomysql.model.Book;
import cn.mark.demomysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.validation.constraints.Max;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//public class BookServiceImpl extends ServiceImpl<BookMapper,Book> implements BookService {
public class BookServiceImpl  implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public int insert(Book record) {
        return bookMapper.insert(record);
    }

    @Override
    public Integer insertSelective(List<Book> record) {
        for (Book book : record) {
            bookMapper.insertSelective(book);
        }
        return 1;
    }

    @Cacheable(value ={"bookRedisAge01"},key = "#root.args[0]")
    @Override
    public Book selectById(Integer id) {
        Map map=new HashMap();
        return bookMapper.selectById(id);
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
    public List<Book> queryListBook(Book book) throws Exception {
        return bookMapper.queryListBook(book);
    }

    @Override
//    ,condition = "'select * from t_book where id ='+#id+'and age ='+#age"
    @Cacheable(value = "bookRedisAge01")
    public List<Book> listBookAge(int age) {
        Book book=new Book();
        book.setAge(age);
        return bookMapper.listBook(book);
    }

    @Override
    public Integer deleteBook(int id) {
        return bookMapper.deleteBook(id);
    }

    @CachePut(value ={"bookRedisAge01"},key ="#book.id")
    @Override
    public Integer updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public List<Map<String, Object>> queryMap(Map<String, Object> map) {
        map.put("setb","男");
        return bookMapper.selectMaps(map);
    }


}
