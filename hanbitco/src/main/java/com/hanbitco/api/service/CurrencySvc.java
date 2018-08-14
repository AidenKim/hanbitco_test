package com.hanbitco.api.service;

public interface CurrencySvc {
	
	/*
	 해당 시세 코인 가격 정보를 조회 합니다.
	 단, 없을 시 전체(5가지) 조회 합니다. 
	 */
	public Object getCurrencyInJson(String currncy);
}
