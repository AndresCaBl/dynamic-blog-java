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
import blogapp1493150package.model.bean.Comment;

public class CommentDAO {
	
	//Define instance variables
		private String DBURL = "jdbc:mysql://127.0.0.1:3306/BlogDB?serverTimezone=Australia/Melbourne";
	    private String DBUsername = "root";
	    private String DBPassword = "Alejandro50";
	    
	    private String SELECTALLCOMM = "select commentsTbl.commentUser, commentsTbl.commentText from commentsTbl WHERE commentsTbl.postID = ? order by commentsTbl.commentDate DESC";
	    private String INSERTCOMMENT = "insert into commentsTbl (commentUser, commentText, commentDate, postID) Values (?,?, NOW(),?);";
	    
	    public CommentDAO() {}
	    
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
	    public List < Comment > selectComments(int id) {
	    	Connection connection = null; 
	      	PreparedStatement preparedStatement = null;
	      	ResultSet rs=null;
	        // using try-with-resources to avoid closing resources (boiler plate code)
	        List < Comment > comments = new ArrayList < > ();
	        // Step 1: Establishing a Connection
	        try { 
	        	connection = getConnection();
	            // Step 2:Create a statement using connection object
	        	preparedStatement = connection.prepareStatement(SELECTALLCOMM);
	        	preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            rs = preparedStatement.executeQuery();
	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	            	Comment comment = null;
	            	String comUser = rs.getString("commentUser");
	            	if(rs.wasNull()) {
	            		comUser = "a";
	            	}
	            	String comText = rs.getString("commentText");
	            	if(rs.wasNull()) {
	            		comText = "a";
	            	}
	            	comment = new Comment(comUser, comText);
	            	comments.add(comment);
	            }       	
	        	
	        	
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        finally {
	        	finallySQLException(connection,preparedStatement,rs);
	        }
	        return comments;
	    }
	    
	    public void insertComment(Comment com) throws SQLException {
	        System.out.println(INSERTCOMMENT);
	        Connection connection = null; 
	    	PreparedStatement preparedStatement = null;
	        // try-with-resource statement will auto close the connection.
	        try {
	        	connection = getConnection(); 
	        	preparedStatement = connection.prepareStatement(INSERTCOMMENT);
	            preparedStatement.setString(1,com.getCommentUser());
	            preparedStatement.setString(2, com.getCommentText());
	            preparedStatement.setInt(3, com.getPostID());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            	printSQLException(e);
	        } finally {
	        	finallySQLException(connection,preparedStatement,null);
	        }
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
