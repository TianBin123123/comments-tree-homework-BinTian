package com.comments.tree.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.comments.tree.api.ICommentsService;
import com.comments.tree.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentsControllerTest {

	@InjectMocks
	private CommentsController controller;

	@Mock
	private ICommentsService service;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * 查询所有评论
	 * @Title: getCommentsTree
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 下午1:28:53 BinTian Created.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void getCommentsTree() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/comments").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
	}
	
	/**
	 * 未登录时不允许发表评论
	 * @Title: addCommentsNoLogin
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 下午1:28:59 BinTian Created.
	 * 
	 * @throws Exception
	 */
	@Test
	public void addCommentsNoLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/comments"))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNAUTHORIZED.value()))
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}

	/**
	 * 登录后，正常发表评论
	 * @Title: addComments
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 下午1:29:17 BinTian Created.
	 * 
	 * @throws Exception
	 */
	@Test
	public void addComments() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/comments").sessionAttr("loginUser", new User())
				.param("pId", "10")
				.param("username", "mockMvc")
				.param("content", "mockMvc addComments")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print()).andReturn();
	}
	
	/**
	 * 评论字数不能少于3
	 * @Title: addCommentsLess3
	 * @Description:
	 * @Author: BinTian
	 * @Create Date: 2022年6月13日 下午1:29:39 BinTian Created.
	 * 
	 * @throws Exception
	 */
	@Test
	public void addCommentsLess3() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/comments").sessionAttr("loginUser", new User())
				.param("pId", "10")
				.param("username", "mockMvc")
				.param("content", "1")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.PRECONDITION_FAILED.value()))
		.andDo(MockMvcResultHandlers.print()).andReturn();
	}

}
