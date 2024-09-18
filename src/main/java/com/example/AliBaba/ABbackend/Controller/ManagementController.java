package com.example.AliBaba.ABbackend.Controller;

import java.util.List;

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
import com.example.AliBaba.ABbackend.ORM.ORMGetService;
import com.example.AliBaba.ABbackend.ORM.ORMGetServiceRequest;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMHotel;
import com.example.AliBaba.ABbackend.ORM.ORMSaveService;
import com.example.AliBaba.ABbackend.ORM.ORMSaveServiceRequest;
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
	
	
	@GetMapping("/getEmployeesByHotelId/{hotelId}")
	public @ResponseBody ResponseEntity<List<ORMGetUser>> getEmployeesByHotel(@PathVariable (value = "hotelId") Long hotelId){
		List<ORMGetUser> resp = managementService.getEmployeesByHotel(hotelId);
		return new ResponseEntity<List<ORMGetUser>>(resp, HttpStatus.OK);
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
	
	
	
	
	//==============================================================================================
		// SERVICES
		
		
		@PostMapping("/createService")
		public @ResponseBody ResponseEntity<ResponseStatus> createService(@RequestBody ORMSaveService saveService){
			ResponseStatus resp = managementService.createService(saveService);
			return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
		}
		
		
		@GetMapping("/getService/{serviceId}")
		public @ResponseBody ResponseEntity<ORMGetService> getService(@PathVariable (value = "serviceId") Long serviceId){
			ORMGetService resp = managementService.getService(serviceId);
			return new ResponseEntity<ORMGetService>(resp, HttpStatus.OK);
		}
		
		
		@GetMapping("/getServicesByHotelId/{hotelId}")
		public @ResponseBody ResponseEntity<List<ORMGetService>> getServicesByHotelId(@PathVariable (value = "hotelId") Long hotelId){
			List<ORMGetService> resp = managementService.getServicesByHotelId(hotelId);
			return new ResponseEntity<List<ORMGetService>>(resp, HttpStatus.OK);
		}
		
		
		@PutMapping("/updateService")
		public @ResponseBody ResponseEntity<ResponseStatus> updateService(@RequestBody ORMSaveService saveService){
			ResponseStatus resp = managementService.updateService(saveService);
			return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
		}
		
		
		@DeleteMapping("/deleteServiceRecord")
		public @ResponseBody ResponseEntity<ResponseStatus> deleteServiceRecord(@RequestBody ORMDeleteRecord deleteRecord){
			ResponseStatus resp = managementService.deleteServiceRecord(deleteRecord);
			return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
		}
	
		
		
		//==============================================================================================
		// SERVICES REQUESTS
	
		@PostMapping("/createServiceRequest")
		public @ResponseBody ResponseEntity<ResponseStatus> createServiceRequest(@RequestBody ORMSaveServiceRequest saveServiceRequest){
			ResponseStatus resp = managementService.createServiceRequest(saveServiceRequest);
			return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
		}
		
		
		@GetMapping("/getServiceRequest/{serviceRequestId}")
		public @ResponseBody ResponseEntity<ORMGetServiceRequest> getServiceRequest(@PathVariable (value = "serviceRequestId") Long serviceRequestId){
			ORMGetServiceRequest resp = managementService.getServiceRequest(serviceRequestId);
			return new ResponseEntity<ORMGetServiceRequest>(resp, HttpStatus.OK);
		}
		
		
		@GetMapping("/getServiceRequestsByHotelId/{hotelId}")
		public @ResponseBody ResponseEntity<List<ORMGetServiceRequest>> getServiceRequestsByHotelId(@PathVariable (value = "hotelId") Long hotelId){
			List<ORMGetServiceRequest> resp = managementService.getServiceRequestsByHotelId(hotelId);
			return new ResponseEntity<List<ORMGetServiceRequest>>(resp, HttpStatus.OK);
		}
		
		
		@PutMapping("/updateServiceRequest")
		public @ResponseBody ResponseEntity<ResponseStatus> updateServiceRequest(@RequestBody ORMSaveServiceRequest saveServiceRequest){
			ResponseStatus resp = managementService.updateServiceRequest(saveServiceRequest);
			return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
		}
		
		
		@DeleteMapping("/deleteServiceRequestRecord")
		public @ResponseBody ResponseEntity<ResponseStatus> deleteServiceRequestRecord(@RequestBody ORMDeleteRecord deleteRecord){
			ResponseStatus resp = managementService.deleteServiceRequestRecord(deleteRecord);
			return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
		}
}
