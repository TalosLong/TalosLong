package servlet;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import daofactory.VCDDAOFactory;

public class InitServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		VCDDAOFactory.getInstance().setType("jdbc");
		
		//��ȡ��·���µ� switch.properties �ļ�
		InputStream in = 
				getServletContext().getResourceAsStream("/WEB-INF/classes/switch.properties");
		Properties properties = new Properties();
		try {
			properties.load(in);
			//��ȡ switch.properties �� type ����ֵ
			String type = properties.getProperty("type");
			//������ CustomerDAOFactory �� type ����ֵ
			VCDDAOFactory.getInstance().setType(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
