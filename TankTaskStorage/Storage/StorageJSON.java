package Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import Task.Category;
import Task.CategoryWrapper;
import Task.Subtask;
import TaskTask.Task;

public class StorageJSON {
	
	private JSONObject category;
	private JSONObject task;
	
	private JSONArray categoryList;
	private JSONArray taskList;
	
	private JSONParser parser;

	public StorageJSON() throws FileNotFoundException, IOException { 
		category = new JSONObject();
		task = new JSONObject();
		taskList = new JSONArray();
		parser = new JSONParser();
	}
	
	// Retrieves all tasks from file
    public ArrayList<CategoryWrapper> getAllTasksFromFile(String jsonString) throws JsonParseException, JsonMappingException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ArrayList<CategoryWrapper> allTasks = mapper.readValue(jsonString, new TypeReference<ArrayList<CategoryWrapper>>() {});
    	return allTasks;
    }
    
    @SuppressWarnings("unchecked")
	public JSONArray addNewCategory(String categoryName) {
    	Task newTask = new Task("Do homework", "Do it now", 5, 123, "School", false);
    	
    	JSONArray allCategories = new JSONArray();
    	JSONObject categoryObject = new JSONObject();

    	JSONObject typesOfTasks = new JSONObject();
    	JSONArray taskArray = new JSONArray();

    	JSONObject taskObject = convertTaskToJSON(newTask);

    	// Each type task array - task, floatTask or event contains 2 taskObject
    	taskArray.add(taskObject);
    	taskArray.add(taskObject);
    	
    	// All type tasks
    	typesOfTasks.put("floatTask", taskArray);
    	typesOfTasks.put("task", taskArray);
    	typesOfTasks.put("event", taskArray);
    	
    	categoryObject.put(categoryName, typesOfTasks);
    	categoryObject.put("categoryName", categoryName);
    	
    	allCategories.add(categoryObject);
    	//allCategories.add(categoryObject);
    	
    	return allCategories;
    }
    
    // Convert Task to JSON
    @SuppressWarnings("unchecked")
	public JSONObject convertTaskToJSON(Task targetTask) {
    	task.put("name", targetTask.getName());
    	task.put("description", targetTask.getDescription());
    	task.put("startDate", targetTask.getStartDate());
    	task.put("endDate", targetTask.getEndDate());
    	task.put("startTime", targetTask.getStartTime());
    	task.put("endTime", targetTask.getEndTime());
    	task.put("reminder", targetTask.getReminder());
    	task.put("priority", targetTask.getPriority());
    	task.put("isDone", targetTask.getDone());
    	
    	return task;
    }
}