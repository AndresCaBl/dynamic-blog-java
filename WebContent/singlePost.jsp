<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Andres BLOG | <c:out value="${singlePost.getTitle()}"/></title>
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
            <div class ="post">
            	<div class="page-title">
            	<h3 class="title uppercase">
            		<c:out value="${singlePost.getTitle()}"/>
            	</h3>
            	</div >
            	<div class="post-content">
	                <p><span class="post-label">Created: </span><c:out value="${singlePost.getPostCreationDate()}"/></p>
	                <p><span class="post-label">Category: </span> <c:out value="${singlePost.getPostCategory()}"/></p>
	                <p class="post-text"><c:out value="${singlePost.getPostContent()}"/></p>  
              	</div>
            </div>  
           	<div class="comments">
           	<!-- postServlet handles comments also -->
           	
           	<h4>Comments</h4>
	           	<div class="comments-block">
		           	<c:forEach var="comment" items="${comment}">	
		           		<div class="postComment">
			           		<span class="author"><c:out value="${comment.getCommentUser()}" />: </span>
			            	<span class="comment"><c:out value="${comment.getCommentText()}" /></span><br>
		           		</div>
		           	</c:forEach>
	           	</div>
			
			<!-- call CommentServlet to hanlde a new comment -->
			
           	<h5>Add Comment</h5>
           		<div class="commentForm">
           			<form action="CommentServlet?action=newComment&postid=<c:out value="${param.postid}"/>" method="post">
           			<label for="name">Name</label><br>
					<input name="name" type="text" placeholder="Enter Your Name" required><br><br>
					<label for="comment">Comment</label><br>
					<textarea name="comment" type="text" placeholder="Enter Your Comment Here" required></textarea><br>
					<button class="button" type="submit">Post Comment</button> 
           			</form>
           		</div> 
            	
           	</div>   
           	 
            

         </main>

        <script src="" async defer></script>
    </body>
</html>