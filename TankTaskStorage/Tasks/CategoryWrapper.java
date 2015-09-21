package Tasks;

public class CategoryWrapper {
	
	private Category category;
	private String categoryName;
	private String categoryColour;
	
	public CategoryWrapper() {
		
	}
	
	public CategoryWrapper(Category category, String categoryName) {
		setCategory(category);
		setCategoryName(categoryName);
	}
	
	public CategoryWrapper(Category category, String categoryName, String categoryColour) {
		setCategory(category);
		setCategoryName(categoryName);
		setCategoryColour(categoryColour);
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryColour() {
		return categoryColour;
	}

	public void setCategoryColour(String categoryColour) {
		this.categoryColour = categoryColour;
	}
}