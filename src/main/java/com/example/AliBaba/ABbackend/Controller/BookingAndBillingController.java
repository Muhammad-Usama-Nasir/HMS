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
import com.example.AliBaba.ABbackend.ORM.ORMGetPayment;
import com.example.AliBaba.ABbackend.ORM.ORMGetReservations;
import com.example.AliBaba.ABbackend.ORM.ORMGetRoom;
import com.example.AliBaba.ABbackend.ORM.ORMSavePayment;
import com.example.AliBaba.ABbackend.ORM.ORMSaveReservations;
import com.example.AliBaba.ABbackend.ORM.ORMSaveRoom;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Service.BookingAndBillingService;

@RestController
@RequestMapping("/Home")
public class BookingAndBillingController {

	@Autowired
	private BookingAndBillingService bookingAndBilingService;

	//=======================================================================================================================
	// creating Reservations
	
	@PostMapping("/createReservation")
	public @ResponseBody ResponseEntity<ResponseStatus> createReservation(@RequestBody ORMSaveReservations saveReservation){
		ResponseStatus resp = bookingAndBilingService.createReservation(saveReservation);
		
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	@GetMapping("/findReservation/{reservationId}")
	public @ResponseBody ResponseEntity<ORMGetReservations> findReservation(@PathVariable (value = "reservationId") Long reservationId){
		ORMGetReservations resp = bookingAndBilingService.findReservation(reservationId);
		return new ResponseEntity<ORMGetReservations>(resp, HttpStatus.OK);
	}
	
	
	@PutMapping("/updateReservation")
	public @ResponseBody ResponseEntity<ResponseStatus> updateReservation(@RequestBody ORMSaveReservations saveReservation){
		ResponseStatus resp = bookingAndBilingService.updateReservation(saveReservation);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteReservationRecord")
	public @ResponseBody ResponseEntity<ResponseStatus> deleteReservationRecord(@RequestBody ORMDeleteRecord deleteRecord){
		ResponseStatus resp = bookingAndBilingService.deleteReservationRecord(deleteRecord);
		return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
	}
	
	
	
	
	
	//=======================================================================================================================
		// creating Payments
		
		@PostMapping("/createPayment")
		public @ResponseBody ResponseEntity<ResponseStatus> createPayment(@RequestBody ORMSavePayment savePayment){
			ResponseStatus resp = bookingAndBilingService.createPayment(savePayment);
			
			return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
		}
		
		
		@GetMapping("/findPayment/{paymentId}")
		public @ResponseBody ResponseEntity<ORMGetPayment> findPayment(@PathVariable (value = "paymentId") Long paymentId){
			ORMGetPayment resp = bookingAndBilingService.findPayment(paymentId);
			return new ResponseEntity<ORMGetPayment>(resp, HttpStatus.OK);
		}
		
		
		@PutMapping("/updatePayment")
		public @ResponseBody ResponseEntity<ResponseStatus> updatePayment(@RequestBody ORMSavePayment savePayment){
			ResponseStatus resp = bookingAndBilingService.updatePayment(savePayment);
			return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
		}
		
		
		@DeleteMapping("/deletePaymentRecord")
		public @ResponseBody ResponseEntity<ResponseStatus> deletePaymentRecord(@RequestBody ORMDeleteRecord deleteRecord){
			ResponseStatus resp = bookingAndBilingService.deletePaymentRecord(deleteRecord);
			return new ResponseEntity<ResponseStatus>(resp, HttpStatus.OK);
		}
}
