package order;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderInsertCommander implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

		OrderDAO dao = new OrderDAO();
		ArrayList<OrderDTO> dto = new ArrayList<OrderDTO>();

		int pdnum = (Integer.parseInt(req.getParameter("pdnum")));
		String orname = req.getParameter("orname");
		int orprice = (Integer.parseInt(req.getParameter("orprice")));
		String orsize = req.getParameter("orsize");
		int orcnt = (Integer.parseInt(req.getParameter("orcnt")));
		String orcolor = req.getParameter("orcolor");
		String oruser = req.getParameter("oruser");
		String oraddr = req.getParameter("oraddr");
		int category =  (Integer.parseInt(req.getParameter("category")));
		String img = req.getParameter("img");
		String msg = null, url = null;
		
		
		HttpSession session = req.getSession();
		String userID = (String) session.getAttribute("userID");
		







		try {
			if(userID != null){
			dao.addOrder(pdnum, orname, orprice, orsize, orcnt, orcolor, oruser, oraddr,category,img);

			msg = "주문 성공!";
			url="orderlist.order";
			}else if(userID == null){
				msg = "주문 실패! 로그인을 먼저 해주세요.";
				url="loginForm.member";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		req.setAttribute("list", dto);
		req.setAttribute("msg",msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}
