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
	
   String userID = null;
if(session.getAttribute("userID") != null){
   userID = (String)session.getAttribute("userID");
}


%>
	<jsp:include page="/menu.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<b class="navbar-brand" style="text-align: center;">�ֹ� ���</b>
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">


				<tr>
					<th style="background-color: #eeeeee; text-align: center;">��ǰ��ȣ</th>
					<th style="background-color: #eeeeee; text-align: center;">�ֹ���ȣ</th>
					<th style="background-color: #eeeeee; text-align: center;">��ǰ</th>
					<th style="background-color: #eeeeee; text-align: center;">��ǰ��</th>
					<th style="background-color: #eeeeee; text-align: center;">��ǰ����</th>
					<th style="background-color: #eeeeee; text-align: center;">������</th>
					<th style="background-color: #eeeeee; text-align: center;">����</th>
					<th style="background-color: #eeeeee; text-align: center;">��</th>
					<th style="background-color: #eeeeee; text-align: center;">�ֹ���</th>
					<th style="background-color: #eeeeee; text-align: center;">��¥</th>
					<th style="background-color: #eeeeee; text-align: center;">�ּ�</th>
					<th style="background-color: #eeeeee; text-align: center;">����</th>
					<th style="background-color: #eeeeee; text-align: center;">�����׻���</th>
				</tr>
				
				<%		
				OrderDAO dao = new OrderDAO();
				ArrayList<OrderDTO> a = dao.getOrder(userID); 
				int sum =0;
				int ssum=0;
				if (a == null || a.size() == 0){%>

				<tr>
					<td colspan="7">�ֹ��Ͻ� ��ǰ�� �����ϴ�.</td>
				</tr>
				<%			}else { 
					for(int i = 0; i<a.size(); i++){
					
					sum +=a.get(i).getOrprice()*a.get(i).getOrcnt();
					ssum+=sum;
					%>
				<tr>

					<td><%=a.get(i).getPdnum()%></td>
					<td><%=a.get(i).getOrnum()%></td>
					<td><img src="images/<%=a.get(i).getImg() %>" width= "30"></td>
					 <td><%=a.get(i).getOrname()%></td>
					<td><%=a.get(i).getOrprice()%>��</td>
					<td><%=a.get(i).getOrsize()%></td>
					<td><%=a.get(i).getOrcnt()%>��</td>
					<td><%=a.get(i).getOrcolor()%></td>
					<td><%=a.get(i).getOruser()%></td>
					<td><%=a.get(i).getOrdate()%></td>
					<td><%=a.get(i).getOraddr()%></td>
					 <td><%=sum %>��</td>
						<td><a href="order_edit.order?no=<%=a.get(i).getOrnum()%>">����</a> | <a
					href="order_delete.app?no=<%=a.get(i).getOrnum()%>">����</a></td>
				</tr>

					<%} %>
					
					<%} %>
				<tr >
					<td colspan="11" align="right" ><b>�ѱ��Ű��� <%=ssum %>��</b></td>
					<td><input type="button" value="��ñ����ϱ�" class="btn btn-primary pull-right"onclick=""></td>
					<td><input type="button" value="��� �����ϱ�" class="btn btn-primary pull-right"onclick="window.location='index.jsp'"></td>
				</tr>
			</table>	
		</div>
		
	</div>
 <footer> <jsp:include page="/Bottom.jsp"></jsp:include> </footer>

</body>
</html>