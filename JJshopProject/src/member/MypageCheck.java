package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MypageCheck implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) {
		String loginedID = null;
		loginedID = req.getParameter("id");
		System.out.println("�α��� ���̵� " +loginedID);
		
		return "member/mypageCheckForm.jsp?id="+ loginedID;
	}

}
