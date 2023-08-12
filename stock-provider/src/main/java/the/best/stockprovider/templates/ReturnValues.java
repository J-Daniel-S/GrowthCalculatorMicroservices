package the.best.stockprovider.templates;

import java.util.Arrays;

public class ReturnValues {

	private double[] percentChange;
	private double fcfChange;
	private double fairPrice;
	private double discountedPrice;
	
	public ReturnValues(double[] percentChange, double fcfChange, double fairPrice,
			double discountedPrice) {
		super();
		this.percentChange = percentChange;
		this.fcfChange = fcfChange;
		this.fairPrice = fairPrice;
		this.discountedPrice = discountedPrice;
	}

	public ReturnValues() {
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
		return "ReturnValues [percentChange=" + Arrays.toString(percentChange) + ", fcfChange=" + fcfChange
				+ ", fairPrice=" + fairPrice + ", discountedPrice=" + discountedPrice + "]";
	}
	
}
