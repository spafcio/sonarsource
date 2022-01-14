package com.interview.sonarsource.domain.operations.impl;

import java.math.BigDecimal;

public class Income extends AbstractAmountOperation {
	public Income(int year, int month, int day, String description, BigDecimal amount) {
		super(year, month, day, description, amount);
	}

	@Override
	public String toString() {
		return "INCOME " +
				String.format("%04d", getYear()) +
				"/" + String.format("%02d", getMonth()) +
				"/" + String.format("%02d", getDay()) +
				" " + getDescription() +
				" " + getAmount();
	}
}
