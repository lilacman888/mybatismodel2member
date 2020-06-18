package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import model.MemberDTO;

public class Delete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Delete");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
//		String id = (String)session.getAttribute("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO old = dao.getMember(id);
		System.out.println("id : " + id);	
		System.out.println("passwd : " + passwd);
		System.out.println("oldpw : " + old.getPasswd());
		if(old.getPasswd().equals(passwd)) {	// 비번 일치시
			int result = dao.delete(id);
			if(result == 1) {
				session.invalidate();
				System.out.println("회원 탈퇴 성공");
			}
		}else {											// 비번 불일치시
			out.print("<script>");
			out.print("alert('회원 탈퇴 실패');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
			
			return null;
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/LoginForm.do");
		return forward;
	}

}
