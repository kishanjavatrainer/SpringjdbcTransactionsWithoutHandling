package com.infotech.dao;

import com.infotech.exception.InsufficientAccountBalanceException;
import com.infotech.model.Account;

public interface BankDAO {

	public abstract void withdraw(Account fromAccount,Double withdrawAmt) throws InsufficientAccountBalanceException;
	public abstract void deposit(Account toAccount,Double depositAmt);
}
