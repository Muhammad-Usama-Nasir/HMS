package com.example.AliBaba.ABbackend.ORM;

import jakarta.persistence.Entity;
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
	private Boolean ResponseStatus;
	
	
	public String getResponse() {
		return Response;
	}
	public void setResponse(String response) {
		Response = response;
	}
	public Boolean getResponseStatus() {
		return ResponseStatus;
	}
	public void setResponseStatus(Boolean responseStatus) {
		ResponseStatus = responseStatus;
	}

	
	
}
