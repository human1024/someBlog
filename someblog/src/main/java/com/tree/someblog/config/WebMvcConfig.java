package com.tree.someblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author human1024
 * @Date 2020/6/17 11:17
 */
@Configuration
public class WebMvcConfig {

    @Bean
   public CharacterEncodingFilter characterEncodingFilter(){
       CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
       characterEncodingFilter.setEncoding("utf-8");
       characterEncodingFilter.setForceRequestEncoding(true);
       characterEncodingFilter.setForceResponseEncoding(true);
       return characterEncodingFilter;
   }
}
