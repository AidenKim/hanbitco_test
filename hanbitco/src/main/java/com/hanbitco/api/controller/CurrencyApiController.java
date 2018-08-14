package com.hanbitco.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hanbitco.api.service.CurrencySvc;

/**
 * @author aidenkim
 * @date 2018. 8. 15. 오전 3:13:23
 * @version 1.0
 * @Desc Currency 값 처리 하는 Controller 입니다.
 */
@RestController
@RequestMapping(value="/api/v1/data")
public class CurrencyApiController {
	
	@Autowired
	CurrencySvc currencySvc; 
	
	/* 
	 * 특정한 currency가 없으면 전체 조회 합니다. (5가지)
	 */
	@RequestMapping(value="/currency", produces={"application/json"})
	public @ResponseBody Object getAllCurrency(){
		return currencySvc.getCurrencyInJson("");
	}
	/* 
	 * 특정한 currency 조회 합니다.
	 */
	@RequestMapping(value="/currency/{currency}", produces={"application/json"})
	public @ResponseBody Object getCurrency(@PathVariable String currency){
		return currencySvc.getCurrencyInJson(currency);
	}
}
