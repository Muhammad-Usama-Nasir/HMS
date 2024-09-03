package com.example.AliBaba.ABbackend.Controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetHotel;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMHotel;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Service.ManagementService;

@RestController
@RequestMapping("/Home/management")
public class ManagementController {

	@Autowired
	private ManagementService managementService;
		
	
	//==============================================================================================
	//HOTEL ENTITY
	
	
	@PostMapping("/createHotel")
	public @ResponseBody ResponseEntity<ResponseStatus> createHotel(@RequestBody ORMHotel hotel){
		ResponseStatus resp = managementService.createHotel(hotel);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	@GetMapping("/getHotelinfo/{hotelId}")
	public @ResponseBody ResponseEntity<ORMGetHotel> getHotel(@PathVariable (value = "hotelId") Long hotelId){
		ORMGetHotel resp = managementService.getHotel(hotelId);
		return new ResponseEntity<ORMGetHotel>(resp, HttpStatus.OK);
	}
	
	
	@PutMapping("/updateHotel")
	public @ResponseBody ResponseEntity<ResponseStatus> updateHotel(@RequestBody ORMHotel hotel){
		ResponseStatus resp = managementService.updateHotel(hotel);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteRecord")
	public @ResponseBody ResponseEntity<ResponseStatus> deleteRecord(@RequestBody ORMDeleteRecord deleteRecord){
		ResponseStatus resp = managementService.deleteRecord(deleteRecord);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	
	//==============================================================================================
	// USER ENTITY
	
	
	@PostMapping("/createEmployee")
	public @ResponseBody ResponseEntity<ResponseStatus> createEmployee(@RequestBody ORMSaveUser employee){
		ResponseStatus resp = managementService.createEmployee(employee);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	@GetMapping("/getEmployee/{userId}")
	public @ResponseBody ResponseEntity<ORMGetUser> getEmployee(@PathVariable (value = "userId") Long userId){
		ORMGetUser resp = managementService.getEmployee(userId);
		return new ResponseEntity<ORMGetUser>(resp, HttpStatus.OK);
	}
	
	
	@PutMapping("/updateEmployee")
	public @ResponseBody ResponseEntity<ResponseStatus> updateEmployee(@RequestBody ORMSaveUser employee){
		ResponseStatus resp = managementService.updateEmployee(employee);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteEmployee")
	public @ResponseBody ResponseEntity<ResponseStatus> deleteEmployee(@RequestBody ORMDeleteRecord deleteRecord){
		ResponseStatus resp = managementService.deleteEmployee(deleteRecord);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	
	
	
}
