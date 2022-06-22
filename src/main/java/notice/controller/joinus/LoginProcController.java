package notice.controller.joinus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.controller.Controller;
import notice.dao.MemberDao;
import notice.dao.NoticeDao;
import notice.vo.Member;
import notice.vo.Notice;

public class LoginProcController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginProcController pass");
		String uid = request.getParameter("id");
		String pwd = request.getParameter("password");

		MemberDao dao = new MemberDao();
		Member m = dao.getMember(uid);

		if (m == null) {// 아이디 없음
			request.setAttribute("error", "아이디 없음");
			request.getRequestDispatcher("loginform.jsp").forward(request, response);
		} else if (!m.getPwd().equals(pwd)) {// 비밀번호 틀림
			request.setAttribute("error", "비밀번호 불일치");
			request.getRequestDispatcher("loginform.jsp").forward(request, response);
		} else {// 로그인 성공
				// 세션에 아이디 보관 go notice.do
			request.getSession().setAttribute("uid", uid);
			response.sendRedirect("../customer/notice.do");

		}

	}

}