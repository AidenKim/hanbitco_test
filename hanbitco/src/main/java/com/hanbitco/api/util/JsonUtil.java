package com.hanbitco.api.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author aidenkim
 * @date 2018. 8. 15. 오전 3:15:30
 * @version 1.0
 * @Desc Json 데이터 변환을 처리하는 Util.
 */
public class JsonUtil {
	/*
	 * String 문자열을 Gson을 이용하여 JsonElements로 변환 합니다.
	 */
	public static JsonObject getJsonObjectFromString(String str){
		return new Gson().fromJson(str, JsonObject.class);
	}
	public static JsonObject getObjectFromJsonObject(JsonElement jEle){
		return new Gson().fromJson(jEle, Object.class);
	}
}
