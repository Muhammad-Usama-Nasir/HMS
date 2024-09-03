package com.example.AliBaba.ABbackend.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class ORMSaveReservations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservation_id;
	
	private Long hotel_id;
	private Long user_id;
	private Long guest_id;
	private Long room_id;
	
	private String check_in_date;
	private String check_out_date;
	
	private Integer number_of_guests;
	private String reservation_status;
	private String payment_status;
	private Long total_amount;
	private String payment_method;
	
	private String created_date ; 
   	private String client_date_created ;
   	private String modified_date ;
   	private String client_date_modified ;
   	private String created_user ;
   	private String modified_user;
   	private Boolean deleted;
   	private String system_ip;
   	
   	
   	
   	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(Long reservation_id) {
		this.reservation_id = reservation_id;
	}
	public Long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}
	public Long getGuest_id() {
		return guest_id;
	}
	public void setGuest_id(Long guest_id) {
		this.guest_id = guest_id;
	}
	public Long getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Long room_id) {
		this.room_id = room_id;
	}
	public String getCheck_in_date() {
		return check_in_date;
	}
	public void setCheck_in_date(String check_in_date) {
		this.check_in_date = check_in_date;
	}
	public String getCheck_out_date() {
		return check_out_date;
	}
	public void setCheck_out_date(String check_out_date) {
		this.check_out_date = check_out_date;
	}
	public Integer getNumber_of_guests() {
		return number_of_guests;
	}
	public void setNumber_of_guests(Integer number_of_guests) {
		this.number_of_guests = number_of_guests;
	}
	public String getReservation_status() {
		return reservation_status;
	}
	public void setReservation_status(String reservation_status) {
		this.reservation_status = reservation_status;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public Long getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Long total_amount) {
		this.total_amount = total_amount;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
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
