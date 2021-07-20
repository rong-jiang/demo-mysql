package cn.mark.demomysql.ulit;

import cn.mark.demomysql.model.Book;
import cn.mark.demomysql.model.Books;
import cn.mark.demomysql.service.BookService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * 信息上传  工具类
 *
 * @author yu
 * @version 1.0
 * @date 2021/07/13 16:19
 */
@Slf4j
public class UploadSendListener extends AnalysisEventListener<Book> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;

    private List<Books> errorList;

    private BookService bookService;

    private Boolean success = false;

    private List<Book> sendList;

    private Book book;

    private Books books;

    private int count=0;

    public UploadSendListener(BookService bookService) {
        this.sendList=new ArrayList<>();
        this.errorList=new ArrayList<>();
        this.bookService = bookService;
    }

    @Override
    public void invoke(Book book, AnalysisContext analysisContext) {
        count++;
        books=new Books();
//        Object custom = analysisContext.getCustom();
        log.info("解析到一条数据:{}", JSON.toJSONString(book));
//        log.info("测试看着数据:{}",custom);
//        baseDTO.setDataId(5063521216561848550L);
//        baseDTO.setDataId(5063521216561848561L);
        if (StringUtils.isBlank(book.getName())) {
            books.setExceptionDescription("第"+count+"条姓名为空");
            this.success = false;
            errorList.add(books);
            return;
        }
        if (StringUtils.isBlank(book.getSetb())) {
            books.setExceptionDescription("第"+count+"条性别为空");
            this.success = false;
            errorList.add(books);
            return;
        }
        sendList.add(book);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (sendList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            sendList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }


    public Boolean getSuccess() {
        return this.success;
    }

    public List<Books> getErrorList() {
        return this.errorList;
    }

    private void saveData() {
        log.info("{}条数据，开始准备存储到数据库:", sendList.size());
        log.info("条{}数据，开始准备存储到数据库的数据:{}！",count, sendList);
        log.info("数据，错误信息:{}", errorList);
        try {
            if (sendList.size() == 0) {
                return;
            }
            Integer integer = bookService.insertSelective(sendList);
            if (integer > 0) {
                this.success = true;
            } else {
                this.success = false;
            }
            sendList.clear();
        } catch (Exception e) {
            sendList.clear();
            this.success = false;
            log.error("[ERROR:数据导入失败]", e);
        }

//        log.info("接受controller过来的数据（登入）"+str);
//        log.info("存储{}条数据库成功！",integer);
    }
}
