package com.webcrud.config;

import com.webcrud.compent.TestInterceptor;
import com.webcrud.compent.TestLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MyMVCConfig implements WebMvcConfigurer {
//不要继承WebMvcConfigurerAdapter废弃了，最好不要使用了

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送 /，/login.html最后都会到login
        registry.addViewController( "/").setViewName("login");
        registry.addViewController( "/login.html").setViewName("login");
        registry.addViewController( "/main.html").setViewName("dashboard");

    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //super.addResourceHandlers(registry);
//        //解决插件样式导入不了的问题
//        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
//
//    }

    //注册我们自己写的国际化设置
   @Bean//必须的注入到自己的配置文件中才能生效
    public org.springframework.web.servlet.LocaleResolver localeResolver(){
        return  new TestLocaleResolver();
    }

    //注册拦截器的
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //springboot2.0之前 静态资源 .css .js .springboot 已经做好了静态资源的映射
      //2.0之后会拦截静态资源需要实现WebMvcConfigurer接口，addPathPatterns("**")里面不能用/**
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("**")
              .excludePathPatterns("/","/login.html","/user/login","/static/**","/webjars/**");  //配置了这里，hellocontrol里面的请求都会拦截掉
    }
}