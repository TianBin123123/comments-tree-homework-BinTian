package com.comments.tree.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.comments.tree.entity.Comments;

/** table: COMMENTS  */
@Mapper
public interface ICommentsMapper {
	
	/**
	 * ORM直接查询完整的树形结构
	 * @Title: getCommentsTree
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月11日 下午4:48:46 BinTian Created.
	 * 
	 * @return
	 */
	List<Comments> getCommentsTree();
	
	/**
	 * 新增
	 * @Title: addComments
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 上午12:40:05 BinTian Created.
	 * 
	 * @param comments 新增的评论
	 * @return
	 */
	int addComments(Comments comments);
    
}
