package com.comments.tree.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comments.tree.entity.User;

/**
 * 检查是否登录，目前只有发表评论需要登录
 * @ClassName: AuthFilter
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月9日 下午11:03:18 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月9日 下午11:03:18 BinTian Created.
 */
public class AuthFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// GET请求查看评论不用登录
		if("POST".equals(req.getMethod())){
			User loginUser = (User)req.getSession().getAttribute("loginUser");
			if(loginUser == null){
				res.getOutputStream().write("尚未登录，或登录已失效，请先登录！".getBytes());
				res.setStatus(401);
				return;
			}
		}
		chain.doFilter(request, response);
	}

}
