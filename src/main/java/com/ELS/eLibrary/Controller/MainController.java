package com.ELS.eLibrary.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ELS.eLibrary.Model.Admin;
import com.ELS.eLibrary.Service.AdminService;
import com.ELS.eLibrary.util.constatnt.Role;


@Controller
public class MainController {

	@Autowired
	 private AdminService adminService;

	@Autowired
	 private PasswordEncoder passwordEncoder;

	
	@GetMapping("/")
	public String home(Model model)
	{
		return "index";
	}
	

	/*@GetMapping("/login")
	public String login()
	{
		return "index";
	}*/
	
	@GetMapping("/register")
	public String register(Model model)
	{
		Admin admin=new Admin();
		model.addAttribute("admiin",admin);
		return "register";
	}

	@PostMapping("/saveRegister")
	public String register(@ModelAttribute Admin admin)
	{
	//	admin.setPassword(passwordEncoder.encode(admin.setPassword());
		admin.setRole(Role.ADMIN);
	    admin.setPassword(passwordEncoder.encode("123"));
		adminService.save(admin);
		System.out.println("Saved");
		return "index";
	}
}
