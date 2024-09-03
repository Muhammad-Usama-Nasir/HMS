package com.example.AliBaba.ABbackend.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMSaveRoom;

@Repository
public interface RoomRepo extends JpaRepository<ORMSaveRoom, Long> {

}
