package com.interview.sonarsource.domain.operations.impl;

import com.interview.sonarsource.domain.operations.Operation;
import com.interview.sonarsource.domain.operations.enums.PrintType;

import java.math.BigDecimal;

public class Print implements Operation {


	private PrintType pringType;
	private Integer year;
	private Integer month;
	private Integer day;

	public Print(PrintType printType, Integer year, Integer month, Integer day){

		this.pringType = printType;
		this.year = year;
		this.month = month;
		this.day = day;
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

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public BigDecimal getAmount() {
		return null;
	}

	public PrintType getPringType() {
		return pringType;
	}

	@Override
	public String toString() {
		return "PRINT" +
				" " + pringType +
				" " + String.format("%04d", year) +
				(month != null ? "/" + String.format("%02d", month)  : "") +
				(day != null ? "/" + String.format("%02d", day) : "");
	}
}
