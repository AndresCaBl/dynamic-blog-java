package blogapp1493150package.model.bean;

public class Comment {
	private int commentID;
	private String commentUser;
	private String commentText;
	private String CommentDate;
	private int postID;
	
	public Comment() {}
	
	public Comment(String comUser, String comText) {
		// TODO Auto-generated constructor stub
		this.commentUser = comUser;
		this.commentText = comText;
	}
	// new comment constructor
	

	public Comment(int postid, String comName, String comText) {
		// TODO Auto-generated constructor stub
		this.commentUser =  comName;
		this.commentText = comText;
		this.postID = postid;
	}

	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getCommentDate() {
		return CommentDate;
	}
	public void setCommentDate(String commentDate) {
		CommentDate = commentDate;
	}
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
}
