package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String uri = req.getRequestURI();			//��ü���
		String path = req.getContextPath();		//���۰��
		String cmd = uri.substring(path.length()); //���ϰ��� �ϴ� �̸�
		
		MemberFactory factory = MemberFactory.getInstance();
		CommandIf cmdIf = factory.createCommand(cmd);
		String nextPage = (String)cmdIf.processCommand(req, resp);
		

		RequestDispatcher view = req.getRequestDispatcher(nextPage);
		view.forward(req, resp);
	}

	
}
