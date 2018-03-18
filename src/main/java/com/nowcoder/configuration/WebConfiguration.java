package com.nowcoder.configuration;



import com.nowcoder.interceptor.LoginRequiredInterceptor;
import com.nowcoder.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Component
public class WebConfiguration extends WebMvcConfigurerAdapter{
	
	@Autowired
	PassportInterceptor passportInterceptor;
	
	/*@Autowired
	LoginRequiredInterceptor loginInterceptor;*/
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(passportInterceptor);//全局处理
		//registry.addInterceptor(passportInterceptor).addPathPatterns("/msg/*");//部分页面
		super.addInterceptors(registry);
	}
}
