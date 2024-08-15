package com.example.AliBaba.ABbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AliBaba.ABbackend.DAO.HomeDao;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

@Service
public class HomeServiceimpl implements HomeService{

	@Autowired
	private HomeDao homeDao;
	
	@Override
	public ResponseStatus saveNewUser(ORMSaveUser saveUser) {
		// TODO Auto-generated method stub
		return homeDao.saveNewUser(saveUser);
	}

}
