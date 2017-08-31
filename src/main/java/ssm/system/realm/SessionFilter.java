package ssm.system.realm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/** 
 * 登录过滤 
 *  
 */  
public class SessionFilter extends OncePerRequestFilter {  
  
    @Override  
    protected void doFilterInternal(HttpServletRequest request,  
            HttpServletResponse response, FilterChain filterChain)  
            throws ServletException, IOException {  
  
  
        // 请求的uri  
        String uri = request.getRequestURI();  
        String path = request.getContextPath();
        //如果登陆uri为登陆页面或者项目名称，则判断session是否存在，存在的话自动转到首页
        if ( uri.indexOf("login.jsp") > 0 || (uri.endsWith(path+"/")) ) {  
            // 执行过滤  
            // 从session中获取登录者实体  
            Object obj = request.getSession().getAttribute("currentUser");  
            if (null != obj) {  
                // 如果session中存在登录者实体，跳转到主页面
                // 设置request和response的字符集，防止乱码  
                request.setCharacterEncoding("UTF-8");  
                response.setCharacterEncoding("UTF-8");  
                PrintWriter out = response.getWriter();  
                String loginPage = path+"/admin/main.jsp";  
                StringBuilder builder = new StringBuilder();  
                builder.append("<script type=\"text/javascript\">");  
                builder.append("window.top.location.href='");  
                builder.append(loginPage);  
                builder.append("';");  
                builder.append("</script>");  
                out.print(builder.toString());  
            } else {
                // 如果session中不存在登录者实体，则继续  
                filterChain.doFilter(request, response);  
            }
        } else {  
            // 如果不执行过滤，则继续  
            filterChain.doFilter(request, response);  
        }  
         
    }  
  
}  
