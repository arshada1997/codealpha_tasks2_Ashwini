package com.ELS.eLibrary.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ELS.eLibrary.Model.Book;
import com.ELS.eLibrary.Model.EBook;

@Repository
public interface EBookRepository extends JpaRepository<EBook,Long>{

}
