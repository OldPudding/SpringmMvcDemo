package ssm.system.util;

import org.apache.poi.xwpf.usermodel.*;  

import java.io.*;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  
public class XwpfTUtil {  
  
    
  
    /** 
     * 替换段落里面的变量 
     * 
     * @param doc    要替换的文档 
     * @param params 参数 
     */  
    public void replaceInPara(XWPFDocument doc, Map<String, Object> params) {  
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();  
        XWPFParagraph para;  
        while (iterator.hasNext()) {  
            para = iterator.next();  
            this.replaceInPara(para, params);  
        }  
    }  
  
    /** 
     * 替换段落里面的变量 
     * 
     * @param para   要替换的段落 
     * @param params 参数 
     */  
    public void replaceInPara(XWPFParagraph para, Map<String, Object> params) {  
        List<XWPFRun> runs;  
        //Matcher matcher;  
        if (this.matcher(para.getParagraphText()).find()) {  
            runs = para.getRuns();  
  
            int start = -1;  
            int end = -1;  
            String str = "";  
            for (int i = 0; i < runs.size(); i++) {  
                XWPFRun run = runs.get(i);  
                String runText = run.toString();  
                if ('$' == runText.charAt(0)&&'{' == runText.charAt(1)) {  
                    start = i;  
                }  
                if ((start != -1)) {  
                    str += runText;  
                }  
                if ('}' == runText.charAt(runText.length() - 1)) {  
                    if (start != -1) {  
                        end = i;  
                        break;  
                    }  
                }  
            }  
  
            for (int i = start; i <= end; i++) {  
                para.removeRun(i);  
                i--;  
                end--;  
            }  
  
            for (String key : params.keySet()) {  
                if (str.equals(key)) {  
                    para.createRun().setText((String) params.get(key));  
                    break;  
                }  
            }  
  
  
        }  
    }  
  
    /** 
     * 替换表格里面的变量 
     * 
     * @param doc    要替换的文档 
     * @param params 参数 
     */  
    public void replaceInTable(XWPFDocument doc, Map<String, Object> params) {  
        Iterator<XWPFTable> iterator = doc.getTablesIterator();  
        XWPFTable table;  
        List<XWPFTableRow> rows;  
        List<XWPFTableCell> cells;  
        List<XWPFParagraph> paras;  
        while (iterator.hasNext()) {  
            table = iterator.next();  
            rows = table.getRows();  
            for (XWPFTableRow row : rows) {  
                cells = row.getTableCells();  
                for (XWPFTableCell cell : cells) {  
                    paras = cell.getParagraphs();  
                    for (XWPFParagraph para : paras) {  
                        this.replaceInPara(para, params);  
                    }  
                }  
            }  
        }  
    }  
  
    /** 
     * 正则匹配字符串 
     * 
     * @param str 
     * @return 
     */  
    private Matcher matcher(String str) {  
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);  
        Matcher matcher = pattern.matcher(str);  
        return matcher;  
    }  
  
    /** 
     * 关闭输入流 
     * 
     * @param is 
     */  
    public void close(InputStream is) {  
        if (is != null) {  
            try {  
                is.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * 关闭输出流 
     * 
     * @param os 
     */  
    public void close(OutputStream os) {  
        if (os != null) {  
            try {  
                os.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
 
    /**
     * 解决设置名称时的乱码  
     * @param request
     * @param fileName
     * @return
     */
    public String processFileName(HttpServletRequest request, String fileName) {  
        String codedfilename = null;  
        try {  
            String agent = request.getHeader("USER-AGENT");  
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent  
                    && -1 != agent.indexOf("Trident")) {// ie  
  
                String name = java.net.URLEncoder.encode(fileName, "UTF8");  
  
                codedfilename = name;  
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等  
  
  
                codedfilename = new String(fileName.getBytes("UTF-8"), "iso-8859-1");  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return codedfilename;  
    }
    
    
    
    /**
     * 导出文件
     * @param bean
     * @param specialMap
     * @param templeName
     * @param outName
     * @param request
     * @param response
     * @throws IOException
     */
    public static void exportFile(Object bean, Map<String, Object> specialMap, String templeName, String outName,
            HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        //转换参数
        Map<String, Object> params = MethodUtils.setOfficeMap(bean);
        //替换特殊参数
        params.putAll(specialMap);
        
        XwpfTUtil xwpfTUtil = new XwpfTUtil();  
        
        XWPFDocument doc;  
        String fileNameInResource = request.getSession().getServletContext().getRealPath("/temple")+templeName;  
        InputStream is;  
        is = new FileInputStream(fileNameInResource);
        doc = new XWPFDocument(is);  
  
        xwpfTUtil.replaceInPara(doc, params);  
        //替换表格里面的变量  
        xwpfTUtil.replaceInTable(doc, params); 
        
        String fileName = outName;
        // 防乱码
        fileName = xwpfTUtil.processFileName(request, fileName);
        response.reset(); 
        response.setContentType("application/vnd.ms-word;charset=utf-8");  
        response.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
        response.setBufferSize(1024); 
        
        OutputStream os = response.getOutputStream();  
        doc.write(os);  
  
        xwpfTUtil.close(os);  
        xwpfTUtil.close(is);  
  
        os.flush();  
        os.close();
        
    }
    
}  
