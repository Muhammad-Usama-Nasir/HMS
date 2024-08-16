package com.example.AliBaba.ABbackend.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
@Repository
public interface UserRepo extends JpaRepository<ORMSaveUser, Long> {

	//ORMGetUser findUserByUserId(Long userId);
	
//	@Procedure(name = "spGetUserByUserId")
//	ORMGetUser findUserByUserId(Long userId);
	
//	@Procedure(name = "spGetUserByUserId")
//	ORMGetUser findUserByUserId(@Param("userId") Long user_id);


}
