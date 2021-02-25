package com.xyz.user.controller.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.xyz.user.UserApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = UserApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = { "classpath:application.properties" })
public class UserControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	private String baseUrl = "/user";

	/**
	 * Creating request path
	 */
	String loginUserRequestPath = "src/test/resources/loginUserRequest" + ".json";

	/**
	 * Creating Json object
	 */
	public static JSONObject loginUserRequestJson = null;

	@Before
	public void setup() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();

		loginUserRequestJson = new JSONObject(FileUtils.readFileToString(new File(loginUserRequestPath), "UTF-8"));
	}

	@Test
	public void testUserLogin() throws Exception {
		String uri = baseUrl + "/login";
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri);
		JSONObject jsonObject = loginUserRequestJson.getJSONObject("loginUserRequest");
		request.content(jsonObject.toString());

		MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertEquals("Response matching", 200, result.getResponse().getStatus());
	}

}
