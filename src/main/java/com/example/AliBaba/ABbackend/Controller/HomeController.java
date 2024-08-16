package com.example.AliBaba.ABbackend.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Service.HomeService;

@RestController
@RequestMapping("/Home")
public class HomeController {

	@Autowired
	private HomeService homeService;
	
	@GetMapping("/admin")
	public String admin() {
		return "Admin is here";
	}
	
	
	@GetMapping("/current-user-login")
	public String getUser(Principal principal) {
		return principal.getName();
	}
	
	
	@PostMapping("/newUser")
	public @ResponseBody ResponseEntity<ResponseStatus> saveNewUser(@RequestBody ORMSaveUser saveUser){
		
		ResponseStatus resp = homeService.saveNewUser(saveUser);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@GetMapping("/findUser/{userId}")
	public @ResponseBody ResponseEntity<ORMGetUser> findUser(@PathVariable (value = "userId") Long userId){
		
		ORMGetUser user = homeService.findUser(userId);
		
		return new ResponseEntity<ORMGetUser>(user, HttpStatus.OK);
		
		
	}
	
	
	@PutMapping("/updateUser")
	public @ResponseBody ResponseEntity<ResponseStatus> updateUser(@RequestBody ORMSaveUser saveUser){
		ResponseStatus resp = homeService.updateUser(saveUser);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
}
