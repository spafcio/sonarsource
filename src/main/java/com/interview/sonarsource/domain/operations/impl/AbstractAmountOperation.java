package com.interview.sonarsource.domain.operations.impl;

import com.interview.sonarsource.domain.operations.Operation;

import java.math.BigDecimal;

public abstract class AbstractAmountOperation implements Operation {
	private Integer year;
	private Integer month;
	private Integer day;
	private String description;
	private BigDecimal amount;

	public AbstractAmountOperation(int year, int month, int day, String description, BigDecimal amount){
		this.year = year;
		this.month = month;
		this.day = day;
		this.description = description;
		this.amount = amount;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public Integer getYear() {
		return year;
	}

	@Override
	public Integer getMonth() {
		return month;
	}

	@Override
	public Integer getDay() {
		return day;
	}
	}
