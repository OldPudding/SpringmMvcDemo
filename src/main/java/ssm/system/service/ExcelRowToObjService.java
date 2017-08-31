package ssm.system.service;


import java.text.ParseException;

import org.apache.poi.ss.usermodel.Row;

public interface ExcelRowToObjService<T> {
    
    public T excelRowToObj(Row row)throws ParseException;
    
}