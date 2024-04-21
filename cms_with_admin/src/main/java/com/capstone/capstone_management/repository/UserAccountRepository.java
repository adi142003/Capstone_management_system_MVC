package com.capstone.capstone_management.repository;

import com.capstone.capstone_management.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    UserAccount findByName(String Name);

    UserAccount findByUsn(String usn);
}

