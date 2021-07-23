package cn.mark.demomysql.ulit;


import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 工具类 （占不用）
 */
public class ExportUtil {
    public static void exportData(HttpServletRequest req, HttpServletResponse resp, List results, String fileName,
                                  String year, String quarter, String filePath) throws Exception{
        FileInputStream fis = null;
        ServletOutputStream os = null;
        XLSTransformer transformer = new XLSTransformer();
        Map params = new HashMap();
        params.put("year", year);
        params.put("quarter", quarter);
        params.put("results", results);

        fis = new FileInputStream(filePath);
        resp.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        resp.setContentType("application/vnd.ms-excel;charset=gbk");
        resp.setHeader("Location", URLEncoder.encode(fileName,"UTF-8") + ".xls");

        HSSFWorkbook wb = (HSSFWorkbook) transformer.transformXLS(fis, params);
        os = resp.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
        fis.close();
    }

}
