package com.ya.spring.mvc.conf;


import com.ya.spring.mvc.interceptor.DemoInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @Description  静态资源的配置
 * @Author ROCIA
 * @Date 2020/8/24
 */
@EnableWebMvc //开启对spring mvc 的支持，如不存在此句，该配置无效
@Configuration  //声明配置类
@ComponentScan("com.ya.spring.mvc") //作用范围
public class MvcConfig implements WebMvcConfigurer {


    //============================静态资源===============================
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler：对外暴露的访问路径 ；addResourceLocations：文件放置目录
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }


    //==============================拦截器================================
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }



    //==============================视图解析器==============================
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//      真实页面的路径前缀
        viewResolver.setPrefix("/WEB-INF/classes/views/");
//      页面的后缀
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 处理页面跳转。
     * 优点：更好的集中管理，更简洁
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");

    }


}
