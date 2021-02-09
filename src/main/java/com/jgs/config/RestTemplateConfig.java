package com.jgs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

@Configuration
public class RestTemplateConfig {
    String host = "47.103.199.109";
    int port = 8000;

    @Bean
    public RestTemplate restTemplate() {
        //初始化代理
        SocketAddress address = new InetSocketAddress(host, port);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);

        //初始化客户端http请求工厂
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(10000);
        httpRequestFactory.setConnectTimeout(10000);
        httpRequestFactory.setProxy(proxy);
        return new RestTemplate(httpRequestFactory);
    }
}
