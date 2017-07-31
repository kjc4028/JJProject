package member;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import loginDao.LoginDao;


public class LoginCommander implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) {
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
	
		LoginDao logdao = new LoginDao();
		try {
			System.out.println("���� ���̵�"+id);
			System.out.println("���������"+password);
			String a = logdao.login(id, password);
			System.out.println("a:"+a);
			String msg = null, url = null;
			HttpSession session = req.getSession();
				if (a=="1"){
					session.setAttribute("userID", id);
					msg = "�α��� ����!!������������ �̵��մϴ�";
					url = "main.jsp";
				}else if (a!="1"){
					msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է��� �ּ���";
					url = "loginForm.app";
				}
				req.setAttribute("msg",msg);
				req.setAttribute("url", url);
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}


		return "message.jsp";
			
	}
}