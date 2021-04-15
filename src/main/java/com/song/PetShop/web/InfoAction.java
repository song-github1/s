package com.song.PetShop.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoAction {
	
	@GetMapping("person")
	public String person(Model m){
		
		return "person";
	}
	
	@GetMapping("information")
	public String information(Model m){
		
		return "information";
	}
	
	@GetMapping("safety")
	public String safety(Model m){
		
		return "safety";
	}
	
	@GetMapping("address")
	public String address(Model m){
		
		return "address";
	}
	
	@GetMapping("order")
	public String order(Model m){
		
		return "order";
	}
	
	@GetMapping("orderinfo")
	public String orderinfo(Model m){
		
		return "orderinfo";
	}
	
	@GetMapping("logistics")
	public String logistics(Model m){
		
		return "logistics";
	}
	
	@GetMapping("collection")
	public String collection(Model m){
		
		return "collection";
	}
	
	@GetMapping("foot")
	public String foot(Model m){
		
		return "foot";
	}
	
	@GetMapping("comment")
	public String comment(Model m){
		
		return "comment";
	}
}
