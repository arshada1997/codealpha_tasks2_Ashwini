package com.ELS.eLibrary.Controller;

import java.io.IOException;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ELS.eLibrary.Model.Book;
import com.ELS.eLibrary.Model.EBook;
import com.ELS.eLibrary.Model.IssueBook;
import com.ELS.eLibrary.Model.Librarian;
import com.ELS.eLibrary.Model.Student;
import com.ELS.eLibrary.Repository.BookRepository;
import com.ELS.eLibrary.Repository.EBookRepository;
import com.ELS.eLibrary.Repository.IssueBookRepository;
import com.ELS.eLibrary.Repository.LibrarianRepository;
import com.ELS.eLibrary.Repository.StudentRepository;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

	
	@Autowired
	private LibrarianRepository librarianRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private EBookRepository ebookRepo;
	
	@Autowired
	private IssueBookRepository issuebookRepo;
	
	 
    @Autowired
    private StudentRepository studentRepository;
  
	
	@GetMapping("/dashboard")
	public String dashboard(Model model)
	{
		String email=" ";
		Optional<Librarian> librarian=librarianRepo.findByEmail(email);
		Librarian liber;
		if(librarian.isPresent())
		{
			liber=librarian.get();
			model.addAttribute(liber);
		}
		return "librarian/welcome";
		
	}
	
	@GetMapping("/addBooks")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String addBooks_page(Model model) {
		
		Book books=new Book();
		model.addAttribute("books",books);
		return "librarian/addBooks";
	}
	
	@PostMapping("/addBooks")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String addBooks(@ModelAttribute Book books,Model model,Principal principal) {
		
		if(principal!=null)
		{	
			books.setCatagory(books.getCatagory());
			books.setBook_name(books.getBook_name());
			books.setAuthor(books.getAuthor());
			books.setPublisher(books.getPublisher());
			books.setBook_copies(books.getBook_copies());
			books.setRecieved(books.getRecieved());//format YYYY-MM-DD
			bookRepo.save(books);
			model.addAttribute("success","Books added successfully!!");
			System.out.println("Books added successfully!!");

		}
		else{
		  System.out.println("Invalid USer!!");
		}
		return "librarian/addBooks";
	}
	
	
	
	@GetMapping("/addEBooks")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String addEBooks_page(Model model) {
		
		EBook ebooks=new EBook();
		model.addAttribute("ebooks",ebooks);
		return "librarian/addBooks";
	}
	
	
	@PostMapping("/addEBooks")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String addEBooks(@ModelAttribute EBook ebooks,@RequestParam("file") MultipartFile file,Model model,Principal principal) {
		
		if(principal!=null)
		{	
			try {
				System.out.println("Adding EBooks ");

				ebooks.setEcatagory(ebooks.getEcatagory());
				ebooks.setFileName(file.getOriginalFilename());
				ebooks.setContentType(file.getContentType());
				ebooks.setData(file.getBytes());
            
			    ebookRepo.save(ebooks);
	          //  pdfFileService.uploadPdf(file);

				model.addAttribute("successEB","EBooks added successfully!!");
				System.out.println("EBooks added successfully!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Books added Failed!!");

			}

		}
		else{
		  System.out.println("Invalid USer!!");
		}
         return "librarian/addBooks";
	}
	
	@GetMapping("/viewBooks")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String viewBooks(Model model)
	{
		List<Book> books=bookRepo.findAll();
		model.addAttribute("books",books);
		return "librarian/viewBooks";
	}
	
	@GetMapping("/updateBooks/{book_id}")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String getupdateBooks(@PathVariable("book_id") long book_id,Model model)
	{
		Optional<Book> optionalBook=bookRepo.findById(book_id);
		if(optionalBook.isPresent())
		{
			Book book=optionalBook.get();
			model.addAttribute("book",book);
			
		}
		return "librarian/updateBooks";
	}
	
	@PostMapping("/updateBooks/{book_id}")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String updateBook(@ModelAttribute Book books,Model model)
	{
		books.setCatagory(books.getCatagory());
		books.setBook_name(books.getBook_name());
		books.setAuthor(books.getAuthor());
		books.setPublisher(books.getPublisher());
		books.setBook_copies(books.getBook_copies());
		books.setRecieved(books.getRecieved());//format YYYY-MM-DD
		
		bookRepo.save(books);
		return "librarian/updateBooks";
	}
	
	@GetMapping("deleteBooks/{book_id}")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String deleteBooks(@PathVariable("book_id") long book_id,Model model)
	{
		bookRepo.deleteById(book_id);
		return "librarian/viewBooks";
	}
	
	@GetMapping("/issueBooks")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String issue_Books(Model model)
	{
	   IssueBook issueBook=new IssueBook();
	   model.addAttribute("issueBook",issueBook);

	   List<Book> books=bookRepo.findAll();
	   model.addAttribute("books",books);
	   return "librarian/issueBook";
	}	
	

	@PostMapping("/issueBooks")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String issue_Book(@RequestParam("book_name") String book_name,@RequestParam("book_id") int book_id,@ModelAttribute IssueBook issueBook,Model model)
	{
		Optional<Student> std=studentRepository.findById(issueBook.getStd_id());
		Optional<Book> book=bookRepo.findById(issueBook.getBook_id());
		Book b=book.get();
        Student s=std.get();
		if(s.getName().equals(issueBook.getName()) && s.getStd_id()==s.getStd_id() && b.getBook_id()==(issueBook.getBook_id()) && b.getBook_name().equals(issueBook.getBook_name()))
		{
			 int book_copies=book.get().getBook_copies();
			 String bookName=book.get().getBook_name();
			 System.out.println("Book Copies :"+book_copies);
			 System.out.println("Book Name :"+bookName+" "+issueBook.getBook_id());
			 System.out.println("Hii!!!!!");

			 if(s.getName().equals(issueBook.getName()) && book_copies>0)
			 {	 
					System.out.println("Issue Book Entered");

		issueBook.setBook_name(issueBook.getBook_name());
		issueBook.setBook_id(issueBook.getBook_id());
		issueBook.setName(issueBook.getName());
		issueBook.setStd_id(issueBook.getStd_id());
		issueBook.setIssue_date(issueBook.getIssue_date());
		issueBook.setDue_date(issueBook.getDue_date());
		issueBook.setStatus("Issued");
		issuebookRepo.save(issueBook);
		model.addAttribute("msg","Book Issued Successfully,Late Penalty is 20rs.PerDay");
		System.out.println("succedd");
		//Book b=new Book();
		b.setBook_copies(b.getBook_copies()-1);
		bookRepo.save(b);
		System.out.println("Updated Books :"+b);

		
	      }
			 else{
					System.out.println("failed,Check for Student Name/Books not available"+book_copies);
					model.addAttribute("msg","Book Issued Failed");

			 }
	   }
		else {
			System.out.println("failed, Invalid details Check proper details");
			model.addAttribute("msg","Book Issued Failed");

		}
		return "librarian/issueBook";
	}
	
	@GetMapping("/returnBook")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String return_Books_page(Model model)
	{
		   List<Book> books=bookRepo.findAll();
		   model.addAttribute("books",books);
		 //  model.addAttribute("issueBook",issueBook);

	  	   return "librarian/returnBook";
	}	
	
	@PostMapping("/returnBook")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String return_Books(@RequestParam("book_name") String book_name,
			@RequestParam("book_id") int book_id,
			@RequestParam("name") String name,
			@RequestParam("std_id") int std_id,
			@RequestParam("issue_date") LocalDate issue_date,
			@RequestParam("due_date") LocalDate due_date,
			Model model)
	{
		   List<IssueBook> issueBook=issuebookRepo.findAll();

		   List<Book> books=bookRepo.findAll();
		   model.addAttribute("books",books);
			System.out.println("Book Details:");

		   for(IssueBook ib:issueBook)
		   {
				System.out.println("Book Details:"+ib.getBook_id());

		   int bid=ib.getBook_id();
		   String bname=ib.getBook_name();
		   String sname=ib.getName();
		   int sid=ib.getStd_id();
		   LocalDate issDate=ib.getIssue_date();
		   LocalDate dueDate=ib.getDue_date();
		   String sts=ib.getStatus();
		   
		   System.out.println(bid+" "+bname+" "+sid+" "+sname+" "+issDate+" "+dueDate+" "+sts);
			System.out.println("Form Details:");

		   System.out.println(book_id+" "+book_name+" "+std_id+" "+name+" "+issue_date+" "+due_date);

		   if(bid ==  book_id  && bname.equals(book_name) && sname.equals(name))//&& sid == std_id && issDate==issue_date && sts=="Issued")
		   {
				System.out.println("Hii Book Updated");
 
			    Optional<Book> optionalbook=bookRepo.findById(book_id);
			   
			    if(optionalbook.isPresent())
			    {
					System.out.println("Hello Book Updated");

			    	Book b=optionalbook.get();
			    
				    b.setBook_copies(b.getBook_copies()+1);
				
				bookRepo.save(b);
				System.out.println("Book Updated");
				}
			    ib.setStatus("Returned");
				ib.setDue_date(due_date);
				issuebookRepo.save(ib);
				System.out.println("IssueBook Updated");
				model.addAttribute("returnStatus","Book Reurned succssfully");
		        long daysBetween = ChronoUnit.DAYS.between(dueDate, due_date);
	            System.out.println("no.of days:"+daysBetween);

                if(daysBetween>0)
                {
                	long fine=(daysBetween)*20;
	              System.out.println("Penalty is Rs.:"+fine);
	              model.addAttribute("fine","Penalty is :"+fine);
                }
                else {
  	              System.out.println("No Penalty :");
	              model.addAttribute("fine","No Penalty ");

                }


		   }
		   else {
			   System.out.println("Invalid Details");
				model.addAttribute("returnStatus","Book Reurned Failed,Invalid Details");
		   }
		 }
			   
	  	   return "librarian/returnBook";
	}	
	
	@GetMapping("/defaulterList")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String defaulterList_page(Model model)
	{
		   String status="Issued";
		   List<IssueBook> issuebooks=issuebookRepo.findAllByStatus(status);
		   
		   model.addAttribute("issuebooks",issuebooks);
		 //  model.addAttribute("issueBook",issueBook);

	  	   return "librarian/defaulter";
	}	

	@GetMapping("/searchBooks")
	@PreAuthorize("ROLE_LIBRARIAN")
	public String searchBooks(Model model)
	{
		   
		  // model.addAttribute("issuebooks",issuebooks);
		 //  model.addAttribute("issueBook",issueBook);

	  	   return "librarian/searchBooks";
	}	
	

	

}
