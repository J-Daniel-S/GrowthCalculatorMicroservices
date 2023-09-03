package the.best.growthvaluecalculator.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import the.best.growthvaluecalculator.templates.StockRequest;

public class Calculator {
	
	private Calculator() {
		
	}
	
	public static double calculateTotal(long principle, double interest, double length, int compoundFrequency) {
		return principle * Math.pow(1 + (interest / compoundFrequency), compoundFrequency* length );
	}

//	calculates and fills the dcf multipliers array: used only by the discounted cash flow method
//	Each multiplier is = 1 / 1.%^i+1 where i = the years into the future and % = yoy percent growth
	static double[] calculateDcfMultipliers(long desiredGrowthPercentage) {
		double[] dcfMultipliers = new double[20];
		String decimals = new String();
		if (desiredGrowthPercentage < 10) {
			decimals = "0" + desiredGrowthPercentage;
		} else {
			decimals = String.valueOf(desiredGrowthPercentage);
		}
		String percent = "1." + decimals;
		for (int i = 1; i < 19; i++) {
			dcfMultipliers[i - 1] = (1 / Math.pow(Double.valueOf(percent), i));
		}
		return dcfMultipliers;
	}

	// capital expenditures taken from cash flow from operations
	public static long[] calculateFreeCashFlow(StockRequest StockAsset) {
		long[] cf = StockAsset.getCashFlows();
		long[] capex = StockAsset.getCapitalExpenditures();
		long[] fcf = new long[5];

		for (int i = 0; i < capex.length; i++) {
			fcf[i] = cf[i] - capex[i];
		}

		return fcf;
	}
	
	public static long[] calculateFreeCashFlow(long[] cf, long[] capex) {
		long[] fcf = new long[5];

		for (int i = 0; i < capex.length; i++) {
			fcf[i] = cf[i] - capex[i];
		}

		return fcf;
	}

	// each index holds the percentage of the prior fcf that is the current fcf e.g.
	// fcf[4] is xxx% of fcf[3]
	public static double[] calculatePercentChange(long[] fcf) {
		double[] changeArr = new double[4];

		for (int i = 3; i >= 0; i--) {
			changeArr[i] = ((fcf[i + 1] * 100) / fcf[i]);
		}

		return changeArr;
	}

	// Average of the last five years of free cash flow with this last year's and
	// the year prior double weighted
	public static long getFcfForCalculation(long[] fcf) {
		long val = (fcf[0] + fcf[1] + fcf[2] + (2 * fcf[3]) + (2 * fcf[4])) / 7;
		return val;
	}

	// round down to the nearest full percent
	// yoy growth of fcf
	public static double getChangeForCalc(double[] change) {
		double val = (change[0] + change[1] + (change[2] * 2) + (change[3] * 2)) / 6;
		return val;
	}

	public static long totalDcf(long calcDcf, double fcfChange, double[] dcfMultipliers) {
		long totalDcf = 0;
		double lastFcf = calcDcf;
		double dcf;

		MathContext mc = new MathContext(4, RoundingMode.HALF_EVEN);
		BigDecimal multiplier = BigDecimal.valueOf(fcfChange).divide(BigDecimal.valueOf(100), mc);

		for (int i = 0; i < 19; i++) {
			if (i < 11) {
				multiplier = multiplier.multiply(BigDecimal.valueOf(0.9931), mc);
			} else if (i != 0) {
				// assumes a 5% growth rate for >10 years in the future
				multiplier = BigDecimal.valueOf(1.05);
			}

			dcf = lastFcf * dcfMultipliers[i];
			lastFcf = (lastFcf * multiplier.doubleValue());
			totalDcf += (long) dcf;
		}

		if (totalDcf < 50000) {
			totalDcf *= 1.1;
		} else if (totalDcf < 100000) {
			totalDcf *= 1.04;
		} else if (totalDcf < 150000) {
			totalDcf *= 1.02;
		}

		return totalDcf;
	}

	public static long calculateTotal(StockRequest stock) {
		stock.setMarginOfSafety(String.valueOf(Double.valueOf(stock.getMarginOfSafety()) / 100));
		long[] fcf = calculateFreeCashFlow(stock);
		double[] percentChange = calculatePercentChange(fcf);
		stock.setChange(percentChange);
		double[] dcfMultipliers = calculateDcfMultipliers(stock.getDesiredReturn());
		long calcFcf = getFcfForCalculation(fcf);
		double fcfChange = getChangeForCalc(percentChange);
		long equity = stock.getCurrentEquity();
		stock.setAvgChange(fcfChange);
		long totalDcf = totalDcf(calcFcf, fcfChange, dcfMultipliers);
		System.out.println(totalDcf + equity);
		return totalDcf + equity;
	}

	public static long calculateTotal(StockRequest stock, long fcfChange) {
		stock.setMarginOfSafety(String.valueOf(Double.valueOf(stock.getMarginOfSafety()) / 100));
		long[] fcf = calculateFreeCashFlow(stock);
		double[] percentChange = calculatePercentChange(fcf);
		stock.setChange(percentChange);
		double[] dcfMultipliers = calculateDcfMultipliers(stock.getDesiredReturn());
		long calcFcf = getFcfForCalculation(fcf);
		stock.setAvgChange(fcfChange);
		long equity = stock.getCurrentEquity();
		long totalDcf = totalDcf(calcFcf, fcfChange, dcfMultipliers);
		return totalDcf + equity;
	}
	
	public static long calculateTotalFcf(long[] fcf, 
			long desiredReturn, 
			long currentEquity, 
			double[] percentChange,
			double fcfChange
			) {
		double[] dcfMultipliers = calculateDcfMultipliers(desiredReturn);
		long calcFcf = getFcfForCalculation(fcf);
		long totalDcf = totalDcf(calcFcf, fcfChange, dcfMultipliers);
		return totalDcf + currentEquity;
	}
	
	public static double getFairPrice(long total, long shares) {
		return  (double) total / shares;
	}
	
	public static double getDiscountedPrice(double fairPrice, double marginOfSafety) {
		return  fairPrice * (1 - marginOfSafety);
	}

}
