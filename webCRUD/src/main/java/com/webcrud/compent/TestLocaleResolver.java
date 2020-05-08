package com.webcrud.compent;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 国际化
 * 重写配置区域解析，为了在链接上带区域信息
 */
public class TestLocaleResolver implements org.springframework.web.servlet.LocaleResolver {

   @Override
   public Locale resolveLocale(HttpServletRequest httpServletRequest) {
       System.out.println("进入国际化。。。。");
        //获取我们自己传入的参数
        String parameter = httpServletRequest.getParameter("l");
        //如果没有传入，就返回默认的
        Locale locale  = Locale.getDefault();
        if(!StringUtils.isEmpty(parameter)){
            String[] split = parameter.split("_");
            locale= new Locale(split[0],split[1]);
        }
        return locale;
    }

   @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
