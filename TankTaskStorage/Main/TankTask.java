package Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import Storage.Storage;
import Storage.StorageJSON;
import Task.Category;
import Task.CategoryWrapper;
import Task.Task;

public class TankTask {
	public static void main(String[] args) throws FileNotFoundException, IOException, JSONException, ParseException {
		Storage storage = new Storage("hey.txt");
		//storage.addFloatingTask("Do homework", "Do it now", 5, 123, "School", false);
		//storage.addFloatingTask("Do homeworky", "Do it now", 5, 123, "School", false);
			
		storage.addCat();
		List<CategoryWrapper> list = storage.getCategories(new String(Files.readAllBytes(Paths.get("hey.txt")), StandardCharsets.UTF_8));
		
		System.out.println(list.size());
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(0).getCategoryName());
			
			// Retrieve first category
			list.get(0).getCategory();
			
			// Retrieve first task under TASK ARRAY
			System.out.println(list.get(0).getCategory().getTask().get(0).getDescription());
		}
		
		//storage.getFloatingTasks();
	}
}
