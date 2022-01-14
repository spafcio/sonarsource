package com.interview.sonarsource.consumers.impl;

import com.interview.sonarsource.datastructures.BalanceMap;
import com.interview.sonarsource.domain.operations.Operation;
import com.interview.sonarsource.domain.operations.impl.Print;

import java.math.BigDecimal;
import java.util.List;

public class PrintConsumer {
	private BalanceMap<Integer, BalanceMap> balanceMap;
	private List<BigDecimal> output;

	public PrintConsumer(BalanceMap<Integer, BalanceMap> balanceMap, List<BigDecimal> output) {
		this.balanceMap = balanceMap;
		this.output = output;
	}

	public void consume(Operation operation) {
		Print print = (Print) operation;
		BigDecimal balance;

		switch (print.getPringType()){
			case DAY:
				balance = getDayBalance(operation);
				output.add(balance);
				break;
			case MONTH:
				balance = getMonthBalance(operation);
				output.add(balance);
				break;
			case YEAR:
				balance =  getYearBalance(operation);
				output.add(balance);
				break;
		}
	}

	private BigDecimal getDayBalance(Operation operation) {
		BalanceMap<Integer, BalanceMap> yearMap = this.balanceMap.getOrDefault(operation.getYear(), new BalanceMap<Integer, BalanceMap>());
		BalanceMap<Integer, BalanceMap> monthMap = yearMap.getOrDefault(operation.getMonth(), new BalanceMap<Integer, BalanceMap>());
		BalanceMap<Integer, Operation> dayMap = monthMap.getOrDefault(operation.getDay(), new BalanceMap<Integer, Operation>());
		return dayMap.getBalance();
	}

	private BigDecimal getMonthBalance(Operation operation) {
		BalanceMap<Integer, BalanceMap> yearMap = this.balanceMap.getOrDefault(operation.getYear(), new BalanceMap<Integer, BalanceMap>());
		BalanceMap<Integer, BalanceMap> monthMap = yearMap.getOrDefault(operation.getMonth(), new BalanceMap<Integer, BalanceMap>());
		return monthMap.getBalance();
	}

	private BigDecimal getYearBalance(Operation operation) {
		BalanceMap<Integer, BalanceMap> yearMap = this.balanceMap.getOrDefault(operation.getYear(), new BalanceMap<Integer, BalanceMap>());
		return yearMap.getBalance();
	}
}
