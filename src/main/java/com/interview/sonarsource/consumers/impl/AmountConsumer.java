package com.interview.sonarsource.consumers.impl;

import com.interview.sonarsource.datastructures.BalanceMap;
import com.interview.sonarsource.domain.operations.Operation;
import com.interview.sonarsource.consumers.OperationConsumer;

public class AmountConsumer implements OperationConsumer {

	private BalanceMap<Integer, BalanceMap> balanceMap;

	public AmountConsumer(BalanceMap<Integer, BalanceMap> balanceMap) {
		this.balanceMap = balanceMap;
	}

	@Override
	public void consume(Operation operation) {
		BalanceMap<Integer, BalanceMap> yearMap = balanceMap.getOrDefault(operation.getYear(), new BalanceMap());
		BalanceMap<Integer, BalanceMap> monthMap = yearMap.getOrDefault(operation.getMonth(), new BalanceMap());
		BalanceMap<Integer, Operation> dayMap = monthMap.getOrDefault(operation.getDay(), new BalanceMap());

		yearMap.add(operation.getAmount());
		monthMap.add(operation.getAmount());
		dayMap.add(operation.getAmount());

		dayMap.put(operation.getDay(), operation);
		monthMap.put(operation.getDay(), dayMap);
		yearMap.put(operation.getMonth(), monthMap);
		balanceMap.put(operation.getYear(), yearMap);
	}
}
