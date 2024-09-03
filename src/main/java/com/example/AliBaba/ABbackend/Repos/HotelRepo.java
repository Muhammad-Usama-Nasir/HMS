package com.example.AliBaba.ABbackend.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMHotel;
import com.example.AliBaba.ABbackend.ORM.ORMSaveRoom;

@Repository
public interface HotelRepo extends JpaRepository<ORMHotel, Long> {


}
