package com.ELS.eLibrary.config;

import java.nio.file.Files;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ELS.eLibrary.Model.Admin;
import com.ELS.eLibrary.Model.Book;
import com.ELS.eLibrary.Model.EBook;
import com.ELS.eLibrary.Model.IssueBook;
import com.ELS.eLibrary.Model.Librarian;
import com.ELS.eLibrary.Model.Student;
import com.ELS.eLibrary.Repository.AdminRepository;
import com.ELS.eLibrary.Repository.BookRepository;
import com.ELS.eLibrary.Repository.EBookRepository;
import com.ELS.eLibrary.Repository.IssueBookRepository;
import com.ELS.eLibrary.Repository.LibrarianRepository;
import com.ELS.eLibrary.Repository.StudentRepository;
import com.ELS.eLibrary.util.constatnt.Role;

import jakarta.persistence.criteria.Path;


@Component
public class DatabaseSeeder implements CommandLineRunner{

	 @Autowired
	 private AdminRepository adminRepository;
     
	 @Autowired
	 private PasswordEncoder passwordEncoder;

     @Autowired
     private LibrarianRepository librarianRepository;
       
     @Autowired
     private BookRepository bookRepository;
    
     @Autowired
     private EBookRepository ebookRepository;
    
     @Autowired
     private IssueBookRepository issuebookRepository;
    
     @Autowired
     private StudentRepository studentRepository;
    
     
	@Override
	public void run(String... args) throws Exception {
		
		Admin admin=new Admin();
		admin.setId(1);
		admin.setName("Neha Joshi");
		admin.setEmail("neha@gmail.com");
		admin.setContactNo(8788835781L);
		admin.setRole(Role.ADMIN);
	    admin.setPassword(passwordEncoder.encode("123"));
		adminRepository.save(admin);
		
		Librarian liber=new Librarian();
		liber.setId(1);
		liber.setName("Jahova");
		liber.setEmail("jahova@gmail.com");
		liber.setContactNo(9823123278L);
		liber.setPassword(passwordEncoder.encode("123"));
		liber.setRole(Role.LIBRARIAN);
        librarianRepository.save(liber);	
        
        
        Book books=new Book();
        books.setCatagory("Technology");
        books.setBook_name("DBMS");
        books.setAuthor("Towser");
        books.setPublisher("B.K");
        books.setBook_copies(5);
        books.setRecieved(LocalDate.of(2025,02,25));
        bookRepository.save(books);
     
        Book book=new Book();
        book.setCatagory("Science Fiction");
        book.setBook_name("The Time Machine");
        book.setAuthor("H.G. Wells");
        book.setPublisher("Lemits");
        book.setBook_copies(2);
        book.setRecieved(LocalDate.of(2025,02,25));
        bookRepository.save(book);
     
        IssueBook issueBook=new IssueBook();
        issueBook.setBook_name("DBMS");
		issueBook.setBook_id(1);
		issueBook.setName("Riya Roy");
		issueBook.setStd_id(1);
		issueBook.setIssue_date(LocalDate.of(2025,03,02));
		issueBook.setDue_date(LocalDate.of(2025,03,22));
		issueBook.setStatus("Issued");
		issuebookRepository.save(issueBook);
		
		Student std=new Student();
		std.setName("Riya Roy");
		std.setEmail("riya@gmail.com");
	    std.setPassword(passwordEncoder.encode("123"));

		std.setPhoneNo("7685768797");
		//std.setDate_of_birth(LocalDate.(1998,02,12));
		std.setGender("FEMALE");
		std.setAddress("S.Y.Colony");
		std.setCourse("IF1G");
		std.setBranch("Information Technology");
		std.setRole(Role.STUDENT);
		studentRepository.save(std);
		
        java.nio.file.Path filePath = new ClassPathResource("uploads/DATABASE MANAGEMENT SYSTEMS.pdf").getFile().toPath();
        byte[] fileData = Files.readAllBytes(filePath);

		EBook ebooks=new EBook();
		ebooks.setEcatagory("Technology");
		ebooks.setFileName("DATABASE MANAGEMENT SYSTEMS");
		ebooks.setContentType("application/pdf");
		ebooks.setData(fileData);

		ebookRepository.save(ebooks);

	   java.nio.file.Path filePath1 = new ClassPathResource("uploads/the-new-day-chapter-obooko.pdf").getFile().toPath();
	   byte[] fileData1 = Files.readAllBytes(filePath1);

			EBook ebook=new EBook();
			ebook.setEcatagory("Technology");
			ebook.setFileName("the-new-day-chapter-obooko");
			ebook.setContentType("application/pdf");
			ebook.setData(fileData1);

		ebookRepository.save(ebook);
    
		
	}
	
}
