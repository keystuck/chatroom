package com.example.chatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
public class WebSocketChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketChatApplication.class, args);
	}

	@RequestMapping("/")
	public ModelAndView login(){
		return new ModelAndView("login");
	}

	@RequestMapping("/chat")
	public ModelAndView chat(String username, HttpServletRequest request) throws UnknownHostException {
		return new ModelAndView("chat");
	}

}
