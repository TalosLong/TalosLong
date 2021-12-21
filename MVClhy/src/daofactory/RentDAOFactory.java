package daofactory;

import java.util.HashMap;
import java.util.Map;

import daoimpl.RentDAOJdbcImpl;
import daoimpl.RentDAOXMLImpl;
import daoimpl.VCDDAOJdbcImpl;
import daoimpl.VCDDAOXMLImpl;
import mvcdao.RentDAO;
import mvcdao.VCDDAO;

public class RentDAOFactory {
private Map<String, RentDAO> daos = new HashMap<String, RentDAO>();
	
	
	private static RentDAOFactory instance = new RentDAOFactory();
	
	public static RentDAOFactory getInstance() {
		return instance;
	}
	
	private String type = null;
	
	public void setType(String type) {
		this.type = type;
	}
	
	private RentDAOFactory(){
		daos.put("jdbc", new RentDAOJdbcImpl());
		daos.put("xml", new RentDAOXMLImpl());
	}
	
	public RentDAO getRentDAO(){
		return daos.get(type);
	}
}
