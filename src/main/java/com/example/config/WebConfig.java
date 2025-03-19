package com.example.config;
import com.example.utils.CookieInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liwu
 * @version 1.0
 * @date 2024/11/22
 * @注释：拦截器配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CookieInterceptor cookieInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cookieInterceptor)
                .addPathPatterns("/**")            // 拦截所有请求
                .excludePathPatterns("/common/login", "/regularuser/enroll"); // 排除/common/login和/regularuser/enroll下的请求
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

}
