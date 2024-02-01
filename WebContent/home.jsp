<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Andres BLOG | Home</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/Styles.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script>
			$(document).ready(function(){
				var maxLength = 300;
				$(".show-read-more").each(function(){
					var myStr = $(this).text();
					if($.trim(myStr).length > maxLength){
						var newStr = myStr.substring(0, maxLength);
						var removedStr = myStr.substring(maxLength, $.trim(myStr).length);
						$(this).empty().html(newStr);
						$(this).append(' <a href="javascript:void(0);" class="read-more">Read More...</a>');
						$(this).append('<span class="more-text">' + removedStr + '</span>');
					}
				});
				$(".read-more").click(function(){
					$(this).siblings(".more-text").contents().unwrap();
					$(this).remove();
				});
			});
		</script>
		<style>
		    .show-read-more .more-text{
		        display: none;
		    }
		</style>
    </head>
    <body>
        <header>
            <nav class="navbar">
                <div class="header">
                    <h1>ANDRES Blog</h1>
                </div>
                <ul class="nav-menu">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/PostServlet?action=home" class="nav-link active-tab">Home</a>
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
        	<div class="page-title">
            	<h2>Recent Posts</h2>
             </div>
            <c:forEach var="post" items="${homePosts}">
            <div class="single-post">
            	<h3 class="post-title uppercase">
            		<a href= "${pageContext.request.contextPath}/PostServlet?action=singlePost&postid=<c:out value='${post.getPostID()}'/>"><c:out value="${post.getTitle()}"/></a>
            	</h3>
	           	<p class="show-read-more post-text"><c:out value="${post.getPostContent()}" /></p>
            </div>
	        </c:forEach>
         </main>

        <script src="" async defer></script>
    </body>
</html>