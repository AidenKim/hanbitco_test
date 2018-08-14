package com.hanbitco.api.dao;

import java.io.File;
import java.util.Scanner;

import org.springframework.stereotype.Repository;

/**
 * @author aidenkim
 * @date 2018. 8. 15. 오전 3:16:10
 * @version 1.0
 * @Desc 필요한 정보를 조회하는 곳 입니다. (임시 json)
 */
@Repository("CurrencyDao")
public class CurrencyDao {
	
	//assuming that price info comes from DataBase
	public String getPrice() throws Exception{
		Scanner sc = new Scanner(new File("src/main/data/currency/price.json"));
		StringBuffer sb = new StringBuffer();
		while(sc.hasNext()){
			sb.append(sc.nextLine());
		}
		return sb.toString();
	}
}
