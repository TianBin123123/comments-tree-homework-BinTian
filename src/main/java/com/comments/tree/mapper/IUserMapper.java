package com.comments.tree.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.comments.tree.entity.User;

/**
 * table: USER
 * @ClassName: IUserMapper
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午1:23:20 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午1:23:20 BinTian Created.
 */
@Mapper
public interface IUserMapper {
	
	/**
	 * 根据 username查询用户
	 * @Title: getUserByUsername
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:23:09 BinTian Created.
	 * 
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);
	
	/**
	 * 根据 email查询用户
	 * @Title: getUserByEmail
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:22:46 BinTian Created.
	 * 
	 * @param email
	 * @return
	 */
	User getUserByEmail(String email);
    
	/**
	 * 添加新用户
	 * @Title: addUser
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午12:40:57 BinTian Created.
	 * 
	 * @param user
	 * @return
	 */
    int addUser(User user);
    
}
