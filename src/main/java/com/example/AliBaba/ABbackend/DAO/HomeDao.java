package com.example.AliBaba.ABbackend.DAO;

import com.example.AliBaba.ABbackend.ORM.ORMGetGuest;
import com.example.AliBaba.ABbackend.ORM.ORMSaveGuest;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;


public interface HomeDao {

	ResponseStatus createNewGuest(ORMSaveGuest saveGuest);

	ORMGetGuest findGuest(Long guestId);

	ResponseStatus updateGuest(ORMSaveGuest saveGuest);

	ResponseStatus createAdmin(ORMSaveUser saveUser);


}
