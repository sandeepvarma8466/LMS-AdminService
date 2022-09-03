package com.blz.adminservice.repository;

import com.blz.adminservice.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long> {
    Optional<AdminModel> findByemailId(String emailId);
}
