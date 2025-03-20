package com.ELS.eLibrary.Model;


import java.io.File;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="ebook")
public class EBook {

	     @Id
	     @GeneratedValue(strategy=GenerationType.IDENTITY)
	     private long ebook_id;
	     
	     private String ecatagory;
	     
	     //private String ebook_name;
	   
	     private String fileName;
	     private String contentType;
	     
	     

		@Lob
	     @Column(columnDefinition = "LONGBLOB") // For large files
	     private byte[] data;
		

	     public EBook() {
			super();
			// TODO Auto-generated constructor stub
		}

		public long getEbook_id() {
			return ebook_id;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}

		public byte[] getData() {
			return data;
		}

		public void setData(byte[] data) {
			this.data = data;
		}

		public void setEbook_id(long ebook_id) {
			this.ebook_id = ebook_id;
		}

		public String getEcatagory() {
			return ecatagory;
		}

		public void setEcatagory(String ecatagory) {
			this.ecatagory = ecatagory;
		}
}
