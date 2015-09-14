package src;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TextBuddyTester {

	private static final String INPUT_FILE_NAME = "mytestFile.txt";
	
	private static final String COMMAND_ADD = "add";
	private static final String COMMAND_DISPLAY = "display";
	private static final String COMMAND_SORT = "sort";
	private static final String COMMAND_SEARCH = "search";
	
	private static final String TEXT_1 = "Potong Pasir";
	private static final String TEXT_2 = "East Coast";
	private static final String TEXT_3 = "Ang Mo Kio";
	private static final String TEXT_4 = "Jurong East";
	private static final String TEXT_5 = "Bishan";
	private static final String TEXT_6 = "Chua Chu Kang";
	
	@Before
	public void createFile() throws FileNotFoundException, IOException {
		TextBuddy.setFileName(INPUT_FILE_NAME);
		TextBuddy.initializeFile();
		
		TextBuddy.executeCommand(COMMAND_ADD, TEXT_1);
		TextBuddy.executeCommand(COMMAND_ADD, TEXT_2);
		TextBuddy.executeCommand(COMMAND_ADD, TEXT_3);
		TextBuddy.executeCommand(COMMAND_ADD, TEXT_4);
		TextBuddy.executeCommand(COMMAND_ADD, TEXT_5);
	}
}
