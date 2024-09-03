package com.example.AliBaba.ABbackend.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel")
public class ORMHotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotel_id;
	
	private String hotel_name;
	private String description;
	private String state;
	private String country;
	private String postal_code;
	private String contact;
	private String email;
	private String room_types;
	private Long total_rooms;

	
	private String created_date;
	private String modified_date;
	private String client_modified_date;
	private String client_created_date;
	private String created_user;
	private String modified_user;
	private Boolean deleted;
	private String ip;
	
	
	
	
	public Long getTotal_rooms() {
		return total_rooms;
	}
	public void setTotal_rooms(Long total_rooms) {
		this.total_rooms = total_rooms;
	}
	public Long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoom_types() {
		return room_types;
	}
	public void setRoom_types(String room_types) {
		this.room_types = room_types;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}
	public String getClient_modified_date() {
		return client_modified_date;
	}
	public void setClient_modified_date(String client_modified_date) {
		this.client_modified_date = client_modified_date;
	}
	public String getClient_created_date() {
		return client_created_date;
	}
	public void setClient_created_date(String client_created_date) {
		this.client_created_date = client_created_date;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
