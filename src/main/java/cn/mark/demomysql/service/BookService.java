package cn.mark.demomysql.service;

import cn.mark.demomysql.model.Book;
import org.springframework.data.repository.query.Param;

import java.util.List;

//public interface BookService extends IService<Book>{
public interface BookService {
    int insert(Book record);

    Integer insertSelective(List<Book> record);

    Book queryList(int id);

    List<Book> listBook( int id, int age) throws Exception;

    List<Book> listBookAge(int age) throws Exception;
}