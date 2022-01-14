package com.interview.sonarsource.service.input.impl;

import com.interview.sonarsource.domain.operations.Operation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SimpleUserInputReaderTest {

	@Test
	void readOperations() {

		//given
		List<String> input = getInput();

		SimpleBalanceService balanceService = new SimpleBalanceService();

		//when
		List<String> actual = balanceService.convertToOperations(input).stream().map(Operation::toString).collect(Collectors.toList());

		//then
		assertLinesMatch(input, actual);

	}

	private List<String> getInput() {
		List<String> input = new ArrayList<>();

		input.add("INCOME 2020/01/01 gift 500");
		input.add("PRINT YEAR 2021");
		input.add("PRINT YEAR 2020");
		input.add("EXPENSE 2020/10/12 coffee 5");
		input.add("EXPENSE 2020/10/11 lunch 25");
		input.add("PRINT YEAR 2020");
		input.add("INCOME 2020/10/01 stock-divident 40");
		input.add("PRINT MONTH 2020/11");
		input.add("PRINT MONTH 2020/10");
		input.add("PRINT DAY 2020/10/11");
		return input;
	}

	@Test
	void checkOutput() {

		//given
		List<String> input = getInput();

		SimpleBalanceService balanceService = new SimpleBalanceService();

		//when
		List<BigDecimal> actual = balanceService.calculateBalance(input).stream().collect(Collectors.toList());

		//then
		List<BigDecimal> expected = new ArrayList<>();

		expected.add(BigDecimal.ZERO);
		expected.add(new BigDecimal(500));
		expected.add(new BigDecimal(470));
		expected.add(new BigDecimal(0));
		expected.add(new BigDecimal(10));
		expected.add(new BigDecimal(-25));

		assertEquals(expected.size(), actual.size());
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(expected.get(i), actual.get(i));
		}
	}

	private void addInputLine(StringBuilder sb, String inputLine) {
		//TODO: extract to a shared variable, see SimpleUserInputReader
		sb.append(inputLine).append(System.getProperty("line.separator"));
	}
}