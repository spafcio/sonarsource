package com.interview.sonarsource.service.input.impl;

import com.interview.sonarsource.datastructures.BalanceMap;
import com.interview.sonarsource.domain.operations.Operation;
import com.interview.sonarsource.consumers.OperationConsumer;
import com.interview.sonarsource.consumers.impl.AmountConsumer;
import com.interview.sonarsource.consumers.impl.PrintConsumer;
import com.interview.sonarsource.domain.operations.enums.OperationType;
import com.interview.sonarsource.input.OperationLineReader;
import com.interview.sonarsource.input.impl.ExpenseOperationLineReader;
import com.interview.sonarsource.input.impl.IncomeOperationLineReader;
import com.interview.sonarsource.input.impl.PrintOperationLineReader;
import com.interview.sonarsource.service.input.BalanceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimpleBalanceService implements BalanceService {
	private Map<OperationType, OperationLineReader> inputReaderMapping = new HashMap();

	private BalanceMap balanceMap = new BalanceMap();
	private List<BigDecimal> output = new ArrayList<>();

	private OperationConsumer amountConsumer = new AmountConsumer(balanceMap);
	private PrintConsumer printConsumer = new PrintConsumer(balanceMap, output);

	public SimpleBalanceService() {
		inputReaderMapping.put(OperationType.INCOME, new IncomeOperationLineReader(amountConsumer));
		inputReaderMapping.put(OperationType.EXPENSE, new ExpenseOperationLineReader(amountConsumer));
		inputReaderMapping.put(OperationType.PRINT, new PrintOperationLineReader(printConsumer));
	}

	@Override
	public List<BigDecimal> calculateBalance(List<String> inputLines) {
		convertToOperations(inputLines);

		return output;
	}

	List<Operation> convertToOperations(List<String> inputLines){
		return inputLines.stream().map(this::convertLineToOperation).collect(Collectors.toList());
	}

	private Operation convertLineToOperation(String line) {
		String[] lineBreakdown = line.split(" ");

		OperationType operationType = OperationType.valueOf(lineBreakdown[0]);

		return inputReaderMapping.get(operationType).readLine(lineBreakdown);
	}
}
