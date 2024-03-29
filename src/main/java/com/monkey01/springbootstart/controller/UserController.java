package com.monkey01.springbootstart.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.monkey01.springbootstart.controller.STS.Sts;
import com.monkey01.springbootstart.controller.VOD.Vod;


//@RestController注解能够使项目支持Rest
@RestController
@SpringBootApplication
//表示该controller类下所有的方法都公用的一级上下文根
@RequestMapping(value = "/AliServer")

public class UserController {
	@Autowired
    private Vod nVod;
	
	@Autowired
    private Sts nSts;
	
    //这里使用@RequestMapping注解表示该方法对应的二级上下文路径
    @RequestMapping(value = "/getUserByGet", method = RequestMethod.GET)
    String getUserByGet(@RequestParam(value = "userName") String userName){
        return "Hello " + userName;
    }

    //通过RequestMethod.POST表示请求需要时POST方式
    @RequestMapping(value = "/getUserByPost", method = RequestMethod.POST)
    String getUserByPost(@RequestParam(value = "userName") String userName){
        return "Hello " + userName;
    }

    //在入参设置@RequestBody注解表示接收整个报文体，这里主要用在接收整个POST请求中的json报文体，
    //目前主流的请求报文也都是JSON格式了，使用该注解就能够获取整个JSON报文体作为入参，使用JSON解析工具解析后获取具体参数
    @RequestMapping(value = "/getUserByJson",method = RequestMethod.POST)
    String getUserByJson(@RequestBody String data){
        return "Json is " + data;
    }
    
    @RequestMapping(value = "/getSTS", method = RequestMethod.GET)
    String getStsByGet() throws JsonProcessingException {
    	
    	return nSts.getSts();
    }
    @RequestMapping(value = "/getPlayAuth", method = RequestMethod.GET)
    String getPlayAuthByGet(@RequestParam(value = "Vid") String Vid) throws JsonProcessingException {
    	
    	return nVod.getPlayAuth(Vid);
    }
    @RequestMapping(value = "/getPlayURL", method = RequestMethod.GET)
    String getgetPlayUrlByGet(@RequestParam(value = "Vid") String Vid) throws JsonProcessingException {
    	
    	return nVod.getPlayURL(Vid);
    } 
    @RequestMapping(value = "/getUpLoadAuth", method = RequestMethod.GET)
    String getUpLoadAuthByGet() throws JsonProcessingException {
    	
    	Sts nSts = new Sts();
    	return nSts.getSts();
    }
    @RequestMapping(value = "/getVodCallBack",method = RequestMethod.POST)
    String getVodCallBackByJson(@RequestBody String data) throws UnsupportedEncodingException{
    	String keyWord = URLDecoder.decode(data, "GBK");
    	System.out.print(keyWord+"\n");
        return "Json is " + keyWord;
    }
}
