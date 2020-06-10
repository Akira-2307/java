

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.board;
import dao.dao;

/**
* Servlet implementation class SelectServlet
*/
@WebServlet("/toppage")
public class toppage extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
  * @see HttpServlet#HttpServlet()
  */
  public toppage() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
  * response)
  */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    String mail = request.getParameter("mail");
    String comment = request.getParameter("comment");
    String time = request.getParameter("time");
    String time2 = request.getParameter("time2");


    board b = new board(name,mail,comment,time,time2);
    //1件追加
    dao.insertboard(b);

    //全件取得
    ArrayList<board> list = new ArrayList<board>();
    list = dao.selectAllboard();

    request.setAttribute("list", list);

    String view = "/WEB-INF/view/board.jsp";
	  RequestDispatcher dispatcher = request.getRequestDispatcher(view);
	  dispatcher.forward(request, response);
  }

  /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
  * response)
  */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }



}