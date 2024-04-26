package com.javaguides.Bankingapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
	
	long id;
	String accountHolderName;
	double balance;

}
