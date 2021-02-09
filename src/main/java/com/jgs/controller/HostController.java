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

    public static void main(String[] args) {
        String host = "47.103.199.109";
        int port = 8000;

        String url = "http://47.103.199.109/host";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map bodyMap = new HashMap();
        bodyMap.put("merchantId","NYG88");
        bodyMap.put("currentTime","1608262656500");
        bodyMap.put("beginTime","2020-12-18 11:49:00");
        bodyMap.put("sign","f3b19b12450fa73269e37302a77e955a");
        HttpEntity<Object> httpEntity = new HttpEntity<>(bodyMap, headers);


        //初始化代理
        SocketAddress address = new InetSocketAddress(host, port);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);

        //初始化客户端http请求工厂
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(10000);
        httpRequestFactory.setConnectTimeout(10000);
        httpRequestFactory.setProxy(proxy);
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println(responseEntity.getBody());
    }
}
  