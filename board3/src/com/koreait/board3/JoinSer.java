package com.koreait.board3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board3.dao.UserDAO;
import com.koreait.board3.vo.UserVO;


@WebServlet("/join")
public class JoinSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsp/join.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cid = request.getParameter("cid");
		String cpw = request.getParameter("cpw");
		String nm = request.getParameter("nm");
		
		System.out.println("cid : " + cid);
		System.out.println("cpw : " + cpw);
		System.out.println("nm : " + nm);
		
		
		UserVO param = new UserVO();
		param.setCid(cid);
		param.setCpw(cpw);
		param.setNm(nm);
		
		int result = UserDAO.join(param);
		System.out.println(result);
		
	
		
		
		
		
		
		if (result == 1) {
			response.sendRedirect("/login");
		} else {
			request.setAttribute("msg", "회원가입에 실패하였습니다.");
			request.setAttribute("data", param);
			doGet(request, response);
		}
		
		
		
	}

}
