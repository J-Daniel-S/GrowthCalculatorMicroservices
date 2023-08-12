package the.best.growthvaluecalculator.templates;

public interface FixedRequest {
	public int getCompoundFrequency();
	public void setCompoundFrequency(int compoundFrequency);
	public long getPrinciple();
	public void setPrinciple(long principle);
	public double getInterestRate();
	public void setInterestRate(double interestRate);
	public int getiLength();
	public void setiLength(double iLength);
	public long getEndValue();
	public void setEndValue(double d);
}
