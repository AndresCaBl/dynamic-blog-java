<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <c:choose>
        <c:when test="${param.visible == 'false'}">
        	 <title>Andres BLOG | Hidden Posts</title>
        </c:when>
        <c:when test="${param.auth == 'true'}">
        	 <title>Andres BLOG | ADMIN CONSOLE</title>
        </c:when>
        <c:otherwise>
        	<title>Andres BLOG | Posts</title>
        </c:otherwise>
        </c:choose>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/Styles.css">
        <script>
        
        </script>
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
                        <a href="${pageContext.request.contextPath}/PostServlet?action=posts" class="nav-link active-tab">Posts</a>
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
        <div class="content_wrap">
		<c:choose>
		<c:when test="${param.visible == 'false'}">
        	<div class="page-title">	
        		<h2>HIDDEN POSTS</h2>
        		<a href="${pageContext.request.contextPath}/LoginServlet?action=validateSession" class="nav-link">Go Back to Admin Console</a>
        	</div>
        </c:when>
			<c:when test="${param.auth == 'true'}">
				<div class="page-title">
	        		<h2>ADMIN PAGE</h2>
	        		 <a href="${pageContext.request.contextPath}/PostServlet?action=new" class="nav-link">Create New Post</a>
	        		 <a href="${pageContext.request.contextPath}/CategoriesServlet?action=newC" class="nav-link">New Category</a>
	        		 <a href="${pageContext.request.contextPath}/PostServlet?action=hidden&auth=true&visible=false" class="nav-link">Show Hiddent Posts</a>
	        		 <a href="${pageContext.request.contextPath}/LoginServlet?action=logout" class="nav-link">Logout</a>
	        	</div>
			</c:when>
			<c:otherwise>
				<div class="page-title">
	        		<h2>All Posts</h2>
	        	</div>
			</c:otherwise>
		</c:choose>   
		
		
    	
            	<c:forEach var="post" items="${listPosts}">
	            	<div class ="single-post">
	            		<h3 class="title uppercase">
	            			<a href= "${pageContext.request.contextPath}/PostServlet?action=singlePost&postid=<c:out value='${post.getPostID()}'/>"><c:out value="${post.getTitle()}"/></a>
	            		</h3>
	               		<p><span class="post-label">Created: </span> <c:out value="${post.getPostCreationDate()}" /></p>
	                	<p><span class="post-label">Category: </span><c:out value="${post.getPostCategory()}" /></p>
	                	<p class="post-text"><c:out value="${post.getPostContent()}" /></p>  
	                	<c:if test="${param.auth == 'true'}">
	                		<a href="${pageContext.request.contextPath}/PostServlet?action=delete&id=<c:out value="${post.getPostID()}"/>" class="nav-link" onclick="return confirm('Are you sure you want to delete this post?')">Delete Post</a>
	                		<a href="${pageContext.request.contextPath}/PostServlet?action=edit&id=<c:out value="${post.getPostID()}"/>" class="nav-link">Edit Post</a>
	                	</c:if>
	            	</div>         
	        	</c:forEach>
        </div>
            
         </main>

        <script src="" async defer></script>
    </body>
</html>