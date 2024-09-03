package com.example.AliBaba.ABbackend.DAO;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.catalina.Manager;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetHotel;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMHotel;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Repos.HotelRepo;
import com.example.AliBaba.ABbackend.Repos.UserRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class ManagementDaoImpl implements ManagementDao{

	@Autowired
	private HotelRepo hotelRepo;
	
	@Autowired
	private EntityManagerFactory emFactory;
	
	@Autowired
	private UserRepo userRepo;
	
	
	private EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }
	
	
	
	
	
	@Override
	public ResponseStatus createHotel(ORMHotel hotel) {
		// TODO Auto-generated method stub
		ResponseStatus resp = new ResponseStatus();

		String serverDateTime = LocalDateTime.now().toString();
		try {
			hotel.setModified_date(serverDateTime);
			hotelRepo.save(hotel);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Hotel Entity Updated Successfully...!!!");
		} catch (Exception e) {
			e.getMessage();
		}
		return resp;
	}


	public ORMGetHotel getHotel(Long hotelId) {
	    EntityManager manager = getEntityManager();
	    
	    try {
	    	StoredProcedureQuery sp = manager.createStoredProcedureQuery("spGetHotelByHotelId", ORMGetHotel.class);
	        sp.registerStoredProcedureParameter("hotelId", Long.class, ParameterMode.IN);
	        sp.setParameter("hotelId", hotelId);
	        sp.execute();
	        
	        return (ORMGetHotel) sp.getSingleResult();
	        
	    }catch (Exception e) {
	        e.printStackTrace(); // or use a logger to log the error
	        throw new RuntimeException("Error while fetching hotel", e);
	    } 
	}
	
	
	@Override
	public ResponseStatus updateHotel(ORMHotel hotel) {
		// TODO Auto-generated method stub
		ResponseStatus resp = new ResponseStatus();
		Optional<ORMHotel> hotelEntity = hotelRepo.findById(hotel.getHotel_id());
		String serverDateTime = LocalDateTime.now().toString();

		if(hotelEntity != null) {
			hotel.setModified_date(serverDateTime);
			hotelRepo.save(hotel);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Hotel Entity Updated Successfully...!!!");
		}else {
			resp.setResult("0");
			resp.setStatus(false);
			resp.setResponse("Error Updating Hotel...!!!");
		}
		
		return resp;
	}
	
	


	@Override
	public ResponseStatus deleteRecord(ORMDeleteRecord deleteRecord) {
		// TODO Auto-generated method stub
		EntityManager manager = getEntityManager();
		ResponseStatus resp = new ResponseStatus();
		
		deleteRecord.setTable_name("hotel");
	    deleteRecord.setColumn_name("hotel_id");

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





	@Override
	public ResponseStatus createEmployee(ORMSaveUser employee) {
		ResponseStatus resp = new ResponseStatus();
		String serverDateTime = LocalDateTime.now().toString();
		try {
			employee.setModified_date(serverDateTime);
			userRepo.save(employee);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Employee Saved Successfully...!!!");
			
		} catch (Exception e) {
			e.getMessage();
		}
		return resp;
	}





	@Override
	public ORMGetUser getEmployee(Long userId) {
		// TODO Auto-generated method stub
		EntityManager manager = getEntityManager();
		
		try {
			StoredProcedureQuery sp = manager.createStoredProcedureQuery("spGetUserByUserId", ORMGetUser.class);
			
			sp.registerStoredProcedureParameter("userId", String.class, ParameterMode.IN);
			sp.setParameter("userId", userId.toString());
			sp.execute();
			
			ORMGetUser employee = (ORMGetUser) sp.getSingleResult();
			return employee;
			
		} catch (Exception e) {
	        e.printStackTrace(); // or use a logger to log the error
	        throw new RuntimeException("Error while fetching hotel", e);
		}
	}


	@Override
	public ResponseStatus updateEmployee(ORMSaveUser employee) {
		ResponseStatus resp = new ResponseStatus();
		Optional<ORMSaveUser> employeeEntity = userRepo.findById(employee.getUser_id());
		String serverDateTime = LocalDateTime.now().toString();
		
		if(employeeEntity != null) {
			employee.setModified_date(serverDateTime);
			userRepo.save(employee);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Employee Updated Successfully...!!!");
		}else {
			resp.setResult("0");
			resp.setStatus(false);
			resp.setResponse("Error Updating Empployee...!!!");
		}
			
		return resp;
	}


	@Override
	public ResponseStatus deleteEmployee(ORMDeleteRecord deleteRecord) {
		EntityManager manager = getEntityManager();
		ResponseStatus resp = new ResponseStatus();
		
		deleteRecord.setTable_name("users");
	    deleteRecord.setColumn_name("user_id");

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
