package com.zomato.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan("com.*")
public class MVCConfiguration extends WebMvcConfigurationSupport {

	
}
