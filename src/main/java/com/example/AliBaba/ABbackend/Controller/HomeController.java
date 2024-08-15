package com.example.AliBaba.ABbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/newUser")
	public ResponseEntity<ResponseStatus> saveNewUser(@RequestBody ORMSaveUser saveUser){
		
		ResponseStatus resp = homeService.saveNewUser(saveUser);
		return new ResponseEntity<>(resp, HttpStatus.OK);
		
		
		
	
	}
}
