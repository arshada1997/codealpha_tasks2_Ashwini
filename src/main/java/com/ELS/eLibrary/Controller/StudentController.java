package com.ELS.eLibrary.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ELS.eLibrary.Model.Book;
import com.ELS.eLibrary.Model.EBook;
import com.ELS.eLibrary.Model.Librarian;
import com.ELS.eLibrary.Model.Student;
import com.ELS.eLibrary.Repository.BookRepository;
import com.ELS.eLibrary.Repository.EBookRepository;
import com.ELS.eLibrary.Repository.LibrarianRepository;
import com.ELS.eLibrary.Repository.StudentRepository;
import com.ELS.eLibrary.Service.EBookService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepository studentRepo;


	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private EBookService ebookService;


	@Autowired
	private EBookRepository ebookRepo;

	@GetMapping("/dashboard")
	public String dashboard(Model model)
	{
		String email=" ";
		Optional<Student> student=studentRepo.findByEmail(email);
		Student std;
		if(student.isPresent())
		{
			std=student.get();
			model.addAttribute(std);
		}
		return "student/student";
		
	}
	@GetMapping("/viewBooks")
	@PreAuthorize("ROLE_STUDENT")
	public String viewBooks(Model model)
	{
		List<Book> books=bookRepo.findAll();
		model.addAttribute("books",books);
		return "student/viewBooks";
	}

	@GetMapping("/viewEBooks")
	@PreAuthorize("ROLE_STUDENT")
	public String viewEBooks(Model model)
	{
		List<EBook> books=ebookRepo.findAll();
		model.addAttribute("books",books);
		return "student/viewEBooks";
	}
	 @GetMapping("/view/{ebook_id}")
		@PreAuthorize("ROLE_STUDENT")

	    public ResponseEntity<byte[]> viewFile(@PathVariable("ebook_id") Long ebook_id) {
	    		
	    	  Optional<EBook> optionalFile = ebookService.getFile(ebook_id);

	    	    if (optionalFile.isPresent()) {
	    	        EBook file = optionalFile.get(); // Get the Ebook object

	    	        if (file.getData() == null || file.getContentType() == null) {
	    	            return ResponseEntity.noContent().build(); // Return 204 No Content if file data is missing
	    	        }

	    	        return ResponseEntity.ok()
	    	                .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
	    	                .body(file.getData()); // Return the PDF file as a response
	    	    } else {
	    	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if file not found
	    	    }}}