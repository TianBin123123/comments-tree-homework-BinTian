package com.comments.tree.entity;

import java.io.Serializable;
import java.util.List;

/**
 * table: COMMENTS
 * @ClassName: Comments
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午1:18:42 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午1:18:42 BinTian Created.
 */
public class Comments implements Serializable {

	private static final long serialVersionUID = -7500100341633268077L;
	
	private int id; // 主键
	private int pId; // 父级评论id
	private String content; // 评论内容
	private String username; // 评论用户
	private String time; // 评论时间
	private List<Comments> subComments; // 下级评论
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<Comments> getSubComments() {
		return subComments;
	}
	public void setSubComments(List<Comments> subComments) {
		this.subComments = subComments;
	}
	@Override
	public String toString() {
		return "Comments [id=" + id + ", pId=" + pId + ", content=" + content + ", username=" + username + ", time="
				+ time + ", subComments=" + subComments + "]";
	}
	
}
