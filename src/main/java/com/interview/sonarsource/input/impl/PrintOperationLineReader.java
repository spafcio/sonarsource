package com.interview.sonarsource.input.impl;

import com.interview.sonarsource.domain.operations.Operation;
import com.interview.sonarsource.consumers.impl.PrintConsumer;
import com.interview.sonarsource.domain.operations.impl.Print;
import com.interview.sonarsource.input.OperationLineReader;
import com.interview.sonarsource.domain.operations.enums.PrintType;

public class PrintOperationLineReader implements OperationLineReader {


	private final PrintConsumer operationConsumer;

	public PrintOperationLineReader(PrintConsumer operationConsumer){
		this.operationConsumer = operationConsumer;
	}

	@Override
	public Operation readLine(String[] line) {

		PrintType printType = PrintType.valueOf(line[1]);

		String[] date = line[2].split("/");

		Integer year = null;
		Integer month = null;
		Integer day = null;

		switch (printType){
			case DAY:
				day = Integer.valueOf(date[2]);
			case MONTH:
				month = Integer.valueOf(date[1]);
			case YEAR:
				year = Integer.valueOf(date[0]);
		}

		Print print = new Print(printType, year, month, day);
		operationConsumer.consume(print);

		return print;
	}
}
