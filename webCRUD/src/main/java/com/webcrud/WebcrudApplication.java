package com.webcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;


//将项目的启动类Application.java继承SpringBootServletInitializer并重写configure方法(为了使用外部tomcat)
@SpringBootApplication
public class WebcrudApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(this.getClass());
        return super.configure(builder);
    }
    public static void main(String[] args) {
        SpringApplication.run(WebcrudApplication.class, args);
        System.out.println("WebcrudApplication 项目启动成功！");
    }

}
