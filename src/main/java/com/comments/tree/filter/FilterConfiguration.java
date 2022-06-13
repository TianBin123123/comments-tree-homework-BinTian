package com.comments.tree.filter;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册 Filter
 * 
 * @ClassName: FilterConfiguration
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午12:39:27 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午12:39:27 BinTian Created.
 */
@Configuration
public class FilterConfiguration {
	
	/**
	 * 注册 AuthFilter
	 * @Title: filterRegistrationBean
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:20:14 BinTian Created.
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean(){
		Filter filter = new AuthFilter();
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>(filter);
		filterRegistrationBean.addUrlPatterns("/comments");
		return filterRegistrationBean;
	}
	
	
}
