package daofactory;

import java.util.HashMap;
import java.util.Map;

import mvcdao.VCDDAO;
import daoimpl.VCDDAOJdbcImpl;
import daoimpl.VCDDAOXMLImpl;

public class VCDDAOFactory {
	
	private Map<String, VCDDAO> daos = new HashMap<String, VCDDAO>();
	
	
	private static VCDDAOFactory instance = new VCDDAOFactory();
	
	public static VCDDAOFactory getInstance() {
		return instance;
	}
	
	private String type = null;
	
	public void setType(String type) {
		this.type = type;
	}
	
	private VCDDAOFactory(){
		daos.put("jdbc", new VCDDAOJdbcImpl());
		daos.put("xml", new VCDDAOXMLImpl());
	}
	
	public VCDDAO getVCDDAO(){
		return daos.get(type);
	}
	
}