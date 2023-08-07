package the.best.growthvaluecalculator.templates;

public interface StockAsset {
	public String getTicker();
	public void setTicker(String ticker);
	public long[] getFreeCashFlow();
	public void setFreeCashFlow(long[] freeCashFlow);
	public double getAvgChange();
	public void setAvgChange(double fcfChange);
	public String getMarginOfSafety();
	public void setMarginOfSafety(String marginOfSafety);
	public long getShares();
	public void setShares(long shares);
	public long getDesiredReturn();
	public void setDesiredReturn(long desiredReturn);
	public long getCurrentEquity();
	public void setCurrentEquity(long currentEquity);
	public double[] getChange();
	public void setChange(double[] percentChange);
	public double getBuyAndHoldValue();
	public void setBuyAndHoldValue(double buyAndHoldValue);
	public double getDiscountedValue();
	public void setDiscountedValue(double discountedValue);
	public long[] getCashFlows();
	public void setCashFlows(long[] cashFlows);
	public long[] getCapitalExpenditures();
	public void setCapitalExpenditures(long[] capitalExpenditures);
}
