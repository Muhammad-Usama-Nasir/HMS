package com.example.AliBaba.ABbackend.DAO;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMGetGuest;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMSaveGuest;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Repos.GuestRepo;
import com.example.AliBaba.ABbackend.Repos.RoleRepository;
import com.example.AliBaba.ABbackend.Repos.UserRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class HomeDaoImpl implements HomeDao {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private GuestRepo guestRepo;
	
	@Autowired
	private EntityManagerFactory emFactory;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	private EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

	

	@Override
	public ResponseStatus createAdmin(ORMSaveUser saveUser) {
		// TODO Auto-generated method stub
		
		ResponseStatus resp = new ResponseStatus();
		
		String serverDateTime = LocalDateTime.now().toString();
		
		try {
			saveUser.setClient_date_modified(serverDateTime);
			userRepo.save(saveUser);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Login saved Successfully...!!!");
			return resp;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Error saving Login Info...!!!", e);
		}

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



	@Override
	public ORMGetGuest findGuest(Long guestId) {
		
		EntityManager manager = getEntityManager();
		try {
			
			StoredProcedureQuery Sp = manager.createStoredProcedureQuery("spGetUserByUserId", ORMGetUser.class);
			Sp.registerStoredProcedureParameter("userId", Long.class, ParameterMode.IN);
			Sp.setParameter("guestId", guestId);
			Sp.execute();
			//Sp.getSingleResult();
			ORMGetGuest result = (ORMGetGuest) Sp.getSingleResult();
	        return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Error while fetching Guest.", e);
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



	
	

	
	
}


	



