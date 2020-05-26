package com.company.springsecurity.task.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.company.springsecurity.task.model.UserModel;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	//To trim whitespaces
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		theModel.addAttribute("userModel", new UserModel());
		return "register-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("userModel") UserModel userModel, 
				BindingResult theBindingResult, 
				Model theModel) {
						
		String userName = userModel.getUserName();

		System.out.println("Processing form for the user "+userName);
		
		//Check for input errors
		if (theBindingResult.hasErrors()) {
			theModel.addAttribute("userModel", new UserModel());
			theModel.addAttribute("registrationError", "Username or password empty");
			return "register-form";	
		}
		
		//Check if user already exists
		boolean userExists = userDetailsManager.userExists(userName);
		
		if (userExists) {
			theModel.addAttribute("userModel", new UserModel());
			theModel.addAttribute("registrationError", "User already exists");
			return "register-form";			
		}

		//Encrypt and store
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        encodedPassword = "{bcrypt}" + encodedPassword;
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
        User tempUser = new User(userName, encodedPassword, authorities);
        userDetailsManager.createUser(tempUser);		
        System.out.println("New user: "+ userName);
        return "confirm";		
	}
	

}

