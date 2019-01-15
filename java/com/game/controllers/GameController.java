package com.game.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.army.TroopData;
import com.game.battle.BattleSequence;
import com.game.models.BattleUpdateProfiles;
import com.game.helpers.Helpers;
import com.game.models.CreateUserProfile;
import com.game.models.PurchaseTroops;
import com.game.models.ReadAllProfiles;
import com.game.models.ReadUserProfile;
import com.game.models.UserProfile;
import com.game.troops.Archers;
import com.game.troops.HeavySwords;
import com.game.troops.Spearmen;

@Controller
public class GameController {

	@GetMapping("*")
	public String home() {
		return "index";
	}
	
	@GetMapping("/get-all-users") 
	@ResponseBody
	public String getAllUsers() throws JsonProcessingException {
		
		List<UserProfile> allProfiles = new ReadAllProfiles().getUserProfiles();
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(allProfiles);
		
		return payload;
	}
	
	@PostMapping("/purchase-troops")
	@ResponseBody
	public String purchaseTroops(@RequestParam(value="username") String username, 
								 @RequestParam(value="spearmen") int spearmen,
								 @RequestParam(value="archers") int archers,
								 @RequestParam(value="heavySwords") int heavySwords) {
		
		Map<String, Integer> spearCosts = Spearmen.getCost();
		Map<String, Integer> archerCosts = Archers.getCost();
		Map<String, Integer> heavySwordCosts = HeavySwords.getCost();
		
		int totalSilverCost = (spearCosts.get("silver") * spearmen) + (archerCosts.get("silver") * archers) + (heavySwordCosts.get("silver") * heavySwords);
		int totalSteelCost = (spearCosts.get("steel") * spearmen) + (archerCosts.get("steel") * archers) + (heavySwordCosts.get("steel") * heavySwords);
		int totalWoodCost = (spearCosts.get("wood") * spearmen) + (archerCosts.get("wood") * archers) + (heavySwordCosts.get("wood") * heavySwords);
		
		Map<String, Integer> costMap = new HashMap<>();
		
		//number of new troops
		costMap.put("spearmen", spearmen);
		costMap.put("archers", archers);
		costMap.put("heavySwords", heavySwords);
		
		//cost of troops
		costMap.put("silverCost", totalSilverCost);
		costMap.put("steelCost", totalSteelCost);
		costMap.put("woodCost", totalWoodCost);
		

		new PurchaseTroops(username, costMap);
		return "Success";
	}
	
	@PostMapping("/make-battle")
	@ResponseBody
	public String makeBattle(@RequestParam(value="username") String username,
			                 @RequestParam(value="defender") String defender,
			                 @RequestParam(value="spearmen") int spearsSent,
			                 @RequestParam(value="archers") int archersSent,
			                 @RequestParam(value="heavySwords") int heavySwordsSent) throws IOException {

		//Read Both Profiles
		UserProfile attackingPlayerData = new ReadUserProfile(username).getUserProfile();
		UserProfile defendingPlayerData = new ReadUserProfile(defender).getUserProfile();
		
		//Check that player does not send more troops than available.
		if(spearsSent > attackingPlayerData.getSpearmen() ||
		   archersSent > attackingPlayerData.getArchers() || 
		   heavySwordsSent > attackingPlayerData.getHeavySwords()) {
			return "Not enough troops";
		}
		//Get Armies
		TroopData attackingArmy = new TroopData(username, 
												attackingPlayerData.getSpearmen(),
				                                attackingPlayerData.getArchers(), 
				                                attackingPlayerData.getHeavySwords());
		
		TroopData defendingArmy = new TroopData(defender, 
												defendingPlayerData.getSpearmen(),
												defendingPlayerData.getArchers(), 
								                defendingPlayerData.getHeavySwords());
		
		BattleSequence battle = new BattleSequence(attackingArmy, defendingArmy);
		new BattleUpdateProfiles(username,defender, battle.getAttacker(), battle.getDefender());
		return "Success";
	}
	
	@PostMapping("/create-profile")
	@ResponseBody
	public String createUser(HttpServletRequest request) throws IOException {
		String profileData = Helpers.convertJsonToString( request.getInputStream() );
		ObjectMapper objectMapper = new ObjectMapper();
		UserProfile newProfile = objectMapper.readValue(profileData, UserProfile.class);
		
		new CreateUserProfile(newProfile.getUsername(), 
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
		
		return "Profile Created";
	}
	
	@PostMapping("/login")
	@ResponseBody
	public String authenticateUser(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
		String loginData = Helpers.convertJsonToString( request.getInputStream() );
		ObjectMapper objectMapper = new ObjectMapper();
		UserProfile profile = objectMapper.readValue(loginData, UserProfile.class);
		
		ReadUserProfile authenticatedProfile = new ReadUserProfile(profile.getUsername());
		String payload = objectMapper.writeValueAsString(authenticatedProfile.getUserProfile());
		
		return payload;
	}
	
}
