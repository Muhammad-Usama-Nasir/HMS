package com.example.AliBaba.ABbackend.DAO;

import java.util.List;

import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

public interface HomeDao {

	ResponseStatus saveNewUser(ORMSaveUser saveUser);

	ResponseStatus updateUser(ORMSaveUser saveUser);

	ORMGetUser findUser(Long userId);


}
