package com.interview.sonarsource.domain.operations.impl;

import java.math.BigDecimal;

public class Expense extends AbstractAmountOperation {

	public Expense(int year, int month, int day, String description, BigDecimal amount) {
		super(year, month, day, description, amount.negate());
	}

	@Override
	public String toString() {
		return "EXPENSE " +
			String.format("%04d", getYear()) +
				"/" + String.format("%02d", getMonth()) +
				"/" + String.format("%02d", getDay()) +
				" " + getDescription() +
				" " + getAmount().negate();
	}
}
