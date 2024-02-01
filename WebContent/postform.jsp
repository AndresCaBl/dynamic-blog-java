<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
    <head>
        <c:choose>
        <c:when test="${param.action == 'new'}">
        	 <title>Andres BLOG | Create New Post</title>
        </c:when>
        <c:when test="${param.auth == 'edit'}">
        	 <title>Andres BLOG | ADMIN CONSOLE</title>
        </c:when>
        </c:choose>
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
        	<div class="content_wrap">
				<c:choose>
					<c:when test="${post == null}">
		        		<div class="page-title">	
		        			<h2>Add New Post</h2>
		        		</div>
	       			</c:when>
					<c:when test="${post != null}">
						<div class="page-title">
		        			<h2>Edit Post</h2>
		        		</div>
					</c:when>
				</c:choose> 
					<div class="post-form" style="margin:10px;">
					<br><a href="${pageContext.request.contextPath}/LoginServlet?action=validateSession" class="nav-link">Go Back to Admin Console</a><br><br>
					<c:if test="${post != null}">
						<form name="postF" action="${pageContext.request.contextPath}/PostServlet" method="post" id="postForm">
						<input type="hidden" name="action" value="update" >
					</c:if>
					<c:if test="${post == null}">
						<form name="postF" action="${pageContext.request.contextPath}/PostServlet?action=newPost" method="post" id="postForm">
					</c:if>
				
					<c:if test="${post != null}">
						<input type="hidden" name="id" value="<c:out value='${post.getPostID()}' />" />
						<input type="hidden" name="author" value="<c:out value='${post.getPostAuthor()}' />" />
					</c:if>
				
					<h3><label for="postTitle">Post Title</label><br></h3>
				  	<input type="text" id="postTitle" name="postTitle" required value="<c:out value='${post.getTitle()}' />"><br>
				  	<h3><label for="postCategory">Post Category</label><br></h3>
				  	<select name="cate" id="selectOption">
					  	<c:choose>
					  		<c:when test="${post != null}">
					  			<option value="<c:out value="${post.getPostCategory()}" />" selected hidden><c:out value="${post.getPostCategory()}"/></option>
					  		</c:when>
					  		<c:otherwise>
					  			<option id="defaulfSelectOption" name="catOp" value="Uncategorized" selected hidden>Choose here</option>
					  		</c:otherwise>
					  	</c:choose>
				  		<c:forEach var="category" items="${categories}">
				  	 		<option name="catOp" value=<c:out value="${category.getCategoryName()}" />><c:out value="${category.getCategoryName()}" /></option>              
			       	</c:forEach>
				  	</select><br><br>
				  	 	
				  	<h3><label for="postCont">Post Content</label><br><br></h3>
				  	<textarea id="postCont" name="postCont" type="text" placeholder="Enter Your Post Here" required><c:out value='${post.getPostContent()}' /></textarea><br>
				  	<h3><label for="visibility">Visible?</label></h3>
				  	<c:choose>
				  		<c:when test="${post == null}">
				  			<input type="checkbox" id="visibility" name="visibility" value="1" checked><br>
				  		</c:when>
				  		<c:when test="${post.getPostVisibility() != 1 }">
				  			<input type="checkbox" id="visibility" name="visibility" value="1" ><br>
				  		</c:when>
				  		<c:otherwise>
				  			<input type="checkbox" id="visibility" name="visibility" value="1" checked ><br>
				  		</c:otherwise>
				  	</c:choose>
			  	<button class="button" type="submit">Submit</button> 
			 </div>
	  	</div>
  	</main> 		
</body>
</html>