package com.ELS.eLibrary.Model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.ELS.eLibrary.util.constatnt.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="student")

@Entity
public class Student {

	 @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int std_id;
	private String name;
	private String email;
	private String password;
	private String phoneNo;
   
	@DateTimeFormat(pattern = "yyyy-MM-dd") // Specify expected date format
    private Date date_of_birth;

    private String gender;
    private String address;
	private String course;
	private String branch;
	//private int issue_id;
	//private int CALLNO;
	@Enumerated(EnumType.STRING)  // Store as a string in DB
	private Role role;
	
	public Student() {
		}

	public Student(int std_id, String name, String course, String branch) {
		this.std_id = std_id;
		this.name = name;
		this.course = course;
		this.branch = branch;
		//this.phoneNo=phoneNo;
	}

	
	

	public Student(String name, String course, String branch) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.course = course;
		this.branch = branch;
		//this.phoneNo=phoneNo;

	}

	public Student(String name, String course, String branch,String phoneNo) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.course = course;
		this.branch = branch;
		this.phoneNo=phoneNo;

	}

	

	public Student(int std_id) {
		// TODO Auto-generated constructor stub
		this.std_id=std_id;
	}


	public int getStd_id() {
		return std_id;
	}

	public void setStd_id(int std_id) {
		this.std_id = std_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
