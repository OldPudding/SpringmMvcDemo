package ssm.system.controller;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import ssm.system.entity.User;
import ssm.system.service.UserService;
import ssm.system.util.MethodUtils;

@Controller
@RequestMapping("/user")
public class UserController {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
	@Resource
    private UserService userService;
	
	/**kaptcha产生验证码所需的类*/
	@Autowired  
	private Producer captchaProducer = null;  
	/** 																				
	 * 获取验证码																				
	 * 																				
	 * @param   request			请求对象
	 *			response			返回对象																				
	 * @return	ModelAndView			Model与View的对象																																								
	 */  
	@RequestMapping("/captcha-image")  
	public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {    
	    /*设置返回头信息，内容类型为图片*/      
		response.setDateHeader("Expires", 0);  
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
		response.setHeader("Pragma", "no-cache");  
		response.setContentType("image/jpeg");  
	    /*产生验证码，并设置在session中*/    
		String capText = captchaProducer.createText();
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  
		/*产生验证码图片，并返回给前端*/   
		BufferedImage bi = captchaProducer.createImage(capText);  
		ServletOutputStream out = response.getOutputStream();  
		ImageIO.write(bi, "jpg", out);  
		try {  
			out.flush();  
		} finally {  
			out.close();  
		}  
		return null;  
	}  
	
	/*
	 * 权限用户登入
	 * @param user
	 * @param request
	 * 
	 */
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request)
	{
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		/*判断输入验证码是否正确*/
        String inputcode = request.getParameter("verificationcode");//获取用户输入验证码
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);//获取session中验证码  
        if(!code.equals(inputcode)){
            request.setAttribute("user", user);
            request.setAttribute("errorInfo", "验证码错误");
            return "login"; //如果认证失败，则跳会登录页面并提示错误信息
        }
		
		String newPassword = MethodUtils.md5(user.getPassword());		
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),newPassword);
		token.setRememberMe(false);
		try
		{
			subject.login(token);
			User currentUser = userService.getByUsername(user.getUserName());
			session.setAttribute("currentUser", currentUser);
			// 重定向到main.jsp页面
			return "redirect:/editPlat/index.jsp";
		}catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("user", user);
            request.setAttribute("errorInfo", "用户名或密码错误！");
            return "login"; 
		}
		
	}	
	
	/**
	 * 安全退出
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
    public String logout(HttpServletRequest request){
	    HttpSession session = request.getSession();
	    session.removeAttribute("currentUser");
        return "redirect:/login.jsp";
	    
	}
	
	
}
