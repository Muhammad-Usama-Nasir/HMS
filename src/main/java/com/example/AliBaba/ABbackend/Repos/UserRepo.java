package com.example.AliBaba.ABbackend.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;

public interface UserRepo extends JpaRepository<ORMSaveUser, Long> {

	

}
