package com.example.AliBaba.ABbackend.DAO;

import java.time.LocalDateTime;
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
			resp.setResponse("Hotel Entity Updated Successfully...!!!");
		} catch (Exception e) {
			e.getMessage();
		}
		return resp;
	}




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
