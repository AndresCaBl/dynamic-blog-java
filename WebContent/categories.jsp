<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Andres BLOG | Categories</title>
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
                        <a href="${pageContext.request.contextPath}/PostServlet?action=posts" class="nav-link ">Posts</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/CategoriesServlet?action=categories" class="nav-link active-tab">Categories</a>
                    </li>
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/LoginServlet?action=validateSession" class="nav-link">Admin</a>
                    </li>
                </ul>
            </nav>
        </header>
        <main>
       		 <div class="page-title">
            	<h2>All Categories</h2>
             </div>
            <div class="categories">
            <c:forEach var="category" items="${categories}">
                <h3 class="category uppercase"><c:out value="${category.getCategoryName()}" /></h3>               
	        </c:forEach>
            </div>
            
         </main>

        <script src="" async defer></script>
    </body>
</html>