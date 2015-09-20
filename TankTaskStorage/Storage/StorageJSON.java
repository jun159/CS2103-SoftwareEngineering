package Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Converts Task object to JSON or vice versa.
 * 
 */

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Task.Subtask;
import Task.Task;

public class StorageJSON {
	
	private StorageFile storage;
	private JSONObject task;
	
	private JSONArray categoryList;
	private JSONArray taskList;

	public StorageJSON() throws FileNotFoundException, IOException { 
		storage = new StorageFile();
		task = new JSONObject();
		taskList = new JSONArray();
	}
	
	// Retrieves all tasks from file
    public ArrayList<Task> getAllTasksFromFile() throws JSONException, JsonParseException, JsonMappingException, IOException {
    	ArrayList<Task> allTasks = new ArrayList<Task>();
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	Task task = null;
    	task = mapper.readValue(storage.getFile(), Task.class);
    	allTasks.add(task);

    	return allTasks;
    }
    
    // Add new task into file
    public JSONObject formatTaskToJSON(Task targetTask) throws JSONException {
    	task.put("name", targetTask.getName());
    	task.put("description", targetTask.getDescription());
    	task.put("startDate", targetTask.getStartDate());
    	task.put("endDate", targetTask.getEndDate());
    	task.put("startTime", targetTask.getStartTime());
    	task.put("endTime", targetTask.getEndTime());
    	task.put("reminder", targetTask.getReminder());
    	task.put("priority", targetTask.getPriority());
    	task.put("done", targetTask.getDone());
    	
    	return task;
    }
    
}