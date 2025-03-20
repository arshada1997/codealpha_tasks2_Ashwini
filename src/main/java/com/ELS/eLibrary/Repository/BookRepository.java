package com.ELS.eLibrary.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ELS.eLibrary.Model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

	Optional<Book> findById(long book_id);

	//@Query("Select *  from  Book  b where b.book_id=:book_id")

	//Book findBookById(long book_id);

	//@Query("update Book SET book_copies= book_copies-1 where book_id=:book_id")
	//void update(Book b);

}
