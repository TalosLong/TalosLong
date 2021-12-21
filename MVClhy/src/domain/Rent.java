package domain;

public class Rent extends VCD{
	private String RDate;
	private String RTime;
	private String RFair;
	
	public Rent(String no,String rf) {
		VNo = no;
		RFair = rf;
	}
	
	public Rent(String rDate, String rTime, String rFair) {
		super();
		RDate = rDate;
		RTime = rTime;
		RFair = rFair;
	}

	public String getRDate() {
		return RDate;
	}
	public void setRDate(String rDate) {
		RDate = rDate;
	}
	public String getRTime() {
		return RTime;
	}
	public void setRTime(String rTime) {
		RTime = rTime;
	}
	public String getRFair() {
		return RFair;
	}
	public void setRFair(String rFair) {
		RFair = rFair;
	}

	@Override
	public String toString() {
		return "Rent [RDate=" + RDate + ", RTime=" + RTime + ", RFair=" + RFair + "]";
	}
	
}
