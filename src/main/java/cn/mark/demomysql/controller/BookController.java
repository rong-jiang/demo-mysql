package cn.mark.demomysql.controller;

import cn.mark.demomysql.model.Book;
import cn.mark.demomysql.service.BookService;
import cn.mark.demomysql.ulit.UploadSendListener;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
@Api(description = "book查询导出导入")
@RequestMapping("/xboot/book")
public class BookController {
    @Autowired
    private BookService bookService;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "测试")
    public String sayHello() {
        return "Hello, World!";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加")
    public String addBook(Book book){
        int insert = bookService.insert(book);
        if (insert>0){
            return "添加成功";
        }else{
            return "添加失败";
        }
    }


    @RequestMapping(value = "/queryBook", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据id查询")
    public Book queryBook(@RequestParam("id") int id) {
//        Book book=new Book();
//        book.setId(1);
        return bookService.queryList(id);
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "条件查询")
    public List<Book> queryList(@RequestParam("id") int id, @RequestParam("age") int age) throws Exception {
        return bookService.listBook(id, age);
    }


    @RequestMapping(value = "/queryListAge", method = RequestMethod.GET)
    @ApiOperation(value = "测试条件查询")
    public List<Book> queryListAge( @RequestParam(value = "nameAge",required = false) String nameAge,@RequestParam("age") int age,HttpServletRequest request) throws Exception {
        String methodValue = request.getContextPath();
        HttpSession header = request.getSession();
        String servletContext = header.getId();
        byte[] bytes = servletContext.getBytes(StandardCharsets.UTF_8);
        for (byte aByte : bytes) {
            System.out.println("数组===:" + aByte);
        }
        System.out.println("methoValue的结果=====" + methodValue + "\r\n" + header + "\n" + servletContext);
        System.out.println("热加载..................");
        System.out.println("成功了...........");
        System.out.println("结束了.......");
        System.out.println("debug调式.....");
        System.out.println("怎么怎么快了快来了有点.....");
        System.out.println("调教查看分支.........");
        System.out.println("再次查看分支合并..........");

        return bookService.listBookAge(age);
    }

    /**
     * 线程测试案列
     * @param book
     * @return
     */
    @RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
    @ApiOperation(value = "线程测试案列")
    public Integer insertSelective(@RequestBody List<Book> book) {
        return bookService.insertSelective(book);
    }

    public static void main(String[] args) throws Exception {
        Thread thread0 = new Thread(() -> {
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

        new Thread(() -> {
            System.out.println(new Date() + "\t" + Thread.currentThread().getName() + "\t醒醒，醒醒，别睡了，起来干活了！！！");
            // 无需获取锁就可以调用interrupt
            thread0.interrupt();
        }).start();
    }

    /**
     * 导入数据
     * @param file
     * @return
     */
    @PostMapping(value = "/loadSendSign")
    @ApiOperation(value = "导入数据")
    public String loadSendSign(@RequestBody MultipartFile file) {
        InputStream inputStream = null;
        try {
            UploadSendListener listener = new UploadSendListener(bookService);
            inputStream = file.getInputStream();
            EasyExcel.read(inputStream, Book.class, listener).sheet(0).doRead();
            if (listener.getSuccess() && listener.getErrorList().size() == 0) {
                log.info("后台打印正确集合信息{}",listener.getSuccess());
//                return ResponseResultBody.createSuccessResult();
            }
            log.info("后台打印错误集合信息{}", listener.getErrorList());
//            return ResponseResultBody.createFailedResult(CommonErrorCode.ERROR,JSONObject.toJSONString(listener.getErrorList()));
        } catch (Exception e) {
            log.error("-----导入签收信息失败------",e);
//            return ResponseResultBody.createFailedResult("导入签收信息失败");
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                log.error("-----导入签收信息 关闭流inputStream失败------", e);
            }
        }
        return "";
    }

    /**
     *导出 模板
     * @param request
     * @param response
     */
    @GetMapping(value = "/downloadExcel")
    @ApiOperation(value = "导出 模板")
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response){
        response.reset();
        response.setContentType("application/x-msdownload; charset=GBK");
        // 导出文件名
        String excelName = "导出模板";
        // 改变编码格式的导出文件名
        String fileName = null;
        try {
            fileName = new String(excelName.getBytes("gb2312"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx\"");
            // 获取模板路径
            String realPath= this.getClass().getResource("/").getPath().replaceFirst("/", "");
            String templateRealPath = realPath + "/template/导入模板.xlsx ";// 文件放在webapp下template文件夹下
            File excel = new File(templateRealPath);
            FileInputStream is = null;
            is = new FileInputStream(excel);
            XSSFWorkbook wb = new XSSFWorkbook(is);
            XSSFSheet sheet = wb.getSheetAt(0);// 第一页
            XSSFFont font = wb.createFont();
            font.setFontHeightInPoints((short)10);
            font.setFontName("宋体");
            XSSFCellStyle style = wb.createCellStyle();
            wb.write(response.getOutputStream());
            response.getOutputStream().close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 导出模板数据
     * @param year
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/export")
    @ApiOperation(value = "导出模板数据")
    public void export(
                       @RequestParam("year") Integer year,
                       @RequestParam("name") String name,
                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        //根据查询条件获取查询数据
        List<Book> equipStatisticsList = bookService.listBookAge(10);
        // 循环数据
        List<Object> list = new ArrayList<>();
        equipStatisticsList.forEach(equipmentStatisticsNormRespVo -> {
            Map<String, Object> data = new HashMap<>();
            //数据处理
            data.put("name", equipmentStatisticsNormRespVo.getName());
            data.put("setb", equipmentStatisticsNormRespVo.getSetb());
            data.put("age", equipmentStatisticsNormRespVo.getAge());
            list.add(data);
        });

        // 表格使用的数据
        Map map = new HashMap();
        map.put("data", list);
//        map.put("title", year + "年" + "导出数据");

        log.info("输出这个map数据：{}",map);
        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("yyyyMMddHHmmss");
        String currntTime = simpl.format(date);
        //导出列表名
        String fileName = "导出数据" + "_" + currntTime;

        //生成的导出文件
        File destFile = File.createTempFile(fileName, ".xlsx");

        //transformer转到Excel
        XLSTransformer transformer = new XLSTransformer();

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            InputStream resourceAsStream = this.getClass().getResourceAsStream("/template/"+name+".xlsx");
//            XLSTransformer xlsTransformer = new XLSTransformer();
            Workbook workbook = transformer.transformXLS(resourceAsStream, map);
            OutputStream os = new BufferedOutputStream(new FileOutputStream(destFile));
            workbook.write(os);
            resourceAsStream.close();
            os.flush();
            os.close();

            //将文件输入
            InputStream inputStream = new FileInputStream(destFile);
            // 设置response参数，可以打开下载页面
            response.reset();
            //设置响应文本格式
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes("gb2312"), "iso-8859-1"));
            //将文件输出到页面
            ServletOutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // 根据读取并写入
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (ParsePropertyException e) {
            log.debug("导出失败：{}", e.getMessage());
        } catch (InvalidFormatException e) {
            log.debug("导出失败：{}", e.getMessage());
        } catch (NullPointerException e){
            log.debug("没找到模板：{}",e.getMessage());
        }
        finally {
            //使用完成后关闭流
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                log.debug("导出失败：{}",e.getMessage());
            }
        }
    }

    @RequestMapping("/toIndex")
    public ModelAndView toindex(){
        ModelAndView ad=new ModelAndView();
        ad.setViewName("index");
        return ad;
    }

    @RequestMapping("/add")
    public ModelAndView add(){
        ModelAndView ad=new ModelAndView();
        ad.setViewName("add");
        return ad;
    }
}
