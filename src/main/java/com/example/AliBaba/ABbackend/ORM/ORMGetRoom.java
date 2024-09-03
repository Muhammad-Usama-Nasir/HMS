package com.example.AliBaba.ABbackend.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ORMGetRoom {

	@Id
	private Long room_id;
	
	private Long hotel_id;
	private Integer room_no;
	private String room_type;
	private String bed_type;
	private Long price_per_night;
	private Boolean room_status;
	private Integer floor_no;
	
	
	public Long getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Long room_id) {
		this.room_id = room_id;
	}
	public Long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
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
	
	
}
