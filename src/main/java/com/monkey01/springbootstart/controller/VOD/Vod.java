package com.monkey01.springbootstart.controller.VOD;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@RestController
/*以下为调用示例*/
public class Vod {
	
	public String  getPlayAuth(String strVid) throws JsonProcessingException
	{
		DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
	    GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
	    try {
	        response = getVideoPlayAuth(client,strVid);
	        //播放凭证
	        System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
	        //VideoMeta信息
	        System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
	    } catch (Exception e) {
	        System.out.print("ErrorMessage = " + e.getLocalizedMessage());
	    }
	    System.out.print("RequestId = " + response.getRequestId() + "\n");
	    PlayAuthResponse nPlayAuthResponse = new PlayAuthResponse();
	    nPlayAuthResponse.setPlayAuth(response.getPlayAuth());
	    ObjectMapper mapper = new ObjectMapper();    
        String json = mapper.writeValueAsString(nPlayAuthResponse);  
        System.out.println(json);
	    return json;
	}
	public String  getPlayURL(String strVid) throws JsonProcessingException
	{
		DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
		List<PlayURLResponse> PlayURLResponse = new ArrayList();
	    GetPlayInfoResponse response = new GetPlayInfoResponse();
	    try {
	        response = getPlayInfo(client,strVid);
	        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
	        //播放地址
	        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
	        	PlayURLResponse nPlayURLResponse = new PlayURLResponse();
	        	nPlayURLResponse.setPlayURL(playInfo.getPlayURL());
	        	PlayURLResponse.add(nPlayURLResponse);
	            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
	        }
	        //Base信息
	        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
	        
	    } catch (Exception e) {
	        System.out.print("ErrorMessage = " + e.getLocalizedMessage());
	    }
	    System.out.print("RequestId = " + response.getRequestId() + "\n");
	    ObjectMapper mapper = new ObjectMapper();    
        String json = mapper.writeValueAsString(PlayURLResponse);  
        System.out.println(json);
        return json;
	}
	
	private GetPlayInfoResponse getPlayInfo(DefaultAcsClient client,String strVid) throws Exception {
	    GetPlayInfoRequest request = new GetPlayInfoRequest();
	    request.setVideoId(strVid);
	    return client.getAcsResponse(request);
	}
	
	private  GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client,String strVid) throws Exception {
	    GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
	    request.setVideoId(strVid);
	    return client.getAcsResponse(request);
	}
	
	private DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
		// TODO Auto-generated method stub
		String regionId = "cn-shanghai";  // 点播服务接入区域
	    DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
	    DefaultAcsClient client = new DefaultAcsClient(profile);
	    return client;
	}
	@Value("${test.accessKeyId}")
	private String accessKeyId;
	
	@Value("${test.accessKeySecret}")
	private String accessKeySecret;
}