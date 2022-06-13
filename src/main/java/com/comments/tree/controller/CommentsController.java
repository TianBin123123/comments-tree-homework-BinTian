package com.comments.tree.controller;

import static com.comments.tree.util.Constants.INTERNAL_SERVER_ERROR_OBJECT;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comments.tree.api.ICommentsService;
import com.comments.tree.entity.Comments;

/**
 * 评论
 * @ClassName: CommentsController
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午12:33:41 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午12:33:41 BinTian Created.
 */
@Controller
@RequestMapping("/comments")
public class CommentsController {
	
	@Autowired
	private ICommentsService commentsService;
	
	/**
	 * 获取评论树
	 * @Title: getCommentsTree
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午12:34:38 BinTian Created.
	 * 
	 * @return
	 */
	@GetMapping
	@ResponseBody
	public List<Comments> getCommentsTree() {
		try {
			return commentsService.getCommentsTree();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	/**
	 * 新增评论
	 * @Title: addComments
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午12:34:48 BinTian Created.
	 * 
	 * @param comments
	 * @return
	 */
	@PostMapping
	@ResponseBody
	public ResponseEntity<Object> addComments(Comments comments) {
		try {
			return commentsService.addComments(comments);
		} catch (Exception e) {
			e.printStackTrace();
			return INTERNAL_SERVER_ERROR_OBJECT;
		}
	}
	
}
