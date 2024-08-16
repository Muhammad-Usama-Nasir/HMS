package com.example.AliBaba.ABbackend.DAO;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.ORM.SpParameters;
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
		
	EntityManager manager = emFactory.createEntityManager();
	
	@Override
	public ResponseStatus saveNewUser(ORMSaveUser saveUser) {
		// TODO Auto-generated method stub
		ResponseStatus resp = new ResponseStatus();
		
		String serverDateTime = LocalDateTime.now().toString();

		saveUser.setModified_date(serverDateTime);
		userRepo.save(saveUser);
		resp.setResponse("Data saved Successfully...");
		resp.setResponseStatus(true);
		return resp;
	}

	@Override
	public ResponseStatus updateUser(ORMSaveUser saveUser) {
		// TODO Auto-generated method stub
		ResponseStatus resp = new ResponseStatus();
		Optional<ORMSaveUser> user = userRepo.findById(saveUser.getUser_id());
		
		String serverDateTime = LocalDateTime.now().toString();
		
		if(user.isPresent() && saveUser != null) {
			
			saveUser.setModified_date(serverDateTime);
			userRepo.save(saveUser);
			resp.setResponse("Data Updated Successfully...!!");
			resp.setResponseStatus(true);
		}else{
			resp.setResponse("Error Updating Data...!!");
			resp.setResponseStatus(false);
		}
		return resp;
	}

	@Override
	public ORMGetUser findUser(Long userId) {
		// TODO Auto-generated method stub
		//List<ORMGetUser> resp = userRepo.findById(userId);
		//List<SpParameters> ListOfUsers = new ArrayList<>();
		//ListOfUsers.add(new SpParameters("userId", userId.toString(), String.class, ParameterMode.IN));
		
		// Call the stored procedure method from the repository

		StoredProcedureQuery Sp = manager.createStoredProcedureQuery("spGetUserByUserId", ORMGetUser.class);
		StoredProcedureQuery sp2 = manager.
        ORMGetUser resp = userRepo.findUserByUserId(userId);

        // Add additional logic if needed, and return the response as a List
        return resp;
		
	}
	
	


}
