package com.capstone.capstone_management.service.impl;

import com.capstone.capstone_management.dto.UserAccountDto;
import com.capstone.capstone_management.models.UserAccount;
import com.capstone.capstone_management.repository.UserAccountRepository;
import com.capstone.capstone_management.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public List<UserAccountDto> findAllUserAccounts() {
        List<UserAccount> userAccounts = userAccountRepository.findAll();
        return userAccounts.stream().map((userAccount)->mapToUserAccountDto(userAccount)).collect(Collectors.toList());
    }

    @Override
    public UserAccount saveUserAccount(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount LoginAccount(UserAccount userAccount) {
       UserAccount user = userAccountRepository.findByUserID(userAccount.getUserID());
       return user;
    }

    private UserAccountDto mapToUserAccountDto(UserAccount userAccount) {
        UserAccountDto userAccountDto = UserAccountDto.builder()
                .UserID(userAccount.getUserID())
                .Name(userAccount.getName())
                .Gender(userAccount.getGender())
                .Role(userAccount.getRole())
                .Department(userAccount.getDepartment())
                .build();
        return userAccountDto;
    }
}
