package com.gl.dbmodel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	
	@Id
	private String examNo;
	private String firstName;
	private String lastName;
	private int age;
	
	public Student() {
		
	}

	public Student(String examNo, String firstName, String lastName, int age) {
		super();
		this.examNo = examNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getExamNo() {
		return examNo;
	}

	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	
	
	
	
	

}
