package com.ELS.eLibrary.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ELS.eLibrary.Model.Librarian;
import com.ELS.eLibrary.Model.Student;
import com.ELS.eLibrary.Repository.LibrarianRepository;
import com.ELS.eLibrary.Repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	
	public Student findById(Long std_id) {
		return studentRepo.getById(std_id);
	}

/*public List<Librarian> findAll() {
/*
/*	return liberRepo.findAll();
/*}*/

	
}
