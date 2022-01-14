package com.interview.sonarsource.input.impl;

import com.interview.sonarsource.domain.operations.Operation;
import com.interview.sonarsource.consumers.OperationConsumer;
import com.interview.sonarsource.domain.operations.impl.Income;
import com.interview.sonarsource.input.OperationLineReader;

import java.math.BigDecimal;


public class IncomeOperationLineReader implements OperationLineReader {
	private final OperationConsumer operationConsumer;

	public IncomeOperationLineReader(OperationConsumer operationConsumer){
		this.operationConsumer = operationConsumer;
	}

	@Override
	public Operation readLine(String[] line) {

		String[] date = line[1].split("/");
		int year = Integer.valueOf(date[0]);
		int month = Integer.valueOf(date[1]);
		int day = Integer.valueOf(date[2]);

		String description = line[2];
		BigDecimal amount = new BigDecimal(line[3]);

		Income income = new Income(year, month, day, description, amount);

		operationConsumer.consume(income);

		return income;
	}
}
