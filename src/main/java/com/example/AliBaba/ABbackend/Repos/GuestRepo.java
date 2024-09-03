package com.example.AliBaba.ABbackend.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AliBaba.ABbackend.ORM.ORMSaveGuest;

public interface GuestRepo extends JpaRepository<ORMSaveGuest, Long> {

}
