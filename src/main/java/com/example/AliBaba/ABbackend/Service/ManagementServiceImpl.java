package com.example.AliBaba.ABbackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AliBaba.ABbackend.DAO.ManagementDao;
import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetHotel;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMHotel;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

@Service
public class ManagementServiceImpl implements ManagementService{

	@Autowired
	private ManagementDao managementDao;

	@Override
	public ResponseStatus createHotel(ORMHotel hotel) {
		// TODO Auto-generated method stub
		return managementDao.createHotel(hotel);
	}

	@Override
	public ORMGetHotel getHotel(Long hotelId) {
		// TODO Auto-generated method stub
		return managementDao.getHotel(hotelId);
	}

	@Override
	public ResponseStatus deleteRecord(ORMDeleteRecord deleteRecord) {
		// TODO Auto-generated method stub
		return managementDao.deleteRecord(deleteRecord);
	}

	@Override
	public ResponseStatus createEmployee(ORMSaveUser employee) {
		// TODO Auto-generated method stub
		return managementDao.createEmployee(employee);
	}

	@Override
	public ORMGetUser getEmployee(Long userId) {
		// TODO Auto-generated method stub
		return managementDao.getEmployee(userId);
	}

	@Override
	public ResponseStatus deleteEmployee(ORMDeleteRecord deleteRecord) {
		// TODO Auto-generated method stub
		return managementDao.deleteEmployee(deleteRecord);
	}

	@Override
	public ResponseStatus updateEmployee(ORMSaveUser employee) {
		// TODO Auto-generated method stub
		return managementDao.updateEmployee(employee);
	}

	@Override
	public ResponseStatus updateHotel(ORMHotel hotel) {
		// TODO Auto-generated method stub
		return managementDao.updateHotel(hotel);
	}

	
}
