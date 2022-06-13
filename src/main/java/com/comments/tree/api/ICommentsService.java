package com.comments.tree.api;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.comments.tree.entity.Comments;

/**
 * 评论
 * @ClassName: ICommentsService
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午12:34:05 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午12:34:05 BinTian Created.
 */
public interface ICommentsService {
	
	/**
	 * 评论查询
	 * @Title: getCommentsTree
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:09:11 BinTian Created.
	 * 
	 * @return
	 */
	List<Comments> getCommentsTree();
	
	/**
	 * 评论新增
	 * @Title: addComments
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午1:09:16 BinTian Created.
	 * 
	 * @param comments
	 * @return
	 */
	ResponseEntity<Object> addComments(Comments comments);
	
}
