package blogapp1493150package.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import blogapp1493150package.model.bean.Login;
import blogapp1493150package.model.bean.Post;

public class LoginDAO {
	
	private String DBURL = "jdbc:mysql://127.0.0.1:3306/BlogDB?serverTimezone=Australia/Melbourne";
    private String DBUsername = "root";
    private String DBPassword = "Alejandro50";
    
    private String SELECTLOGIN =  ("select * from UsersTBL where uname = ? and upassword = ? ");
	
	public LoginDAO() {}
	
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
	
	public static boolean validate(Login loginBean) throws ClassNotFoundException {
		boolean status = false;
		
		Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
      	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BlogDB?serverTimezone=Australia/Melbourne","root", "Alejandro50");
        	preparedStatement = connection.prepareStatement("select * from UsersTBL where userName = ? and userPassword = ? ");
        	preparedStatement.setString(1, loginBean.getUname());
        	preparedStatement.setString(2, loginBean.getUpassword());
			
			rs = preparedStatement.executeQuery(); 
			status = rs.next();
		}
	 catch (SQLException e) { 
		e.printStackTrace(System.err);
		System.err.println("SQLState: " + ((SQLException) e).getSQLState()); 
		System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
		System.err.println("Message: " + e.getMessage());		
	 } finally {
		 if (rs != null) {
			 try {
				 rs.close();
				 }catch (Exception e) {
					 
				 }
			 	rs = null;
		 }
		 if (preparedStatement != null) {
			 try {
				 preparedStatement.close();
			 }catch (Exception e) {
				 
			 }
			 preparedStatement = null;
		 }
		 if (connection != null) {
			 try {
				 connection.close();
				 } catch (Exception e) {
					 connection = null;
				 }
		 }
	 }
		return status;
}
}
