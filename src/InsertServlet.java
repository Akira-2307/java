

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.board;
import dao.dao;

/**
* Servlet implementation class SelectServlet
*/
@WebServlet("/insert")
@MultipartConfig(maxFileSize=1048576896)
public class InsertServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
  * @see HttpServlet#HttpServlet()
  */
  public InsertServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
  * response)
  */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {

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
      doGet(request, response);


  }


}