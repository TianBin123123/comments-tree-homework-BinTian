package com.comments.tree.api;

import org.springframework.http.ResponseEntity;

import com.comments.tree.entity.User;

/**
 * 用户
 * @ClassName: IUserService
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午12:34:14 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午12:34:14 BinTian Created.
 */
public interface IUserService {
	/**
	 * 验证账号和email是否已存在，是否符合格式
	 * @Title: checkAccount
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:10:24 BinTian Created.
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<String> checkAccount(User user) throws Exception;
	
	/**
	 * 注册新用户
	 * @Title: register
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:14:03 BinTian Created.
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<String> register(User user) throws Exception;
	
	/**
	 * @Title: login 登录
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:14:12 BinTian Created.
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	User login(User user) throws Exception;
	
}
