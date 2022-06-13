package com.comments.tree.util;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 常量
 * @ClassName: Constants
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午12:47:58 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午12:47:58 BinTian Created.
 */
public interface Constants {

	SimpleDateFormat YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// 30天
	int MONTH_TO_SECONDS = (int)TimeUnit.DAYS.toSeconds(30);

	String USERNAME_REGEX = "^[0-9A-Za-z]{5,20}$";
	String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
	String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[%1$s])[\\da-zA-Z%1$s]{8,20}$";
	// 特殊字符目前支持： `~!@#$%^&*()_-+=<>?:"{}|,./;'\[]·
	String SPECIAL_CHARACTERS = "`~!@#$%^&*()_\\-+=<>?:\"{}|,./;'\\\\\\[\\]·";
	String PASSWORD_REGEX = String.format(PASSWORD_PATTERN, SPECIAL_CHARACTERS);
	
	/** 账号或密码错误！*/
	ResponseEntity<Object> LOGIN_FAIL = new ResponseEntity<>("账号或密码错误！", HttpStatus.UNAUTHORIZED);
	/** 邮箱已存在！ */
	ResponseEntity<String> EMAIL_ALREADY_EXISTS = new ResponseEntity<>("邮箱已存在！", HttpStatus.NOT_ACCEPTABLE);
	/** 用户名已存在！ */
	ResponseEntity<String> USERNAME_ALREADY_EXISTS = new ResponseEntity<>("用户名已存在！", HttpStatus.NOT_ACCEPTABLE);
	/** 邮箱格式不正确,请填写正确的邮箱 */
	ResponseEntity<String> EMAIL_FORMAT_ERROR = new ResponseEntity<>("邮箱格式不正确,请填写正确的邮箱", HttpStatus.PRECONDITION_FAILED);
	/** 邮箱长度不支持超过256位 */
	ResponseEntity<String> EMAIL_LENGTH_ERROR = new ResponseEntity<>("邮箱长度不支持超过256位", HttpStatus.PRECONDITION_FAILED);
	/** 用户名格式不正确,请使用5~20位字母或数字组合 */
	ResponseEntity<String> USERNAME_FORMAT_ERROR = new ResponseEntity<>("用户名格式不正确,请使用5~20位字母或数字组合", HttpStatus.PRECONDITION_FAILED);
	/** 请填写邮箱！ */
	ResponseEntity<String> EMPTY_EMAIL = new ResponseEntity<>("请填写邮箱！", HttpStatus.PRECONDITION_FAILED);
	/** 请填写用户名！ */
	ResponseEntity<String> EMPTY_USERNAME = new ResponseEntity<>("请填写用户名！", HttpStatus.PRECONDITION_FAILED);
	
	/** 密码必须包含大、小写字母、数字、特殊符号！ */
	ResponseEntity<String> PWD_FORMAT_ERROR = new ResponseEntity<>("密码必须包含大、小写字母、数字、特殊符号！", HttpStatus.PRECONDITION_FAILED);
	/** 密码长度为8~20位！ */
	ResponseEntity<String> PWD_LENGTH_ERROR = new ResponseEntity<>("密码长度为8~20位！", HttpStatus.PRECONDITION_FAILED);
	/** 请填写密码！ */
	ResponseEntity<String> EMPTY_PWD = new ResponseEntity<>("请填写密码！", HttpStatus.PRECONDITION_FAILED);
	/** 服务器内部错误 */
	ResponseEntity<String> INTERNAL_SERVER_ERROR = new ResponseEntity<>("服务器内部错误", HttpStatus.INTERNAL_SERVER_ERROR);
	/** 注册失败，服务器内部错误 */
	ResponseEntity<String> INTERNAL_SERVER_ERROR_REGISTER = new ResponseEntity<>("注册失败，服务器内部错误", HttpStatus.INTERNAL_SERVER_ERROR);
	/** 登录失败，服务器内部错误 */
	ResponseEntity<Object> INTERNAL_SERVER_ERROR_LOGIN = new ResponseEntity<>("登录失败，服务器内部错误", HttpStatus.INTERNAL_SERVER_ERROR);
	/** 服务器内部错误    */
	ResponseEntity<Object> INTERNAL_SERVER_ERROR_OBJECT = new ResponseEntity<>("服务器内部错误", HttpStatus.INTERNAL_SERVER_ERROR);
	
	/** 注册成功 */
	ResponseEntity<String> REGISTER_SUCCESS = new ResponseEntity<>("注册成功", HttpStatus.OK);
	/** 验证通过 */
	ResponseEntity<String> SUCCESS = new ResponseEntity<>("验证通过", HttpStatus.OK);
	
	/** 评论内容不能超过200个字    */
	ResponseEntity<Object> COMMENTS_TOO_LONG = new ResponseEntity<Object>("评论内容不能超过200个字", HttpStatus.PRECONDITION_FAILED);
	/** 评论内容不能少于3个字 */
	ResponseEntity<Object> COMMENTS_TOO_SHORT = new ResponseEntity<Object>("评论内容不能少于3个字", HttpStatus.PRECONDITION_FAILED);
	
}
