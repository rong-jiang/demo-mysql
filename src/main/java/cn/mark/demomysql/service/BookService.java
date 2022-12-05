package cn.mark.demomysql.service;

import cn.mark.demomysql.model.Book;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Map;

//public interface BookService extends IService<Book> {
public interface BookService {
    int insert(Book record);

    Integer insertSelective(List<Book> record);

    Book selectById(Integer id);

    List<Book> listBook( int id, int age) throws Exception;

    List<Book> queryListBook(Book book) throws Exception;

    List<Book> listBookAge(int age) throws Exception;

    Integer deleteBook(int id);

    Integer updateBook(Book book);

    List<Map<String,Object>> queryMap(Map<String,Object> map);
}