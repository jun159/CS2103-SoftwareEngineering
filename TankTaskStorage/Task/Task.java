package Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Task {
	
	private String name;
	private String categoryName;
	private String description;
    private String startDate;
    private String endDate;
    
    private long startTime;
    private long endTime;
    private long reminder;
    
	private int priority;
    
    private boolean isDone;
    
    public Task() {
    	
    }
    
    // Floating task
    public Task(String taskName, String taskDescription, int priority, long reminder, 
    		String categoryName, boolean isDone) {
    	setName(taskName);
		setDescription(taskDescription);
		setStartDate("-1");
		setEndDate("-1");
		setStartTime(-1);
		setEndTime(-1);
		setPriority(priority);
		setReminder(reminder);
		setCategoryName(categoryName);
		setDone(isDone);
	}
    
    // Task
	public Task(String taskName, String taskDescription, String deadline, long endTime, int priority, 
			int reminder, String categoryName, boolean isDone) {
		setName(taskName);
		setDescription(taskDescription);
		setStartDate("-1");
		setEndDate(deadline);
		setStartTime(-1);
		setEndTime(-1);
		setPriority(priority);
		setReminder(reminder);
		setCategoryName(categoryName);
		setDone(isDone);
	}

	// Event
	public Task(String eventName, String eventDescription, String startDate, String endDate, long startTime,
			long endTime, int priority, long reminder, String categoryName) {
		setName(eventName);
		setDescription(eventDescription);
		setStartDate(startDate);
		setEndDate(endDate);
		setStartTime(startTime);
		setEndTime(endTime);
		setPriority(priority);
		setReminder(reminder);
		setCategoryName(categoryName);
	}

	public int getPriority() {
    	return priority;
    }
    
    public void setPriority(int priority) {
    	this.priority = priority;
    }
    
    public boolean getDone() {
    	return isDone;
    }
    
    public void setDone(boolean isDone) {
    	this.isDone = isDone;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public String getStartDate() {
    	return startDate;
    }
    
    public void setStartDate(String startDate) {
    	this.startDate = startDate;
    }
    
    public String getEndDate() {
    	return endDate;
    }
    
    public void setEndDate(String endDate) {
    	this.endDate = endDate;
    }
    
    public long getStartTime() {
    	return startTime;
    }
    
    public void setStartTime(long startTime) {
    	this.startTime = startTime;
    }
    
    public long getEndTime() {
    	return endTime;
    }
    
    public void setEndTime(long endTime) {
    	this.endTime = endTime;
    }
    
    public long getReminder() {
    	return reminder;
    }
    
    public void setReminder(long reminder) {
    	this.reminder = reminder;
    }
    
    public String getCategoryName() {
    	return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
    	this.categoryName = categoryName;
    }
    
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(name+System.getProperty("line.separator"));
    	sb.append(description+System.getProperty("line.separator"));
    	sb.append(startDate+System.getProperty("line.separator"));
    	sb.append(endDate+System.getProperty("line.separator"));
    	sb.append(startTime+System.getProperty("line.separator"));
    	sb.append(endTime+System.getProperty("line.separator"));
    	sb.append(reminder+System.getProperty("line.separator"));
    	sb.append(priority+System.getProperty("line.separator"));
    	sb.append(done+System.getProperty("line.separator"));
    	return sb.toString();
    }
}