package com.infotech.daoImpl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.infotech.dao.BankDAO;
import com.infotech.exception.InsufficientAccountBalanceException;
import com.infotech.model.Account;
import com.infotech.rowmapper.AccountRowMapper;


public class BankDAOImpl extends JdbcDaoSupport implements BankDAO {
	
	@Override
	public void withdraw(Account fromAccount, Double withdrawAmt) throws InsufficientAccountBalanceException {
		Account accountFromDb = null;
		if(fromAccount != null && fromAccount.getAccountNumber() != null){
			accountFromDb = getAccountFromDb(fromAccount.getAccountNumber());
		}else{
			throw new RuntimeException("Account Number doesn't exits");
		}
		
		if(accountFromDb.getAccountBalance() >= withdrawAmt){
			Double accountBalance = accountFromDb.getAccountBalance()-withdrawAmt;
			String sql="UPDATE icici_bank set account_balance=? WHERE account_no=?;";
			 int update = getJdbcTemplate().update(sql,accountBalance,fromAccount.getAccountNumber());
			 if(update>0)
				 System.out.println("Amount Rs:"+withdrawAmt+" is deducted from account:"+fromAccount.getAccountNumber());
		}else{
			throw new InsufficientAccountBalanceException("Insufficient account balance");
		}
	}

	@Override
	public void deposit(Account toAccount, Double depositAmt) {
		Account accountFromDb = null;
		Double accountBalance = null;
		if(toAccount != null && toAccount.getAccountNumber() != null){
			accountFromDb = getAccountFromDb(toAccount.getAccountNumber());
			accountBalance = accountFromDb.getAccountBalance()+depositAmt;
		}else{
			throw new RuntimeException("Account Number doesn't exits");
		}
		String sql="UPDATE icici_bank set account_balance=? WHERE account_no=?;";
		int update = getJdbcTemplate().update(sql,accountBalance,toAccount.getAccountNumber());
		 if(update>0)
			 System.out.println("Amount Rs: "+depositAmt+" is deposited in Account:"+toAccount.getAccountNumber());
	}
	
	private Account getAccountFromDb(Long accountNumber){
		String SQL ="SELECT * FROM icici_bank WHERE account_no = ?";
		Account dDAccount = getJdbcTemplate().queryForObject(SQL, new AccountRowMapper(), accountNumber);
		return dDAccount;
	}
}
