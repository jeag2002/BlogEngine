package es.symbioserver.controller.test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import es.symbioserver.beans.UserBean;
import es.symbioserver.controller.SymbioUserServerController;
import es.symbioserver.dao.engine.IUserCrudDaoEngine;
import es.symbioserver.service.IUserService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = SymbioUserServerController.class, secure = false)
public class SymbioUserServerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IUserCrudDaoEngine uCrudDaoEngine;
	
	@MockBean
	private IUserService uService;
	
	
	//--> GET /userService/getUserByUID/{UID}
	@org.junit.Test
	public void testGetUserByUID() throws Exception{
		
		UserBean uBean = new UserBean();
		uBean.setId(2);
		uBean.setName("user2");
		uBean.setPassword("user2");
		uBean.setRole("USER");
		uBean.setEmail("jeag2002@gmail.com");
		
		Mockito.when(uCrudDaoEngine.getUserByUID(Mockito.anyInt())).thenReturn(uBean);
		Mockito.when(uService.getUserByUID(Mockito.anyInt())).thenReturn(uBean);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/userService/getUserByUID/2").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"id\": 2, \"name\": \"user2\", \"password\": \"user2\", \"email\": \"jeag2002@gmail.com\", \"role\": \"USER\"}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}
