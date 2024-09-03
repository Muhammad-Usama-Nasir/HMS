package com.example.AliBaba.ABbackend.Controller;

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
import com.example.AliBaba.ABbackend.ORM.ORMGetRoom;
import com.example.AliBaba.ABbackend.ORM.ORMHotel;
import com.example.AliBaba.ABbackend.ORM.ORMSaveRoom;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Service.HotelResourcesService;

@RestController
@RequestMapping("/Home/Hotel-Resources")
public class HotelResourcesController {

	
	@Autowired
	private HotelResourcesService hotelResourcesService;
	
	
	
	//=======================================================================================================================
	// creating Rooms
	
	@PostMapping("/createRoom")
	public @ResponseBody ResponseEntity<ResponseStatus> createRoom(@RequestBody ORMSaveRoom saveRoom){
		ResponseStatus resp = hotelResourcesService.createRoom(saveRoom);
		
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	@GetMapping("/findRoom/{roomId}")
	public @ResponseBody ResponseEntity<ORMGetRoom> findRoom(@PathVariable (value = "roomId") Long roomId){
		ORMGetRoom resp = hotelResourcesService.findRoom(roomId);
		return new ResponseEntity<ORMGetRoom>(resp, HttpStatus.OK);
	}
	
	
	@PutMapping("/updateRoom")
	public @ResponseBody ResponseEntity<ResponseStatus> updateRoom(@RequestBody ORMSaveRoom saveRoom){
		ResponseStatus resp = hotelResourcesService.updateRoom(saveRoom);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteRecord")
	public @ResponseBody ResponseEntity<ResponseStatus> deleteRecord(@RequestBody ORMDeleteRecord deleteRecord){
		ResponseStatus resp = hotelResourcesService.deleteRecord(deleteRecord);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	
	//=======================================================================================================================
	// creating Guests
	
	
	
	
}
