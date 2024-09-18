package com.example.AliBaba.ABbackend.DAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetRoom;
import com.example.AliBaba.ABbackend.ORM.ORMHotel;
import com.example.AliBaba.ABbackend.ORM.ORMSaveRoom;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Repos.HotelRepo;
import com.example.AliBaba.ABbackend.Repos.RoomRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class HotelResourcesDaoImpl implements HotelResourcesDao{

	@Autowired
	private RoomRepo roomRepo;
	
	@Autowired
	private EntityManagerFactory emFactory;
	
	
	private EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }
	
	
	
	
	@Override
	public ResponseStatus createRoom(ORMSaveRoom saveRoom) {
		ResponseStatus resp = new ResponseStatus();

		String serverDateTime = LocalDateTime.now().toString();
		try {
			saveRoom.setModified_date(serverDateTime);
			roomRepo.save(saveRoom);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Room Saved Successfully...!!!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR while saving Room Entity", e);
		}
		return resp;
	}

	
//-----------------------------------------------------------------------------------------------
//#################################==-- PROCEDURE to be executed in DATABASE --==################################################
//	create procedure spGetRoomDetailsByRoomId @roomId bigint      
//	as begin      
//	set transaction isolation level read uncommitted      
//	select r.room_id, r.hotel_id, r.room_no,  r.room_status, r.floor_no, r.room_type, r.bed_type, r.price_per_night
//			from rooms r
//			join hotel h on r.hotel_id = h.hotel_id
//		where r.room_id = @roomId
//	 and isnull(r.deleted, 0)<>1      
//	end 

	@Override
	public ORMGetRoom findRoom(Long roomId) {
		EntityManager manager = getEntityManager();
	    
	    try {
	    	StoredProcedureQuery sp = manager.createStoredProcedureQuery("spGetRoomDetailsByRoomId", ORMGetRoom.class);
	        sp.registerStoredProcedureParameter("roomId", Long.class, ParameterMode.IN);
	        sp.setParameter("roomId", roomId);
	        sp.execute();
	        
	        return (ORMGetRoom) sp.getSingleResult();
	        
	    }catch (Exception e) {
	        e.printStackTrace(); // or use a logger to log the error
	        throw new RuntimeException("Error while fetching Room Details", e);
	    }
	}
	
	
//-----------------------------------------------------------------------------------------------
//#################################==-- PROCEDURE to be executed in DATABASE --==################################################
//	create procedure spGetRoomDetailsByHotelId @hotelId bigint      
//	as begin      
//	set transaction isolation level read uncommitted      
//	select r.room_id, r.hotel_id, r.room_no,  r.room_status, r.floor_no, r.room_type, r.bed_type, r.price_per_night
//			from rooms r
//			join hotel h on r.hotel_id = h.hotel_id
//		where r.hotel_id = @hotelId
//	 and isnull(r.deleted, 0)<>1      
//	end 
	
	@Override
	public List<ORMGetRoom> findRoomByHotelId(Long hotelId) {
		EntityManager manager = getEntityManager();
	    
	    try {
	    	StoredProcedureQuery sp = manager.createStoredProcedureQuery("spGetRoomDetailsByHotelId", ORMGetRoom.class);
	        sp.registerStoredProcedureParameter("hotelId", Long.class, ParameterMode.IN);
	        sp.setParameter("hotelId", hotelId);
	        sp.execute();
	        
	        List<ORMGetRoom> roomList = sp.getResultList();
	        return roomList;
	        
	    }catch (Exception e) {
	        e.printStackTrace(); // or use a logger to log the error
	        throw new RuntimeException("Error while fetching Room Details", e);
	    }
	}
	
	
	
	@Override
	public ResponseStatus updateRoom(ORMSaveRoom saveRoom) {
		// TODO Auto-generated method stub
		ResponseStatus resp = new ResponseStatus();
		Optional<ORMSaveRoom> hotelEntity = roomRepo.findById(saveRoom.getRoom_id());
		String serverDateTime = LocalDateTime.now().toString();

		if(hotelEntity != null) {
			saveRoom.setModified_date(serverDateTime);
			roomRepo.save(saveRoom);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Room Details Updated Successfully...!!!");
		}else {
			resp.setResult("0");
			resp.setStatus(false);
			resp.setResponse("Error Updating Room Details...!!!");
		}
		
		return resp;
	}
	
	


	@Override
	public ResponseStatus deleteRecord(ORMDeleteRecord deleteRecord) {
		// TODO Auto-generated method stub
		EntityManager manager = getEntityManager();
		ResponseStatus resp = new ResponseStatus();
		
		deleteRecord.setTable_name("rooms");
	    deleteRecord.setColumn_name("room_id");

	    try {
	        StoredProcedureQuery sp = manager.createStoredProcedureQuery("spDelRecord");
	        sp.registerStoredProcedureParameter("table_name", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("column_name", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("column_id", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("modified_user", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("client_date_time", String.class, ParameterMode.IN);
	        sp.registerStoredProcedureParameter("system_ip", String.class, ParameterMode.IN);
	        
	        sp.setParameter("table_name", deleteRecord.getTable_name());
	        sp.setParameter("column_name", deleteRecord.getColumn_name());
	        sp.setParameter("column_id", deleteRecord.getColumn_id());
	        sp.setParameter("modified_user", deleteRecord.getModified_user());
	        sp.setParameter("client_date_time", deleteRecord.getClient_date_time());
	        sp.setParameter("system_ip", deleteRecord.getSystem_ip());
	        
	        sp.execute();
	        
	        resp.setResponse("Data Deleted Successfully...!!!");
	        resp.setStatus(true);
	        
	    } catch (Exception e) {
	    	
	        e.printStackTrace();
	        resp.setResponse("Error Deleting Data...!!! " + e.getMessage());
	        resp.setStatus(false);
	        
	    }
	    return resp;
	}






	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
