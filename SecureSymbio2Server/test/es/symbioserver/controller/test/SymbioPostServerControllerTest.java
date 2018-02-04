package es.symbioserver.controller.test;

import java.util.ArrayList;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import es.symbioserver.beans.PostsBean;
import es.symbioserver.beans.UserBean;
import es.symbioserver.controller.SymbioPostServerController;
import es.symbioserver.dao.engine.IPostsCrudDaoEngine;
import es.symbioserver.service.IPostService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = SymbioPostServerController.class, secure = false)

public class SymbioPostServerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IPostsCrudDaoEngine pCrudDaoEngine;
	
	@MockBean
	private IPostService pService;
	
	
	//--> GET /postService/getPostById/{id}
	@org.junit.Test
	public void testGetUserByUID() throws Exception{
			
		
			PostsBean bean = new PostsBean();
			bean.setId(2);
			bean.setTitle("test");
			bean.setContent("this is a test");
			bean.setUserid(1);
			bean.setUsername("user1");
			bean.setDate("1978-01-01 01:01:01");
			
			ArrayList<PostsBean> arrayBean = new ArrayList<PostsBean>();
			arrayBean.add(bean);
			
			Mockito.when(pCrudDaoEngine.getPostsById(Mockito.anyInt())).thenReturn(arrayBean);
			Mockito.when(pService.getPostById(Mockito.anyInt())).thenReturn(arrayBean);

			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/postService/getPostById/2").accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			String expected = "[{\"id\": 2, \"title\": \"test\", \"content\": \"this is a test\", \"userid\": 1, \"username\": \"user1\", \"date\": \"1978-01-01 01:01:01\"}]";		
					
			JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	
	
	

}
