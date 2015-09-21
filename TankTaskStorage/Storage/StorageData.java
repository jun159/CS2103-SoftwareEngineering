package Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import Tasks.Category;
import Tasks.CategoryWrapper;
import Tasks.Task;

public class StorageData {

	private static final String TYPE_TASK = "task";
	private static final String TYPE_FLOAT = "floatTask";
	private static final String TYPE_EVENT = "event";

	private HashMap<String, CategoryWrapper> allCategories;
	
	private StorageFile storageFile;

	public StorageData() throws FileNotFoundException, IOException  { 
		storageFile = new StorageFile();
		allCategories = storageFile.getAllCategoriesFromFile();
	}
	
	// Create new category if category does not exist
	public void addNewCategory(String categoryName) 
			throws JsonParseException, JsonMappingException, IOException, JSONException {

		// TODO: Check if category exist
		Category category = new Category();
		CategoryWrapper categoryWrapper = new CategoryWrapper(category, categoryName);
		allCategories.put(categoryName, categoryWrapper);

		storageFile.setAllCategoriesToFile(allCategories);
	}
	
	// Create new category if category does not exist
	public void addNewTask(String categoryName, String taskType, Task newTask) 
			throws JsonParseException, JsonMappingException, IOException, JSONException {

		CategoryWrapper categoryWrapper = isCategoryWrapperExist(allCategories.get(categoryName));
		Category category = isCategoryExist(categoryWrapper.getCategory());
		HashMap<String, Task> allTasks = getTargetTaskList(category, taskType);
		
		allTasks.put(newTask.getName(), newTask);
		category.setFloatTasks(allTasks);
		categoryWrapper.setCategory(category);
		allCategories.put(categoryName, categoryWrapper);

		storageFile.setAllCategoriesToFile(allCategories);
	}
	
	public void setCategoryColour(String categoryName, String colourId) 
			throws JsonParseException, JsonMappingException, IOException {

		Category category = allCategories.get(categoryName).getCategory();
		category.setCategoryColour(colourId);
		storageFile.setAllCategoriesToFile(allCategories);
	}

	public void setDone(String categoryName, String taskName, boolean isDone) 
			throws JsonParseException, JsonMappingException, IOException {
		
		HashMap<String, Task> targetTaskList = getTargetTaskList(categoryName, taskName);
		targetTaskList.get(taskName).setDone(isDone);
		storageFile.setAllCategoriesToFile(allCategories);
	}
	
	public void setReminder(String categoryName, String taskName, long reminder) 
			throws JsonParseException, JsonMappingException, IOException {
		
		HashMap<String, Task> targetTaskList = getTargetTaskList(categoryName, taskName);
		targetTaskList.get(taskName).setReminder(reminder);
		storageFile.setAllCategoriesToFile(allCategories);
	}
	
	public void setDescription(String categoryName, String taskName, String description) 
			throws JsonParseException, JsonMappingException, IOException {
		
		HashMap<String, Task> targetTaskList = getTargetTaskList(categoryName, taskName);
		targetTaskList.get(taskName).setDescription(description);
		storageFile.setAllCategoriesToFile(allCategories);
	}
	
	public void setDeadline(String categoryName, String taskName, long deadline) 
			throws JsonParseException, JsonMappingException, IOException {
		
		HashMap<String, Task> targetTaskList = getTargetTaskList(categoryName, taskName);
		// TODO: When updating enddate, update endtime as well
		targetTaskList.get(taskName).setEndTime(deadline);
		storageFile.setAllCategoriesToFile(allCategories);
	}
	
	public ArrayList<CategoryWrapper> getAllCategories(HashMap<String, CategoryWrapper> categories) {
		
		ArrayList<CategoryWrapper> allTasks = new ArrayList<CategoryWrapper> ();
		
		for(String taskName : categories.keySet()) {
			allTasks.add(categories.get(taskName));
		}
		
		return allTasks;
	}
	
	public ArrayList<Task> getAllTargetCategoryTasks(String categoryName, String taskType) 
			throws ParseException, IOException, JSONException {
		
		ArrayList<Task> allCategoryTasks = new ArrayList<Task> ();
		Category category = allCategories.get(categoryName).getCategory();
		
		allCategoryTasks.addAll(getTasks(category.getTasks()));
		allCategoryTasks.addAll(getTasks(category.getFloatTasks()));
		allCategoryTasks.addAll(getTasks(category.getEvents()));

		return allCategoryTasks;
	}
	
	public ArrayList<Task> getAllTargetTypeTasks(String taskType) 
			throws ParseException, IOException, JSONException {
		
		ArrayList<Task> allTypeTasks = new ArrayList<Task> ();
		int size = allCategories.size();
		
		for(int i = 0; i < size; i++) {
			allTypeTasks.addAll(getTypeTaskArray(allCategories.get(i).getCategory(), taskType));	
		}

		return allTypeTasks;
	}

	private ArrayList<Task> getTasks(HashMap<String, Task> tasks) {
		
		ArrayList<Task> allTasks = new ArrayList<Task> ();
		
		for(String taskName : tasks.keySet()) {
			allTasks.add(tasks.get(taskName));
		}
		
		return allTasks;
	}
	
	private HashMap<String, Task> getTargetTaskList(Category category, String taskType) {
		switch(taskType) {
			case TYPE_TASK:
				return category.getTasks();
			case TYPE_FLOAT:
				return category.getFloatTasks();
			case TYPE_EVENT:
				return category.getEvents();
		}
		return new HashMap<String, Task> ();
	}
	
	private HashMap<String, Task> getTargetTaskList(String categoryName, String taskName) {
		Category category = allCategories.get(categoryName).getCategory();
		return getTargetTaskList(category, getTargetTaskType(category, taskName));
	}
	
	private ArrayList<Task> getTypeTaskArray(Category category, String taskType) {
		
		switch(taskType) {
			case TYPE_TASK:
				return getTasks(category.getTasks());
			case TYPE_FLOAT:
				return getTasks(category.getFloatTasks());
			case TYPE_EVENT:
				return getTasks(category.getEvents());
		}
		
		return new ArrayList<Task> ();
	}
	
	private String getTargetTaskType(Category category, String taskName) {
		HashMap<String, Task> tasks = category.getTasks();
		HashMap<String, Task> floatTasks = category.getFloatTasks();
		HashMap<String, Task> events = category.getEvents();
		
		if(tasks.containsKey(taskName)) {
			return TYPE_TASK;
		} else if(floatTasks.containsKey(taskName)) {
			return TYPE_FLOAT;
		} else if(events.containsKey(taskName)) {
			return TYPE_EVENT;
		} else {
			return null;
		}
	}
	
	private CategoryWrapper isCategoryWrapperExist(CategoryWrapper categoryWrapper) {
		if(categoryWrapper == null) {
			return new CategoryWrapper();
		} else {
			return categoryWrapper;
		}
	}
	
	private Category isCategoryExist(Category category) {
		if(category == null) {
			return new Category();
		} else {
			return category;
		}
	}
}