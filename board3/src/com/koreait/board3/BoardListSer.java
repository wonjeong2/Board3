package com.koreait.board3;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.board3.dao.BoardDAO;

@WebServlet("/boardList")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		 List<BoardListModel> list = BoardDAO.selectList();
//		 request.setAttribute("list", list);

		HttpSession hs = request.getSession();

		if (hs.getAttribute("loginUser") == null) {
			response.sendRedirect("/login");
		} else {
			request.setAttribute("data", BoardDAO.selectList()); // 이렇게 쓰면 import안해줘도 된다!!!!
			request.getRequestDispatcher("/WEB-INF/jsp/boardList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
