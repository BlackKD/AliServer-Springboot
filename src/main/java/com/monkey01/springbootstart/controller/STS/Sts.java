package com.monkey01.springbootstart.controller.STS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
public class Sts {
	@Value("${test.accessKeyId}")
	private String accessKeyId;
	
	@Value("${test.accessKeySecret}")
	private String accessKeySecret;
	
	@Value("${test.roleArn}")
	private String roleArn;
 public String getSts() throws JsonProcessingException
 {
	String endpoint = "sts.aliyuncs.com";
 	String roleSessionName = "session-name";
 	        try {
 	            // 构造default profile（参数留空，无需添加region ID）
 	            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
 	            // 用profile构造client
 	            DefaultAcsClient client = new DefaultAcsClient(profile);
 	            final AssumeRoleRequest request = new AssumeRoleRequest();
 	            request.setSysEndpoint(endpoint);
 	            request.setSysMethod(MethodType.POST);
 	            request.setRoleArn(roleArn);
 	            request.setRoleSessionName(roleSessionName);
 	            final AssumeRoleResponse response = client.getAcsResponse(request);
 	            System.out.println("Expiration: " + response.getCredentials().getExpiration());
 	            System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
 	            System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
 	            System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
 	            System.out.println("RequestId: " + response.getRequestId());
 	            StsResponse nUser = new StsResponse();
 	            nUser.setAccessKeyId(response.getCredentials().getAccessKeyId());
 	            nUser.setAccessKeySecret(response.getCredentials().getAccessKeySecret());
 	            nUser.setSecurityToken(response.getCredentials().getSecurityToken());
 	            
 	            ObjectMapper mapper = new ObjectMapper();    
 	            String json = mapper.writeValueAsString(nUser);  
 	            System.out.println(json);
 	            return json;
 	        } catch (ClientException e) {
 	            System.out.println("Failed：");
 	            System.out.println("Error code: " + e.getErrCode());
 	            System.out.println("Error message: " + e.getErrMsg());
 	            System.out.println("RequestId: " + e.getRequestId());
 	            
 	            return e.getErrMsg();
 	        }
 }
}


