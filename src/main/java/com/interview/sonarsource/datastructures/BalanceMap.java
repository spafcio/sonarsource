package com.interview.sonarsource.datastructures;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class BalanceMap<K,V> extends HashMap<K, V> {
	private BigDecimal balance = BigDecimal.ZERO;

	public void add(BigDecimal amount){
		balance = balance.add(amount);
	}

	public BigDecimal getBalance(){
		return balance;
	}
}
