package com.game.dto;

import com.game.models.UserProfile;
import com.game.troops.Archers;
import com.game.troops.HeavySwords;
import com.game.troops.Spearmen;

public class GameDTO {
	
	private UserProfile user;
	private Archers archerInfo;
	private Spearmen spearmenInfo;
	private HeavySwords heavySwordsInfo;
	
	public GameDTO(UserProfile user) {
		this.user = user;
		this.archerInfo = new Archers();
		this.spearmenInfo = new Spearmen();
		this.heavySwordsInfo = new HeavySwords();
	}
	
	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	public Archers getArcherInfo() {
		return archerInfo;
	}

	public void setArcherInfo(Archers archerInfo) {
		this.archerInfo = archerInfo;
	}

	public Spearmen getSpearmenInfo() {
		return spearmenInfo;
	}

	public void setSpearmenInfo(Spearmen spearmenInfo) {
		this.spearmenInfo = spearmenInfo;
	}

	public HeavySwords getHeavySwordsInfo() {
		return heavySwordsInfo;
	}

	public void setHeavySwordsInfo(HeavySwords heavySwordsInfo) {
		this.heavySwordsInfo = heavySwordsInfo;
	}

}
