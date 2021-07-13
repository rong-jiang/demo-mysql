package cn.mark.demomysql.mapper;

import cn.mark.demomysql.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
//public interface BookMapper extends BaseMapper<Book> {
public interface BookMapper  {
    int insert(Book record);
    int insertSelective(Book record);
//    List<Book> listBook(@Param("bookId") int id, @Param("age") int age);
    List<Book> listBook(Book book);
}