package com.example.AliBaba.ABbackend.DAO;

import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetHotel;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMHotel;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

public interface ManagementDao {

	ResponseStatus createHotel(ORMHotel hotel);

	ORMGetHotel getHotel(Long hotelId);

	ResponseStatus deleteRecord(ORMDeleteRecord deleteRecord);

	ResponseStatus createEmployee(ORMSaveUser employee);

	ORMGetUser getEmployee(Long userId);

	ResponseStatus deleteEmployee(ORMDeleteRecord deleteRecord);

	ResponseStatus updateEmployee(ORMSaveUser employee);

	ResponseStatus updateHotel(ORMHotel hotel);


	
	
}
