package Storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import Task.Task;

public class Storage {
	
	private static ArrayList<Task> taskList;
	private static ArrayList<String> categoryList;
	
	private StorageFile storageFile;
	private StorageJSON storageJSON;

	public Storage (String fileName) throws FileNotFoundException, IOException {
		taskList = new ArrayList<Task>();
		categoryList = new ArrayList<String>();
		storageFile = new StorageFile(fileName);
		storageJSON = new StorageJSON();
	}

	public void addFloatingTask(String taskName, String taskDescription, int priority, long reminder,
			String category, boolean done) throws IOException, JSONException {
		Task newFloatingTask = new Task(taskName, taskDescription, priority, reminder, category, done);
		taskList.add(newFloatingTask);
		storageFile.addTaskToFile(storageJSON.setTaskToJSON(newFloatingTask));
	}
	
	public void addTask(String taskName, String taskDescription, String deadline, long endTime, int priority, 
			int reminder, String category, boolean done) {
		Task newTask = new Task(taskName, taskDescription, deadline, endTime, priority, reminder, category, done);
		taskList.add(newTask);
	}

	public void addEvent(String eventName, String eventDescription, String startDate, String endDate, long startDateMilliseconds,
			long endDateMilliseconds, int priority, long reminder, String category) {
		Task newEvent = new Task(eventName, eventDescription, startDate, endDate, startDateMilliseconds,
				endDateMilliseconds, priority, reminder, category);
		taskList.add(newEvent);
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

	public ArrayList<Task> getTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Task> getFloatingTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Task> getEvents() {
		// TODO Auto-generated method stub
		return null;
	}

}