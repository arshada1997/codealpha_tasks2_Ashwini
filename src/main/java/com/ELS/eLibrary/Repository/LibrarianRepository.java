package com.ELS.eLibrary.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ELS.eLibrary.Model.Admin;
import com.ELS.eLibrary.Model.Librarian;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian,Long>{

	Librarian save(Librarian liber);

	//@Query("SELECT L FROM librarian L")
	List<Librarian> findAll();
	
	Optional<Librarian> findByEmail(String email);
	

}
