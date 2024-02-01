package blogapp1493150package.model.bean;

import java.util.Date;

public class Post {
	protected int postID;
	protected String title;
	protected String postContent;
	protected String postCreationDate;
	protected int postVisibility;
	protected String postAuthor;
	protected String postCategory;
	
	public Post(){}
	
	public Post(int postID, String title, String postContent, String postCreationDate, int postVisibility) {
		this.postID = postID;
		this.title = title;
		this.postContent = postContent;
		this.postCreationDate = postCreationDate;
		this.postVisibility = postVisibility;
	}
	
//	single post constructor
	public Post (String title, String postContent, int postVisibility, String postAuthor, String postCategory) {
		
		this.title = title;
		this.postContent = postContent;
		this.postVisibility = postVisibility;
		this.postAuthor = postAuthor;
		this.postCategory = postCategory;
	}
	
	// new Post(id, title, postContent, postCreationDate, postCategory);
	
	public Post(int postid, String title, String postContent) {
		// TODO Auto-generated constructor stub
		this.postID = postid;
		this.title = title;
		this.postContent = postContent;
	}
	// post constructor for posts page 
	public Post(int postid, String title, String postContent, int postVisi, String postAuthor, String postCategory) {
		
		// TODO Auto-generated constructor stub
		this.postID = postid;
		this.title = title;
		this.postContent = postContent;
		this.postVisibility = postVisi;
		this.postAuthor = postAuthor;
		this.postCategory = postCategory;
	}

	//single post
	public Post(int id, String title, String postCreationDate, String postCategory, String postAuthor, String postContent, int postVisi) {
		// TODO Auto-generated constructor stub
		this.postID = id;
		this.title = title;
		this.postContent = postContent;
		this.postCreationDate = postCreationDate;
		this.postCategory = postCategory;
		this.postVisibility = postVisi;
		this.postAuthor = postAuthor;
	}

	public Post(int postid, String title, String postContent, String postCreationDate, String postAuthor, String postCategory) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.postContent = postContent;
		this.postCreationDate = postCreationDate;
		this.postCategory = postCategory;
		this.postID = postid;
		this.postAuthor = postAuthor;
	}

	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getPostCreationDate() {
		return postCreationDate;
	}
	public void setPostCreationDate(String postCreationDate) {
		this.postCreationDate = postCreationDate;
	}
	public int getPostVisibility() {
		return postVisibility;
	}
	public void setPostVisibility(int postVisibility) {
		this.postVisibility = postVisibility;
	}
	public String getPostAuthor() {
		return postAuthor;
	}
	public void setPostAuthor(String postAuthor) {
		this.postAuthor = postAuthor;
	}
	public String getPostCategory() {
		return postCategory;
	}
	public void setPostCategory(String postCategory) {
		this.postCategory = postCategory;
	}
}
