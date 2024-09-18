package com.example.AliBaba.ABbackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AliBaba.ABbackend.DAO.ManagementDao;
import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetHotel;
import com.example.AliBaba.ABbackend.ORM.ORMGetService;
import com.example.AliBaba.ABbackend.ORM.ORMGetServiceRequest;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMHotel;
import com.example.AliBaba.ABbackend.ORM.ORMSaveService;
import com.example.AliBaba.ABbackend.ORM.ORMSaveServiceRequest;
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

	@Override
	public ResponseStatus createService(ORMSaveService saveService) {
		// TODO Auto-generated method stub
		return managementDao.createService(saveService);
	}

	@Override
	public ORMGetService getService(Long serviceId) {
		// TODO Auto-generated method stub
		return managementDao.getService(serviceId);
	}

	@Override
	public ResponseStatus updateService(ORMSaveService saveService) {
		// TODO Auto-generated method stub
		return managementDao.updateService(saveService);
	}

	@Override
	public ResponseStatus deleteServiceRecord(ORMDeleteRecord deleteRecord) {
		// TODO Auto-generated method stub
		return managementDao.deleteServiceRecord(deleteRecord);
	}

	@Override
	public List<ORMGetUser> getEmployeesByHotel(Long hotelId) {
		// TODO Auto-generated method stub
		return managementDao.getEmployeesByHotel(hotelId);
	}

	@Override
	public List<ORMGetService> getServicesByHotelId(Long hotelId) {
		// TODO Auto-generated method stub
		return managementDao.getServicesByHotelId(hotelId);
	}

	@Override
	public ResponseStatus createServiceRequest(ORMSaveServiceRequest saveServiceRequest) {
		// TODO Auto-generated method stub
		return managementDao.createServiceRequest(saveServiceRequest);
	}

	@Override
	public ORMGetServiceRequest getServiceRequest(Long serviceRequestId) {
		// TODO Auto-generated method stub
		return managementDao.getServiceRequest(serviceRequestId);
	}

	@Override
	public List<ORMGetServiceRequest> getServiceRequestsByHotelId(Long hotelId) {
		// TODO Auto-generated method stub
		return managementDao.getServiceRequestsByHotelId(hotelId);
	}

	@Override
	public ResponseStatus updateServiceRequest(ORMSaveServiceRequest saveServiceRequest) {
		// TODO Auto-generated method stub
		return managementDao.updateServiceRequest(saveServiceRequest);
	}

	@Override
	public ResponseStatus deleteServiceRequestRecord(ORMDeleteRecord deleteRecord) {
		// TODO Auto-generated method stub
		return managementDao.deleteServiceRequestRecord(deleteRecord);
	}

	
}
