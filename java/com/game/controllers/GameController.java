package com.game.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
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
import com.game.buildings.Building;
import com.game.buildings.LoggingCamps;
import com.game.buildings.SteelFoundry;
import com.game.buildings.TaxableEconomy;
import com.game.helpers.Helpers;
import com.game.models.BattleUpdateProfiles;
import com.game.models.CreateUserProfile;
import com.game.models.PurchaseTroops;
import com.game.models.ReadAllProfiles;
import com.game.models.ReadUserProfile;
import com.game.models.UpgradeBuilding;
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
	
	@GetMapping("/username")
	@ResponseBody
	public String currentUserName(Authentication authentication) {
		return authentication.getName();
	}
	
//	@GetMapping("/username")
//    @ResponseBody
//    public String currentUserName(Authentication authentication) {
//        return authentication.getName();
//    }
	
	@GetMapping("/get-all-users") 
	@ResponseBody
	public String getAllUsers() throws JsonProcessingException {
		
		List<UserProfile> allProfiles = new ReadAllProfiles().getUserProfiles();
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = objectMapper.writeValueAsString(allProfiles);
		
		return payload;
	}
	
	@GetMapping("/get-building-info")
	@ResponseBody
	public String getBuildingInfo(Authentication authentication) throws JSONException, JsonProcessingException {
		
		
		UserProfile user = new ReadUserProfile(authentication.getName()).getUserProfile();
		
		
		Building loggingCamp = new LoggingCamps(user.getWoodLevel());
		Building steelFoundry = new SteelFoundry(user.getSteelLevel());
		Building taxableEconomy = new TaxableEconomy(user.getSilverLevel());
		
		JSONObject payload = new JSONObject();
		
		payload.put("woodLevel", loggingCamp.getLevel());
		payload.put("steelLevel", steelFoundry.getLevel());
		payload.put("silverLevel", taxableEconomy.getLevel());
		
		payload.put("woodProduction", loggingCamp.getProduction());
		payload.put("steelProduction", steelFoundry.getProduction());
		payload.put("silverProduction", taxableEconomy.getProduction());
		
		payload.put("woodUpgradeCost", loggingCamp.getUpgradeCost());
		payload.put("steelUpgradeCost", steelFoundry.getUpgradeCost());
		payload.put("silverupgradeCost", taxableEconomy.getUpgradeCost());
		
		return payload.toString();
	}
	
	@GetMapping("/get-user-data")
	@ResponseBody
	public String getUserData(Authentication authentication) throws JsonProcessingException {
		UserProfile user = new ReadUserProfile(authentication.getName()).getUserProfile();
		ObjectMapper obj = new ObjectMapper();
		return obj.writeValueAsString(user);
	}
	
	@PostMapping("/upgrade-building")
	@ResponseBody
	public String upgradeBuilting(Authentication authentication, @RequestParam(value="building") String building) throws JSONException {
		
		UpgradeBuilding upgrade = new UpgradeBuilding(authentication.getName(), building);
		
		JSONObject json = new JSONObject();
		json.put("canAfford", upgrade.getCanAfford());
		
		return json.toString();
	}
	
	@PostMapping("/purchase-troops")
	@ResponseBody
	public String purchaseTroops(Authentication authentication, 
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
		
		new PurchaseTroops(authentication.getName(), costMap);
		return "Success";
	}
	
	@PostMapping("/make-battle")
	@ResponseBody
	public String makeBattle(Authentication authentication,
			                 @RequestParam(value="defender") String defender,
			                 @RequestParam(value="spearmen") int spearsSent,
			                 @RequestParam(value="archers") int archersSent,
			                 @RequestParam(value="heavySwords") int heavySwordsSent) throws IOException {

		//Read Both Profiles
		UserProfile attackingPlayerData = new ReadUserProfile(authentication.getName()).getUserProfile();
		UserProfile defendingPlayerData = new ReadUserProfile(defender).getUserProfile();
		
		//Check that player does not send more troops than available.
		if(spearsSent > attackingPlayerData.getSpearmen() ||
		   archersSent > attackingPlayerData.getArchers() || 
		   heavySwordsSent > attackingPlayerData.getHeavySwords()) {
			return "Not enough troops";
		}
		//Get Armies
		TroopData attackingArmy = new TroopData(authentication.getName(), 
												attackingPlayerData.getSpearmen(),
				                                attackingPlayerData.getArchers(), 
				                                attackingPlayerData.getHeavySwords());
		
		TroopData defendingArmy = new TroopData(defender, 
												defendingPlayerData.getSpearmen(),
												defendingPlayerData.getArchers(), 
								                defendingPlayerData.getHeavySwords());
		
		BattleSequence battle = new BattleSequence(attackingArmy, defendingArmy);
		new BattleUpdateProfiles(authentication.getName(),defender, battle.getAttacker(), battle.getDefender());
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
