package domain;

public class VCD {
	public String VNo;
	
	private String VRent;
	
	private String VSale;
	
	private String VStatue;
	
	private String VName;
	
	private String VDuration;
	
	public VCD() {}
	
	public VCD(String Vno,String Vrent,String Vsale,String VSat, String VNAme,String Duration) {
		VNo = Vno;
		VRent = Vrent;
		VSale = Vsale;
		VStatue = VSat;
		VName = VNAme;
		VDuration = Duration;
	}

	public String getVNo() {
		return VNo;
	}

	public void setVNo(String vNo) {
		VNo = vNo;
	}

	public String getVRent() {
		return VRent;
	}

	public void setVRent(String vRent) {
		VRent = vRent;
	}

	public String getVSale() {
		return VSale;
	}

	public void setVSale(String vSale) {
		VSale = vSale;
	}

	public String getVStatue() {
		return VStatue;
	}

	public void setVStatue(String vStatue) {
		VStatue = vStatue;
	}

	public String getVName() {
		return VName;
	}

	public void setVName(String vName) {
		VName = vName;
	}

	public String getVDuration() {
		return VDuration;
	}

	public void setVDuration(String vDuration) {
		VDuration = vDuration;
	}

	@Override
	public String toString() {
		return "VCD [VNo=" + VNo + ", VRent=" + VRent + ", VSale=" + VSale + ", VStatue=" + VStatue + ", VName=" + VName
				+ ", VDuration=" + VDuration + "]";
	}
	

}
