package the.best.stockprovider.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import the.best.stockprovider.asset.Stock;
import the.best.stockprovider.templates.ReturnValues;

public class RequestHandler {
	
	private RequestHandler() {
		
	}
	
	public static ResponseEntity<Stock> handleReturn(Stock stockRequest, ReturnValues response) {
		try {
			return RequestHandler.setReturnValues(stockRequest, response);
		} catch (NullPointerException e) {
			return ResponseEntity.internalServerError().body(stockRequest);
		}
	}
	public static ResponseEntity<Stock> handleReturn(Stock stockRequest, long growthRate, ReturnValues response) {
		try {
			return RequestHandler.setReturnValues(stockRequest, response, growthRate);
		} catch (NullPointerException e) {
			return ResponseEntity.internalServerError().body(stockRequest);
		}
	}


	public static StringBuilder buildRequestUrl(Stock stockRequest, long growthRate, Environment env) {
		return new StringBuilder()
				.append(env.getProperty("calculator.path"))
				.append(env.getProperty("calculator.port"))
				.append("/stock")
				.append("?desiredReturn=").append(stockRequest.getDesiredReturn())
				.append("&cf0=").append(stockRequest.getCashFlows()[0])
				.append("&cf1=").append(stockRequest.getCashFlows()[1])
				.append("&cf2=").append(stockRequest.getCashFlows()[2])
				.append("&cf3=").append(stockRequest.getCashFlows()[3])
				.append("&cf4=").append(stockRequest.getCashFlows()[4])
				.append("&capex0=").append(stockRequest.getCapitalExpenditures()[0])
				.append("&capex1=").append(stockRequest.getCapitalExpenditures()[1])
				.append("&capex2=").append(stockRequest.getCapitalExpenditures()[2])
				.append("&capex3=").append(stockRequest.getCapitalExpenditures()[3])
				.append("&capex4=").append(stockRequest.getCapitalExpenditures()[4])
				.append("&shares=").append(stockRequest.getShares())
				.append("&marginOfSafety=").append(stockRequest.getMarginOfSafety())
				.append("&currentEquity=").append(stockRequest.getCurrentEquity())
				.append("&growthRate=").append(growthRate);

	}
	
	public static StringBuilder buildRequestUrl(Stock stockRequest, Environment env) {
		return new StringBuilder()
				.append(env.getProperty("calculator.path"))
				.append(env.getProperty("calculator.port"))
				.append("/stock")
				.append("?desiredReturn=").append(stockRequest.getDesiredReturn())
				.append("&cf0=").append(stockRequest.getCashFlows()[0])
				.append("&cf1=").append(stockRequest.getCashFlows()[1])
				.append("&cf2=").append(stockRequest.getCashFlows()[2])
				.append("&cf3=").append(stockRequest.getCashFlows()[3])
				.append("&cf4=").append(stockRequest.getCashFlows()[4])
				.append("&capex0=").append(stockRequest.getCapitalExpenditures()[0])
				.append("&capex1=").append(stockRequest.getCapitalExpenditures()[1])
				.append("&capex2=").append(stockRequest.getCapitalExpenditures()[2])
				.append("&capex3=").append(stockRequest.getCapitalExpenditures()[3])
				.append("&capex4=").append(stockRequest.getCapitalExpenditures()[4])
				.append("&shares=").append(stockRequest.getShares())
				.append("&marginOfSafety=").append(stockRequest.getMarginOfSafety())
				.append("&currentEquity=").append(stockRequest.getCurrentEquity());
	}
	
	public static StringBuilder buildRequestUrlFcf(Stock stockRequest, long growthRate, Environment env) {
		return new StringBuilder()
				.append(env.getProperty("calculator.path"))
				.append(env.getProperty("calculator.port"))
				.append("/stock-fcf")
				.append("?desiredReturn=").append(stockRequest.getDesiredReturn())
				.append("&fcf0=").append(stockRequest.getFreeCashFlow()[0])
				.append("&fcf1=").append(stockRequest.getFreeCashFlow()[1])
				.append("&fcf2=").append(stockRequest.getFreeCashFlow()[2])
				.append("&fcf3=").append(stockRequest.getFreeCashFlow()[3])
				.append("&fcf4=").append(stockRequest.getFreeCashFlow()[4])
				.append("&shares=").append(stockRequest.getShares())
				.append("&marginOfSafety=").append(stockRequest.getMarginOfSafety())
				.append("&currentEquity=").append(stockRequest.getCurrentEquity())
				.append("&growthRate=").append(growthRate);
	}
	
	public static StringBuilder buildRequestUrlFcf(Stock stockRequest, Environment env) {
		return new StringBuilder()
				.append(env.getProperty("calculator.path"))
				.append(env.getProperty("calculator.port"))
				.append("/stock-fcf")
				.append("?desiredReturn=").append(stockRequest.getDesiredReturn())
				.append("&fcf0=").append(stockRequest.getFreeCashFlow()[0])
				.append("&fcf1=").append(stockRequest.getFreeCashFlow()[1])
				.append("&fcf2=").append(stockRequest.getFreeCashFlow()[2])
				.append("&fcf3=").append(stockRequest.getFreeCashFlow()[3])
				.append("&fcf4=").append(stockRequest.getFreeCashFlow()[4])
				.append("&shares=").append(stockRequest.getShares())
				.append("&marginOfSafety=").append(stockRequest.getMarginOfSafety())
				.append("&currentEquity=").append(stockRequest.getCurrentEquity());
	}

	private static ResponseEntity<Stock> setReturnValues(Stock stockRequest, ReturnValues response) {
		stockRequest.setAvgChange(response.getFcfChange());
		stockRequest.setBuyAndHoldValue(response.getFairPrice());
		stockRequest.setDiscountedValue(response.getDiscountedPrice());
		stockRequest.setChange(response.getPercentChange());

		return ResponseEntity.ok(stockRequest);
	}
	
	private static ResponseEntity<Stock> setReturnValues(Stock stockRequest, ReturnValues response, long growthRate) {
		stockRequest.setAvgChange(growthRate);
		stockRequest.setBuyAndHoldValue(response.getFairPrice());
		stockRequest.setDiscountedValue(response.getDiscountedPrice());
		stockRequest.setChange(response.getPercentChange());

		return ResponseEntity.ok(stockRequest);
	}
	
	
}
