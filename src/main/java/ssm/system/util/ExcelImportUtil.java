package ssm.system.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import ssm.system.service.ExcelRowToObjService;


public class ExcelImportUtil<T> {

    public static final DecimalFormat df = new DecimalFormat("0");
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    public void parseExcel(CommonsMultipartFile excelFile, HttpServletRequest request, List<T> datas,
            ExcelRowToObjService<T> rowToObj)
            throws Exception {
        String updatePath = request.getServletContext().getRealPath("upload");
        String fileName = excelFile.getFileItem().getName();

        File file = new File(updatePath, fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = null;
        try {
            // 保存临时文件
            excelFile.transferTo(file);
            Workbook workbook = null;

            try {
                fileInputStream = new FileInputStream(file);
                workbook = new HSSFWorkbook(fileInputStream);
            } catch (Exception e) {
                fileInputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(fileInputStream);
            }
            // 读取第一个sheet
            Sheet sheet = workbook.getSheetAt(0);
            // 读取数据,从第二行开始，第一列岗位名称，第二列岗位描述,第三列岗位编号
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                if (StringUtils.isNotBlank(getCellValue(sheet.getRow(i).getCell(0)))) {
                    T t= (T) rowToObj.excelRowToObj(sheet.getRow(i));
                    datas.add(t);
                }
            }

            if (null != fileInputStream) {
                fileInputStream.close();
            }
        } catch (Exception e) {
            throw new Exception("excel文件错误", e);
        } finally {
            file.delete();
        }
    }


    public static String getCellValue(Cell cell) {

        Object value = null;
        if (null == cell) {
            return null;
        }
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_BOOLEAN:
            value = cell.getBooleanCellValue();
            break;
        case Cell.CELL_TYPE_NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                value = sdf.format(cell.getDateCellValue());
            } else {
                value = df.format(cell.getNumericCellValue());
            }
            break;
        default:
            value = cell.getStringCellValue();
            break;
        }
        return String.valueOf(value);
    }



}
