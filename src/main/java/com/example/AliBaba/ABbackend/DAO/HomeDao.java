package com.example.AliBaba.ABbackend.DAO;

import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

public interface HomeDao {

	ResponseStatus saveNewUser(ORMSaveUser saveUser);

}
