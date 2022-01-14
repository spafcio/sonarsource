package com.interview.sonarsource;

import com.interview.sonarsource.service.input.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SonarsourceApplication {

	@Autowired
	BalanceService balanceService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SonarsourceApplication.class, args);

		SonarsourceApplication sonarsourceApplication = context.getBean(SonarsourceApplication.class);
		String operationsList = sonarsourceApplication.createInput();

		List<BigDecimal> output = sonarsourceApplication.processInput(operationsList);

		System.out.println(output);

		System.exit(0);
	}

	private List<BigDecimal> processInput(String operationsList) {
		String[] split = operationsList.split(System.getProperty("line.separator"));
		List<BigDecimal> bigDecimals = balanceService.calculateBalance(Arrays.stream(split).collect(Collectors.toList()));

		return bigDecimals;
	}

	private String createInput() {
		StringBuilder sb = new StringBuilder();

		addInputLine(sb,"INCOME 2020/01/01 gift 500");
		addInputLine(sb,"PRINT YEAR 2021");
		addInputLine(sb,"PRINT YEAR 2020");
		addInputLine(sb,"EXPENSE 2020/10/12 coffee 5");
		addInputLine(sb,"EXPENSE 2020/10/11 lunch 25");
		addInputLine(sb,"PRINT YEAR 2020");
		addInputLine(sb,"INCOME 2020/10/01 stock-divident 40");
		addInputLine(sb,"PRINT MONTH 2020/11");
		addInputLine(sb,"PRINT MONTH 2020/10");
		addInputLine(sb,"PRINT DAY 2020/10/11");

		return sb.toString();
	}

	private void addInputLine(StringBuilder sb, String inputLine) {
		//TODO: extract to a shared variable, see SimpleUserInputReader
		sb.append(inputLine).append(System.getProperty("line.separator"));
	}

}
