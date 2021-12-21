<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加新vcd</title>
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
	%>
	
	<form action="addVCD.do" method="post">
		<table>
		<tr>
				<td>vcdNo:</td>
				<td><input type="text" name="VNo" 
					value="<%= request.getParameter("VNo") == null ? "" : request.getParameter("VNo") %>"/></td>
			</tr>
			<tr>
				<td>vcdRent:</td>
				<td><input type="text" name="VRent" 
					value="<%= request.getParameter("VRent") == null ? "" : request.getParameter("VRent") %>"/></td>
			</tr>
			<tr>
				<td>vcdSale:</td>
				<td><input type="text" name="VSale" 
					value="<%= request.getParameter("VSale") == null ? "" : request.getParameter("VSale") %>"/></td>
			</tr>
			<tr>
				<td>vcdStatue:</td>
				<td><input type="text" name="VStatue" 
					value="<%= request.getParameter("VStatue") == null ? "" : request.getParameter("VStatue") %>"/></td>
			</tr>
			<tr>
				<td>vcdName:</td>
				<td><input type="text" name="VName" 
					value="<%= request.getParameter("VName") == null ? "" : request.getParameter("VName") %>"/></td>
			</tr>
			<tr>
				<td>vcdDuration:</td>
				<td><input type="text" name="VDuration" 
					value="<%= request.getParameter("VDuration") == null ? "" : request.getParameter("VDuration") %>"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"/></td>
			</tr>
		</table>
	</form>
	
</body>
</html>