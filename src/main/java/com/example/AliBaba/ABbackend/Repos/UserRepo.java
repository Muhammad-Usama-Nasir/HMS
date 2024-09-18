package com.example.AliBaba.ABbackend.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;

@Repository
public interface UserRepo extends JpaRepository<ORMSaveUser, Long> {

	public Optional<ORMSaveUser> findByEmail(String email);

	public ORMSaveUser findByVerificationCode(String code);

}
