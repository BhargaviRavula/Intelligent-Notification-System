package beans;

public class Category {

	Integer Category_ID;
	String Category_Name;
	
	public Category(Integer Category_ID, String Category_Name ){
		
		this.Category_ID = Category_ID;
		this.Category_Name = Category_Name;
	}
	public Category(){
		super();
	}
	public Integer getCategory_ID() {
		return Category_ID;
	}
	public void setCategory_ID(Integer category_ID) {
		Category_ID = category_ID;
	}
	public String getCategory_Name() {
		return Category_Name;
	}
	public void setCategory_Name(String category_Name) {
		Category_Name = category_Name;
	}
	
}
