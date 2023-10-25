package com.acorn.exbatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class userController {

	@Autowired
	userRepository rep;
	
	@RequestMapping("/batisuserselectall" )
	public String  main(Model model ) {		
		List<userDTO> result;
		try {
			result = rep.selectAll();
			model.addAttribute("list", result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "selectall";
	}
	@GetMapping("/batisuserselect")
	public String selectAll( Model model) {
		userDTO result  = rep.select("1");
		model.addAttribute("user", result);
		System.out.println( result);
		return "select";
		}
	
}
