package src;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TextBuddyTester {

	private static final String INPUT_FILE_NAME = "testFile.txt";
	
	private static final String TEXT_1 = "Potong Pasir";
	private static final String TEXT_2 = "East Coast";
	private static final String TEXT_3 = "Ang Mo Kio";
	private static final String TEXT_4 = "Jurong East";
	private static final String TEXT_5 = "Bishan";
	
	@Before
	public void createFile() throws FileNotFoundException, IOException {
		TextBuddy.setFileName(INPUT_FILE_NAME);
		TextBuddy.initializeFile();
		addItems();
	}
	
	@Before
	public void addItems() throws IOException {
		TextBuddy.executeCommand(TextBuddy.COMMAND_TYPE.COMMAND_ADD.name(), TEXT_1);
		TextBuddy.executeCommand(TextBuddy.COMMAND_TYPE.COMMAND_ADD.name(), TEXT_2);
		TextBuddy.executeCommand(TextBuddy.COMMAND_TYPE.COMMAND_ADD.name(), TEXT_3);
		TextBuddy.executeCommand(TextBuddy.COMMAND_TYPE.COMMAND_ADD.name(), TEXT_4);
		TextBuddy.executeCommand(TextBuddy.COMMAND_TYPE.COMMAND_ADD.name(), TEXT_5);
	}
	
	@Test
	public void testSortList() throws IOException {
		
	}
	
	@Test
	public void testSortEmptyList() {
		
	}
	
	@Test
	public void testSearchResult() {
		
	}
	
	@Test
	public void testSearchEmptyResult() {
		
	}
}
