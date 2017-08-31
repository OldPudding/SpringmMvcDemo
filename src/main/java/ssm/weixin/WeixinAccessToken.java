package ssm.weixin;

/**
 * 微信的AccessToken類
 * @author lishch
 *
 */
public class WeixinAccessToken {  
    private String accessToken;  
    private long expirationTime;  
      
    public WeixinAccessToken(){  
          
    }  
   public WeixinAccessToken(String accessToken,long expirationTime){  
      this.accessToken=accessToken;  
      this.expirationTime=expirationTime;  
    }  
  
      public String getAccessToken() {  
        return accessToken;  
    }  
    public void setAccessToken(String accessToken) {  
        this.accessToken = accessToken;  
    }  
    public long getExpirationTime() {  
        return expirationTime;  
    }  
    public void setExpirationTime(long expirationTime) {  
        this.expirationTime = expirationTime;  
    }    
}  
