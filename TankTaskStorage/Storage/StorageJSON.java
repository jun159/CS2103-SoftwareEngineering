package Storage;

/**
 * Converts Task object to JSON or vice versa.
 * 
 */

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Task.Subtask;
import Task.Task;

public class StorageJSON {
	
	private Task newTask;
	
   	private JSONObject task;
	private JSONArray taskList;

	public StorageJSON() { 
		task = new JSONObject();
		taskList = new JSONArray();
	}
	
    /*
	// Retrieves all tasks from file
    public void getAllTasksFromFile() throws JSONException {
    	int size = allTargetTasks.size();
    	
    	for(int i = 0; i < size; i++) {
    		taskList.put(setTask(allTargetTasks.get(i)));
    	}
    	
    	taskList.put(newTask);
    }
    */
    
    // Add new task into file
    public JSONObject setTaskToJSON(Task targetTask) throws JSONException {
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