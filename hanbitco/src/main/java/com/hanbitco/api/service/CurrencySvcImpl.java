package com.hanbitco.api.service;

import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hanbitco.api.dao.CurrencyDao;
import com.hanbitco.api.util.JsonUtil;

/**
 * @author aidenkim
 * @date 2018. 8. 15. 오전 3:14:39
 * @version 1.0
 * @Desc Currency 비니지스 로직을 처리하는 곳입니다.
 */
@Service("CurrencySvc")
public class CurrencySvcImpl implements CurrencySvc{
	private static final Logger logger = LoggerFactory.getLogger(CurrencySvcImpl.class);
	
	@Autowired
	CurrencyDao currencyDao;
	
	final String[] allCurrencyList = new String[]{"BTC_KRW","ETH_KRW","EOS_KRW","BCH_KRW","XRP_KRW"};
	final String[] defaultInfoFromTC = new String[]{"originPair","last"}; //default Info From Trade Center
	/*
	 해당 시세 코인 가격 정보를 조회 합니다.
	 단, 없을 시 전체(5가지) 조회 합니다. 
	 */
	public Object getCurrencyInJson(String currency){	
		JsonObject rtnObj = new JsonObject();
		String[] currencyList = currency == null || "".equals(currency) ? allCurrencyList : new String[]{currency};
		try {
			
			JsonObject jObj = JsonUtil.getJsonObjectFromString(currencyDao.getPrice());
			
			//1. get the right currency
			//2. get only the originPair and last price => getSomeInfoFromTrade
			JsonElement jData;
			if(currencyList.length==1){
				jData = getSomeInfoFromTradeCenter(jObj.get(currencyList[0]+"_KRW"));
			} else{
				jData = new JsonObject();
				for(String cur : currencyList){
					((JsonObject) jData).add(cur, getSomeInfoFromTradeCenter(jObj.get(cur)));
				}
			}
			
			rtnObj.addProperty("status", "success");
			rtnObj.add("data", jData);

		} catch (Exception e) {
			e.printStackTrace();
			rtnObj.addProperty("status", "fail");
			rtnObj.addProperty("error", e.getMessage());
		}
		return JsonUtil.getObjectFromJsonObject(rtnObj);
	}
	
	public JsonElement getSomeInfoFromTradeCenter(JsonElement jEle,String[] someInfo){
		someInfo = someInfo.length == 0 ? null : someInfo; //null or all
		if(jEle.isJsonObject() && someInfo != null){
			for(Entry<String, JsonElement> jse : jEle.getAsJsonObject().entrySet()){
				String key = jse.getKey();
				JsonObject oldObj = (JsonObject)jse.getValue();
				JsonObject newObj = new JsonObject();
				for(String some: someInfo){
					newObj.add(some, oldObj.get(some));
				}
				((JsonObject)jEle).add(key,newObj);
			}
		}
		return jEle;
	}
	
	public JsonElement getSomeInfoFromTradeCenter(JsonElement jEle){ 
		return getSomeInfoFromTradeCenter(jEle, defaultInfoFromTC);
	}
}
