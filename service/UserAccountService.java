package com.capstone.capstone_management.service;

import com.capstone.capstone_management.dto.UserAccountDto;
import com.capstone.capstone_management.models.UserAccount;
import org.apache.catalina.User;

import java.util.List;

public interface UserAccountService {
    List<UserAccountDto> findAllUserAccounts();
    UserAccount saveUserAccount(UserAccount userAccount);
    UserAccount LoginAccount(UserAccount userAccount);
    String GetPassword(String Name);
}
