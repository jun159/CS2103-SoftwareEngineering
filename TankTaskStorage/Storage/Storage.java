package Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import Tasks.Task;
import Tasks.CategoryWrapper;

public class Storage {
	
	/* The following represents the types of tasks */
	private static final String TYPE_TASK = "task";
	private static final String TYPE_FLOAT = "floatTask";
	private static final String TYPE_EVENT = "event";
	
	private StorageJSON storageJSON;
	private StorageFile storageFile;

	public Storage () throws FileNotFoundException, IOException {
		storageJSON = new StorageJSON();
		storageFile = new StorageFile();
	}

	public void addFloatingTask(String taskName, String taskDescription, int priority, long reminder,
			String category, boolean done) throws IOException, JSONException {
		Task newFloatingTask = new Task(taskName, taskDescription, priority, reminder, done);
		storageJSON.addNewCategory(category, TYPE_FLOAT, newFloatingTask);
	}
	
	public void addTask(String taskName, String taskDescription, String deadline, long endTime, int priority, 
			int reminder, String category, boolean done) throws IOException, JSONException {	
		Task newTask = new Task(taskName, taskDescription, deadline, endTime, priority, reminder, done);
		storageJSON.addNewCategory(category, TYPE_TASK, newTask);
	}

	public void addEvent(String eventName, String eventDescription, String startDate, String endDate, long startDateMilliseconds,
			long endDateMilliseconds, int priority, long reminder, String category) throws IOException, JSONException {
		Task newEvent = new Task(eventName, eventDescription, startDate, endDate, startDateMilliseconds,
				endDateMilliseconds, priority, reminder, category);
		storageJSON.addNewCategory(category, TYPE_EVENT, newEvent);
	}
	
	public void setUndone(String taskID) {
		// TODO Auto-generated method stub
		
	}

	public void setDone(String taskID) {
		// TODO Auto-generated method stub
		
	}

	public void setCol(String categoryName, String colourId) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<String> getCategoryList() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCategory(String taskId, String categoryName) {
		// TODO Auto-generated method stub
		
	}

	public void addCategory(String categoryName) {
		// TODO Auto-generated method stub
		
	}

	public void addSubTask(String taskId, String subtaskDescription) {
		// TODO Auto-generated method stub
		
	}

	public void subTask(String taskId) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Task> getCurrentState() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateState(ArrayList<Task> currentState) {
		// TODO Auto-generated method stub
		
	}

	public void setReminder(String taskId, String reminder) {
		// TODO Auto-generated method stub
		
	}

	public void setDescription(String taskId, String description) {
		// TODO Auto-generated method stub
		
	}

	public void setDeadline(String taskId, String deadline) {
		// TODO Auto-generated method stub
		
	}

	public void delete(String taskId) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<CategoryWrapper> getAllCategories()
			throws JsonParseException, JsonMappingException, JSONException, IOException {
		ArrayList<CategoryWrapper> allCategories = storageFile.getAllCategoriesFromFile();
		
		if(allCategories == null) {
			return new ArrayList<CategoryWrapper> ();
		} else {
			return allCategories;
		}
	}

	public void getFloatingTasks() throws ParseException, IOException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Task> getEvents() {
		// TODO Auto-generated method stub
		return null;
	}

}
