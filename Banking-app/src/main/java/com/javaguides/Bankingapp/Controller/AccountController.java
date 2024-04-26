package com.javaguides.Bankingapp.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaguides.Bankingapp.Dto.AccountDto;
import com.javaguides.Bankingapp.Service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	//Add account REST API;
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		
		return new ResponseEntity<AccountDto>(accountService.createAccount(accountDto),HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
		}
	
	//Build deposit REST API
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		double amount = request.get("amount");

		AccountDto accountDto = accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);

	}

	// Build withdrawal amount REST API
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);

		return ResponseEntity.ok(accountDto);

	}
    
	@GetMapping
	public ResponseEntity<List<AccountDto>>  getAllAccountDetails(){
		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
		
		
	}
}
