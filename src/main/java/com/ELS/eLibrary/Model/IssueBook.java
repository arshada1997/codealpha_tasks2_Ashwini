package com.ELS.eLibrary.Model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="issueBook")

@Entity
public class IssueBook {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
	private int issue_id;
	 
//	@Column(name = "book_id")
	
	private String book_name;
	
    private int book_id;
    
    private String name;
    
	//@Column(name = "std_id")
    private int std_id;
    
	//@DateTimeFormat(pattern = "yyyy-MM-dd") // Specify expected date format

    private LocalDate issue_date;
    
	//@DateTimeFormat(pattern = "yyyy-MM-dd") // Specify expected date format

    private LocalDate due_date;
    
    private String status;
    
    

	public IssueBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IssueBook(int issue_id, String book_name, int book_id, String name, int std_id, LocalDate issue_date,
			LocalDate due_date, String status) {
		super();
		this.issue_id = issue_id;
		this.book_name = book_name;
		this.book_id = book_id;
		this.name = name;
		this.std_id = std_id;
		this.issue_date = issue_date;
		this.due_date = due_date;
		this.status = status;
	}

	public int getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(int issue_id) {
		this.issue_id = issue_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStd_id() {
		return std_id;
	}

	public void setStd_id(int std_id) {
		this.std_id = std_id;
	}

	public LocalDate getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(LocalDate issue_date) {
		this.issue_date = issue_date;
	}

	public LocalDate getDue_date() {
		return due_date;
	}

	public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
	
}
