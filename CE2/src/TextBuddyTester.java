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
	
	private static final String SEARCH_WORD = "East";
	
	private static final String TEXT_1 = "Potong Pasir";
	private static final String TEXT_2 = "East Coast";
	private static final String TEXT_3 = "Ang Mo Kio";
	private static final String TEXT_4 = "Jurong East";
	private static final String TEXT_5 = "Bishan";
	private static final String TEXT_6 = "Chua Chu Kang";
	
	private static final String NUM_1 = "1. ";
	private static final String NUM_2 = "2. ";
	private static final String NUM_3 = "3. ";
	private static final String NUM_4 = "4. ";
	private static final String NUM_5 = "5. ";
	
	
	private static final int NUMBER_OF_TEXTS_CONTAINING_WORD_EAST = 2;
	
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
	
	/*
	 * ============================== SORTING ================================
	 * The methods below represents the testing operations for sort command.
	 * =======================================================================
	 */
	
	@Test
	public void testSortList() throws IOException {
		TextBuddy.executeCommand(COMMAND_SORT, null);
		assertEquals(NUM_1 + TEXT_3 + NUM_2 + TEXT_5 + NUM_3 + TEXT_2 + NUM_4 
				+ TEXT_4 + NUM_5 + TEXT_1, TextBuddy.executeCommand(COMMAND_DISPLAY, null));
	}
	
	/*
	 * ============================= SEARCHING ===============================
	 * The methods below represents the testing operations for search command.
	 * =======================================================================
	 */
	
	@Test
	public void testSearchNullInput() throws IOException {
		assertEquals(TextBuddy.ERROR_NULL_SEARCH_INPUT, 
				TextBuddy.executeCommand(COMMAND_SEARCH, null));
	}
	
	
	@Test
	public void testSearchEmptyResult() throws IOException {
		assertEquals(String.format(TextBuddy.MESSAGE_SEARCH_IS_EMPTY, TEXT_6, INPUT_FILE_NAME), 
				TextBuddy.executeCommand(COMMAND_SEARCH, TEXT_6));
	}
	
	@Test
	public void testSearchResult() throws IOException {
		assertEquals(String.format(TextBuddy.MESSAGE_SEARCH_TEXT, NUMBER_OF_TEXTS_CONTAINING_WORD_EAST , 
				SEARCH_WORD, INPUT_FILE_NAME) + NUM_1 + TEXT_2 + NUM_2 + TEXT_4,
				TextBuddy.executeCommand(COMMAND_SEARCH, SEARCH_WORD));
	}
}