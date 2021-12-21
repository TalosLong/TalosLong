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
		
		//1. 获取 ServletPath: /edit.do 或 /addCustomer.do
		String servletPath = req.getServletPath();
		
		//2. 去除 / 和 .do, 得到类似于 edit 或 addCustomer 这样的字符串
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length() - 3);
		
		try {
			//3. 利用反射获取 methodName 对应的方法
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, 
					HttpServletResponse.class);
			//4. 利用反射调用对应的方法
			method.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			//可以有一些响应.
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
		
		//1. 获取请求参数 id
		String idStr = request.getParameter("VNo");
		
		//2. 调用 VCDDAO 的 VCDDAO.get(id) 获取和 id 对应的 VCD 对象 vcd
		try {
			VCD vcd = vcdDAO.get(Integer.parseInt(idStr));
			if(vcd != null){
				forwardPath = "/updatevcd.jsp";
				//3. 将 vcd 放入 request 中
				request.setAttribute("vcd", vcd);
			}
		} catch (NumberFormatException e) {} 
		
		
		//4. 响应 updatevcd.jsp 页面: 转发.
		request.getRequestDispatcher(forwardPath).forward(request, response);
	}
	
	@SuppressWarnings("unused")
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//1. 获取表单参数: no,name,rent,sale,statue,duration
		String no = request.getParameter("VNo");
		String name = request.getParameter("VName");
		String rent = request.getParameter("VRent");
		String sale = request.getParameter("VSale");
		String statue = request.getParameter("VStatue");
		String duration = request.getParameter("VDuration");
		String oldName = request.getParameter("oldName");
		
		//2. 检验 name 是否已经被占用:
		
		//2.1 比较 name 和 oldName 是否相同, 若相同说明 name 可用. 
		//2.1 若不相同, 则调用VCDDAO 的 getCountWithName(String name) 获取 name 在数据库中是否存在
		if(!oldName.equalsIgnoreCase(name)){
			long count = vcdDAO.getCountWithName(name);
			//2.2 若返回值大于 0, 则响应 updatevcd.jsp 页面: 通过转发的方式来响应 newvcd.jsp
			if(count > 0){
				//2.2.1 在 updatevcd.jsp 页面显示一个错误消息: 用户名 name 已经被占用, 请重新选择!
				//在 request 中放入一个属性 message: 用户名 name 已经被占用, 请重新选择!, 
				//在页面上通过 request.getAttribute("message") 的方式来显示
				request.setAttribute("message", "用户名" + name + "已经被占用, 请重新选择!");
				
				//2.2.2 newvcd.jsp 的表单值可以回显. 
				//address, phone 显示提交表单的新的值, 而 name 显示 oldName, 而不是新提交的 name
				
				//2.2.3 结束方法: return 
				request.getRequestDispatcher("/updatevcd.jsp").forward(request, response);
				return;
			}
		}
		
		//3. 若验证通过, 则把表单参数封装为一个 VCD 对象 vcd
		VCD vcd = new VCD(no, rent, sale,statue,name,duration);
		
		//4. 调用 VCDDAO 的  update(VCD vcd) 执行更新操作
		vcdDAO.update(vcd);
		
		//5. 重定向到 query.do
		response.sendRedirect("query.do");
	}

	@SuppressWarnings("unused")
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取模糊查询的请求参数
		String no = request.getParameter("VNo");
		String name = request.getParameter("VName");
		String rent = request.getParameter("VRent");
		String sale = request.getParameter("VSale");
		String statue = request.getParameter("VStatue");
		String duration = request.getParameter("VDuration");
		
		//把请求参数封装为一个 CriteriaVCD 对象
		CriteriaVCD cc = new CriteriaVCD(no, rent, sale, statue, name, duration);
		
		//1. 调用VCDDAO 的 getForListWithCriteriaVCD() 得到 VCD 的集合
		List<VCD> vcd = vcdDAO.getForListWithCriteriaVCD(cc);
		
		//2. 把 VCD 的集合放入 request 中
		request.setAttribute("vcd", vcd);
		
		//3. 转发页面到 index.jsp(不能使用重定向)
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	
	
	@SuppressWarnings("unused")
	private void Rquery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String no = request.getParameter("VNo");
		String rf = request.getParameter("RFair");
		
		List<VCD> vcd = vcdDAO.getWithNo(no);
		
		//2. 把 VCD 的集合放入 request 中
		request.setAttribute("vcd", vcd);
		
		//3. 转发页面到 index.jsp(不能使用重定向)
		request.getRequestDispatcher("/Rent.jsp").forward(request, response);
	}
	
	
	
//query 获取界面中输入的查询条件，创建一个模糊查询的对象，使用条件在/MVClhy/src/daoimpl/VCDDAOJdbcImpl.java中得到sql语句，
//再使用sql语句在DAO中进行对数据库的查询并返回结果，在servlet中体现界面的信息，通过属性绑定将数据绑定到vcd属性中，jsp再显示出数据库中的信息
	@SuppressWarnings("unused")
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String idStr = request.getParameter("VNo");
		int id = 0;
		
		//try ... catch 的作用: 防止 idStr 不能转为 int 类型
		//若不能转则 id = 0, 无法进行任何的删除操作. 
		try {
			id = Integer.parseInt(idStr);
			System.out.println(id); 
			vcdDAO.delete(id);
		} catch (Exception e) {}
		
		response.sendRedirect("query.do");
	}

	@SuppressWarnings("unused")
	private void addVCD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1. 获取表单参数: name, address, phone
		String no = request.getParameter("VNo");
		String name = request.getParameter("VName");
		String rent = request.getParameter("VRent");
		String sale = request.getParameter("VSale");
		String statue = request.getParameter("VStatue");
		String duration = request.getParameter("VDuration");
		
		//2. 检验 name 是否已经被占用:
		
		//2.1 调用 CustomerDAO 的 getCountWithName(String name) 获取 name 在数据库中是否存在
		long count = vcdDAO.getCountWithName(name);
		
		//2.2 若返回值大于 0, 则响应 newcustomer.jsp 页面: 
		//通过转发的方式来响应 newcustomer.jsp
		if(count > 0){
			//2.2.1 要求再 newcustomer.jsp 页面显示一个错误消息: 用户名 name 已经被占用, 请重新选择!
			//在 request 中放入一个属性 message: 用户名 name 已经被占用, 请重新选择!, 
			//在页面上通过 request.getAttribute("message") 的方式来显示
			request.setAttribute("message", "用户名" + name + "已经被占用, 请重新选择!");
			
			//2.2.2 newcustomer.jsp 的表单值可以回显. 
			//通过 value="<%= request.getParameter("name") == null ? "" : request.getParameter("name") %>"
			//来进行回显
			//2.2.3 结束方法: return 
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return;
		}
		
		//3. 若验证通过, 则把表单参数封装为一个 Customer 对象 customer
		VCD vcd = new VCD(no, rent, sale,statue,name,duration);
		
		//4. 调用 CustomerDAO 的  save(Customer customer) 执行保存操作
		vcdDAO.save(vcd);
		
		//5. 重定向到 success.jsp 页面: 使用重定向可以避免出现表单的重复提交问题.  
		response.sendRedirect("success.jsp");
		//request.getRequestDispatcher("/success.jsp").forward(request, response);
	}

}
