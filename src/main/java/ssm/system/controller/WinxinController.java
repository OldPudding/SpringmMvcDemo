package ssm.system.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.SystemOutLogger;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.scene.control.skin.TitledPaneSkin;
import com.sun.org.apache.bcel.internal.util.ClassPath;

import ssm.weixin.*;

@Controller
@RequestMapping("/")
public class WinxinController {
	@Resource
	private WinxinService wxservice;
	
	
	/**
	 * 发布
	 * @param content
	 * @return
	 */
	@RequestMapping("/send")
	public @ResponseBody String send(HttpServletRequest request, String content){
		// 对content内容进行解析
		List<String> imgpaths = new ArrayList<String>();
		int index1 = content.indexOf("src=\"",0);
		int index2 = 0;
		while (index1 > -1) {
			index2 = content.indexOf("\"", index1 + 5);
			imgpaths.add(content.substring(index1 + 5,index2));
			index1  = content.indexOf("src=\"",index2); 
		}
		
		// 对提取出来的图片进行上传并保存url
		List<String>  imgUrls= new ArrayList<String>();
		for (String path : imgpaths) {
			String directory = request.getServletContext().getRealPath("") + path.replace('/', '\\');
			imgUrls.add(wxservice.photoUpload(directory, request));
		}
		
		// 用新的url替换旧的路径
		for (int i = 0; i < imgpaths.size(); i++) {
			content = content.replace(imgpaths.get(i), imgUrls.get(i));
		}
		
		// 初始化多条图文消息,把新的conent内容设置到文章列表中
		String mediaId = wxservice.uploadThumb("E:\\加好.png",request); // 上传缩略图片
		List<Article> articles = new ArrayList<Article>();
		for(int i = 0; i < 1; i++){
			Article article = new Article();
			article.setThumb_media_id(mediaId);
			article.setAuthor("大哥大");
			article.setTitle("韦斯特布鲁克爆砍逆天三双");
			article.setContent(content);
			article.setContent_source_url("http://www.qq.com");
			article.setDigest("描述" + i);
			/*// 只有第一条图文消息才显示封面
			if(i == 0){
				article.setShow_cover_pic("1");
			}*/
			articles.add(article);
		}
		
		// 调用业务类，发送图文消息
		String[] openIDs = new String[2];
		openIDs[0] = "ogZac1DowVnBvCcG6AE-P_g_6TzM";
		openIDs[1] = "ogZac1FOCvjUSzJtNW0ZdiWNChIo";
		String jason = wxservice.sendArticles(articles, openIDs, request);
		
		return jason;
	}
	
	/**
	 * 获取用户openId
	 * @param req
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/getOpenId")
	public void getOpenId(HttpServletRequest req, HttpServletResponse response) throws IOException{
	    String code = req.getParameter("code");
	    String openID = wxservice.getOpenId(code, req);
	    
	    // 打印openID至后台
	    System.out.println("获取到的openID是："+openID);
	    // 获取到的openID是：ogZac1DowVnBvCcG6AE-P_g_6TzM
	    
	    // 打印opendID至页面
	    response.setHeader("Content-Type", "text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
	    PrintWriter out = response.getWriter();
		out.print("获取到的openID是："+openID);
		out.flush();
		out.close();
	}
	/**
	 * 微信认证函数
	 * @param req
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/wxconnect")  
	public @ResponseBody String weixinConnect(HttpServletRequest req, HttpServletResponse response) throws Exception {    
		Signature sg = new Signature(
				req.getParameter("signature"),
				req.getParameter("timestamp"),
				req.getParameter("nonce"),
				req.getParameter("echostr"));
		String method = req.getMethod();
		// 如果是微信发过来的GET请求
		if("GET".equals(method)){
			if(CheckUtil.checkSignature(sg)){
				System.out.println("微信连接成功！");
				return sg.getEchostr();
			}
		}
		
		return "";  
	}  
	
	/**
	 * 获取唯一调用api权限token，此方法为私有方法，不允许外部调用，因此从controller中剔除
	 * @param req
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getToken")
	@ResponseBody
	public String getToken(HttpServletRequest req, HttpServletResponse response) throws Exception {    
		/*String token = wxservice.getWeixinAccessToken();
		System.out.println("獲取到的token是："+token);
		return token;*/
		return null;
	}
	
	/**
	 * 微信群发接口（文本消息）
	 * @param req
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sendAll")
	@ResponseBody
	public String sendAll(HttpServletRequest req, HttpServletResponse response) throws Exception {    
		return wxservice.sendAll(req);
	}
	
	/**
	 * 微信上传图片接口
	 * @param media
	 * @param filepath
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value="/uploadImg", method=RequestMethod.POST)
	@ResponseBody
	public String photoUpload(
			@RequestParam MultipartFile[] media,
			@RequestParam String filepath,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws IllegalStateException, IOException{
		String strTest = wxservice.photoUpload(filepath,request);
		System.out.println(strTest);
		return strTest;
	}
	
	@RequestMapping(value="/sendArticles", method=RequestMethod.POST)
	@ResponseBody
	public String sendArticles(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws IllegalStateException, IOException{
		String[] titles = new String[3];
		String[] authors = new String[3];
		String[] imgs = new String[3];
		
		titles[0]  = request.getParameter("title1");
		titles[1]  = request.getParameter("title2");
		titles[2]  = request.getParameter("title3");
		
		authors[0] = request.getParameter("author1");
		authors[1] = request.getParameter("author2");
		authors[2] = request.getParameter("author3");
		
		imgs[0]    = request.getParameter("image1");
		imgs[1]    = request.getParameter("image2");
		imgs[2]    = request.getParameter("image3");
		
		// 上传缩略图片
		String mediaId1 = wxservice.uploadThumb(imgs[0],request);
		System.out.println("得到的缩略图thumb_media_id是："+mediaId1);
		String picUrl1 = "http://mmbiz.qpic.cn/mmbiz_png/C4gh3tp0t1Zoxfqfxicrrxo3nrRkTl3"
				+ "t7L11EemrBx4GwkXQfS9766ibdrFicLzOdZYI7Vs45jxB9tiagHMtFz6eKA/0";
		/*String mediaId2 = "http://mmbiz.qpic.cn/mmbiz_jpg/C4gh3tp0t1Zoxfqfxicrrxo3nrRkTl3"
				+ "t73iaibzbuAsLmKbajbaeStA1pgPmFJ3NZvArzBR3pYsD1bT9NNrOrOpFQ/0";*/

		// 初始化多条图文消息
		List<Article> articles = new ArrayList<Article>();
		for(int i = 0; i < 3; i++){
			Article article = new Article();
			article.setThumb_media_id(mediaId1);
			article.setAuthor(authors[i]);
			article.setTitle(titles[i]);
			switch (i) {
			case 0:
				article.setContent("<section id='wrap_node' style='border: dashed 1px"
						+ " #797979; padding: 5px;'>     <section id='shifu_p_024' donone"
						+ "='shifuMouseDownPic(&#39;shifu_p_024&#39;)' label='Copyright "
						+ "Reserved by PLAYHUDONG.' style='background:#ffffff;"
						+ "border-style: none; clear: both;margin: 1em auto;'>         "
						+ "<section style='padding-right: 10px; background-color: "
						+ "#ffffff !important; margin-right: 3px; width: 26%; "
						+ "float:left;'>             "
						+ "<img style='width: 100%;' "
						+ "src='http://7xo6kd.com1.z0.glb.clouddn.com/"
						+ "upload-ueditor-image-20170404-1491298133501049254.jpg'/>         "
						+ "</section>         <section style=' font-size: 1.3em; padding: "
						+ "5px 8px;  border-bottom: solid 1px #000000;'>             "
						+ "三毛/《说给自己听》         </section>         <section style=' padding: "
						+ "10px 10px 0; line-height: 1.5; font-size: 14px;'>             "
						+ "<p style='display: inline;'>                 "
						+ "如果有来生，要做一棵树，站成永恒，没有悲欢的姿势。一半在尘土里安详，一半在风里飞扬，一半洒落阴凉，"
						+ "一半沐浴阳光。”如果有来生，要做一只鸟，飞越永恒，没有迷途的苦恼。东方有火红的希望，南方有温暖的巢床，"
						+ "向西逐退残阳，向北唤醒芬芳。<br/>             </p>         </section>     "
						+ "</section> </section> <p class='shifubrush'>     <br/> </p>");
				break;
			case 1:
				article.setContent("<section id='wrap_node' style='border: dashed 1px "
						+ "#797979; padding: 5px;'>    <section id='shifu_p_023' "
						+ "donone='shifuMouseDownPic(&#39;shifu_p_023&#39;)' "
						+ "label='Copyright Reserved by PLAYHUDONG.' "
						+ "style='background:#ffffff;border-style: none; "
						+ "clear: both;margin: 1em auto;'>        "
						+ "<section style='padding:0.5em;background: "
						+ "#fff;vertical-align: bottom;-webkit-box-shadow: "
						+ "0px 0px 4px rgba(0, 0, 0, 0.5);-moz-box-shadow: "
						+ "0px 0px 4px rgba(0, 0, 0, 0.5);-o-box-shadow: "
						+ "0px 0px 4px rgba(0, 0, 0, 0.5);box-shadow: "
						+ "0px 0px 4px rgba(0, 0, 0, 0.5);width: 49%;margin-left: "
						+ "1%;box-sizing: border-box;display: inline-block;'>            "
						+ "<img style='height: auto;vertical-align: middle;width: "
						+ "320px;display: inline-block;' "
						+ "src='"
						+ picUrl1
						+ "'/>"
						+ "        </section>        <section style='display: inline-block;"
						+ "vertical-align: bottom;padding: 10px 0 0 8px;line-height: 1.5;"
						+ "font-size: 14px;text-align: left;width: 50%;box-sizing: "
						+ "border-box;color: #000000;'>            "
						+ "<section style='font-size: 20px'>                "
						+ "<section style='font-size: 18px; display: inline-block;' "
						+ "class='color'>                    "
						+ "◀六画                </section>            </section>            "
						+ "<p style='margin: 0;'>                "
						+ "远看山有色，<br/>近听水无声。<br/>春去花还在，<br/>人来尿不尽。            </p>            "
						+ "<p style='margin-top: 0px; margin-bottom: 0px;'>                "
						+ "<br/>            </p>        </section>    </section></section>"
						+ "<p class='shifubrush'>    <br/></p><p class='shifubrush'>    "
						+ "<br/></p>");
				break;
			default:
				article.setContent("<section label='Copyright © 2016 playhudong All "
						+ "Rights Reserved.' style='border:none;border-style:none;"
						+ "width: 94%;margin:1em auto;color:#000;' id='shifu_spri_001' "
						+ "donone='shifuMouseDownPayStyle(&#39;shifu_spri_001&#39;)'> "
						+ "<section style='width:100%;height:auto;overflow:hidden;"
						+ "padding:1.5em 1.5em;box-sizing:border-box;box-shadow:0px "
						+ "0px 10px #666'> <section style='width:100%;'> "
						+ "<img src='http://1251001145.cdn.myqcloud.com/1251001145/"
						+ "stylenew/viptemplet/spri_001.png' style='width:100%;"
						+ "border:1px solid #ddd;'/> </section> <section "
						+ "style='width:100%;margin:0 auto;margin-top:2em;box-sizing:"
						+ "border-box;'> <section class='wihudong' "
						+ "style=' width: 100%; font-size: 3em;font-family: "
						+ "arial; color: #A6CE4D; line-height: 1em; font-weight: "
						+ "bolder; '> “ </section> <section style='text-align:center;"
						+ "font-size:1em;margin-top:-1.5em;line-height:2em;"
						+ "color:#6B6C6E;'> <p style='margin:0;'> 盼望着，<br/>盼望着，"
						+ "<br/>东风来了，<br/>春天的脚步近了。 </p> </section> <section "
						+ "class='wihudong' style=' width: 100%; font-size: 3em;"
						+ "font-family: arial; color: #A6CE4D; text-align:right;"
						+ "line-height: 1em; font-weight: bolder; '> ” </section> "
						+ "<section style='width:100%;font-size:1em;line-height:1.4em;"
						+ "text-align:right;color:#A9A9A9;'> <p style='margin:0'> "
						+ "---费玉清《春》 </p> </section> </section> </section></section>"
						+ "<p class='shifubrush'> <br/></p><p class='shifubrush'> "
						+ "<br/></p>");
				break;
			}
			
			article.setContent_source_url("http://www.qq.com");
			article.setDigest("描述" + i);
			
			// 只有第一条图文消息才显示封面
			if(i == 0){
				article.setShow_cover_pic("1");
			}
			articles.add(article);
		}
		
		// 调用业务类，发送图文消息
		String[] openIDs = new String[2];
		openIDs[0] = "ogZac1DowVnBvCcG6AE-P_g_6TzM";
		openIDs[1] = "ogZac1FOCvjUSzJtNW0ZdiWNChIo";
		String jason = wxservice.sendArticles(articles, openIDs, request);
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jason);
		out.flush();
		out.close();
		return null;
	}
}
