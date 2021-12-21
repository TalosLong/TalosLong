<%@page import="domain.VCD"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="Rquery.do" method="post">
		<table>
		<tr>
				<td>No:</td>
				<td><input type="text" name="VNo"/></td>
			</tr>
			<tr>
				<td>RFair:</td>
				<td><input type="text" name="RFair"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Query"/></td>
			</tr>
		</table>
	</form>
		<% 
		List<VCD> vcds = (List<VCD>)request.getAttribute("vcd");
		if(vcds != null && vcds.size() > 0){
	%>
	<hr>	
	<br><br>
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>No</th>
				<th>Rent</th>
				<th>Sale</th>
				<th>Name</th>
				<th>Statue</th>
				<th>Duration</th>
				<th>UPDATE\DELETE</th>
				<th>RENT</th>
			</tr>
			<% 
				for(VCD vcd: vcds){
					int statue = Integer.parseInt(vcd.getVStatue());
					if(statue==1)
			%>
					<tr>
						<td><%= vcd.getVNo() %></td>
						<td><%= vcd.getVRent() %></td>
						<td><%= vcd.getVSale() %></td>
						<td><%= vcd.getVName() %></td>
						<td><%= vcd.getVStatue() %></td>
						<td><%= vcd.getVDuration() %></td>
						<td>
							<a href="edit.do?VNo=<%= vcd.getVNo() %>">UPDATE</a>
							<a href="delete.do?VNo=<%= vcd.getVNo() %>" class="delete">DELETE</a>
						</td>
						<td><a id="rent" href="rent.do?VNo=<%=vcd.getVNo() %>">RENT THIS ONE</a></td>
					</tr>
			<%
			}	
			%>
		</table>
	<%		
		}
	%>
</body>
</html>