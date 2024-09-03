package com.example.AliBaba.ABbackend.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ORMGetHotel {

	
	@Id
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
	public Long getTotal_rooms() {
		return total_rooms;
	}
	public void setTotal_rooms(Long total_rooms) {
		this.total_rooms = total_rooms;
	}

	
	
}
