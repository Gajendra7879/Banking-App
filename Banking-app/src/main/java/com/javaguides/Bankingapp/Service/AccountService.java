package com.javaguides.Bankingapp.Service;

import java.util.List;

import com.javaguides.Bankingapp.Dto.AccountDto;

public interface AccountService {
	
	AccountDto createAccount(AccountDto accountDto);
	
	AccountDto getAccountById(Long id);
	
	AccountDto deposit(Long id,double amount);
	
	AccountDto withdraw(Long id,double amount);
	
	List<AccountDto> getAllAccounts();

}
