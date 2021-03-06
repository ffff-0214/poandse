package edu.qingtai.poandse.config;

import edu.qingtai.poandse.interceptor.VerifyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public VerifyInterceptor getVerifyInterceptor(){
        return new VerifyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getVerifyInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/seminar/content")
        .excludePathPatterns("/xdjobs/content");
    }


    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowedOrigins("*").allowCredentials(true).allowedMethods("*");
    }
}
