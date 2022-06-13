package com.comments.tree.service;

import static com.comments.tree.util.Constants.*;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.comments.tree.api.IUserService;
import com.comments.tree.entity.User;
import com.comments.tree.mapper.IUserMapper;

/**
 * 用户相关
 * @ClassName: UserService
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午1:14:24 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午1:14:24 BinTian Created.
 */
@Service
public class UserService implements IUserService {

	@Autowired
	private IUserMapper userMapper;

	/**
	 * @see com.comments.tree.api.IUserService#checkAccount(com.comments.tree.entity.User)
	 */
	@Override
	public ResponseEntity<String> checkAccount(User user) {
		try {
			String username = user.getUsername();
			String email = user.getEmail();
			String usernameReg = USERNAME_REGEX;
			String emailReg = EMAIL_REGEX;
			if (username == null) {
				return EMPTY_USERNAME;
			}
			if (email == null) {
				return EMPTY_EMAIL;
			}
			if (!Pattern.matches(usernameReg, username)) {
				return USERNAME_FORMAT_ERROR;
			}
			if (email.length() > 256) {
				return EMAIL_LENGTH_ERROR;
			}
			if (!Pattern.matches(emailReg, email)) {
				return EMAIL_FORMAT_ERROR;
			}
			if (userMapper.getUserByUsername(username) != null) {
				return USERNAME_ALREADY_EXISTS;
			}
			if (userMapper.getUserByEmail(email) != null) {
				return EMAIL_ALREADY_EXISTS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return INTERNAL_SERVER_ERROR;
		}
		return SUCCESS;
	}

	/**
	 * @see com.comments.tree.api.IUserService#register(com.comments.tree.entity.User)
	 */
	@Override
	public ResponseEntity<String> register(User user) {
		// 1. 检查数据合法性
		ResponseEntity<String> checkRst = checkRegisterData(user);
		if (checkRst.getStatusCode().isError()) {
			return checkRst;
		}
		// 2. 保存数据
		try {
			String password = user.getPassword();
			String pwdMD5 = DigestUtils.md5DigestAsHex(password.getBytes());
			user.setPassword(pwdMD5);
			userMapper.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return INTERNAL_SERVER_ERROR;
		}
		return REGISTER_SUCCESS;
	}

	private ResponseEntity<String> checkRegisterData(User user) {
		// 1. 验证账号,邮箱
		ResponseEntity<String> checkRst = checkAccount(user);
		if (checkRst.getStatusCode().isError()) {
			return checkRst;
		}
		// 2. 验证密码格式
		String password = user.getPassword();
		if (password == null) {
			return EMPTY_PWD;
		}
		if (password.length() < 8 || password.length() > 20) {
			return PWD_LENGTH_ERROR;
		}
		if (!Pattern.matches(PASSWORD_REGEX, password)) {
			return PWD_FORMAT_ERROR;
		}
		return SUCCESS;
	}

	/**
	 * @Title: login 登录
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:15:41 BinTian Created.
	 * 
	 * @param user
	 * @return 登陆成功返回用户信息，登陆失败返回null
	 * @throws Exception
	 * @see com.comments.tree.api.IUserService#login(com.comments.tree.entity.User)
	 */
	@Override
	public User login(User user) throws Exception {
		// 用户输入的可能是用户名，也可能是邮箱
		String account = user.getUsername();
		// 账号不能包含特殊字符，所以包含@的一定是邮箱
		User foundUser = null;
		if (account.contains("@")) {
			foundUser = userMapper.getUserByEmail(account);
		} else {
			foundUser = userMapper.getUserByUsername(account);
		}
		// 验证密码
		String pwdMD5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		if (foundUser != null
				&& pwdMD5.equals(foundUser.getPassword())) {
			foundUser.setPassword(null); // 密码不返回前台
			return foundUser;
		}
		return null;
	}

}
