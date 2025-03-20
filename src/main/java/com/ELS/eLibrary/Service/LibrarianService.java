package com.ELS.eLibrary.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ELS.eLibrary.Model.Librarian;
import com.ELS.eLibrary.Repository.LibrarianRepository;

@Service
public class LibrarianService {

	@Autowired
	private LibrarianRepository liberRepo;
	
	public Librarian save(Librarian liber) {
        
		return liberRepo.save(liber);
	}

	public Librarian findById(Long id) {
		return liberRepo.getById(id);
	}

/*public List<Librarian> findAll() {
/*
/*	return liberRepo.findAll();
/*}*/

	
}
