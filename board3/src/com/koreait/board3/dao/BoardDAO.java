package com.koreait.board3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board3.vo.BoardListModel;
import com.koreait.board3.vo.BoardVO;

public class BoardDAO {

	public static int regBoard(BoardVO param) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i_board = 0;

		String sql = "insert into t_board3 (i_board, title, ctnt, i_user) values (seq_board3.nextval, ?, ?, ?)";

		String cols[] = { "i_board" }; // 컬럼명을 적는다. ex)두개의 값을 받고싶다. {"i_board", "r_dt"};이렇게 적으면 된다!!!
										// pk값을 몰르때 사용!
		try {

			con = DbCon.getCon();
			ps = con.prepareStatement(sql, cols);
			ps.setNString(1, param.getTitle());
			ps.setNString(2, param.getCtnt());
			ps.setInt(3, param.getI_user());
			ps.executeUpdate(); // 잘들어갔니 안들어갔니!
			rs = ps.getGeneratedKeys(); // 방금 넣은 pk값을 넣고 싶을때!!!!

			if (rs.next()) {
				i_board = rs.getInt(1); // 첫번째 열의 값을 달라!!! (String cols[] = {"i_board"}; 의 첫번째 열은 i_board이당!
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DbCon.close(con, ps);
		}

		return i_board;
	}

//	public static List<BoardVO> selectList () {
//		
//		List<BoardVO> list = new ArrayList<BoardVO>();
//		BoardVO param = new BoardVO();
//		
//		
//		
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		
//		String sql = "select i_board, A.title, A.r_dt, B.i_user, B.nm "
//				+ "from t_board3 A "
//				+ "inner join t_user3 B on A.i_user = B.i_user "
//				+ "order by i_board desc";
//		
//		try {
//			
//			con = DbCon.getCon();
//			ps = con.prepareStatement(sql);
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				
//				int i_board = rs.getInt("i_board");
//				String title = rs.getNString("title");
//				String r_dt = rs.getNString("r_dt");
//				int i_user = rs.getInt("i_user");
//				String nm = rs.getNString("nm");
//				
//				param.setI_board(i_board);
//				param.setTitle(title);
//				param.setR_dt(r_dt);
//				param.setI_user(i_user);
//				param.setNm(nm);
//				
//				list.add(param);
//				
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			DbCon.close(con, ps);
//		}
//		
//		
//		return list;
//	}

	public static List<BoardListModel> selectList() {

		List<BoardListModel> list = new ArrayList<BoardListModel>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "   select i_board, A.title, A.r_dt, B.i_user, B.nm as userNm" + " from t_board3 A "
				+ "inner join t_user3 B on A.i_user = B.i_user order by i_board desc   ";

		try {

			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				int i_board = rs.getInt("i_board");
				String title = rs.getNString("title");
				String r_dt = rs.getNString("r_dt");
				int i_user = rs.getInt("i_user");
				String userNm = rs.getNString("userNm");

				BoardListModel bm = new BoardListModel();

				bm.setI_board(i_board);
				bm.setTitle(title);
				bm.setR_dt(r_dt);
				bm.setI_user(i_user);
				bm.setUserNm(userNm);

				list.add(bm);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DbCon.close(con, ps);
		}

		return list;
	}

	public static BoardListModel selectBoard(int i_board) {

		BoardListModel bm = new BoardListModel();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select i_board, B.nm, A.title, A.ctnt, A.r_dt, B.i_user from t_board3 A inner join t_user3 B on A.i_user = B.i_user where i_board = ?";

		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i_board);
			rs = ps.executeQuery();

			while (rs.next()) {
				String userNm = rs.getNString("nm");
				String title = rs.getNString("title");
				String ctnt = rs.getNString("ctnt");
				String r_dt = rs.getNString("r_dt");
				int i_user = rs.getInt("i_user");

				bm.setI_board(i_board);
				bm.setUserNm(userNm);
				bm.setTitle(title);
				bm.setCtnt(ctnt);
				bm.setR_dt(r_dt);
				bm.setI_user(i_user);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DbCon.close(con, ps);
		}

		return bm;

	}

//	public static void boardDel(int i_board) {
//		
//		Connection con = null;
//		PreparedStatement ps = null;
//		
//		String sql = "delete t_board3 where i_board = ?";
//		
//		try {
//			con = DbCon.getCon(); 
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, i_board);
//			ps.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		} finally {
//			DbCon.close(con, ps);
//		}
//		
//	}

	public static int delBoard(BoardVO param) {

		int result = 0;

		Connection con = null;
		PreparedStatement ps = null;

		String sql = "delete t_board3 where i_board = ? and i_user = ?";

		try {

			con = DbCon.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			ps.setInt(2, param.getI_user());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DbCon.close(con, ps);
		}

		return result;
	}

	public static void modBoard(BoardVO param) {

		Connection con = null;
		PreparedStatement ps = null;

		String sql = "update t_board3 set title = ?, ctnt = ? where i_board = ?";

		try {
			con = DbCon.getCon();
			ps = con.prepareStatement(sql);

			ps.setNString(1, param.getTitle());
			ps.setNString(2, param.getCtnt());
			ps.setInt(3, param.getI_board());
			ps.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();

			
		} finally {
			DbCon.close(con, ps);
		}
	}
}
