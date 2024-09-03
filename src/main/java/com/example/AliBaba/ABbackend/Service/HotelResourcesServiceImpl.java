package com.example.AliBaba.ABbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AliBaba.ABbackend.DAO.HotelResourcesDao;
import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetRoom;
import com.example.AliBaba.ABbackend.ORM.ORMSaveRoom;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

@Service
public class HotelResourcesServiceImpl implements HotelResourcesService{

	@Autowired
	private HotelResourcesDao hotelResourcesDao;
	
	@Override
	public ResponseStatus createRoom(ORMSaveRoom saveRoom) {
		// TODO Auto-generated method stub
		return hotelResourcesDao.createRoom(saveRoom);
	}

	@Override
	public ORMGetRoom findRoom(Long roomId) {
		// TODO Auto-generated method stub
		return hotelResourcesDao.findRoom(roomId);
	}

	@Override
	public ResponseStatus updateRoom(ORMSaveRoom saveRoom) {
		// TODO Auto-generated method stub
		return hotelResourcesDao.updateRoom(saveRoom);
	}

	@Override
	public ResponseStatus deleteRecord(ORMDeleteRecord deleteRecord) {
		// TODO Auto-generated method stub
		return hotelResourcesDao.deleteRecord(deleteRecord);
	}

	
	
}
