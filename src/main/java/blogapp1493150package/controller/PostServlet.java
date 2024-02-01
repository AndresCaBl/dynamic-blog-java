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
import javax.servlet.http.HttpSession;

import blogapp1493150package.model.dao.PostDAO;
import blogapp1493150package.model.bean.Post;

import blogapp1493150package.model.dao.*;
import blogapp1493150package.model.bean.Category;
import blogapp1493150package.model.bean.Comment;
/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostDAO postdao;
	private CommentDAO comdao;
	private CategoryDAO catdao;

    /**
     * Default constructor. 
     */
    public PostServlet() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public void init() {
    	postdao = new PostDAO();
    	comdao = new CommentDAO();
    	catdao = new CategoryDAO();
   	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

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
			case "new":
				showNewPost(request, response);
				break;
			case "newPost":
				insertNewPost(request, response);
				break;
			case "delete":
				deleteExistingPost(request, response);
				break;
			case "edit":
				showEditPost(request, response);
				break;
			case "update":
				updateExistingPost(request, response);
				break;
			case "posts":
				listPost(request, response);
				break;
			case "singlePost":
				singlePost(request, response);
				break;
			case "hidden":
				showHiddenPost(request, response);
			default:
				homePosts(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void showHiddenPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Post> allPost = postdao.selectAllHidPosts();
		request.setAttribute("listPosts", allPost);
		RequestDispatcher dispatcher = request.getRequestDispatcher("posts.jsp");
		dispatcher.forward(request, response);
	}

	private void singlePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("postid"));
		Post singleP = postdao.selectUniquePosts(id);
		List < Comment > comments = comdao.selectComments(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("singlePost.jsp");
		request.setAttribute("singlePost", singleP);
		request.setAttribute("comment", comments);
		dispatcher.forward(request, response);
	}

	private void homePosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Post> allPost = postdao.selectHomePosts();
		request.setAttribute("homePosts", allPost);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}

	private void listPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Post> allPost = postdao.selectAllPosts();
		request.setAttribute("listPosts", allPost);
		RequestDispatcher dispatcher = request.getRequestDispatcher("posts.jsp");
		dispatcher.forward(request, response);
	}

	private void updateExistingPost(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		int postVis;
		int postId = Integer.parseInt(request.getParameter("id"));
		String postTitle = request.getParameter("postTitle");
		String postCont = request.getParameter("postCont");
		if(request.getParameter("visibility") == null) {
			postVis = 0;
		}
		else {
			postVis = 1;
		}
		String postAuthor = (String) request.getParameter("author");
		String postCat = (String) postdao.selectCatID(request.getParameter("cate")); 		
		Post npost = new Post(postId,postTitle, postCont,postVis,postAuthor,postCat);
		postdao.updatePost(npost);
		response.sendRedirect(request.getContextPath() + "/LoginServlet?action=validateSession");
	}

	private void showEditPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		Post existingPost = postdao.selectUniquePosts(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("postform.jsp");
		request.setAttribute("post", existingPost);
		List<Category> allCategories = catdao.selectAllCategories();
		request.setAttribute("categories", allCategories); 
		dispatcher.forward(request, response);
	}

	private void deleteExistingPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int postid = Integer.parseInt(request.getParameter("id"));
		postdao.deletePost(postid);
		response.sendRedirect(request.getContextPath() + "/LoginServlet?action=validateSession");
	}

	private void insertNewPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int postVis;
		String postTitle = request.getParameter("postTitle");
		String postCont = request.getParameter("postCont");
		if(request.getParameter("visibility") == null) {
			postVis = 0;
		}
		else {
			postVis = 1;
		}
		String postAuthor = (String) postdao.selectAuthorID((String) session.getAttribute("username"));
		String postCat = (String) postdao.selectCatID(request.getParameter("cate")); 		
		Post npost = new Post(postTitle, postCont,postVis,postAuthor,postCat);
		System.out.println("this is the cat:"+postCat);
		postdao.insertNewPost(npost);
		response.sendRedirect(request.getContextPath() + "/LoginServlet?action=validateSession");
	}

	private void showNewPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {//action="new"
		RequestDispatcher dispatcher = request.getRequestDispatcher("postform.jsp");
		List<Category> allCategories = catdao.selectAllCategories();
		request.setAttribute("categories", allCategories);
		dispatcher.forward(request, response);
		
	
	}

}
