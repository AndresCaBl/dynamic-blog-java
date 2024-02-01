package blogapp1493150package.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;

import blogapp1493150package.model.dao.CommentDAO;
import blogapp1493150package.model.dao.PostDAO;
import blogapp1493150package.model.bean.Comment;
import blogapp1493150package.model.bean.Post;
/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDAO comDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public void init() {
    	comDAO = new CommentDAO();
   	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost (request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null) {
			action = "No action";
		}
		try {
			switch (action) {
			case "postcom":
				showComments(request, response);
				break;
			case "newComment":
				newComment(request, response);
				break;
			default:
				showComments(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void newComment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int postid = Integer.parseInt(request.getParameter("postid"));
		String comName = request.getParameter("name");
		String comText = request.getParameter("comment");
		Comment com = new Comment(postid, comName, comText);
		comDAO.insertComment(com);
		response.sendRedirect(request.getContextPath() + "/PostServlet?action=singlePost&postid="+postid);

	}

	private void showComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=16;
		System.out.println(id);
		List<Comment> allComments = comDAO.selectComments(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("comments.jsp");
		request.setAttribute("comPost", allComments);
		dispatcher.forward(request, response);
	}

}
