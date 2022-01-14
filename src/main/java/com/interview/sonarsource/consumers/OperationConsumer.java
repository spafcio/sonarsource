package com.interview.sonarsource.consumers;

import com.interview.sonarsource.domain.operations.Operation;

public interface OperationConsumer {

	void consume(Operation operation);
}
