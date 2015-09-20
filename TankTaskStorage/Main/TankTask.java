package Main;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;

import Storage.Storage;
import Task.Task;

public class TankTask {
	public static void main(String[] args) throws FileNotFoundException, IOException, JSONException {
		Storage storage = new Storage("hey.txt");
		storage.addFloatingTask("Do homework", "Do it now", 5, 123, "School", false);
	}
}
