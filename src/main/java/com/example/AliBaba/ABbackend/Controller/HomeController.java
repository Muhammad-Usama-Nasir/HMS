package com.example.AliBaba.ABbackend.Controller;

import java.security.Principal;
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

import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetGuest;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMSaveGuest;
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
	
	
	//=======================================================================================================================
	// creating Guests
	
	
	@PostMapping("/createNewGuest")
	public @ResponseBody ResponseEntity<ResponseStatus> createNewGuest(@RequestBody ORMSaveGuest saveGuest){
		
		ResponseStatus resp = homeService.createNewGuest(saveGuest);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	@GetMapping("/findGuest/{guestId}")
	public @ResponseBody ResponseEntity<ORMGetGuest> findGuest(@PathVariable (value = "guestId") Long guestId){
		
		ORMGetGuest resp = homeService.findGuest(guestId);
		return new ResponseEntity<ORMGetGuest>(resp, HttpStatus.OK);
	}
	
	
	@PutMapping("/updateGuest")
	public ResponseEntity<ResponseStatus> updateGuest(@RequestBody ORMSaveGuest saveGuest){
		
		ResponseStatus resp = homeService.updateGuest(saveGuest);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	
	
	
}
