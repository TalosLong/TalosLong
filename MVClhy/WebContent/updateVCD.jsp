<%@page import="domain.VCD"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<% 
		Object msg = request.getAttribute("message");
		if(msg != null){
	%>
			<br>
			<font color="red"><%= msg %></font>
			<br>
			<br>
	<%
		}
		
		String No = null;
		String OldName = null;
		String Name = null;
		String Rent = null;
		String Sale = null;
		String Duration = null;
		String Statue = null;
		
		VCD vcd = (VCD)request.getAttribute("vcd");
		if(vcd != null){
			No = vcd.getVNo() + "";
			OldName = vcd.getVName();
			Name = vcd.getVName();
			Rent = vcd.getVRent();
			Sale = vcd.getVSale();
			Statue = vcd.getVStatue();
			Duration = vcd.getVDuration();
		}else{
			No = request.getParameter("VNo");
			OldName = request.getParameter("oldName");
			Name = request.getParameter("oldName");
			Rent = request.getParameter("VRent");
			Sale = request.getParameter("VSale");
			Statue = request.getParameter("VStatue");
			Duration = request.getParameter("VDuration");
		}
	%>
	
	<form action="update.do" method="post">
	
		<input type="hidden" name="id" value="<%= No %>"/>
		<input type="hidden" name="oldName" value="<%= OldName %>"/>
	
		<table>
		<tr>
				<td>VCDNo:</td>
				<td><input type="text" name="VNo" 
					value="<%= No %>"/></td>
			</tr>
			<tr>
				<td>VCDRent:</td>
				<td><input type="text" name="VRent" 
					value="<%= Rent %>"/></td>
			</tr>
			<tr>
				<td>VCDSale:</td>
				<td><input type="text" name="VSale" 
					value="<%= Sale %>"/></td>
			</tr>
			<tr>
				<td>VCDStatue:</td>
				<td><input type="text" name="VStatue" 
					value="<%= Statue %>"/></td>
			</tr>
			<tr>
				<td>VCDName:</td>
				<td><input type="text" name="VName" 
					value="<%= Name %>"/></td>
			</tr>
			<tr>
				<td>VCDDuration:</td>
				<td><input type="text" name="VDuration" 
					value="<%= Duration %>"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"/></td>
			</tr>
		</table>
	</form>
	
</body>
</html>