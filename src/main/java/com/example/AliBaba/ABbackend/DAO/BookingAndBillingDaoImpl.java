package com.example.AliBaba.ABbackend.DAO;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetPayment;
import com.example.AliBaba.ABbackend.ORM.ORMGetReservations;
import com.example.AliBaba.ABbackend.ORM.ORMSavePayment;
import com.example.AliBaba.ABbackend.ORM.ORMSaveReservations;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Repos.PaymentRepo;
import com.example.AliBaba.ABbackend.Repos.ReservationRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class BookingAndBillingDaoImpl implements BookingAndBillingDao{

	@Autowired
	private ReservationRepo reservationRepo;
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@Autowired
	private EntityManagerFactory emFactory;
	
	
	private EntityManager getEntityManager(){
		return emFactory.createEntityManager();
	}
	
	
	

	@Override
	public ResponseStatus createReservation(ORMSaveReservations saveReservation) {
		ResponseStatus resp = new ResponseStatus();

		String serverDateTime = LocalDateTime.now().toString();
		try {
			saveReservation.setClient_date_created(serverDateTime);
			reservationRepo.save(saveReservation);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Reservation Saved Successfully...!!!");
		} catch (Exception e) {
			e.getMessage();
		}
		return resp;
	}

	@Override
	public ORMGetReservations findReservation(Long reservationId) {
		EntityManager manager = getEntityManager();
	    
	    try {
	    	StoredProcedureQuery sp = manager.createStoredProcedureQuery("spGetReservationsByReservationId", ORMGetReservations.class);
	        sp.registerStoredProcedureParameter("reservationId", Long.class, ParameterMode.IN);
	        sp.setParameter("reservationId", reservationId);
	        sp.execute();
	        
	        return (ORMGetReservations) sp.getSingleResult();
	        
	    }catch (Exception e) {
	        e.printStackTrace(); // or use a logger to log the error
	        throw new RuntimeException("Error Getting Reservations...!!!", e);
	    } 
	}

	@Override
	public ResponseStatus updateReservation(ORMSaveReservations saveReservation) {
		ResponseStatus resp = new ResponseStatus();
		Optional<ORMSaveReservations> hotelEntity = reservationRepo.findById(saveReservation.getReservation_id());
		String serverDateTime = LocalDateTime.now().toString();

		if(hotelEntity != null) {
			saveReservation.setClient_date_created(serverDateTime);
			reservationRepo.save(saveReservation);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Reservation Updated Successfully...!!!");
		}else {
			resp.setResult("0");
			resp.setStatus(false);
			resp.setResponse("Error Updating Reservation...!!!");
		}
		
		return resp;
	}

	@Override
	public ResponseStatus deleteReservationRecord(ORMDeleteRecord deleteRecord) {
		EntityManager manager = getEntityManager();
		ResponseStatus resp = new ResponseStatus();
		
		deleteRecord.setTable_name("reservations");
	    deleteRecord.setColumn_name("reservation_id");

	    try {
	        StoredProcedureQuery sp = manager.createStoredProcedureQuery("spDelRecord");
	        sp.registerStoredProcedureParameter("column_id", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("modified_user", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("client_date_time", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("system_ip", String.class, ParameterMode.IN);
	        
	        sp.setParameter("table_name", deleteRecord.getTable_name());
	        sp.setParameter("column_name", deleteRecord.getColumn_name());
	        sp.setParameter("column_id", deleteRecord.getColumn_id());
	        sp.setParameter("modified_user", deleteRecord.getModified_user());
	        sp.setParameter("client_date_time", deleteRecord.getClient_date_time());
	        sp.setParameter("system_ip", deleteRecord.getSystem_ip());
	        
	        sp.execute();
	        
	        resp.setResponse("Data Deleted Successfully...!!!");
	        resp.setStatus(true);
	        
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        resp.setResponse("Error Deleting Data...!!! " + e.getMessage());
	        resp.setStatus(false);
	        
	    }
	    return resp;
	}




	@Override
	public ResponseStatus createPayment(ORMSavePayment savePayment) {
		ResponseStatus resp = new ResponseStatus();

		String serverDateTime = LocalDateTime.now().toString();
		try {
			savePayment.setClient_date_created(serverDateTime);
			paymentRepo.save(savePayment);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Payment Saved Successfully...!!!");
		} catch (Exception e) {
			e.getMessage();
		}
		return resp;
	}




	@Override
	public ORMGetPayment findPayment(Long paymentId) {
		EntityManager manager = getEntityManager();
	    
	    try {
	    	StoredProcedureQuery sp = manager.createStoredProcedureQuery("spGetReservationsByReservationId", ORMGetPayment.class);
	        sp.registerStoredProcedureParameter("paymentId", Long.class, ParameterMode.IN);
	        sp.setParameter("paymentId", paymentId);
	        sp.execute();
	        
	        return (ORMGetPayment) sp.getSingleResult();
	        
	    }catch (Exception e) {
	        e.printStackTrace(); // or use a logger to log the error
	        throw new RuntimeException("Error Getting Payment...!!!", e);
	    } 
	}




	@Override
	public ResponseStatus updatePayment(ORMSavePayment savePayment) {
		ResponseStatus resp = new ResponseStatus();
		Optional<ORMSaveReservations> hotelEntity = reservationRepo.findById(savePayment.getPayment_id());
		String serverDateTime = LocalDateTime.now().toString();

		if(hotelEntity != null) {
			savePayment.setClient_date_created(serverDateTime);
			paymentRepo.save(savePayment);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Payment Updated Successfully...!!!");
		}else {
			resp.setResult("0");
			resp.setStatus(false);
			resp.setResponse("Error Updating Payment...!!!");
		}
		
		return resp;
	}




	@Override
	public ResponseStatus deletePaymentRecord(ORMDeleteRecord deleteRecord) {
		EntityManager manager = getEntityManager();
		ResponseStatus resp = new ResponseStatus();
		
		deleteRecord.setTable_name("payments");
	    deleteRecord.setColumn_name("payment_id");

	    try {
	        StoredProcedureQuery sp = manager.createStoredProcedureQuery("spDelRecord");
	        sp.registerStoredProcedureParameter("column_id", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("modified_user", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("client_date_time", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("system_ip", String.class, ParameterMode.IN);
	        
	        sp.setParameter("table_name", deleteRecord.getTable_name());
	        sp.setParameter("column_name", deleteRecord.getColumn_name());
	        sp.setParameter("column_id", deleteRecord.getColumn_id());
	        sp.setParameter("modified_user", deleteRecord.getModified_user());
	        sp.setParameter("client_date_time", deleteRecord.getClient_date_time());
	        sp.setParameter("system_ip", deleteRecord.getSystem_ip());
	        
	        sp.execute();
	        
	        resp.setResponse("Data Deleted Successfully...!!!");
	        resp.setStatus(true);
	        
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        resp.setResponse("Error Deleting Data...!!! " + e.getMessage());
	        resp.setStatus(false);
	        
	    }
	    return resp;
	}
	
	

}
