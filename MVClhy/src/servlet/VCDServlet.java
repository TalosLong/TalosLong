package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daofactory.RentDAOFactory;
import daofactory.VCDDAOFactory;
import domain.Rent;
import domain.VCD;
import mvcdao.CriteriaVCD;
import mvcdao.RentDAO;
import mvcdao.VCDDAO;

public class VCDServlet extends HttpServlet {

	private VCDDAO vcdDAO = 
			VCDDAOFactory.getInstance().getVCDDAO();
	
	private RentDAO rentDAO = RentDAOFactory.getInstance().getRentDAO();
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//1. ��ȡ ServletPath: /edit.do �� /addCustomer.do
		String servletPath = req.getServletPath();
		
		//2. ȥ�� / �� .do, �õ������� edit �� addCustomer �������ַ���
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);
		
		try {
			//3. ���÷����ȡ methodName ��Ӧ�ķ���
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, 
					HttpServletResponse.class);
			//4. ���÷�����ö�Ӧ�ķ���
			method.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			//������һЩ��Ӧ.
			resp.sendRedirect("error.jsp");
		}
		
	}

	@SuppressWarnings("unused")
	private void rent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String forwardPath = "/error.jsp";
		
		String idStr = request.getParameter("VNo");
		
		VCD vcd = vcdDAO.get(Integer.parseInt(idStr));
		
		if(vcd!= null) {
			forwardPath="/rentsuccess.jsp";
			vcdDAO.rent(vcd);
		}
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}
	@SuppressWarnings("unused")
	private void edit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String forwardPath = "/error.jsp";
		
		//1. ��ȡ������� id
		String idStr = request.getParameter("VNo");
		
		//2. ���� VCDDAO �� VCDDAO.get(id) ��ȡ�� id ��Ӧ�� VCD ���� vcd
		try {
			VCD vcd = vcdDAO.get(Integer.parseInt(idStr));
			if(vcd != null){
				forwardPath = "/updatevcd.jsp";
				//3. �� vcd ���� request ��
				request.setAttribute("vcd", vcd);
			}
		} catch (NumberFormatException e) {} 
		
		
		//4. ��Ӧ updatevcd.jsp ҳ��: ת��.
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}
	
	@SuppressWarnings("unused")
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//1. ��ȡ������: no,name,rent,sale,statue,duration
		String no = request.getParameter("VNo");
		String name = request.getParameter("VName");
		String rent = request.getParameter("VRent");
		String sale = request.getParameter("VSale");
		String statue = request.getParameter("VStatue");
		String duration = request.getParameter("VDuration");
		String oldName = request.getParameter("oldName");
		
		//2. ���� name �Ƿ��Ѿ���ռ��:
		
		//2.1 �Ƚ� name �� oldName �Ƿ���ͬ, ����ͬ˵�� name ����. 
		//2.1 ������ͬ, �����VCDDAO �� getCountWithName(String name) ��ȡ name �����ݿ����Ƿ����
		if(!oldName.equalsIgnoreCase(name)){
			long count = vcdDAO.getCountWithName(name);
			//2.2 ������ֵ���� 0, ����Ӧ updatevcd.jsp ҳ��: ͨ��ת���ķ�ʽ����Ӧ newvcd.jsp
			if(count > 0){
				//2.2.1 �� updatevcd.jsp ҳ����ʾһ��������Ϣ: �û��� name �Ѿ���ռ��, ������ѡ��!
				//�� request �з���һ������ message: �û��� name �Ѿ���ռ��, ������ѡ��!, 
				//��ҳ����ͨ�� request.getAttribute("message") �ķ�ʽ����ʾ
				request.setAttribute("message", "�û���" + name + "�Ѿ���ռ��, ������ѡ��!");
				
				//2.2.2 newvcd.jsp �ı�ֵ���Ի���. 
				//address, phone ��ʾ�ύ�����µ�ֵ, �� name ��ʾ oldName, ���������ύ�� name
				
				//2.2.3 ��������: return 
				request.getRequestDispatcher("/updatevcd.jsp").forward(request, response);
				return;
			}
		}
		
		//3. ����֤ͨ��, ��ѱ�������װΪһ�� VCD ���� vcd
		VCD vcd = new VCD(no, rent, sale,statue,name,duration);
		
		//4. ���� VCDDAO ��  update(VCD vcd) ִ�и��²���
		vcdDAO.update(vcd);
		
		//5. �ض��� query.do
		response.sendRedirect("query.do");
	}

	@SuppressWarnings("unused")
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//��ȡģ����ѯ���������
		String no = request.getParameter("VNo");
		String name = request.getParameter("VName");
		String rent = request.getParameter("VRent");
		String sale = request.getParameter("VSale");
		String statue = request.getParameter("VStatue");
		String duration = request.getParameter("VDuration");
		
		//�����������װΪһ�� CriteriaVCD ����
		CriteriaVCD cc = new CriteriaVCD(no, rent, sale, statue, name, duration);
		
		//1. ����VCDDAO �� getForListWithCriteriaVCD() �õ� VCD �ļ���
		List<VCD> vcd = vcdDAO.getForListWithCriteriaVCD(cc);
		
		//2. �� VCD �ļ��Ϸ��� request ��
		request.setAttribute("vcd", vcd);
		
		//3. ת��ҳ�浽 index.jsp(����ʹ���ض���)
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	
	
	@SuppressWarnings("unused")
	private void Rquery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String no = request.getParameter("VNo");
		String rf = request.getParameter("RFair");
		
		List<VCD> vcd = vcdDAO.getWithNo(no);
		
		//2. �� VCD �ļ��Ϸ��� request ��
		request.setAttribute("vcd", vcd);
		
		//3. ת��ҳ�浽 index.jsp(����ʹ���ض���)
		request.getRequestDispatcher("/Rent.jsp").forward(request, response);
	}
	
	
	
//query ��ȡ����������Ĳ�ѯ����������һ��ģ����ѯ�Ķ���ʹ��������/MVClhy/src/daoimpl/VCDDAOJdbcImpl.java�еõ�sql��䣬
//��ʹ��sql�����DAO�н��ж����ݿ�Ĳ�ѯ�����ؽ������servlet�����ֽ������Ϣ��ͨ�����԰󶨽����ݰ󶨵�vcd�����У�jsp����ʾ�����ݿ��е���Ϣ
	@SuppressWarnings("unused")
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String idStr = request.getParameter("VNo");
		int id = 0;
		
		//try ... catch ������: ��ֹ idStr ����תΪ int ����
		//������ת�� id = 0, �޷������κε�ɾ������. 
		try {
			id = Integer.parseInt(idStr);
			System.out.println(id); 
			vcdDAO.delete(id);
		} catch (Exception e) {}
		
		response.sendRedirect("query.do");
	}

	@SuppressWarnings("unused")
	private void addVCD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1. ��ȡ������: name, address, phone
		String no = request.getParameter("VNo");
		String name = request.getParameter("VName");
		String rent = request.getParameter("VRent");
		String sale = request.getParameter("VSale");
		String statue = request.getParameter("VStatue");
		String duration = request.getParameter("VDuration");
		
		//2. ���� name �Ƿ��Ѿ���ռ��:
		
		//2.1 ���� CustomerDAO �� getCountWithName(String name) ��ȡ name �����ݿ����Ƿ����
		long count = vcdDAO.getCountWithName(name);
		
		//2.2 ������ֵ���� 0, ����Ӧ newcustomer.jsp ҳ��: 
		//ͨ��ת���ķ�ʽ����Ӧ newcustomer.jsp
		if(count > 0){
			//2.2.1 Ҫ���� newcustomer.jsp ҳ����ʾһ��������Ϣ: �û��� name �Ѿ���ռ��, ������ѡ��!
			//�� request �з���һ������ message: �û��� name �Ѿ���ռ��, ������ѡ��!, 
			//��ҳ����ͨ�� request.getAttribute("message") �ķ�ʽ����ʾ
			request.setAttribute("message", "�û���" + name + "�Ѿ���ռ��, ������ѡ��!");
			
			//2.2.2 newcustomer.jsp �ı�ֵ���Ի���. 
			//ͨ�� value="<%= request.getParameter("name") == null ? "" : request.getParameter("name") %>"
			//�����л���
			//2.2.3 ��������: return 
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return;
		}
		
		//3. ����֤ͨ��, ��ѱ�������װΪһ�� Customer ���� customer
		VCD vcd = new VCD(no, rent, sale,statue,name,duration);
		
		//4. ���� CustomerDAO ��  save(Customer customer) ִ�б������
		vcdDAO.save(vcd);
		
		//5. �ض��� success.jsp ҳ��: ʹ���ض�����Ա�����ֱ����ظ��ύ����.  
		response.sendRedirect("success.jsp");
		//request.getRequestDispatcher("/success.jsp").forward(request, response);
	}

}
