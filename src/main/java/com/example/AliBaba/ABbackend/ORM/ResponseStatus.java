package com.example.AliBaba.ABbackend.ORM;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseStatus {

	private String Response;
	private Boolean Status;
	private String result;
	
	

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Boolean getStatus() {
		return Status;
	}
	public void setStatus(Boolean status) {
		Status = status;
	}
	public String getResponse() {
		return Response;
	}
	public void setResponse(String response) {
		Response = response;
	}

	
	
}
