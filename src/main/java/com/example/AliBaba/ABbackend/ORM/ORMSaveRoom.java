package com.example.AliBaba.ABbackend.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class ORMSaveRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long room_id;
	
	private Long hotel_id;
	private Integer room_no;
	private String room_type;
	private String bed_type;
	private Long price_per_night;
	private Boolean room_status;
	private Integer floor_no;
	
	private String created_date ; 
   	private String client_date_created ;
   	private String modified_date ;
   	private String client_date_modified ;
   	private String created_user ;
   	private String modified_user;
   	private Boolean deleted;
   	private String system_ip;
   	
   	
	public Long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}
	public Long getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Long room_id) {
		this.room_id = room_id;
	}
	public Integer getRoom_no() {
		return room_no;
	}
	public void setRoom_no(Integer room_no) {
		this.room_no = room_no;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public String getBed_type() {
		return bed_type;
	}
	public void setBed_type(String bed_type) {
		this.bed_type = bed_type;
	}
	public Long getPrice_per_night() {
		return price_per_night;
	}
	public void setPrice_per_night(Long price_per_night) {
		this.price_per_night = price_per_night;
	}
	public Boolean getRoom_status() {
		return room_status;
	}
	public void setRoom_status(Boolean room_status) {
		this.room_status = room_status;
	}
	public Integer getFloor_no() {
		return floor_no;
	}
	public void setFloor_no(Integer floor_no) {
		this.floor_no = floor_no;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getClient_date_created() {
		return client_date_created;
	}
	public void setClient_date_created(String client_date_created) {
		this.client_date_created = client_date_created;
	}
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}
	public String getClient_date_modified() {
		return client_date_modified;
	}
	public void setClient_date_modified(String client_date_modified) {
		this.client_date_modified = client_date_modified;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
   	
   	
	
}
