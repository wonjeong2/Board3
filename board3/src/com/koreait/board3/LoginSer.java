package com.koreait.board3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board3.dao.UserDAO;
import com.koreait.board3.vo.UserVO;

@WebServlet("/login")
public class LoginSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cid = request.getParameter("cid");
		String cpw = request.getParameter("cpw");

		UserVO param = new UserVO();
		param.setCid(cid);
		param.setCpw(cpw);
		
		int result = UserDAO.login(param);
		

		
		if (result == 1) {
			HttpSession hs = request.getSession();
			
			param.setCpw(null);
			hs.setAttribute("loginUser", param);
			response.sendRedirect("/boardList");
			return;
		} 
			
		String msg = "에러발생";  //db과부화, db를 못찾았거나, 쿼리문을 잘못썼거나			
			
		switch(result) {
			case 2 :
				msg = "아이디가 없습니다.";
				break;
			case 3 :
				msg = "비밀번호를 확인해주세요.";
				break;
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("writedCid", cid);
			request.setAttribute("writedCpw", cpw);
			
			doGet(request, response);
	
		}

	}
