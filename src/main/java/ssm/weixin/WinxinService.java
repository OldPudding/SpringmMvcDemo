package ssm.weixin;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 業務類
 * @author lishch
 *
 */
@Service("wxservice")
public class WinxinService {
	/**
	 * 根据code获得openId
	 */
	public String getOpenId(String code, HttpServletRequest request){
		String path= request.getServletContext().getRealPath("");//获取项目动态绝对路径
		path += "/Info.json";
		JSONArray jsonObjects = readInfo(path);
		String appid = jsonObjects.getJSONObject(1).getString("appId");
		String secret = jsonObjects.getJSONObject(1).getString("appSecret");
		
	    if(code != null && code != ""){
	        String result = sendGet(
	        		"https://api.weixin.qq.com/sns/oauth2/access_token?appid="
	        +appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code", null);  
	       if(result != null && result != ""){  
	           JSONObject json = JSONObject.parseObject(result);  
	           if(json.get("openid")!=null){  
	                return json.get("openid").toString();  
	            }  
	        }  
	    }  
	    return "";  
	}
	

	/**
	 * 同时上传多条图文消息
	 * @param articles
	 * @param opendID
	 * @return 文图消息的jason字符串
	 */
	public String sendArticles(
			List<Article> articles,
			String[] opendIDs,
			HttpServletRequest request){
		// 如果参数错误则直接返回
		if(articles.isEmpty()){
			return null;
		}
		// 将articles类转化为jason字符串
		String jsonArray = JSONArray.toJSONString(articles);
		jsonArray = "{\"articles\":" + jsonArray +"}";
		
		// 调用微信接口【上传图文消息素材】
		String token = this.getWeixinAccessToken(request);
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="
				+ token;
		String result = this.sendPostJason(url, jsonArray);
		JSONObject jsonObj = JSONObject.parseObject(result);
		String mediaId = jsonObj.getString("media_id");
		System.out.println("图文消息素材上传之后，取得的media_id是："+ mediaId);
		
		/*// 根据分组群发图文消息
		url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="
				+token;
		String jasonPara = "{\"filter\":{\"is_to_all\":false,"
				+ "\"group_id\":\"2\"},"
				+ "\"mpnews\":{\"media_id\":"
				+ "\"" + mediaId
				+ "\"},\"msgtype\":\"mpnews\"}";*/
		
		// 根据用户OPENID群发图文消息
		url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="
				+token;
		
		String opendIDStr = "";
		for(int i = 0; i < opendIDs.length; i++){
			opendIDStr += "\"" + opendIDs[i] + "\",";
		}
		opendIDStr = opendIDStr.substring(0, opendIDStr.length() - 1);
		System.out.println("组装后的openID列表是："+ opendIDStr);
		
		String jasonPara = "{\"touser\":["
				+ opendIDStr +"],"
				+ "\"mpnews\":{\"media_id\":"
				+ "\"" + mediaId
				+ "\"},\"msgtype\":\"mpnews\"}";
		
		String finalResult = this.sendPostJason(url, jasonPara);
		System.out.println("最后上传结果为："+finalResult);
		return jsonArray;
	}
	
	/**
	 * 微信群發消息
	 * @return
	 */
	public String sendAll(HttpServletRequest request){
		String url;
		String jason;
		String result;
		url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=";
		// 獲取token標識
		String token = getWeixinAccessToken(request);
		url+= token;
		jason = "{\"filter\":{\"is_to_all\":false,\"group_id\":\"2\"},\"text\":{\"content\":\"What is this!!!\"},\"msgtype\":\"text\"}";
		System.out.println(jason);
		result = sendPostJason(url, jason);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 上传缩略图
	 * @param filepath
	 * @return 缩略图的thumb_media_id
	 */
	public String uploadThumb(String filepath, HttpServletRequest request) {
		// 获取token
		String token = this.getWeixinAccessToken(request);
		String url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="
				+ token+ "&type=thumb";
		String strTest = "";
		String mediaId = "";
		try {
			strTest = this.sendPostFile(url, filepath);
			JSONObject jsonObj = JSONObject.parseObject(strTest);
			mediaId = jsonObj.getString("thumb_media_id");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mediaId;
	}


	/**
	 * 上传图片
	 * @param filepath
	 * @return 上传图片之后的url
	 */
	public String photoUpload(String filepath, HttpServletRequest request) {
		// 获取token
		String token = this.getWeixinAccessToken(request);
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token="+token;
		String strTest = "";
		try {
			strTest = this.sendPostFile(url, filepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject jsonObj = JSONObject.parseObject(strTest);
		return jsonObj.getString("url");
	}
	
	/** 
	 * 获取微信公众号平台接口的ACCESS_TOKEN 
	 * @return 从内存或者微信服务器取到的token
	 */  
	private  String getWeixinAccessToken(HttpServletRequest request){  
	    WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
	    ServletContext application = webApplicationContext.getServletContext();
	    String token = "";
	    if(application.getAttribute("tokenMap")!=null){  
	        WeixinAccessToken tempToken=(WeixinAccessToken) application.getAttribute("tokenMap");  
	        if(System.currentTimeMillis() > tempToken.getExpirationTime()){  
	            // 從內存獲取
	        	token = tempToken.getAccessToken();
	        	System.out.println("【"+new Date().getTime() + "】从内存中取得的token是："
	        			+ token);
	        }else{  
	        	// 從微信服務器獲取
	        	token = getAccessTokenPrivate(request);  
	        	System.out.println("【"+new Date().getTime() + "】来自微信服务器的token是："
	        			+ token);
	        }  
	    }else{  
	    	// 從微信服務器獲取
	    	token = getAccessTokenPrivate(request);
	    	System.out.println("【"+new Date().getTime() + "】来自微信服务器的token是："
        			+ token);
	    }
	    return token;
	}

	/**
	 * 從微信服務器獲取
	 * @return
	 */
	private String getAccessTokenPrivate(HttpServletRequest request) {  
	    WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
	    ServletContext application = webApplicationContext.getServletContext();  
	    
		String path= request.getServletContext().getRealPath("");//获取项目动态绝对路径
		path += "\\WEB-INF\\classes\\ssm\\weixin\\Info.json";
		JSONArray jsonObjects = readInfo(path);
		String appid = jsonObjects.getJSONObject(1).getString("appId");
		String secret = jsonObjects.getJSONObject(1).getString("appSecret");
	    
	    String url="https://api.weixin.qq.com/cgi-bin/token";  
	    String returnData= sendGet(url,"grant_type=client_credential&appid="+appid+"&secret="+secret);  
	    JSONObject json=JSONObject.parseObject(returnData);  
	    if(json.containsKey("access_token")){  
	        if(json.get("access_token")!=null&&!json.get("access_token").equals("")){  
	            application.setAttribute("tokenMap", new WeixinAccessToken(json.get("access_token").toString(),  
	                    System.currentTimeMillis()+Integer.parseInt(json.get("expires_in").toString())));  
	            return json.get("access_token").toString();  
	        }  
	    }  
	    return null;  
	}

	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    private String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
        	String urlNameString = url;
        	if(param != null && param != ""){
        		urlNameString = url + "?" + param;
        	}
        	
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    private String sendPostJason(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
	/**
	 * 模拟form表单的形式 ，上传文件 以输出流的形式把文件写入到url中，然后用输入流来获取url的响应
	 * @param url
	 *            请求地址 form表单url地址
	 * @param filePath
	 *            文件在服务器保存路径
	 * @return 相应的结果JASON 字符串类型
	 * @throws IOException
	 */
    private String sendPostFile(String url, String filePath) throws IOException {
		String result = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		/**
		 * 第一部分
		 */
		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		/**
		 * 设置关键值
		 */
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		// 请求正文信息

		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}

		}

		return result;
	}
    
    /**
     * 读取json文件
     * @param 文件路径
     * @return json实体类数组
     */
    private JSONArray readInfo(String path){
        File file = new File(path);  
        BufferedReader reader = null;  
        String laststr = "";  
        try {  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            while ((tempString = reader.readLine()) != null) {  
                laststr = laststr + tempString;  
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
        
        JSONArray jsonObjects = JSONObject.parseArray(laststr); 
        return jsonObjects; 
    }
}
