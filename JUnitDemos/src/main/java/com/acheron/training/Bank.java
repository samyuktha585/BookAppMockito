package com.acheron.training;

import com.acheron.exception.DailyLimitExceededException;
import com.acheron.exception.InsufficientBalanceException;
import com.acheron.exception.MinimumBalanceException;
import com.acheron.exception.NegativeNumberException;

public class Bank {
	double balance;
double minBalance=1000;
	public Bank(double balance) {
		this.balance = balance;
	}

	public double withdraw(double amount)
			throws InsufficientBalanceException, DailyLimitExceededException, NegativeNumberException, MinimumBalanceException {
		if (amount < 0)
			throw new NegativeNumberException("negitive value given");
		else if (amount > 15000)
			throw new DailyLimitExceededException("Sorry..!!Daily limit exceeded..!!");
		else if (amount > balance)
			throw new InsufficientBalanceException("insufficinet balance please check..!!");
		else if((balance-amount) < minBalance)
			throw new MinimumBalanceException("Penalty..!!");
		else
			return balance = balance - amount;
	}

	public double deposit(double amount) throws InsufficientBalanceException {
	
		return balance = balance + amount;
	}

	public double getBalance() {
		return balance;
	}	
}
