package Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import Tasks.Category;
import Tasks.CategoryWrapper;
import Tasks.Task;

public class StorageJSON {
	
	/* The following represents the types of tasks */
	private static final String TYPE_TASK = "task";
	private static final String TYPE_FLOAT = "floatTask";
	private static final String TYPE_EVENT = "event";
	
	private StorageFile storageFile;

	public StorageJSON() throws FileNotFoundException, IOException  { 
		storageFile = new StorageFile();
	}
	
	public Category updateCategory(Category category, String taskType, Task newTask) {
		
		switch(taskType) {
			case TYPE_TASK:
				ArrayList<Task> task = category.getTask();
				task.add(newTask);
				category.setTask(task);
				return category;
			case TYPE_FLOAT:
				ArrayList<Task> floatTask = category.getFloatTask();
				floatTask.add(newTask);
				category.setFloatTask(floatTask);
				return category;
			case TYPE_EVENT:
				ArrayList<Task> event = category.getEvent();
				event.add(newTask);
				category.setEvent(event);
				return category;
		}
		
		return null;
	}

    // Create new category if category does not exist
    public void addNewCategory(String categoryName, String taskType, Task newTask) 
    		throws JsonParseException, JsonMappingException, IOException, JSONException {
    	
    	ArrayList<CategoryWrapper> allCategories = storageFile.getAllCategoriesFromFile();
    	boolean isCategoryExist = false;
    	int size = allCategories.size();
    	
    	for(int i = 0; i < size; i++) {
    		if(allCategories.get(i).getCategoryName().equals(categoryName)) {
    			Category category = updateCategory(allCategories.get(i).getCategory(), taskType, newTask);
    	    	CategoryWrapper categoryWrapper = allCategories.get(i);
    	    	categoryWrapper.setCategory(category);
    	    	isCategoryExist = true;
    	    	break;
    		}
    	}
    	
    	if(!isCategoryExist) {
    		Category category = updateCategory(new Category(), taskType, newTask);
	    	CategoryWrapper categoryWrapper = new CategoryWrapper(category, categoryName);
	    	allCategories.add(categoryWrapper);
    	}
    	
    	storageFile.setAllCategoriesToFile(allCategories);
    }
}