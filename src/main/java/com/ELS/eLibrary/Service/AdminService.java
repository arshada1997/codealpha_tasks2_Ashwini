package com.ELS.eLibrary.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ELS.eLibrary.Model.Admin;
import com.ELS.eLibrary.Repository.AdminRepository;

@Service
public class AdminService {

	@Autowired 
	private AdminRepository adminRepo;
	
	public void save(Admin admin) {

		adminRepo.save(admin);
	}

	public Optional<Admin> findByEmail(String email) {
		
		return adminRepo.findByEmail(email);
	}

	
}
