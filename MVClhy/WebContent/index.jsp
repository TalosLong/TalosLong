<%@page import="domain.VCD"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>VCDrent/sale</title>
</head>
<script type="text/javascript" src="scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var content = $(this).parent().parent().find("td:eq(1)").text();
			var flag = confirm("确定要是删除" + content + "的信息吗?");
			return flag;
		});
	});

</script>
<body>

<form action="query.do" method="post">
		<table>
		<tr>
				<td>No:</td>
				<td><input type="text" name="VNo"/></td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="VName"/></td>
			</tr>
			<tr>
				<td>VRent:</td>
				<td><input type="text" name="VRent"/></td>
			</tr>
			<tr>
				<td>VSale:</td>
				<td><input type="text" name="VSale:"/></td>
			</tr>
			<tr>
				<td>VStatue:</td>
				<td><input type="text" name="VStatue"/></td>
			</tr>
			<tr>
				<td>VDuration:</td>
				<td><input type="text" name="VDuration"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Query"/></td>
				<td><a href="newVCD.jsp">Add New VCD</a></td>
			</tr>
			<tr>
				<td><a id="rent" href="Rent.jsp">rent one VCD</a></td>
			</tr>
		</table>
	</form>
	
	<br><br>

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
			</tr>
			
			<% 
				for(VCD vcd: vcds){
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