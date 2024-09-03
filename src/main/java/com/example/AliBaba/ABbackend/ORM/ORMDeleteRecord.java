package com.example.AliBaba.ABbackend.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ORMDeleteRecord {

	@Id
	private String table_name;
	private String column_name;
	private Long column_id;
	private String modified_user;
	private String client_date_time;
	private String system_ip;
	
	
	
	public String getSystem_ip() {
		return system_ip;
	}
	public void setSystem_ip(String system_ip) {
		this.system_ip = system_ip;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getColumn_name() {
		return column_name;
	}
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	
	public Long getColumn_id() {
		return column_id;
	}
	public void setColumn_id(Long column_id) {
		this.column_id = column_id;
	}
	public String getModified_user() {
		return modified_user;
	}
	public void setModified_user(String modified_user) {
		this.modified_user = modified_user;
	}
	public String getClient_date_time() {
		return client_date_time;
	}
	public void setClient_date_time(String client_date_time) {
		this.client_date_time = client_date_time;
	}
	
	@Override
	public String toString() {
		return "ORMDeleteRecord [table_name=" + table_name + ", column_name=" + column_name + ", column_id=" + column_id
				+ ", modified_user=" + modified_user + ", client_date_time=" + client_date_time + "]";
	}
	
	
	public ORMDeleteRecord(String table_name, String column_name, Long column_id, String modified_user,
			String client_date_time, String system_ip) {
		super();
		this.table_name = table_name;
		this.column_name = column_name;
		this.column_id = column_id;
		this.modified_user = modified_user;
		this.client_date_time = client_date_time;
		this.system_ip = system_ip;
	}
	
	
	public ORMDeleteRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
