package ssm.system.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ssm.system.util.ResultUtils;


@Controller
@RequestMapping("/editor")
public class EditorController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/**
	 * 导入数据
	 * @return
	 */
	@RequestMapping("/importdata")
	public void importdata(@RequestParam MultipartFile[] fileupload, 
				HttpServletRequest request, HttpServletResponse response){
		try{
			InputStream is = fileupload[0].getInputStream();//多文件也适用,我这里就一个文件  
	        byte[] b = new byte[(int)fileupload[0].getSize()];  
	        int read = 0;  
	        int i = 0;  
	        while((read=is.read())!=-1){  
	            b[i] = (byte) read;  
	            i++;  
	        }
	        is.close();
			String msg = new String(b);
			ResultUtils.renderSuccess(msg, response);
		}catch (Exception e) { 
			ResultUtils.renderFail("失败", response);
		}
	}
	
	/**
	 * 保存
	 * @param content
	 * @return
	 */
	@RequestMapping("/save")
	public String save(String content){
		
		logger.info("保存成功:"+content);
		
		return "/wxeditor/index";
	}
	
}
