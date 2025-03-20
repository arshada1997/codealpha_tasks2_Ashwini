package com.ELS.eLibrary.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="book")

@Entity
public class Book {

     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private long book_id;
     
     private String catagory;
     
     private String book_name;
     
     private String author;
     
     private String publisher;
     
     private int book_copies;
     
    private  LocalDate recieved;
    
    

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getBook_copies() {
		return book_copies;
	}

	public void setBook_copies(int book_copies) {
		this.book_copies = book_copies;
	}

	public LocalDate getRecieved() {
		return recieved;
	}

	public void setRecieved(LocalDate recieved) {
		this.recieved = recieved;
	}
     
     
     
     
}
