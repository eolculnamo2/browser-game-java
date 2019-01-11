package com.game.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.helpers.Helpers;

@Controller
public class GameController {

	@GetMapping("*")
	public String home() {
//		new BattleSequence();
		return "index";
	}
	
	@PostMapping("/purchase-troops")
	@ResponseBody
	public String purchaseTroops(HttpServletRequest request, Model model) throws IOException {
		String payload = Helpers.convertJsonToString( request.getInputStream() );
		
		System.out.println(payload);

//		ObjectMapper objectMapper = new ObjectMapper();
		
		return payload;
	}
	
}
