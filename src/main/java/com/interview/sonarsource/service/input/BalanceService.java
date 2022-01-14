package com.interview.sonarsource.service.input;

import java.math.BigDecimal;
import java.util.List;

public interface BalanceService {

	List<BigDecimal> calculateBalance(List<String> operationList);
}
