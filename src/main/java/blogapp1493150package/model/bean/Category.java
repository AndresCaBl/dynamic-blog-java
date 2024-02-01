package blogapp1493150package.model.bean;

public class Category {
	protected int categoryID;
	protected String categoryName;
	
	public Category(){
		
	}
	
	public Category(String cname) {
		// TODO Auto-generated constructor stub
		this.categoryName = cname;
	}

	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
