package com.game.controllers;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.army.TroopData;
import com.game.battle.BattleSequence;
import com.game.helpers.Helpers;
import com.game.models.CreateUserProfile;
import com.game.models.UserProfile;

@Controller
public class GameController {

	@GetMapping("*")
	public String home() {
		return "index";
	}
	
	@PostMapping("/purchase-troops")
	@ResponseBody
	public String purchaseTroops(HttpServletRequest request) throws IOException {
		String payload = Helpers.convertJsonToString( request.getInputStream() );
		System.out.println(payload);
		return payload;
	}
	
	@PostMapping("/make-battle")
	@ResponseBody
	public String makeBattle(HttpServletRequest request) throws IOException {
		String troopData = Helpers.convertJsonToString( request.getInputStream() );
		System.out.println(troopData);
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		TroopData playerArmy = objectMapper.readValue(troopData, TroopData.class);
		TroopData defendingArmy = new TroopData(playerArmy.getPlayerToAttack(), 20, 50, 10);
		
		new BattleSequence(playerArmy, defendingArmy);
		return "index";
	}
	
	@PostMapping("/create-profile")
	public void createUser(HttpServletRequest request) throws IOException {
		String profileData = Helpers.convertJsonToString( request.getInputStream() );
		ObjectMapper objectMapper = new ObjectMapper();
		UserProfile newProfile = objectMapper.readValue(profileData, UserProfile.class);
		
		new CreateUserProfile(newProfile.getId(), 
				              newProfile.getUsername(), 
				              newProfile.getPassword(),
				              newProfile.getLastLogin(),
				              newProfile.getPowerRating(),
				              newProfile.getEmail(), 
				              newProfile.getSpearmen(),
				              newProfile.getArchers(),
				              newProfile.getHeavySwords(),
				              newProfile.getSilver(),
				              newProfile.getWood(),
				              newProfile.getSteel());
		
		System.out.println("Profile Created");
		

	}
	
}
