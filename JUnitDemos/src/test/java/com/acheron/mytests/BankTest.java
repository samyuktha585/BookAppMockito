package com.acheron.mytests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.acheron.exception.DailyLimitExceededException;
import com.acheron.exception.InsufficientBalanceException;
import com.acheron.exception.MinimumBalanceException;
import com.acheron.exception.NegativeNumberException;
import com.acheron.exception.NullNumberException;
import com.acheron.training.Bank;

class BankTest {
	Bank bank;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		bank = new Bank(5000);
	}

	@AfterEach
	void tearDown() throws Exception {
		bank = null;
	}

	@DisplayName("Daily limit test")
	@Test
	public void testDilayLimit() throws DailyLimitExceededException {
		assertThrows(DailyLimitExceededException.class, () -> bank.withdraw(16000), "not equal");
	}

	@Test
	@DisplayName("insufficient bal  test")
	public void testInsufficient() throws InsufficientBalanceException {
		assertThrows(InsufficientBalanceException.class, () -> bank.withdraw(10000));
	}

	@Test
	@DisplayName("withdraw test")
	public void testWithdraw()
			throws InsufficientBalanceException, DailyLimitExceededException, NegativeNumberException, MinimumBalanceException {
		double balance = bank.withdraw(1000);
		assertEquals(4000, balance, "not equal");
	}

	@DisplayName("negitive test")
	@Test
	public void testDepositNeg() throws NegativeNumberException {
		assertThrows(NegativeNumberException.class, () -> bank.withdraw(-1000), "Not equal");
	}

	@DisplayName("Deposit test")
	@Test
	public void testDeposit() throws NegativeNumberException, InsufficientBalanceException {
		double balance = bank.deposit(1000);
		assertEquals(6000, balance, "not equal");
	}

	@DisplayName("invalid test")
	@Test
	public void testgetBalance() throws NullNumberException {
		assertEquals(5000, bank.getBalance(), "not equal");
	}

	@Test
	public void testWithdrawAppliesPenaltyWhenOverdrawn()
			throws InsufficientBalanceException, DailyLimitExceededException, NegativeNumberException {
		// bank.withdraw(2000);
		assertThrows(InsufficientBalanceException.class, () -> bank.withdraw(4500), "Not equal");

	}

}