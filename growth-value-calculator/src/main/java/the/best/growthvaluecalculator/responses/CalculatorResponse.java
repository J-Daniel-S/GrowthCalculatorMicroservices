package the.best.growthvaluecalculator.responses;

import java.util.Arrays;

public class CalculatorResponse {

	private double[] percentChange;
	private double fcfChange;
	private double fairPrice;
	private double discountedPrice;
	
	public CalculatorResponse(double[] percentChange, double fcfChange, double fairPrice,
			double discountedPrice) {
		super();
		this.percentChange = percentChange;
		this.fcfChange = fcfChange;
		this.fairPrice = fairPrice;
		this.discountedPrice = discountedPrice;
	}

	public CalculatorResponse() {
		super();
	}

	public double[] getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(double[] percentChange) {
		this.percentChange = percentChange;
	}

	public double getFcfChange() {
		return fcfChange;
	}

	public void setFcfChange(double fcfChange) {
		this.fcfChange = fcfChange;
	}

	public double getFairPrice() {
		return fairPrice;
	}

	public void setFairPrice(double fairPrice) {
		this.fairPrice = fairPrice;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	@Override
	public String toString() {
		return "CalculatorResponse [percentChange=" + Arrays.toString(percentChange) + ", fcfChange=" + fcfChange
				+ ", fairPrice=" + fairPrice + ", discountedPrice=" + discountedPrice + "]";
	}
	
}
