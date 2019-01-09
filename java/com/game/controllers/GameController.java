package com.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.game.battle.BattleSequenceBase;

@Controller
public class GameController {

	@GetMapping("/")
	public String home() {
		new BattleSequenceBase();
		return "index";
	}
}
