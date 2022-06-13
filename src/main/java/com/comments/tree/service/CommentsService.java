package com.comments.tree.service;

import static com.comments.tree.util.Constants.*;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.comments.tree.api.ICommentsService;
import com.comments.tree.entity.Comments;
import com.comments.tree.mapper.ICommentsMapper;

/**
 * 评论
 * @ClassName: CommentsService
 * @Description:  
 * @author: BinTian
 * @Create Date: 2022年6月13日 上午1:25:06 BinTian.
 *   
 * @version V1.0
 * @History: 2022年6月13日 上午1:25:06 BinTian Created.
 */
@Service
public class CommentsService implements ICommentsService {
	

	@Autowired
	private ICommentsMapper commentsMapper;
	
	/**
	 * 获取评论树
	 * @Title: getCommentsTree
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午12:49:32 BinTian Created.
	 * 
	 * @return
	 * @see com.comments.tree.api.ICommentsService#getCommentsTree()
	 */
	@Override
	public List<Comments> getCommentsTree() {
		return commentsMapper.getCommentsTree();
	}
	
	/**
	 * 成功返回 Comments，失败返回错误信息
	 * @Title: addComments
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月12日 下午1:20:55 BinTian Created.
	 * 
	 * @param comments
	 * @return
	 * @see com.comments.tree.api.ICommentsService#addComments(com.comments.tree.entity.Comments)
	 */
	@Override
	public ResponseEntity<Object> addComments(Comments comments) {
		// 1. 检查
		String content = comments.getContent();
		if(content == null || content.length() < 3){
			return COMMENTS_TOO_SHORT;
		}
		if(content.length() > 200){
			return COMMENTS_TOO_LONG;
		}
		/**
		 *  2. 保存，并且把评论的时间和主键加上，返回前端，和树节点的数据内容保持一致
		 *  主键值可以通过 useGeneratedKeys 取到，时间需要查询后才能取，所以这边直接设置时间，减少一次查询
		 */
		comments.setTime(YMDHMS.format(new Date()));
		commentsMapper.addComments(comments);
		
		return new ResponseEntity<Object>(comments, HttpStatus.OK);
	}
	

}
