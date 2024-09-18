package com.example.AliBaba.ABbackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AliBaba.ABbackend.DAO.HomeDao;
import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetGuest;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMSaveGuest;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

@Service
public class HomeServiceimpl implements HomeService{

	@Autowired
	private HomeDao homeDao;
	

	@Override
	public ResponseStatus createNewGuest(ORMSaveGuest saveGuest) {
		// TODO Auto-generated method stub
		return homeDao.createNewGuest(saveGuest);
	}

	@Override
	public ORMGetGuest findGuest(Long guestId) {
		// TODO Auto-generated method stub
		return homeDao.findGuest(guestId);
	}

	@Override
	public ResponseStatus updateGuest(ORMSaveGuest saveGuest) {
		// TODO Auto-generated method stub
		return homeDao.updateGuest(saveGuest);
	}

	@Override
	public ResponseStatus createAdmin(ORMSaveUser saveUser) {
		// TODO Auto-generated method stub
		return homeDao.createAdmin(saveUser);
	}

	@Override
	public List<ORMGetGuest> findGuestByHotelId(Long hotelId) {
		// TODO Auto-generated method stub
		return homeDao.findGuestByHotelId(hotelId);
	}

	@Override
	public ResponseStatus deleteGuestRecord(ORMDeleteRecord deleteRecord) {
		// TODO Auto-generated method stub
		return homeDao.deleteGuestRecord(deleteRecord);
	}

	@Override
	public ResponseStatus verifyAccount(String code) {
		// TODO Auto-generated method stub
		return homeDao.verifyAccount(code);
	}





}
