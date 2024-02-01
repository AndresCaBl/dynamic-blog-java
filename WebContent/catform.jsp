<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Andres BLOG | Add new Category</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/Styles.css">
    </head>
    <body>
        <header>
            
            <nav class="navbar">
                <div class="header">
                    <h1>ANDRES Blog</h1>
                </div>
                <ul class="nav-menu">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/PostServlet?action=home" class="nav-link">Home</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/PostServlet?action=posts" class="nav-link">Posts</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/CategoriesServlet?action=categories" class="nav-link">Categories</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/LoginServlet?action=validateSession" class="nav-link">Admin</a>
                    </li>
                </ul>
            </nav>
        </header>
        <main>
            <div>
            	<div class="page-title">
	            	<h3 class="title uppercase">
	            		Add new Category
	            	</h3>
            	</div >
            	<div class="catForm">
            	<br><a href="${pageContext.request.contextPath}/LoginServlet?action=validateSession" class="nav-link">Go Back to Admin Console</a><br><br>
           		
           			<form action="CategoriesServlet?action=newCat" method="post">
           			<label for="catName">Category Name</label><br>
					<input name="catName" type="text" placeholder="Enter The Name of The New Category" required><br><br>
					<button class="button" type="submit">Save New Category</button> 
           			</form>
           		</div> 
            	
           	</div>   
           	 
            
        	<%-- <jsp:include page="comments.jsp">
         		<jsp:param value="showComments" name="action"/>
         		<jsp:param value="${param.postid}" name="postid"/>
         	</jsp:include> --%>

         </main>

        <script src="" async defer></script>
    </body>
</html>