package com.jgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jgs")
public class ApplicationStarter {
    public static void main(String[] args) {
        /**
         * SpringApplication引导我们的应用程序，启动Spring，然后启动自动配置的Tomcat web服务器。
         * 我们将ApplicationStarter.class传递给run方法，告诉SpringApplication哪个才是重要的spring组件
         * args数组也被传入以公开任何命令行参数
         */
        SpringApplication.run(ApplicationStarter.class,args);
    }
}
