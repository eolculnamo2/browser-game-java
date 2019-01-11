package com.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.game.battle.BattleSequence;

@Controller
public class GameController {

	@GetMapping("*")
	public String home() {
//		new BattleSequence();
		return "index";
	}
}
