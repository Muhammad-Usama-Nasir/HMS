package com.example.AliBaba.ABbackend.Service;

import java.util.List;

import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetRoom;
import com.example.AliBaba.ABbackend.ORM.ORMSaveRoom;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;

public interface HotelResourcesService {

	ResponseStatus createRoom(ORMSaveRoom saveRoom);

	ORMGetRoom findRoom(Long roomId);

	ResponseStatus updateRoom(ORMSaveRoom saveRoom);

	ResponseStatus deleteRecord(ORMDeleteRecord deleteRecord);

	List<ORMGetRoom> findRoomByHotelId(Long hotelId);

}
