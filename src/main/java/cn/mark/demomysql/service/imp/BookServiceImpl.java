package cn.mark.demomysql.service.imp;

import cn.mark.demomysql.mapper.BookMapper;
import cn.mark.demomysql.mapper.DeptMapper;
import cn.mark.demomysql.mapper.TestMapper;
import cn.mark.demomysql.model.Book;
import cn.mark.demomysql.model.Test;
import cn.mark.demomysql.service.BookService;
import cn.mark.demomysql.service.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import java.beans.Transient;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
//public class BookServiceImpl extends ServiceImpl<BookMapper,Book> implements BookService {
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private TestMapper testMapper;

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

    @Cacheable(value = {"bookRedisAge01"}, key = "#root.args[0]")
    @Override
    public Book selectById(Integer id) {
        Map map = new HashMap();
        return bookMapper.selectById(id);
    }

    @Override
//    ,condition = "'select * from t_book where id ='+#id+'and age ='+#age"
    @Cacheable(cacheNames = "bookRedisIdAge", key = "'t_book_'+#id+'_'+#age")
    public List<Book> listBook(int id, int age) {
        Book book = new Book();
        book.setId(id);
        book.setAge(age);
        return bookMapper.listBook(book);
    }

    @Override
    public List<Book> queryListBook(Book book) throws Exception {
        return bookMapper.queryListBook(book);
    }

    /**
     * 查询book所有数据带分页
     * @return
     * @throws Exception
     */
    @Override
    public PageInfo<List<Book>> queryListBookAll(Integer pageNum, Integer pageSize) throws Exception {
        // 开始分页
        //1、调用PageHelper的startPage方法设置参数,其中page为当前页，size为每页显示条数
        PageHelper.startPage(pageNum,pageSize);
        //2、调用Mapper层查询全部的方法
        List<Book> all = bookMapper.queryListBookAll();
        //3、构造pageInfo返回值
        return new PageInfo(all);
    }

    /**
     * 测试事物
     *
     * @return
     * @Transactional 第一种
     * TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();第二种（目前测试能成功）
     */
    @Transactional(rollbackFor = Exception.class)
    public String testBookUser() {
        try {
            Book book = new Book(1021000000, "李四1000", "男", 10);
//            Book book = new Book(1021000,"李四1000你到底在不在能不能成功就看你这一波了好兄弟","男",10);
            Integer integer = bookMapper.updateBook(book);
            log.info("book修改是否成功:" + integer);

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//觉得出错需要回滚到代码段后面（目前测试能成功）

            Test test = new Test();
            test.setTestId(38);
            test.setTestName("李四");
            test.setStatus(1);
            test.setTestTetlie("123456");
            test.setTestUpdate(new Date());
            int i = testMapper.updateByPrimaryKeySelective(test);
            log.info("test修改是否成功:", i);
            return integer > 0 ? "book修改成功" : "book修改失败";
        } catch (Exception e) {
            log.info("会出现在?");
            return "报出异常，进行回滚";
        }
    }

    @Override
//    ,condition = "'select * from t_book where id ='+#id+'and age ='+#age"
    @Cacheable(value = "bookRedisAge01")
    public List<Book> listBookAge(int age) {
        Book book = new Book();
        book.setAge(age);
        return bookMapper.listBook(book);
    }

    @Override
    public Integer deleteBook(int id) {
        return bookMapper.deleteBook(id);
    }

    @CachePut(value = {"bookRedisAge01"}, key = "#book.id")
    @Override
    public Integer updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public List<Map<String, Object>> queryMap(Map<String, Object> map) {
        map.put("setb", "男");
        return bookMapper.selectMaps(map);
    }


}
