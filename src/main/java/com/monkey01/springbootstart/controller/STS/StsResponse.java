package com.monkey01.springbootstart.controller.STS;

public class StsResponse {
 
    public void setAccessKeyId(String accessKeyId) {  
        this.accessKeyId = accessKeyId;  
    }
    public void setAccessKeySecret(String accessKeySecret) {  
        this.accessKeySecret = accessKeySecret;  
    } 
    public void setSecurityToken(String SecurityToken) {  
        this.SecurityToken = SecurityToken;  
    }
    public String getAccessKeyId() {  
          return accessKeyId;
    }
    public String getAccessKeySecret() {  
        return accessKeySecret;
  }
    public String getSecurityToken() {  
        return SecurityToken;
  }
       
	private String accessKeyId;
    private String accessKeySecret;
    private String SecurityToken;
}


