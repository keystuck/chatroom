package com.example.chatroom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(WebSocketChatApplication.class)
public class ChatroomApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testLoginAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
		.get("/"))
				.andExpect(status().isOk())
		.andExpect(view().name("/login"));
	}

	@Test
	public void getChatFromLoginAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/chat?username=testuser"))
				.andExpect(status().isOk())
				.andExpect(view().name("/chat"));
	}


}
