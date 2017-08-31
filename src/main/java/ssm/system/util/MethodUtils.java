package ssm.system.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class MethodUtils {
    
    public static final String salt = "javacoder";
	
    public static String md5(String str){
        return md5(str, salt);
    }
    
	public static String md5(String str,String salt)
	{
		return new Md5Hash(str, salt).toString();
	}
	

    /**
     * 取某对象中某字段的值
     * @param object
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object object,String fieldName) {
        Object value =null;
        if(object!=null){
            Class clszz=object.getClass();
            Method getMdthod=null;
            try {
                getMdthod = clszz.getMethod("get"+StringUtils.capitalize(fieldName));
                value=(Object) getMdthod.invoke(object);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                getMdthod=null;
                clszz=null;
            }
        }
        return value;
    }
    

    /**
     * 给某个字段赋值
     * @param object
     * @param fieldName
     * @param value
     */
    public static void setFieldValue(Object object,String fieldName,Object value) {
        if(object!=null){
            Class clszz=object.getClass();
            String setMethodName="";
            Method setMdthod=null;
            Field field =null;
            try {
                field = object.getClass().getDeclaredField(fieldName);
                setMethodName="set"+StringUtils.capitalize(fieldName);
                setMdthod=clszz.getDeclaredMethod(setMethodName, field.getType()); 
                setMdthod.invoke(object, value);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                setMdthod=null;
                setMethodName=null;
                clszz=null;
            }
        }
    }
    	
    /**
     * 根据字段名称取值（同getFieldValue方法）
     * @param obj
     * @param fieldName
     * @return
     */
    public static String getClassValue(Object obj, String fieldName) {
        String value = null;
        if( obj != null ){
            Method[] methods = obj.getClass().getDeclaredMethods();
            for(int i=0;i<methods.length;i++) {
                String methodName = methods[i].getName();
                if (methodName.substring(0,3).toUpperCase().equals("GET") 
                        && methodName.substring(3).toUpperCase().equals(fieldName.toUpperCase())) {    
                    Method method = methods[i];
                    try {    
                        value = (String)method.invoke(obj, new Object[] {});  
                    } catch (Exception e) {
                        e.printStackTrace();
                    }    
                }    
            }  
        }
        return value;
    }
	
    /**
     * 记录变更日志
     * @param oldBean
     * @param newBean
     * @param logKey
     * @return
     */
    public static Map<String, String[]> changValue(Object oldBean, Object newBean, String[] logKey) {
        Map<String, String[]> map = new HashMap<String, String[]>();
        for(String key : logKey){
            String[] value = new String[2];
            String oldValue = getClassValue(oldBean, key);
            String newValue = getClassValue(newBean, key);
            if( oldValue==null ){
                oldValue = "";
            }
            if( newValue==null ){
                newValue = "";
            }
            //记录变更前后
            value[0] = oldValue;
            value[1] = newValue;
            if( !newValue.equals(oldValue) ){
                map.put(key, value);
            }
        }
        
        return map;
    }
    
    /**
     * 设置生成office的map
     * @param bean
     * @return
     */
    public static Map<String, Object> setOfficeMap(Object bean){
        Map<String, Object> params = new HashMap<String, Object>();
        if( bean != null ){
            Method[] methods = bean.getClass().getDeclaredMethods();
            for(int i=0;i<methods.length;i++) {
                String methodName = methods[i].getName();
                if (methodName.substring(0,3).toUpperCase().equals("GET")) {    
                    Method method = methods[i];
                    String key = "${" + methodName.substring(3).toLowerCase() + "}";
                    try {
                        Object objValue = method.invoke(bean, new Object[]{});
                        String value = "";
                        if(objValue!=null){
                            if(objValue instanceof String){
                                value = objValue.toString();
                            } else if (objValue instanceof Integer){
                                value = objValue.toString();
                            } else if (objValue instanceof Date){
                                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                                value = sf.format(objValue);
                            }
                            
                        }
                        
                        params.put(key,value);  
                    } catch (Exception e) {
                        e.printStackTrace();
                    }    
                }    
            }  
        }
        return params;
    }
    
    
    /**
     * 替换选中符号
     * @param specialMap
     * @param objKey
     * @param objValue
     * @param pMap
     * @param isRadio
     */
    public static void setMapChecked(Map<String, Object> specialMap, String objKey, Object objValue, 
            LinkedHashMap<String, String> pMap, boolean isRadio){
        
        String symbol = "○";
        if(!isRadio){
            symbol = "□";
        }
        
        String checked="";
        if(objValue!=null){
            checked = objValue.toString();
        }
        
        String key = "${" + objKey.trim() + "}";
        String value = "";
        for(String pKey : pMap.keySet()){
            // 判断选中,包含多选
            if(checked.indexOf(pKey)>-1){
                value += "√"+pMap.get(pKey)+" ";
            } else {
                value += symbol+pMap.get(pKey)+" ";
            }
        }
        
        specialMap.put(key, value);
        
    }
    
    
}
