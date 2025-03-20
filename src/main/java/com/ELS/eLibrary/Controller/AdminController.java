package com.ELS.eLibrary.Controller;

import java.security.Principal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ELS.eLibrary.Model.Student;
import com.ELS.eLibrary.Model.Admin;
import com.ELS.eLibrary.Model.Librarian;
import com.ELS.eLibrary.Repository.LibrarianRepository;
import com.ELS.eLibrary.Repository.StudentRepository;
import com.ELS.eLibrary.Service.AdminService;
import com.ELS.eLibrary.Service.LibrarianService;
import com.ELS.eLibrary.Service.StudentService;
import com.ELS.eLibrary.util.constatnt.Role;

 
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private LibrarianService librarianService;
	
	@Autowired
	private StudentService studentService;
	

	@Autowired
	private LibrarianRepository librarianRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@GetMapping("/dashboard")
	public String admin_page(Model model,Principal principal) {
	    // Admin admin = new Admin();

	    String email = "email";
	    
	    if (principal != null) {
	      email = principal.getName();
	    }
	    Optional<Admin> adminAccount = adminService.findByEmail(email);
	    if (adminAccount.isPresent()) {
	      Admin admin = adminAccount.get();
	      model.addAttribute("admin", admin);
		    System.out.println("User"+admin.getName());

	    }else{
	    System.out.println("Invalid User");}
	    return "admin/dashboard";
	}
	
    @PreAuthorize("isAuthenticated()")
	@GetMapping("/addLibrarian")
	public String addLibrarian(Model model) {
    	
    	Librarian lib=new Librarian();
    	model.addAttribute("lib",lib);

	   	    return "admin/addLibrarian";
	}
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/saveLibrarian")
    public String saveLibrarian(@ModelAttribute Librarian liber,Model model)
    {
    	liber.setRole(Role.LIBRARIAN);
    	liber.setPassword(passwordEncoder.encode(liber.getPassword()));
    	librarianService.save(liber);
    	model.addAttribute("msg","Added");
    	return "admin/addLibrarian";
    }
    
    @PreAuthorize("isAuthenticated()")
   	@GetMapping("/viewLibrarian")
   	public String viewLibrarian(Model model) {
       	
      // 	Librarian liber=new Librarian();
    	List<Librarian> liber=librarianRepo.findAll();
       	model.addAttribute("liber",liber);
       	model.addAttribute("msg","success");

   	   	    return "admin/viewLibrarian";
   	}
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/updateLibrarian/{id}")
    public String updateLibrarian(@PathVariable("id") Long id,Model model)
    {
        Librarian liber=librarianService.findById(id);    	
    	model.addAttribute("liber",liber);
    	System.out.println("Updated");
    	return "admin/updateLibrarian";
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateLibrarian/{id}")
    public String updatedLibrarian(@ModelAttribute Librarian liber,Model model)
    {

        //Librarian liber=librarianService.findById(id);    	
    	model.addAttribute("liber",liber);
    
    	librarianRepo.save(liber);
    	model.addAttribute("msg","Updated Successfully");

    	System.out.println("Updated Successfully");
    	return "admin/viewLibrarian";

    }
    
    @PreAuthorize("isAuthemticated")
    @GetMapping("/deleteLibrarian/{id}")
    public String deleteLibrarian(@PathVariable("id") Long id,Model model)
    {

        Librarian liber=librarianService.findById(id);    	
    	model.addAttribute("liber",liber);
    
        librarianRepo.deleteById(id);
        model.addAttribute("msg","Deleted Successfully");
        System.out.println("Deleted Successfully");
        return "admin/viewLibrarian";
    }
    
    @PreAuthorize("isAuthenticated()")
   	@GetMapping("/addStudent")
   	public String addStudent(Model model) {
       	
       	Student student=new Student();
       	model.addAttribute("student",student);

   	   	    return "admin/addStudent";
   	}
       
       @PreAuthorize("isAuthenticated()")
       @PostMapping("/saveStudent")
       public String saveStudent(@ModelAttribute Student student,Model model)
       {
       	student.setRole(Role.STUDENT);
    	student.setPassword(passwordEncoder.encode(student.getPassword()));

       	studentRepo.save(student);
       	model.addAttribute("msg","Student Added");
       	return "admin/addStudent";
       }
       
       @PreAuthorize("isAuthenticated()")
      	@GetMapping("/viewStudent")
      	public String viewStudent(Model model) {
          	
         // 	Librarian liber=new Librarian();
       	List<Student> student=studentRepo.findAll();
          	model.addAttribute("student",student);
          	model.addAttribute("msg","success");

      	   	    return "admin/viewStudent";
      	}

       @PreAuthorize("isAuthenticated()")
       @GetMapping("/updateStudent/{std_id}")
       public String updateStudent(@PathVariable("std_id") Long id,Model model)
       {
        Student student=studentService.findById(id);    	
       	model.addAttribute("student",student);
       	return "admin/updateStudent";
       }
       
       @PreAuthorize("isAuthenticated()")
       @PostMapping("/updateStudent/{id}")
       public String updatedStudent(@ModelAttribute Student student,Model model)
       {

           //Librarian liber=librarianService.findById(id);    	
       	model.addAttribute("student",student);
       
       	studentRepo.save(student);
       	model.addAttribute("msg","Updated Successfully");

       	System.out.println("Updated Successfully");
       	return "admin/updateStudent";

       }
       
       @PreAuthorize("isAuthemticated")
       @GetMapping("/deleteStudent/{std_id}")
       public String deleteStudent(@PathVariable("std_id") Long std_id,Model model)
       {

           Student student=studentService.findById(std_id);    	
       	model.addAttribute("student",student);
       
       	studentRepo.deleteById(std_id);
           model.addAttribute("msg","Deleted Successfully");
           System.out.println("Deleted Successfully");
           return "admin/updateStudent";
       }
       

}
