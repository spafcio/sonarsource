package com.interview.sonarsource.input;

import com.interview.sonarsource.domain.operations.Operation;

public interface OperationLineReader {

	Operation readLine(String[] line);
}
