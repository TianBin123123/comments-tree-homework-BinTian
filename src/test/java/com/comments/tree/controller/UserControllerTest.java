package com.comments.tree.controller;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.comments.tree.api.IUserService;

/**
 * 测试 LoginController， RegisterController
 * 
 * @ClassName: UserControllerTest
 * @Description:
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午2:59:39 BinTian.
 * 
 * @version V1.0
 * @History: 2022年6月13日 上午2:59:39 BinTian Created.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	static final String username = "MockMvc";
	static final String email = "MockMvc@test.com";
	static final String pwd = "MockMvc123~";

	@InjectMocks
	private LoginController loginController;

	@InjectMocks
	private RegisterController registerController;

	@Mock
	private IUserService service;

	@Autowired
	private MockMvc mockMvc;
	
	/**
	 * 账号注册前验证
	 * @Title: checkAccount
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 下午1:27:15 BinTian Created.
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkAccount() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/register/checkAccount"))
		// 412 用户名为空
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.PRECONDITION_FAILED.value()))
		.andDo(MockMvcResultHandlers.print()).andReturn();
		
		// 验证通过
		mockMvc.perform(MockMvcRequestBuilders.post("/register/checkAccount").param("username", username)
				.param("email", email))
		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
	}
	
	/**
	 * 注册 ，注册完回滚
	 * @Title: register
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 下午1:27:27 BinTian Created.
	 * 
	 * @throws Exception
	 */
	@Test
	@Transactional
	public void register() throws Exception {
		
		/** setp 1  */
		mockMvc.perform(MockMvcRequestBuilders.post("/register").param("username", username).param("email", email)
				.param("password", pwd)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
		
		/** setp 2  验证账号是否已存在  */
		mockMvc.perform(MockMvcRequestBuilders.post("/register/checkAccount"))
		// 412 用户名为空
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.PRECONDITION_FAILED.value()))
		.andDo(MockMvcResultHandlers.print()).andReturn();
		
		/** setp 3  */
		mockMvc.perform(MockMvcRequestBuilders.post("/register/checkAccount")
				.param("username", username)
				.param("email", email))
		// 406 用户名已存在
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_ACCEPTABLE.value()))
		.andDo(MockMvcResultHandlers.print()).andReturn();
		
		/** setp 4  */
		mockMvc.perform(MockMvcRequestBuilders.post("/register/checkAccount")
				.param("username", username + "1")
				.param("email", email))
		// 406 邮箱已存在
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_ACCEPTABLE.value()))
		.andDo(MockMvcResultHandlers.print()).andReturn();
		
		/** setp 5  */
		// 重复注册
		mockMvc.perform(MockMvcRequestBuilders.post("/register").param("username", username).param("email", email)
				.param("password", pwd))
		// 406 用户名已存在
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_ACCEPTABLE.value()))
		.andDo(MockMvcResultHandlers.print()).andReturn();
	}
	

	/**
	 * 错误的密码进行登录
	 * @Title: loginErr
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 下午1:27:44 BinTian Created.
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginErr() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/login").param("username", "tb123").param("password", "xxx"))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNAUTHORIZED.value()))
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

	/**
	 * 根据用户名登录
	 * @Title: loginByName
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 下午1:28:01 BinTian Created.
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginByName() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/login").param("username", "tb123").param("password", "123"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
	}

	/**
	 * 根据邮箱登录
	 * @Title: loginByEmail
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 下午1:28:14 BinTian Created.
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginByEmail() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/login").param("username", "13861823302@163.com").param("password", "123"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
	}

	/**
	 * 记住我，查看cookie存活时间
	 * @Title: loginRememberMe
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 下午1:28:23 BinTian Created.
	 * 
	 * @throws Exception
	 */
	@Test
	public void loginRememberMe() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/login").param("username", "tb123").param("password", "123")
				.param("rememberMe", "true")).andExpect(MockMvcResultMatchers.status().isOk())
				// 过期时间为30天
				.andExpect(MockMvcResultMatchers.cookie().maxAge("JSESSIONID", (int) TimeUnit.DAYS.toSeconds(30)))
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

}
