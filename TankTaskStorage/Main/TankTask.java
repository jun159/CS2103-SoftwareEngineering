package Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import Storage.Storage;
import Storage.StorageJSON;
import Task.Task;

public class TankTask {
	public static void main(String[] args) throws FileNotFoundException, IOException, JSONException {
		Storage storage = new Storage("hey.txt");
		storage.addFloatingTask("Do homework", "Do it now", 5, 123, "School", false);
		//storage.addFloatingTask("Do homeworky", "Do it now", 5, 123, "School", false);
		ArrayList<Task> allTasks = storage.getTasks();
		
		for(int i = 0; i < allTasks.size(); i++) 
			System.out.println(allTasks.get(i).getName());
	}
}
