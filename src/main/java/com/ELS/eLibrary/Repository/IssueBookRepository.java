package com.ELS.eLibrary.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ELS.eLibrary.Model.Book;
import com.ELS.eLibrary.Model.EBook;
import com.ELS.eLibrary.Model.IssueBook;

@Repository
public interface IssueBookRepository extends JpaRepository<IssueBook,Long>{

	List<IssueBook> findAllByStatus(String status);

}
