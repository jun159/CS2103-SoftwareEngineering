package Tasks;

import java.util.ArrayList;

public class Category {
	
	/* The following represents the list of task types */
	private ArrayList<Task> task;
	private ArrayList<Task> floatTask;
	private ArrayList<Task> event;
	
	public Category() {
		setTask(new ArrayList<Task> ());
		setFloatTask(new ArrayList<Task> ());
		setEvent(new ArrayList<Task> ());
	}
	
	public Category(ArrayList<Task> task, ArrayList<Task> floatTask, ArrayList<Task> event) {
		setTask(task);
		setFloatTask(floatTask);
		setEvent(event);
	}
	
	public ArrayList<Task> getTask() {
		return task;
	}
	
	public void setTask(ArrayList<Task> arrayList) {
		this.task = arrayList;
	}
	
	public ArrayList<Task> getFloatTask() {
		return floatTask;
	}
	
	public void setFloatTask(ArrayList<Task> floatTask) {
		this.floatTask = floatTask;
	}
	
	public ArrayList<Task> getEvent() {
		return event;
	}
	
	public void setEvent(ArrayList<Task> event) {
		this.event = event;
	}
}