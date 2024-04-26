package com.javaguides.Bankingapp.Service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaguides.Bankingapp.Dto.AccountDto;
import com.javaguides.Bankingapp.Entity.Account;
import com.javaguides.Bankingapp.Repository.AccountRepository;
import com.javaguides.Bankingapp.Service.AccountService;
import com.javaguides.Bankingapp.Mapper.*;


@Service
public class AccountServiceImpl implements AccountService {
	
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account savedAccount = AccountMapper.mapToAccount(accountDto);
		Account account = accountRepository.save(savedAccount);
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));

		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		

		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id,double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));

		if (amount > account.getBalance()) {
			throw new RuntimeException("Insufficient Balance!");
		}

		double balance = account.getBalance() - amount;
		account.setBalance(balance);
		Account savedAccount = accountRepository.save(account);

		return AccountMapper.mapToAccountDto(savedAccount);
	}

	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());

	}
}
