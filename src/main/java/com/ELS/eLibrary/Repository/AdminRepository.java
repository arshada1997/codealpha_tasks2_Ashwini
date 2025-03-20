package com.ELS.eLibrary.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ELS.eLibrary.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{

	Admin save(Admin admin);

	Optional<Admin> findByEmail(String email);

}
