package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProCommand implements CommandIf {

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) {
		BoardDBBean dto = new BoardDBBean();
		dto.setWriter(req.getParameter("writer"));
		dto.setEmail(req.getParameter("email"));
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		dto.setPasswd(req.getParameter("passwd"));
		dto.setNum(Integer.parseInt(req.getParameter("num")));
		BoardDataBean dao = new BoardDataBean();
		int res = dao.updateBoard(dto);
		String msg = null, url = null;
		if (res>0){
			msg = "�Խñ� ���� ����!! �۸���������� �̵��մϴ�.";
			url = "board.app?pageNum=1&perPageNum=10";
		}else if (res<0){
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է��� �ּ���";
			url = "update_form.board?num="+dto.getNum();
		}else {
			msg = "�Խñ� ���� ����!! �ۻ󼼺����������� �̵��մϴ�.";
			url = "content.board?num="+dto.getNum();
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}
}
