package com.example.AliBaba.ABbackend.DAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMDeleteRecord;
import com.example.AliBaba.ABbackend.ORM.ORMGetHotel;
import com.example.AliBaba.ABbackend.ORM.ORMGetService;
import com.example.AliBaba.ABbackend.ORM.ORMGetServiceRequest;
import com.example.AliBaba.ABbackend.ORM.ORMGetUser;
import com.example.AliBaba.ABbackend.ORM.ORMHotel;
import com.example.AliBaba.ABbackend.ORM.ORMSaveService;
import com.example.AliBaba.ABbackend.ORM.ORMSaveServiceRequest;
import com.example.AliBaba.ABbackend.ORM.ORMSaveUser;
import com.example.AliBaba.ABbackend.ORM.ResponseStatus;
import com.example.AliBaba.ABbackend.Repos.HotelRepo;
import com.example.AliBaba.ABbackend.Repos.ServiceRepo;
import com.example.AliBaba.ABbackend.Repos.ServiceRequestRepo;
import com.example.AliBaba.ABbackend.Repos.UserRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

@Repository
public class ManagementDaoImpl implements ManagementDao{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private HotelRepo hotelRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ServiceRepo serviceRepo;
	
	@Autowired
	private ServiceRequestRepo serviceRequestRepo;
	
	@Autowired
	private EntityManagerFactory emFactory;

	
	
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
			resp.setResponse("Hotel Entity saved Successfully...!!!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while Saving Hotel...!!!", e);
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





	@Override
	public ResponseStatus createEmployee(ORMSaveUser employee) {
		ResponseStatus resp = new ResponseStatus();
		String serverDateTime = LocalDateTime.now().toString();
		try {
			employee.setModified_date(serverDateTime);
			employee.setPassword(passwordEncoder.encode(employee.getPassword()));
			userRepo.save(employee);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Employee Saved Successfully...!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while Saving Employee...!!!", e);
		}
		return resp;
	}
	
	
//-----------------------------------------------------------------------------------------------
//#################################==-- PROCEDURE to be executed in DATABASE --==################################################
//	create procedure spGetUserByUserId @userId bigint      
//	as begin      
//	set transaction isolation level read uncommitted      
//	select u.user_id, r.role_id, concat(u.first_name,' ', u.last_name) as name, r.role_name, u.password, 
//			u.email, u.contact_number, u.address, u.registration_date, u.status      
//		from users u    
//		left join roles r on u.role_id = r.role_id
//	 where u.user_id = @userId      
//	 and isnull(u.deleted, 0)<>1      
//	end 

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

	
//-----------------------------------------------------------------------------------------------
//#################################==-- PROCEDURE to be executed in DATABASE --==################################################
//	create procedure spGetUserByHotelId @hotelId bigint      
//	as begin      
//	set transaction isolation level read uncommitted      
//	select u.user_id, r.role_id, concat(u.first_name,' ', u.last_name) as name, r.role_name, u.password, 
//			u.email, u.contact_number, u.address, u.registration_date, u.status, u.hotel_id     
//		from users u    
//		left join roles r on u.role_id = r.role_id
//	 where u.hotel_id = @hotelId   
//	 and isnull(u.deleted, 0)<>1      
//	end 
	
	
	
	@Override
	public List<ORMGetUser> getEmployeesByHotel(Long hotelId) {
		EntityManager manager = getEntityManager();
	    
	    try {
	    	StoredProcedureQuery sp = manager.createStoredProcedureQuery("spGetUserByHotelId", ORMGetUser.class);
	        sp.registerStoredProcedureParameter("hotelId", Long.class, ParameterMode.IN);
	        sp.setParameter("hotelId", hotelId);
	        sp.execute();
	        
	        List<ORMGetUser> resultList = sp.getResultList();
	        return resultList;
	        
	    }catch (Exception e) {
	        e.printStackTrace(); // or use a logger to log the error
	        throw new RuntimeException("Error while Getting Users", e);
	    } 
	}
	

	@Override
	public ResponseStatus updateEmployee(ORMSaveUser employee) {
		ResponseStatus resp = new ResponseStatus();
		Optional<ORMSaveUser> employeeEntity = userRepo.findById(employee.getUser_id());
		String serverDateTime = LocalDateTime.now().toString();
		
		if(employeeEntity != null) {
			employee.setModified_date(serverDateTime);
			employee.setPassword(passwordEncoder.encode(employee.getPassword()));
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





	@Override
	public ResponseStatus createService(ORMSaveService saveService) {
		ResponseStatus resp = new ResponseStatus();

		String serverDateTime = LocalDateTime.now().toString();
		try {
			saveService.setClient_date_created(serverDateTime);
			serviceRepo.save(saveService);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Service Saved Successfully...!!!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while Saving Service...!!!", e);
		}
		return resp;
	}

	
//-----------------------------------------------------------------------------------------------
//#################################==-- PROCEDURE to be executed in DATABASE --==################################################
//	create procedure spGetServiceByServiceId @serviceId bigint      
//	as begin      
//	set transaction isolation level read uncommitted      
//	select service_id, service_name, service_description, service_price, client_date_created
//			from service 
//		where service_id = @serviceId
//	 and isnull(deleted, 0)<>1      
//	end 

	@Override
	public ORMGetService getService(Long serviceId) {
		EntityManager manager = getEntityManager();
	    
	    try {
	    	StoredProcedureQuery sp = manager.createStoredProcedureQuery("spGetServiceByServiceId", ORMGetService.class);
	        sp.registerStoredProcedureParameter("serviceId", Long.class, ParameterMode.IN);
	        sp.setParameter("serviceId", serviceId);
	        sp.execute();
	        
	        return (ORMGetService) sp.getSingleResult();
	        
	    }catch (Exception e) {
	        e.printStackTrace(); // or use a logger to log the error
	        throw new RuntimeException("Error while fetching Service", e);
	    } 
	}

//-----------------------------------------------------------------------------------------------
//#################################==-- PROCEDURE to be executed in DATABASE --==################################################
//	create procedure spGetServicesByHotelId @hotelId bigint      
//	as begin      
//	set transaction isolation level read uncommitted      
//	select service_id, hotel_id, service_name, service_description, service_price, client_date_created
//			from service 
//		where hotel_id = @hotelId
//	 and isnull(deleted, 0)<>1      
//	end 
	
	

	@Override
	public List<ORMGetService> getServicesByHotelId(Long hotelId) {
		EntityManager manager = getEntityManager();
	    
	    try {
	    	StoredProcedureQuery sp = manager.createStoredProcedureQuery("spGetServicesByHotelId", ORMGetService.class);
	        sp.registerStoredProcedureParameter("hotelId", Long.class, ParameterMode.IN);
	        sp.setParameter("hotelId", hotelId);
	        sp.execute();
	        
	        List<ORMGetService> serviceList = sp.getResultList();
	        return serviceList;
	    }catch (Exception e) {
	        e.printStackTrace(); // or use a logger to log the error
	        throw new RuntimeException("Error while fetching Service", e);
	    } 
	}


	@Override
	public ResponseStatus updateService(ORMSaveService saveService) {
		ResponseStatus resp = new ResponseStatus();
		Optional<ORMSaveService> service = serviceRepo.findById(saveService.getService_id());
		String serverDateTime = LocalDateTime.now().toString();
		
		if(service != null) {
			saveService.setCreated_date(serverDateTime);
			serviceRepo.save(saveService);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Service Updated Successfully...!!!");
		}else {
			resp.setResult("0");
			resp.setStatus(false);
			resp.setResponse("Error Updating Service...!!!");
		}
			
		return resp;
	}





	@Override
	public ResponseStatus deleteServiceRecord(ORMDeleteRecord deleteRecord) {
		EntityManager manager = getEntityManager();
		ResponseStatus resp = new ResponseStatus();
		
		deleteRecord.setTable_name("service");
	    deleteRecord.setColumn_name("service_id");

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





	@Override
	public ResponseStatus createServiceRequest(ORMSaveServiceRequest saveServiceRequest) {
		ResponseStatus resp = new ResponseStatus();

		String serverDateTime = LocalDateTime.now().toString();
		try {
			saveServiceRequest.setClient_date_created(serverDateTime);
			serviceRequestRepo.save(saveServiceRequest);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Service Request Saved Successfully...!!!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while Saving Service Request...!!!", e);
		}
		return resp;
	}





	@Override
	public ORMGetServiceRequest getServiceRequest(Long serviceRequestId) {
		EntityManager manager = getEntityManager();
	    
	    try {
	    	StoredProcedureQuery sp = manager.createStoredProcedureQuery("spGetServiceByServiceId", ORMGetServiceRequest.class);
	        sp.registerStoredProcedureParameter("serviceRequestId", Long.class, ParameterMode.IN);
	        sp.setParameter("serviceRequestId", serviceRequestId);
	        sp.execute();
	        
	        return (ORMGetServiceRequest) sp.getSingleResult();
	        
	    }catch (Exception e) {
	        e.printStackTrace(); // or use a logger to log the error
	        throw new RuntimeException("Error while fetching Service", e);
	    } 
	}





	@Override
	public List<ORMGetServiceRequest> getServiceRequestsByHotelId(Long hotelId) {
		EntityManager manager = getEntityManager();
	    
	    try {
	    	StoredProcedureQuery sp = manager.createStoredProcedureQuery("spGetServicesByHotelId", ORMGetServiceRequest.class);
	        sp.registerStoredProcedureParameter("hotelId", Long.class, ParameterMode.IN);
	        sp.setParameter("hotelId", hotelId);
	        sp.execute();
	        
	        List<ORMGetServiceRequest> serviceRequestList = sp.getResultList();
	        return serviceRequestList;
	    }catch (Exception e) {
	        e.printStackTrace(); // or use a logger to log the error
	        throw new RuntimeException("Error while fetching Service", e);
	    } 
	}





	@Override
	public ResponseStatus updateServiceRequest(ORMSaveServiceRequest saveServiceRequest) {
		ResponseStatus resp = new ResponseStatus();
		Optional<ORMSaveServiceRequest> serviceRequest = serviceRequestRepo.findById(saveServiceRequest.getService_request_id());
		String serverDateTime = LocalDateTime.now().toString();
		
		if(serviceRequest != null) {
			saveServiceRequest.setCreated_date(serverDateTime);
			serviceRequestRepo.save(saveServiceRequest);
			resp.setResult("1");
			resp.setStatus(true);
			resp.setResponse("Service Request Updated Successfully...!!!");
		}else {
			resp.setResult("0");
			resp.setStatus(false);
			resp.setResponse("Error Updating Service Request...!!!");
		}
			
		return resp;
	}





	@Override
	public ResponseStatus deleteServiceRequestRecord(ORMDeleteRecord deleteRecord) {
		EntityManager manager = getEntityManager();
		ResponseStatus resp = new ResponseStatus();
		
		deleteRecord.setTable_name("service_requests");
	    deleteRecord.setColumn_name("service_request_id");

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
