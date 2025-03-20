package com.ELS.eLibrary.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ELS.eLibrary.Model.EBook;
import com.ELS.eLibrary.Model.Librarian;
import com.ELS.eLibrary.Model.Student;
import com.ELS.eLibrary.Repository.EBookRepository;
import com.ELS.eLibrary.Repository.LibrarianRepository;
import com.ELS.eLibrary.Repository.StudentRepository;

@Service
public class EBookService {

	@Autowired
	private EBookRepository liberRepo;

	public Optional<EBook> getFile(Long ebook_id) {
		// TODO Auto-generated method stub
		return liberRepo.findById(ebook_id);
	}
	
	 public Optional<EBook> getPdf(Long ebook_id) {
	        return liberRepo.findById(ebook_id);
	    }
/*public List<Librarian> findAll() {
/*
/*	return liberRepo.findAll();
/*}*/

	
}
