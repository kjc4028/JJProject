<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*,mvReserve.*"%>
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
	<jsp:include page="/menu2.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<b class="navbar-brand" style="text-align: center;">������Ȳ</b>
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">


				<tr>
					<th style="background-color: #eeeeee; text-align: center;">������</th>
					<th style="background-color: #eeeeee; text-align: center;">�󿵳�¥</th>
					<th style="background-color: #eeeeee; text-align: center;">��ȭ�ð�</th>
					<th style="background-color: #eeeeee; text-align: center;">��ȭ����</th>
					<th style="background-color: #eeeeee; text-align: center;">�¼�</th>
					<th style="background-color: #eeeeee; text-align: center;">����</th>
					<th style="background-color: #eeeeee; text-align: center;">�ֹ���</th>
				</tr>
				
				<%		
				MvReDAO dao = new MvReDAO();
				ArrayList<MvReDTO> a = dao.getMvrelist(userID); 
				int sum =0;
				int ssum=0;
				if (a == null || a.size() == 0){%>

				<tr>
					<td colspan="7">�����Ͻ� ��ȭ�� �����ϴ�.</td>
				</tr>
				<%			}else { 
					for(int i = 0; i<a.size(); i++){
					
					
					%>
				<tr>

					<td><%=a.get(i).getMvArea()%></td>
					<td><%=a.get(i).getMvdate()%></td>
					 <td><%=a.get(i).getMvTime()%></td>
					<td><%=a.get(i).getMvName()%></td>
					<td><%=a.get(i).getSeatID()%></td>
					<td><%=a.get(i).getMvprice()%>��</td>
					<td><%=a.get(i).getMvuser()%></td>
				</tr>

					<%} %>
					
					<%} %>
				<tr >
					<td colspan="7" align ="center"><input type="button" value="��������" class="btn btn-primary "onclick="window.location='mvindex.jsp'"></td>
				</tr>
			</table>	
		</div>
		
	</div>
 <footer> <jsp:include page="/Bottom.jsp"></jsp:include> </footer>

</body>
</html>