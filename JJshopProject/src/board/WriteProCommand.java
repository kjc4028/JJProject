package board;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class WriteProCommand implements CommandIf{

	@Override
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) {
		
		MultipartRequest mr = null;
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("board/files");
		int len = 10*1024*1024;
		try{
			req.setCharacterEncoding("utf-8");
			mr = new MultipartRequest(req, upPath, len, "utf-8");
			//mr��ü�� ���������, ���Ͼ��ε� ����!!, ��ü ���н� IOException �߻�!!
		}catch(IOException e){
			System.err.println("���� ���ε� �� ����!!");
		}
		BoardDBBean dto = new BoardDBBean();
		BoardDataBean dao = new BoardDataBean();
		
		
		if(mr.getParameter("re_level").equals("0")){
			dto.setRe_level(Integer.parseInt(dao.maxNum()));
		}else if(!mr.getParameter("re_level").equals("0")){
			dto.setRe_level(Integer.parseInt(mr.getParameter("re_level"))); //�׷� ���̵�
		}
	

		if(mr.getParameter("re_renum").equals("new")){
			dto.setRe_renum(0);
			System.out.println("no����"+mr.getParameter("re_renum"));
		}else if(!mr.getParameter("re_renum").equals("new")){
			int rss = Integer.parseInt(mr.getParameter("re_renum"));
			System.out.println("aaat����"+Integer.parseInt(mr.getParameter("re_renum")));
			dto.setRe_renum(Integer.parseInt(mr.getParameter("re_renum"))); //�� �׷�ȿ����� ���� ���̵�
		}
		
	
		
		
		
		
		if(mr.getParameter("re_step").equals("0")){
			dto.setRe_step(Integer.parseInt(mr.getParameter("re_step")));  //��� ����ŭ �鿩����

		}else if(!mr.getParameter("re_step").equals("0")){
			dto.setRe_step(Integer.parseInt(mr.getParameter("re_step"))+1);  //��� ����ŭ �鿩����
		}
		
		
		
		dto.setWriter(mr.getParameter("writer"));
		dto.setEmail(mr.getParameter("email"));
		dto.setSubject(mr.getParameter("subject"));
		dto.setContent(mr.getParameter("content"));
		dto.setPasswd(mr.getParameter("passwd"));
		dto.setIp(req.getRemoteAddr());
		dto.setFilename(mr.getFilesystemName("filename"));
		int filesize = 0;
		File file = mr.getFile("filename");
		if (file != null){
			filesize = (int)file.length();
		}
		dto.setFilesize(filesize);
		
		
		int res = 0;
		if(mr.getParameter("re_level").equals("0") ){
			res = dao.insertBoard(dto);
			System.out.println("�Ϲ��߰�");
		}else if( !mr.getParameter("re_level").equals("0")){
			res=dao.replyBoard(dto);
			System.out.println("�����߰�");

		}
		String msg = null, url = null;
		if (res>0){
			msg = "�Խñ� ��� ����!! �۸���������� �̵��մϴ�";
			url = "board.app?pageNum=1&perPageNum=10";
		}else {
			msg = "�Խñ� ��� ����!! �۵���������� �̵��մϴ�.";
			url = "write_form.board";
		}
		req.setAttribute("msg", msg);
		req.setAttribute("url", url);
		return "message.jsp";
	}

}










