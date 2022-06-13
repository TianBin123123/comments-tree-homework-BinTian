package com.comments.tree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comments.tree.api.IUserService;
import com.comments.tree.entity.User;
import com.comments.tree.util.Constants;

/**
 * 注册
 * @ClassName: RegisterController
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午1:17:31 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午1:17:31 BinTian Created.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private IUserService userService;
	
	/**
	 * 注册新用户
	 * @Title: register
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:17:39 BinTian Created.
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	@ResponseBody
	public ResponseEntity<String> register(User user) throws Exception {
		try {
			return userService.register(user);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.INTERNAL_SERVER_ERROR_REGISTER;
		}
	}
	
	/**
	 * 检查账号邮箱，是否已存在
	 * @Title: checkAccount
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:17:50 BinTian Created.
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/checkAccount")
	@ResponseBody
	public ResponseEntity<String> checkAccount(User user) throws Exception {
		try {
			return userService.checkAccount(user);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.INTERNAL_SERVER_ERROR;
		}
	}

}
