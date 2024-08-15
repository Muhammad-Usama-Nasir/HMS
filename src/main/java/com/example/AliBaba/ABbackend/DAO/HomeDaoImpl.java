package com.example.AliBaba.ABbackend.DAO;


import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Repos.UserRepo;

@Repository
public class HomeDaoImpl implements HomeDao {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public ResponseStatus saveNewUser(ORMSaveUser saveUser) {
		// TODO Auto-generated method stub
		ResponseStatus resp = new ResponseStatus();
		
		String serverDateTime = LocalDateTime.now().toString();
		Optional<ORMSaveUser> user = userRepo.findById(saveUser.getUser_id());
		
		if(user.isPresent() && saveUser != null) {

			saveUser.setModified_date(serverDateTime);
			userRepo.save(saveUser);
			resp.setResponse("Data updated Successfully...");
			resp.setResponseStatus(true);
		}else{
			saveUser.setModified_date(serverDateTime);
			userRepo.save(saveUser);
			resp.setResponse("Data saved Successfully...");
			resp.setResponseStatus(true);
		}
		return resp;
	}

}
