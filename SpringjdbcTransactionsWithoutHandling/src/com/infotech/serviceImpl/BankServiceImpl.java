package com.infotech.serviceImpl;

import com.infotech.dao.BankDAO;
import com.infotech.exception.InsufficientAccountBalanceException;
import com.infotech.model.Account;
import com.infotech.service.BankService;


public class BankServiceImpl implements BankService {

	private BankDAO bankDAO;
	
	public void setBankDAO(BankDAO bankDAO) {
		this.bankDAO = bankDAO;
	}
	
	public BankDAO getBankDAO() {
		return bankDAO;
	}
	
	@Override
	public void transferFund(Account fromAccount, Account toAccount, Double amount) throws InsufficientAccountBalanceException {
		getBankDAO().withdraw(fromAccount, amount);
		getBankDAO().deposit(toAccount, amount);
	}
}
