package com.example.AliBaba.ABbackend.Service;

import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

public interface HomeService {

	ResponseStatus saveNewUser(ORMSaveUser saveUser);

}
