package Storage;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Task.CategoryWrapper;
import TaskTask.Task;

public class StorageJSON {
	
	/* The following represents the types of tasks */
	private static final String TYPE_TASK = "task";
	private static final String TYPE_FLOAT = "floatTask";
	private static final String TYPE_EVENT = "event";
	
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
	private static final int EMPTY = -1;

	public StorageJSON()  { 
		
	}
	
	private String checkTaskType(Task task) {
		if (task.getStartTime() == EMPTY && task.getEndTime() != EMPTY) {
			return TYPE_TASK;
		} else if (task.getStartTime() == EMPTY && task.getEndTime() == EMPTY) {
			return TYPE_FLOAT;
		} else {
			return TYPE_EVENT;
		}
	}
	
	// Retrieve all tasks from file
    public ArrayList<CategoryWrapper> getAllTasksFromFile(String jsonString) 
    		throws JsonParseException, JsonMappingException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ArrayList<CategoryWrapper> allTasks = 
        		mapper.readValue(jsonString, new TypeReference<ArrayList<CategoryWrapper>>() {});
    	return allTasks;
    }
    
    // Add new category if category does not exist
    @SuppressWarnings(UNCHECKED)
	public JSONArray addNewCategory(String categoryName, Task newTask) {

    	JSONArray allCategories = new JSONArray();
    	JSONObject categoryObject = new JSONObject();
    	JSONObject taskType = new JSONObject();
    	JSONArray allTaskTypes = new JSONArray();
    	JSONObject taskObject = convertTaskToJSON(newTask);

    	// Each type task array - task, floatTask or event contains taskObject
    	allTaskTypes.add(taskObject);
    	
    	// Check type of task to add under
    	taskType.put(checkTaskType(newTask), allTaskTypes);
    	
    	categoryObject.put(categoryName, taskType);
    	categoryObject.put(CATEGORY_KEY_NAME, categoryName);
    	
    	allCategories.add(categoryObject);
    	//allCategories.add(categoryObject);
    	
    	return allCategories;
    }
    
    // Add new task into existing category
    @SuppressWarnings(UNCHECKED)
	public JSONArray addNewTask(String categoryName, Task newTask) {

    	JSONArray allCategories = new JSONArray();
    	JSONObject categoryObject = new JSONObject();
    	JSONObject taskType = new JSONObject();
    	JSONArray allTaskTypes = new JSONArray();
    	JSONObject taskObject = convertTaskToJSON(newTask);

    	// Each type task array - task, floatTask or event contains taskObject
    	allTaskTypes.add(taskObject);
    	
    	// Check type of task to add under
    	taskType.put(checkTaskType(newTask), allTaskTypes);
    	
    	categoryObject.put(categoryName, taskType);
    	categoryObject.put("categoryName", categoryName);
    	
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