package com.comments.tree.controller;

import static com.comments.tree.util.Constants.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comments.tree.api.IUserService;
import com.comments.tree.entity.User;

/**
 * 
 * @ClassName: LoginController
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午12:35:01 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午12:35:01 BinTian Created.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserService userService;

	/**
	 * 获取当前登录用户，从session中取
	 * @Title: getLoginUser
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:05:31 BinTian Created.
	 * 
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<Object> getLoginUser(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			User user = (User)req.getSession().getAttribute("loginUser");
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return INTERNAL_SERVER_ERROR_OBJECT;
		}
	}
	
	/**
	 * 登录，登录用户存储在session中
	 * 用户名或邮箱 存储在 username 字段中
	 * @Title: login
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月11日 上午12:29:48 BinTian Created.
	 * 
	 * @param req
	 * @param res
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	@ResponseBody
	public ResponseEntity<Object> login(HttpServletRequest req, HttpServletResponse res, User user) throws Exception {
		try {
			// 1. 验证账号密码
			User loginUser = userService.login(user);
			if (loginUser == null) {
				return LOGIN_FAIL;
			}
			// 2. 保存登录信息
			HttpSession session = req.getSession(); // 创建session
			session.setAttribute("loginUser", loginUser);
			// 需要免登录的情况，手动设置JSESSIONID的过期时间
			if(user.isRememberMe()){
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				// 浏览器存储 cookie 30天(默认关闭浏览器后就销毁)
				cookie.setMaxAge(MONTH_TO_SECONDS);
				res.addCookie(cookie);
				// session 默认1800秒(30分钟)过期, 改为 30天
				session.setMaxInactiveInterval(MONTH_TO_SECONDS);
			}
			// 3. 登录成功
			return new ResponseEntity<>(loginUser, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return INTERNAL_SERVER_ERROR_LOGIN;
		}
	}

}
