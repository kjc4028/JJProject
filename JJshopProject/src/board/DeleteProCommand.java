package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) {
		String num = req.getParameter("num");
		String passwd = req.getParameter("passwd");
		
		BoardDataBean dao = new BoardDataBean();
		int res = dao.deleteBoard(Integer.parseInt(num), passwd);
		String msg = null, url = null;
		if (res>0){
			msg = "�Խñ� ���� ����!! �۸���������� �̵��մϴ�.";
			url = "board.app?pageNum=1&perPageNum=10";
		}else if (res<0){
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է��� �ּ���";
			url = "delete_form.board?num="+num;
		}else {
			msg = "�Խñ� ���� ����!! �ۻ󼼺����������� �̵��մϴ�.";
			url = "content.board?num="+num;
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}










