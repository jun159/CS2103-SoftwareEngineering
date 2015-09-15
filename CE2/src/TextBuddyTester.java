package src;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TextBuddyTester {
	
	Parser parser;
	Storage storage;
	
	private void initialize() throws IOException {
		parser = new Parser();
		storage = new Storage();
	}
	
	@Before
	public void createFile() throws FileNotFoundException, IOException {
		initialize();
		storage.createNewFile(Constant.INPUT_FILE_NAME);
	}
	
	/*
	 * ============================== ADDITION ===============================
	 * The methods below represents the testing operations for add command.
	 * =======================================================================
	 */
	
	@Before
	public void testAdd() throws IOException {
		assertEquals(String.format(Constant.MESSAGE_ADD_TEXT, Constant.INPUT_FILE_NAME, 
				Constant.TEXT_1), parser.executeCommand(Constant.COMMAND_ADD, Constant.TEXT_1));
		assertEquals(String.format(Constant.MESSAGE_ADD_TEXT, Constant.INPUT_FILE_NAME, 
				Constant.TEXT_2), parser.executeCommand(Constant.COMMAND_ADD, Constant.TEXT_2));
		assertEquals(String.format(Constant.MESSAGE_ADD_TEXT, Constant.INPUT_FILE_NAME, 
				Constant.TEXT_3), parser.executeCommand(Constant.COMMAND_ADD, Constant.TEXT_3));
		assertEquals(String.format(Constant.MESSAGE_ADD_TEXT, Constant.INPUT_FILE_NAME, 
				Constant.TEXT_4), parser.executeCommand(Constant.COMMAND_ADD, Constant.TEXT_4));
		assertEquals(String.format(Constant.MESSAGE_ADD_TEXT, Constant.INPUT_FILE_NAME, 
				Constant.TEXT_5), parser.executeCommand(Constant.COMMAND_ADD, Constant.TEXT_5));
	}
	
	/*
	 * ============================== DELETION ===============================
	 * The methods below represents the testing operations for add command.
	 * =======================================================================
	 */
	
	@Test
	public void testDelete() throws IOException {
		assertEquals(String.format(Constant.MESSAGE_DELETE_TEXT, Constant.INPUT_FILE_NAME, 
				Constant.TEXT_2), parser.executeCommand(Constant.COMMAND_DELETE, Constant.INDEX_TO_DELETE));
	}
	
	/*
	 * ================================= CLEAR ===============================
	 * The methods below represents the testing operations for add command.
	 * =======================================================================
	 */
	
	@Test
	public void testClear() throws IOException {
		assertEquals(String.format(Constant.MESSAGE_CLEAR_TEXT, Constant.INPUT_FILE_NAME), 
				parser.executeCommand(Constant.COMMAND_CLEAR, null));
	}
	
	/*
	 * ============================== SORTING ================================
	 * The methods below represents the testing operations for sort command.
	 * =======================================================================
	 */
	
	@Test
	public void testSortList() throws IOException {
		parser.executeCommand(Constant.COMMAND_SORT, null);
		assertEquals(Constant.NUM_1 + Constant.TEXT_3 + Constant.MESSAGE_NEW_LINE 
				+ Constant.NUM_2 + Constant.TEXT_5 + Constant.MESSAGE_NEW_LINE 
				+ Constant.NUM_3 + Constant.TEXT_2 + Constant.MESSAGE_NEW_LINE 
				+ Constant.NUM_4 + Constant.TEXT_4 + Constant.MESSAGE_NEW_LINE 
				+ Constant.NUM_5 + Constant.TEXT_1, 
				parser.executeCommand(Constant.COMMAND_DISPLAY, null));
	}
	
	/*
	 * ============================= SEARCHING ===============================
	 * The methods below represents the testing operations for search command.
	 * =======================================================================
	 */
	
	@Test
	public void testSearchNullInput() throws IOException {
		assertEquals(Constant.ERROR_NULL_SEARCH_INPUT, 
				parser.executeCommand(Constant.COMMAND_SEARCH, null));
	}
	
	@Test
	public void testSearchEmptyResult() throws IOException {
		assertEquals(String.format(Constant.MESSAGE_SEARCH_IS_EMPTY, 
				Constant.TEXT_6, Constant.INPUT_FILE_NAME), 
				parser.executeCommand(Constant.COMMAND_SEARCH, Constant.TEXT_6));
	}
	
	@Test
	public void testSearchResult() throws IOException {
		assertEquals(String.format(Constant.MESSAGE_SEARCH_TEXT, Constant.NUMBER_OF_TEXTS_CONTAINING_WORD_EAST, 
				Constant.SEARCH_WORD, Constant.INPUT_FILE_NAME) + Constant.NUM_1 + 
				Constant.TEXT_2 + Constant.NUM_2 + Constant.TEXT_4,
				parser.executeCommand(Constant.COMMAND_SEARCH, Constant.SEARCH_WORD));
	}
}