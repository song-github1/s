package com.song.PetShop.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesAction {
	
	@GetMapping("services")
	public String index(Model m){
		
		return "services";
	}
}
