package com.comments.tree.entity;

import java.io.Serializable;

/**
 * table: USER
 * @ClassName: User
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午1:19:16 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午1:19:16 BinTian Created.
 */
public class User implements Serializable {
	private static final long serialVersionUID = -3597799894416215071L;
	
	private int id; // 主键，自增值
	private String username; // 用户名
	private String password; // 密码
	private String email; // 邮箱
	private boolean rememberMe; // 用户名
	
	public User() {
	}
	
	public User(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", rememberMe=" + rememberMe + "]";
	}
	
}
