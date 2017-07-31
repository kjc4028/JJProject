package member;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MemberDao.MemberDao;

public class IdOverlapCheckCommander implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) {

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		String viewpage = null;
	
		try {
			String id = req.getParameter("id");
			
			MemberDao dao = new MemberDao();
			String a = dao.idCheck(id);
			
			if(a.equals("1")){
				viewpage = "member/canuseid.jsp?num=1";
				System.out.println("id��밡��");
				
				
			}else if(a.equals("2")){
				viewpage = "member/cantuseid.jsp?num=2";
				System.out.println("id ���Ұ�");
			

			}
			
			 
		} catch (Exception e) {
e.printStackTrace();
		}

		
		
		
		return viewpage;
	}

	
	
}
