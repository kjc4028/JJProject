<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="board.*"%>
<html>
<head>
<title>�Խ���</title>
</head>
<%
	BoardDBBean dto = (BoardDBBean) request.getAttribute("getBoard");
%>
<body>
	<%
		String contextpath = request.getContextPath();
	%>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
	%>

	<%
		if (userID != null) {
	%>

	<jsp:include page="/menu.jsp"></jsp:include>
	<div class="contaniner">
		<div class="row">
			<form name="f" action="update_pro.board" method="post">
				<input type="hidden" name="num" value="<%=dto.getNum()%>">
				<table style="width: 600px;" class="table table-striped"
					style="text-align: center; border:1px solid #dddddd "
					align="center">
					<tr>
						<th colspan="2">�ۼ���</th>
					</tr>
					<tr>
						<th>�̸�</th>
						<td><input type="text" class="form-control" name="writer"
							value="<%=userID%>"></td>
					</tr>
					<tr>
						<th>����</th>
						<td><input type="text" class="form-control" name="subject"
							value="<%=dto.getSubject()%>"></td>
					</tr>
					<tr>
						<th>Email</th>
						<td><input type="text" class="form-control" name="email"
							value="<%=dto.getEmail()%>"></td>
					</tr>
					<tr>
						<th>����</th>
						<td><textarea name="content" class="form-control" rows="10"
								cols="80"><%=dto.getContent()%></textarea></td>
					</tr>
					<tr>
						<th>��й�ȣ</th>
						<td><input type="password" class="form-control" name="passwd"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="�ۼ���" class="btn btn-primary pull-right"> <input
							type="reset" value="�ٽ��ۼ�" class="btn btn-primary pull-right">
							<input type="button" value="��Ϻ���"
							class="btn btn-primary pull-right"
							onclick="window.location='list.board'"></td>
					</tr>
				</table>
			</form>
			<%
				}
			%>
		</div>
	</div>
	<center>
		<footer>
			<jsp:include page="/Bottom.jsp"></jsp:include>
		</footer>
	</center>
</body>
</html>