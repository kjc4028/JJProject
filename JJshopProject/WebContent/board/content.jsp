<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="board.*"%>
<!-- content.jsp -->
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
	<title>�۳���</title>
</head>
<%		BoardDBBean dto = (BoardDBBean)request.getAttribute("getBoard"); %>
<body>
<%
String Wuser = dto.getWriter();
String userID = null;
if(session.getAttribute("userID") != null){
   userID = (String)session.getAttribute("userID");
}

System.out.println(Wuser);
%>
<jsp:include page="/menu.jsp"></jsp:include>
	<div class="container">
			 <div class="row">
		<b>�۳��� ����</b>
		<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
			<tr>
				<th style="background-color: #eeeeee; text-align: center;">�۹�ȣ</th>
				<td align="center"><%=dto.getNum()%></td>
				<th style="background-color: #eeeeee; text-align: center;">��ȸ��</th>
				<td align="center"><%=dto.getReadcount()%></td>
			</tr>
			<tr>
				<th style="background-color: #eeeeee; text-align: center;">�ۼ���</th>
				<td align="center"><%=dto.getWriter()%></td>
				<th style="background-color: #eeeeee; text-align: center;">�ۼ���</th>
				<td align="center"><%=dto.getReg_date()%></td>
			</tr>
			<tr>
				<th style="background-color: #eeeeee; text-align: center;">������</th>
				<td colspan="3" align="center"><%=dto.getSubject()%></td>
			</tr>
			<tr>
				<th style="background-color: #eeeeee; text-align: center;">�۳���</th>
				<td colspan="3" align="center"><%=dto.getContent()%></td>
			</tr>
<%		if (dto.getFilesize()>0){%>
			<tr>
				<th style="background-color: #eeeeee; text-align: center;">���ϸ�</th>
				<td colspan="3" align="center"><%=dto.getFilename()%></td>
			</tr>			
<%		}%>			
			<tr>
				<td colspan="4" align="right">
						<%
          if(userID != null){
        	  if(userID.equals(Wuser)){
        	  
          
          %>
					<input type="button" value="��۴ޱ�" class="btn btn-primary pull-right"
						onclick="window.location='write_form.board?num=<%=dto.getNum()%>&re_step=<%=dto.getRe_step()%>&re_level=<%=dto.getRe_level()%>&re_renum=<%=dto.getRe_renum()%>'">					
					<input type="button" value="�ۼ���" class="btn btn-primary pull-right"onclick="window.location='update_form.board?num=<%=dto.getNum()%>'">
					<input type="button" value="�ۻ���" class="btn btn-primary pull-right"onclick="window.location='delete_form.board?num=<%=dto.getNum()%>'">
					<input type="button" value="�۸��" class="btn btn-primary pull-right"onclick="window.location='board.app?pageNum=1&perPageNum=10'">
				<%}else if(!userID.equals(Wuser)){
					%>
					<input type="button" value="�۸��" class="btn btn-primary pull-right"onclick="window.location='board.app?pageNum=1&perPageNum=10'">
					<input type="button" value="��۴ޱ�" class="btn btn-primary pull-right"
						onclick="window.location='write_form.board?num=<%=dto.getNum()%>&re_step=<%=dto.getRe_step()%>&re_level=<%=dto.getRe_level()%>&re_renum=<%=dto.getRe_renum()%>'">					
				<% }%>
				
			<% }else{%>
				<input type="button" value="�۸��" class="btn btn-primary pull-right"onclick="window.location='board.app?pageNum=1&perPageNum=10'">
			<% }%>
			</td>
			</tr>
		</table>
	</div>
	</div>
	 <jsp:include page="/Bottom.jsp"></jsp:include> 

</body>
</html>






