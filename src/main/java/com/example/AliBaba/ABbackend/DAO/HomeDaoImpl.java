package com.example.AliBaba.ABbackend.DAO;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
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
	private EntityManagerFactory emFactory;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

	
	@Override
	public ResponseStatus saveNewUser(ORMSaveUser saveUser) {
		// TODO Auto-generated method stub
		ResponseStatus resp = new ResponseStatus();
		
		String serverDateTime = LocalDateTime.now().toString();

		saveUser.setModified_date(serverDateTime);
		saveUser.setPassword(passwordEncoder.encode(saveUser.getPassword()));
		userRepo.save(saveUser);
		resp.setResponse("Data saved Successfully...");
		resp.setStatus(true);
		return resp;
	}
	
	@Override
	public ORMGetUser findUser(Long userId) {
		
		EntityManager manager = getEntityManager();

		StoredProcedureQuery Sp = manager.createStoredProcedureQuery("spGetUserByUserId", ORMGetUser.class);
		Sp.registerStoredProcedureParameter("userId", Long.class, ParameterMode.IN);
		Sp.setParameter("userId", userId);
		Sp.execute();
		Sp.getSingleResult();
		
		ORMGetUser result = (ORMGetUser) Sp.getSingleResult();
        return result;
		
	}
	

	@Override
	public ResponseStatus updateUser(ORMSaveUser saveUser) {
		// TODO Auto-generated method stub
		ResponseStatus resp = new ResponseStatus();
		Optional<ORMSaveUser> user = userRepo.findById(saveUser.getUser_id());
		
		String serverDateTime = LocalDateTime.now().toString();
		
		if(user.isPresent() && saveUser != null) {
			
			saveUser.setModified_date(serverDateTime);
			saveUser.setPassword(passwordEncoder.encode(saveUser.getPassword()));
			userRepo.save(saveUser);
			resp.setResponse("Data Updated Successfully...!!");
			resp.setStatus(true);
		}else{
			resp.setResponse("Error Updating Data...!!");
			resp.setStatus(false);
		}
		return resp;
	}


	


}
