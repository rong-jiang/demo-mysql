package cn.mark.demomysql.ulit;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class fileUpload {
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response){
        response.reset();
        response.setContentType("application/x-msdownload; charset=GBK");
        // 导出文件名
        String excelName = "导入模板";
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
// xlsx 就把 .xls 改成.xlsx 并用 XSSFWorkbook 代替 HSSFWorkbook 因为Excel的版本不同 要不然会报错 (后面的 HSSFSheet 什么的也把H换成X)
}
