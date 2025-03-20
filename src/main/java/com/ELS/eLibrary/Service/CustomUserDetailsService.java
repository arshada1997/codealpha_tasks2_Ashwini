package com.ELS.eLibrary.Service;

import java.util.ArrayList;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ELS.eLibrary.Model.Admin;
import com.ELS.eLibrary.Model.Librarian;
import com.ELS.eLibrary.Model.Student;
import com.ELS.eLibrary.Repository.AdminRepository;
import com.ELS.eLibrary.Repository.LibrarianRepository;
import com.ELS.eLibrary.Repository.StudentRepository;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	    @Autowired
	    private AdminRepository adminRepo;
	    

	    @Autowired
	    private LibrarianRepository librarianRepo;
	    
	    @Autowired
		private StudentRepository studentRepo;

	    
//	    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);


	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	        List<SimpleGrantedAuthority> grantedAutority = new ArrayList<>();
	        Optional<Admin> admin = adminRepo.findByEmail(email);
	      //  logger.info("User Name:", admin);

	        if (admin.isPresent()) {
	            Admin user = admin.get();
	            System.out.println("User:"+user);
	            grantedAutority.add(new SimpleGrantedAuthority(user.getRole().getAuthority()));
	        //    logger.debug("Checking admin availability :",user.getRole(),user.getEmail());

	            return new User(user.getEmail(), user.getPassword(), grantedAutority);
	        }
	        else {
	           // logger.error("Error");

	        }
	        
	        Optional<Librarian> librarian=librarianRepo.findByEmail(email);
	        Librarian liber;
	        if(librarian.isPresent())
	        {
	        	liber=librarian.get();
	        	System.out.println("Librarian :"+liber);
	        	grantedAutority.add(new SimpleGrantedAuthority(liber.getRole().getAuthority()));
	        	return new User(liber.getEmail(),liber.getPassword(),grantedAutority);
	        }
	        

	        Optional<Student> student=studentRepo.findByEmail(email);
	        Student std;
	        if(student.isPresent())
	        {
	        	std=student.get();
	        	System.out.println("Student :"+std);
	        	grantedAutority.add(new SimpleGrantedAuthority(std.getRole().getAuthority()));
	        	return new User(std.getEmail(),std.getPassword(),grantedAutority);
	        }
	       throw new UsernameNotFoundException("User Not Found");
	    }

}
