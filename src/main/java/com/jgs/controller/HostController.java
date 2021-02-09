package com.jgs.controller;

import com.alibaba.fastjson.JSONObject;
import com.jgs.uti.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@RestController
public class HostController {

    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;

    @RequestMapping(path = "/host", method = RequestMethod.GET)
    String home() {
        //答应请求头
        StringBuffer sb = new StringBuffer();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {// 判断是否还有下一个元素
            String key = headerNames.nextElement();// 获取headerNames集合中的请求头
            String value = request.getHeader(key);// 通过请求头得到请求内容
            sb.append(key + ":" + value);
            if(headerNames.hasMoreElements()){
                sb.append("\\r\\n");
            }

        }
//        return "hello" + WebUtils.getIP();
        return  sb.toString();
    }

    @RequestMapping(path = "/host/aa", method = RequestMethod.GET)
    String home_aa() {
        return "hello-aa" + WebUtils.getIP();
    }

}
  