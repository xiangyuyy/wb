package com.example.demo.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@PropertySource(value = "classpath:/application.properties",encoding = "utf-8")
@Component
public class ConfigBeanValue {
 
    @Value("${good.sign}")
    public String goodSign;    	
    
    @Value("${showphoto.type}")
    public String showPhotoType;    	
}

