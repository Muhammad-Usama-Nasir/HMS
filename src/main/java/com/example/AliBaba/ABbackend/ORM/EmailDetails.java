package com.example.AliBaba.ABbackend.ORM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

	
	private String recipeint; 
	private String subject;
	private String body;
	
	
	public String getRecipeint() {
		return recipeint;
	}
	public void setRecipeint(String recipeint) {
		this.recipeint = recipeint;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
