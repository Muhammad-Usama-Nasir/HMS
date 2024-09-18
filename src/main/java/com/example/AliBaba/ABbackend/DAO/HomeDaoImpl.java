package com.example.AliBaba.ABbackend.DAO;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetGuest;
import com.example.AliBaba.ABbackend.ORM.ORMSaveGuest;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Repos.GuestRepo;
import com.example.AliBaba.ABbackend.Repos.RoleRepository;
import com.example.AliBaba.ABbackend.Repos.UserRepo;
import com.example.AliBaba.ABbackend.Service.EmailService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class HomeDaoImpl implements HomeDao {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private GuestRepo guestRepo;
	
	@Autowired
	private EntityManagerFactory emFactory;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

	
	public String generateVerificationCode() {
		return UUID.randomUUID().toString();
	}
	
	
	public ORMSaveUser findByVerificationCode(String code) {
		return userRepo.findByVerificationCode(code);
	}
	
	
	

	@Override
	public ResponseStatus createAdmin(ORMSaveUser saveUser) {
	    ResponseStatus resp = new ResponseStatus();
	    
	    String serverDateTime = LocalDateTime.now().toString();
	    String verificationCode = generateVerificationCode();
	    try {
	        saveUser.setClient_date_modified(serverDateTime);
	        saveUser.setPassword(passwordEncoder.encode(saveUser.getPassword()));
	        saveUser.setVerificationCode(verificationCode);
	        saveUser.setIsVerified(false);
	        userRepo.save(saveUser);
	        emailService.sendConfirmationMail(saveUser.getEmail(), verificationCode);
	        resp.setResponse("A verification code has been sent to your email. Please verify your account.");
	        return resp;
	    } catch (Exception e) {
	        e.printStackTrace();
	        resp.setResponse("Error saving Login Info...");
	        resp.setStatus(false);
	        return resp;
	    }
	}


	
	
	
	
	
	@Override
	public ResponseStatus verifyAccount(String code) {
		ORMSaveUser saveUser = userRepo.findByVerificationCode(code);
		ResponseStatus resp = new ResponseStatus();
		
		if(saveUser == null) {
			resp.setResult("0");
			resp.setStatus(false);
			resp.setResponse("Invalid verification code!");
			return resp;
		}
		
		saveUser.setIsVerified(true);
		saveUser.setVerificationCode(null);
		userRepo.save(saveUser);
		resp.setResult("1");
		resp.setStatus(true);
		resp.setResponse("Your account has been successfully verified!");
		
		return resp;
	}

	
	
	
	
	
	//=======================================================================================================================
	// creating Guests
	
	
	@Override
	public ResponseStatus createNewGuest(ORMSaveGuest saveGuest) {
		// TODO Auto-generated method stub
		ResponseStatus resp = new ResponseStatus();
		
		String serverDateTime = LocalDateTime.now().toString();
		try {
			saveGuest.setClient_date_modified(serverDateTime);
			guestRepo.save(saveGuest);
			resp.setResponse("Guest Saved Sucessfully..!!!");
			resp.setStatus(true);
			resp.setResult("1");
			return resp;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Error while saving Guest...!!!", e);
		}
	}
	
	
//-----------------------------------------------------------------------------------------------
//#################################==-- PROCEDURE to be executed in DATABASE --==################################################
//	create procedure spGetGuestDetailByGuestId @guestId bigint      
//	as begin      
//	set transaction isolation level read uncommitted      
//	select g.guest_id, g.hotel_id, CONCAT(g.first_name,' ', g.last_name) as full_name, g.father_name, g.cnic_number, g.age, g.guest_email, g.contact_info, g.address, g.emergency_contact
//			from guests g
//			join hotel h on h.hotel_id = g.hotel_id
//		where g.guest_id = @guestId
//	 and isnull(g.deleted, 0)<>1      
//	end 
	
	@Override
	public ORMGetGuest findGuest(Long guestId) {
		
		EntityManager manager = getEntityManager();
		try {
			
			StoredProcedureQuery Sp = manager.createStoredProcedureQuery("spGetGuestDetailByGuestId", ORMGetGuest.class);
			Sp.registerStoredProcedureParameter("guestId", Long.class, ParameterMode.IN);
			Sp.setParameter("guestId", guestId);
			Sp.execute();
			//Sp.getSingleResult();
			ORMGetGuest result = (ORMGetGuest) Sp.getSingleResult();
	        return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Error while fetching Guest...!!!", e);
		}
	}
	
//-----------------------------------------------------------------------------------------------
//#################################==-- PROCEDURE to be executed in DATABASE --==################################################
//	create procedure spGetGuestsByHotelId @hotelId bigint      
//	as begin      
//	set transaction isolation level read uncommitted      
//	select g.guest_id, g.hotel_id, CONCAT(g.first_name,' ', g.last_name) as full_name, g.father_name, g.cnic_number, g.age, g.guest_email, g.contact_info, g.address, g.emergency_contact
//			from guests g
//			join hotel h on h.hotel_id = g.hotel_id
//		where g.hotel_id = @hotelId
//	 and isnull(g.deleted, 0)<>1      
//	end 
	
	@Override
	public List<ORMGetGuest> findGuestByHotelId(Long hotelId) {
		EntityManager manager = getEntityManager();
		try {
			
			StoredProcedureQuery Sp = manager.createStoredProcedureQuery("spGetGuestsByHotelId", ORMGetGuest.class);
			Sp.registerStoredProcedureParameter("hotelId", Long.class, ParameterMode.IN);
			Sp.setParameter("hotelId", hotelId);
			Sp.execute();
			//Sp.getSingleResult();
			List<ORMGetGuest> GuestList = Sp.getResultList();
	        return GuestList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Error while fetching Guests...!!!", e);
		}
	}
	
	
	

	@Override
	public ResponseStatus updateGuest(ORMSaveGuest saveGuest) {
		// TODO Auto-generated method stub
		ResponseStatus resp = new ResponseStatus();
		Optional<ORMSaveGuest> Guest = guestRepo.findById(saveGuest.getGuest_id());
		String serverDateTime = LocalDateTime.now().toString();	
		
		if (Guest != null) {
			saveGuest.setClient_date_modified(serverDateTime);
			guestRepo.save(saveGuest);
			resp.setResponse("Guest Updated Sucessfully..!!!");
			resp.setStatus(true);
			resp.setResult("1");
			return resp;
		}else {
			resp.setResult("0");
			resp.setStatus(false);
			resp.setResponse("Error while updating Guest...!!!");
		}
			
			
		return resp;
	}



	@Override
	public ResponseStatus deleteGuestRecord(ORMDeleteRecord deleteRecord) {
		EntityManager manager = getEntityManager();
		ResponseStatus resp = new ResponseStatus();
		
		deleteRecord.setTable_name("Guests");
	    deleteRecord.setColumn_name("guest_id");

	    try {
	        StoredProcedureQuery sp = manager.createStoredProcedureQuery("spDelRecord");
	        sp.registerStoredProcedureParameter("table_name", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("column_name", String.class, ParameterMode.IN);
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


	



