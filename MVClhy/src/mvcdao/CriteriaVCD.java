package mvcdao;

public class CriteriaVCD {
	private String VNo;
	
	private String VRent;
	
	private String VSale;
	
	private String VStatue;
	
	private String VName;
	
	private String VDuration;

	public CriteriaVCD(String vNo, String vRent, String vSale, String vStatue, String vName, String vDuration) {
		VNo = vNo;
		VRent = vRent;
		VSale = vSale;
		VStatue = vStatue;
		VName = vName;
		VDuration = vDuration;
	}

	public void setVNo(String vNo) {
		VNo = vNo;
	}

	public void setVRent(String vRent) {
		VRent = vRent;
	}

	public String getVNo() {
		if(VNo==null)
			VNo="%%";
		else {
			VNo="%"+VNo+"%";
		}
		return VNo;
	}

	public String getVRent() {
		if(VRent==null)
			VRent="%%";
		else {
			VRent="%"+VRent+"%";
		}
		return VRent;
	}

	public String getVSale() {
		if(VSale==null)
			VSale="%%";
		else {
			VSale="%"+VSale+"%";
		}
		return VSale;
	}

	public String getVStatue() {
		if(VStatue==null)
			VStatue="%%";
		else {
			VStatue="%"+VStatue+"%";
		}
		return VStatue;
	}

	public String getVName() {
		if(VName==null)
			VName="%%";
		else {
			VName="%"+VName+"%";
		}
		return VName;
	}

	public String getVDuration() {
		if(VDuration==null)
			VDuration="%%";
		else {
			VDuration="%"+VDuration+"%";
		}
		return VDuration;
	}
}
