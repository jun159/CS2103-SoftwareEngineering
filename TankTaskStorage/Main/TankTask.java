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
import Tasks.Category;
import Tasks.CategoryWrapper;
import Tasks.Task;

public class TankTask {
	public static void main(String[] args) throws FileNotFoundException, IOException, JSONException, ParseException {
		Storage storage = new Storage();
		storage.addFloatingTask("Do homework", "Do it now", 5, 123, "School", false);
		//storage.setDone("School", "floatTask", "Do homework");
		//storage.addFloatingTask("Do homework", "Do it now", 5, 123, "School", false);
		
		//storage.addEvent("Party time!", "Woohoo", "March", "July", 5, 10, 1, -1, "School");
	}
}