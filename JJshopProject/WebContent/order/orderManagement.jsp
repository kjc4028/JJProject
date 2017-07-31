<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*,order.*"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>JSP �Խ��� �� ����Ʈ</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
   String userID = null;
if(session.getAttribute("userID") != null){
   userID = (String)session.getAttribute("userID");
}


%>
	<jsp:include page="/menu.jsp"></jsp:include>
	
	<div class="container">
	
	&nbsp;
	<div id = "search1">
	<form action="searchOrder.order">
	
	<input type="text" class="form-control"  placeholder="�˻�" name = "search" style="width: 200px;"/>
	<select name = "searchList">
	<option value="oruser">�ֹ��� �̸�</option>
	<option value="orname">�ֹ� ��ǰ��</option>
	<option value="pdnum">��ǰ ��ȣ</option>
	<option value="ornum">�ֹ� ��ȣ</option>
	</select>
	<input type="submit"  value = "search" class="btn btn-primary "/>
	&nbsp;
	
		<div class="row">
			<b class="navbar-brand" style="text-align: center;">�ֹ� ���</b>
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">


				<tr>
					<th style="background-color: #eeeeee; text-align: center;">��ȣ</th>
					<th style="background-color: #eeeeee; text-align: center;">�ֹ���ȣ</th>
					<th style="background-color: #eeeeee; text-align: center;">��ǰ��</th>
					<th style="background-color: #eeeeee; text-align: center;">��ǰ����</th>
					<th style="background-color: #eeeeee; text-align: center;">������</th>
					<th style="background-color: #eeeeee; text-align: center;">����</th>
					<th style="background-color: #eeeeee; text-align: center;">��</th>
					<th style="background-color: #eeeeee; text-align: center;">�ֹ���</th>
					<th style="background-color: #eeeeee; text-align: center;">��¥</th>
					<th style="background-color: #eeeeee; text-align: center;">�ּ�</th>
				</tr>
				<%		
				OrderDAO dao = new OrderDAO();
				
				ArrayList<OrderDTO> a = dao.getOrderMG(); 
				if (a == null || a.size() == 0){%>
				<tr>
					<td colspan="7">�ֹ��Ͻ� ��ǰ�� �����ϴ�.</td>
				</tr>
				<%			}else { 
					for(int i = 0; i<a.size(); i++){
					
					System.out.println("i��:"+i);
					System.out.println("get i ��:"+ a.get(i).getOrnum());
					%>
				<tr>
					<td><%=a.get(i).getPdnum()%></td>
					<td><%=a.get(i).getOrnum()%></td>
					 <td><%=a.get(i).getOrname()%></td>
					<td><%=a.get(i).getOrprice()%></td>
					<td><%=a.get(i).getOrsize()%></td>
					<td><%=a.get(i).getOrcnt()%></td>
					<td><%=a.get(i).getOrcolor()%></td>
					<td><%=a.get(i).getOruser()%></td>
					<td><%=a.get(i).getOrdate()%></td>
					<td><%=a.get(i).getOraddr()%></td> 
				</tr>
					<%} %>
					<%} %>
				
			</table>	
		</div>
		
	</div>
	<center>
 <footer> <jsp:include page="/Bottom.jsp"></jsp:include> </footer>
 </center>
</body>
</html>