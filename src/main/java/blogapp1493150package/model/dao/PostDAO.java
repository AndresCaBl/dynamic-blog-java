package blogapp1493150package.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import blogapp1493150package.model.bean.Post;

import blogapp1493150package.model.bean.Category;

public class PostDAO {
	
	//Define instance variables
	private String DBURL = "jdbc:mysql://127.0.0.1:3306/BlogDB?serverTimezone=Australia/Melbourne";
    private String DBUsername = "root";
    private String DBPassword = "Alejandro50";
    
    private String DELETECOMMENTS = "DELETE FROM commentsTbl where postId = ?;";
    private String DELETEPOST = "DELETE FROM PostTbl where postId = ?;";
    
    private String INSERTPOSTSQL = "INSERT INTO PostTbl (title, postContent, postCreationDate, postVisibility, postAuthor, postCategory) VALUES (?, ?, NOW(), ?, ?, ?);";
    private String SELECTAUTHORID = "SELECT userID FROM UsersTbl where username = ?";
    private String CATID = "SELECT categoryID FROM CategoriesTbl where categoryName = ?";
    
    private String SELECTPOSTID = "select Posttbl.postID, PostTbl.title, PostTbl.postCreationDate, PostTbl.postAuthor, categoriesTbl.categoryName, PostTbl.postContent, PostTbl.postVisibility "
    		+ "	from postTbl "
    		+ "inner join CategoriesTbl on PostTbl.postCategory=CategoriesTbl.categoryId where Posttbl.postID = ?  "
    		+ "order by postCreationDate desc;";
    private String SELECTALLPOSTS = "select Posttbl.postID, PostTbl.title, PostTbl.postCreationDate, PostTbl.postAuthor,  categoriesTbl.categoryName, PostTbl.postContent "
    		+ "	from postTbl "
    		+ "inner join CategoriesTbl on PostTbl.postCategory=CategoriesTbl.categoryId "
    		+ "where Posttbl.postVisibility = 1 order by postCreationDate desc;";
    
    private String SELECTALLHIDPOSTS = "select Posttbl.postID, PostTbl.title, PostTbl.postCreationDate, PostTbl.postAuthor, categoriesTbl.categoryName, PostTbl.postContent "
    		+ "	from postTbl "
    		+ "inner join CategoriesTbl on PostTbl.postCategory=CategoriesTbl.categoryId "
    		+ "where Posttbl.postVisibility = 0 order by postCreationDate desc;";

    private String HOMEPOSTS = "select postid, title, postContent from PostTbl where Posttbl.postVisibility = 1 order by postCreationDate DESC LIMIT 3";

    	private String UPDATEPOST ="update PostTBL set title = ?, postContent =?, postVisibility = ?, postAuthor =?, postCategory =? where postID =?;";
    
  //constructor
    public PostDAO() { }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    
    public void deletePost(int id) {
    	Connection connection = null; 
    	PreparedStatement preparedStatement1 = null;
    	PreparedStatement preparedStatement2 = null;
    	try {
        	connection = getConnection(); 
        	preparedStatement1 = connection.prepareStatement(DELETECOMMENTS);
        	preparedStatement1.setInt(1,id);
        	preparedStatement2 = connection.prepareStatement(DELETEPOST);
        	preparedStatement2.setInt(1,id);
            System.out.println(preparedStatement1);
            System.out.println(preparedStatement2);
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            	printSQLException(e);
        } finally {
        	finallySQLException(connection,preparedStatement1,null);
        	finallySQLException(connection,preparedStatement2,null);
        }
    }
    public String selectAuthorID (String author) {
    	Connection connection = null; 
    	PreparedStatement preparedStatement1 = null;
    	ResultSet rs1=null;
    	String authorid = "";
    	try {
        	connection = getConnection(); 
        	preparedStatement1 = connection.prepareStatement(SELECTAUTHORID);
        	preparedStatement1.setString(1,author);
            System.out.println(preparedStatement1);
            rs1= preparedStatement1.executeQuery();
            while (rs1.next()){
            	authorid=rs1.getString("userID");
            }
        } catch (SQLException e) {
            	printSQLException(e);
        } finally {
        	finallySQLException(connection,preparedStatement1,null);
        }
    	return(authorid);

    }
    public String selectCatID (String cat) {
    	Connection connection = null; 
    	PreparedStatement preparedStatement1 = null;
    	ResultSet rs1=null;
    	String catid = "";
    	try {
        	connection = getConnection(); 
        	preparedStatement1 = connection.prepareStatement(CATID);
        	preparedStatement1.setString(1,cat);
            System.out.println(preparedStatement1);
            rs1=preparedStatement1.executeQuery();
            while (rs1.next()){
            	catid=rs1.getString("categoryID");
            }
        } catch (SQLException e) {
            	printSQLException(e);
        } finally {
        	finallySQLException(connection,preparedStatement1,null);
        }
    	return(catid);

    }
    public void insertNewPost(Post post) {
    	Connection connection = null; 
    	PreparedStatement preparedStatement = null;
        // try-with-resource statement will auto close the connection.
        try {
        	connection = getConnection(); 
        	
        	preparedStatement = connection.prepareStatement(INSERTPOSTSQL);
            preparedStatement.setString(1,post.getTitle());
            preparedStatement.setString(2, post.getPostContent());
            preparedStatement.setInt(3, post.getPostVisibility());
            preparedStatement.setString(4, post.getPostAuthor());
            preparedStatement.setInt(5, Integer.parseInt(post.getPostCategory()));
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            	printSQLException(e);
        } finally {
        	finallySQLException(connection,preparedStatement,null);
        }
    	
    }
   
    public boolean updatePost (Post post) throws SQLException {
        boolean postUpdated = false;
        Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	try {
        	connection = getConnection(); 
        	preparedStatement = connection.prepareStatement(UPDATEPOST);
        	preparedStatement.setString(1, post.getTitle());
        	preparedStatement.setString(2, post.getPostContent());
        	preparedStatement.setInt(3, post.getPostVisibility());
        	preparedStatement.setString(4, post.getPostAuthor());
        	preparedStatement.setString(5, post.getPostCategory());
        	preparedStatement.setInt(6, post.getPostID());

        	postUpdated = preparedStatement.executeUpdate() > 0 ? true:false;
        }
        catch (SQLException e) {
        	printSQLException (e);
        }     
      	finally {
        	finallySQLException(connection,preparedStatement,null);
        }
        return postUpdated;
    }
    public Post selectUniquePosts(int id) {
		// TODO Auto-generated method stub
    	
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
      	Post post = null;
        // Step 1: Establishing a Connection
        try { 
        	connection = getConnection();
            // Step 2:Create a statement using connection object
        	preparedStatement = connection.prepareStatement(SELECTPOSTID);
        	preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int postId = rs.getInt("postID");
            	String title = rs.getString("title");
            	String postCreationDate = rs.getString("postCreationDate");
            	String postAuthor = rs.getString("postAuthor");
            	String postCategory = rs.getString("categoryName");
            	String postContent = rs.getString("postContent");
            	int postVisi = rs.getInt("postVisibility");
                post = new Post(postId, title, postCreationDate, postCategory, postAuthor, postContent, postVisi);

                
            }      	
        	
        	
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        
        return post;
	}
    public List < Post > selectHomePosts() {
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Post > posts = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        	connection = getConnection();
            // Step 2:Create a statement using connection object
        	preparedStatement = connection.prepareStatement(HOMEPOSTS);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	Post post = null;
            	int postid = rs.getInt("postId");
            	String title = rs.getString("title");
            	String postContent = rs.getString("postContent");
            	
                post = new Post(postid, title, postContent);
                posts.add(post);
            }       	
        	
        	
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return posts;
    }
    public List < Post > selectAllPosts() {
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Post > posts = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        	connection = getConnection();
            // Step 2:Create a statement using connection object
        	preparedStatement = connection.prepareStatement(SELECTALLPOSTS);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	Post post = null;
            	int id = rs.getInt("postID");
            	String title = rs.getString("title");
            	String postCreationDate = rs.getString("postCreationDate");
            	String postAuthor = rs.getString("postAuthor");
            	String postCategory = rs.getString("categoryName");
            	String postContent = rs.getString("postContent");  	 	
            		post = new Post(id, title, postContent, postCreationDate,postAuthor, postCategory);
                posts.add(post);
            }       	
        	
        	
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return posts;
    }
    public List < Post > selectAllHidPosts() {
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Post > posts = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        	connection = getConnection();
            // Step 2:Create a statement using connection object
        	preparedStatement = connection.prepareStatement(SELECTALLHIDPOSTS);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	Post post = null;
            	int id = rs.getInt("postID");
            	String title = rs.getString("title");
            	String postCreationDate = rs.getString("postCreationDate");
            	String postAuthor = rs.getString("postAuthor");
            	String postCategory = rs.getString("categoryName");
            	String postContent = rs.getString("postContent");  	 	
                post = new Post(id, title, postContent, postCreationDate, postAuthor, postCategory);
                posts.add(post);
            }       	
        	
        	
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return posts;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    private void finallySQLException(Connection c, PreparedStatement p, ResultSet r){
    	 if (r != null)	{ //no result?
             try {
                r.close();
             } catch (Exception e) {}
                r = null;
             }
 	
          if (p != null) {
             try {
                p.close();
             } catch (Exception e) {}
                p = null;
             }
 	
          if (c != null) {
             try {
                c.close();
             } catch (Exception e) {
           	  c = null;
             }

          }
    }

		
}