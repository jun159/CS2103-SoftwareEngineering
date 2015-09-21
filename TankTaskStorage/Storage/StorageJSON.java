package Storage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import Tasks.Category;
import Tasks.CategoryWrapper;
import Tasks.Task;

public class StorageJSON {
	
	/* The following represents the key of each category attributes */
	private static final String CATEGORY_KEY_NAME = "categoryName";
	
	/* The following represents the key of each task attributes */
	private static final String TASK_KEY_NAME = "name";
	private static final String TASK_KEY_DESCRIPTION = "description";
	private static final String TASK_KEY_STARTDATE = "startDate";
	private static final String TASK_KEY_ENDDATE = "endDate";
	private static final String TASK_KEY_STARTTIME = "startTime";
	private static final String TASK_KEY_ENDTIME = "endTime";
	private static final String TASK_KEY_REMINDER = "reminder";
	private static final String TASK_KEY_PRIORITY = "priority";
	private static final String TASK_KEY_ISDONE = "isDone";
	
	private static final String UNCHECKED = "unchecked";
	
	private StorageFile storageFile;

	public StorageJSON()  { 
		storageFile = new StorageFile();
	}

    // Create new category if category does not exist
    public void addNewCategory(String categoryName, String taskType, Task newTask) 
    		throws JsonParseException, JsonMappingException, IOException {
    	
    	ArrayList<CategoryWrapper> allCategories = storageFile.getAllCategoriesFromFile();
    	Category category = new Category(taskType, newTask);
    	CategoryWrapper categoryWrapper = new CategoryWrapper(category, categoryName);
    	allCategories.add(categoryWrapper);
    	storageFile.setAllCategoriesToFile(allCategories);
    }
    
    // Add new task into existing category
    @SuppressWarnings(UNCHECKED)
    public JSONArray addNewTask(String categoryName, String taskType, Task newTask) {

    	JSONArray allCategories = new JSONArray();
    	JSONObject categoryObject = new JSONObject();
    	JSONObject taskTypeObject = new JSONObject();
    	JSONArray allTaskTypes = new JSONArray();
    	JSONObject taskObject = convertTaskToJSON(newTask);

    	// Each type task array - task, floatTask or event contains taskObject
    	allTaskTypes.add(taskObject);
    	
    	// Check type of task to add under
    	taskTypeObject.put(taskType, allTaskTypes);
    	
    	categoryObject.put(categoryName, taskType);
    	categoryObject.put(CATEGORY_KEY_NAME, categoryName);
    	
    	allCategories.add(categoryObject);
    	//allCategories.add(categoryObject);
    	
    	return allCategories;
    }
    
    // Convert Task to JSON
    @SuppressWarnings(UNCHECKED)
	public JSONObject convertTaskToJSON(Task targetTask) {
    	JSONObject task = new JSONObject();
    
    	task.put(TASK_KEY_NAME, targetTask.getName());
    	task.put(TASK_KEY_DESCRIPTION, targetTask.getDescription());
    	task.put(TASK_KEY_STARTDATE, targetTask.getStartDate());
    	task.put(TASK_KEY_ENDDATE, targetTask.getEndDate());
    	task.put(TASK_KEY_STARTTIME, targetTask.getStartTime());
    	task.put(TASK_KEY_ENDTIME, targetTask.getEndTime());
    	task.put(TASK_KEY_REMINDER, targetTask.getReminder());
    	task.put(TASK_KEY_PRIORITY, targetTask.getPriority());
    	task.put(TASK_KEY_ISDONE, targetTask.getDone());
    	
    	return task;
    }
}