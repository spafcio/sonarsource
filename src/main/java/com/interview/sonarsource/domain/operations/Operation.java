package com.interview.sonarsource.domain.operations;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Operation {
	Integer getYear();
	Integer getMonth();
	Integer getDay();

	String getDescription();

	BigDecimal getAmount();


}
