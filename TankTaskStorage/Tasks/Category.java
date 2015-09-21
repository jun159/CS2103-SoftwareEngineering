package Tasks;

import java.util.TreeSet;

public class Category {
	
	/* The following represents the list of task types */
	private TreeSet<Task> task;
	private TreeSet<Task> floatTask;
	private TreeSet<Task> event;
	
	public Category() {
		setTask(new TreeSet<Task> ());
		setFloatTask(new TreeSet<Task> ());
		setEvent(new TreeSet<Task> ());
	}
	
	public Category(TreeSet<Task> task, TreeSet<Task> floatTask, TreeSet<Task> event) {
		setTask(task);
		setFloatTask(floatTask);
		setEvent(event);
	}
	
	public TreeSet<Task> getTask() {
		return task;
	}
	
	public void setTask(TreeSet<Task> TreeSet) {
		this.task = TreeSet;
	}
	
	public TreeSet<Task> getFloatTask() {
		return floatTask;
	}
	
	public void setFloatTask(TreeSet<Task> floatTask) {
		this.floatTask = floatTask;
	}
	
	public TreeSet<Task> getEvent() {
		return event;
	}
	
	public void setEvent(TreeSet<Task> event) {
		this.event = event;
	}
}