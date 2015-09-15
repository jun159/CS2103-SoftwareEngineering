package src;

public class Constant {
	
	public static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %1$s is ready for use";
	public static final String MESSAGE_COMMAND = "command: ";
	public static final String MESSAGE_ADD_TEXT = "added to %1$s: \"%2$s\"";
	public static final String MESSAGE_CLEAR_TEXT = "all content deleted from %1$s";
	public static final String MESSAGE_SORT_TEXT = "%1$s content sorted in alphabetical order";
	public static final String MESSAGE_SEARCH_TEXT = "%1$s results containing word \"%2$s\" found in %3$s:";
	public static final String MESSAGE_SEARCH_IS_EMPTY = "there is no text containing word \"%1$s\" found in %2$s";
	public static final String MESSAGE_DELETE_TEXT = "deleted from %1$s: \"%2$s\"";
	public static final String MESSAGE_EMPTY_FILE = "%1$s is empty";
	public static final String MESSAGE_DOT = ". ";
	public static final String MESSAGE_NEW_LINE = "\n";
	
	public static final String ERROR_CANNOT_DELETE_FILE = "unable to delete file";
	public static final String ERROR_CANNOT_RENAME_FILE = "unable to rename file";
	public static final String ERROR_COMMAND_NOT_RECOGNIZED = "unrecognized command type";
	public static final String ERROR_COMMAND_CANNOT_BE_NULL = "command type string cannot be null";
	public static final String ERROR_LIST_IS_EMPTY = "unable to sort list as the list is empty";
	public static final String ERROR_NULL_SEARCH_INPUT = "please ensure that your input is not empty";
	
	public static final String INPUT_FILE_NAME = "mytestFile.txt";
	public static final String TEMP_FILE_NAME = "tempfile.txt";
	
	public static final String COMMAND_ADD = "add";
	public static final String COMMAND_DISPLAY = "display";
	public static final String COMMAND_SORT = "sort";
	public static final String COMMAND_SEARCH = "search";
	
	public static final String SEARCH_WORD = "East";
	
	public static final String TEXT_1 = "Potong Pasir";
	public static final String TEXT_2 = "East Coast";
	public static final String TEXT_3 = "Ang Mo Kio";
	public static final String TEXT_4 = "Jurong East";
	public static final String TEXT_5 = "Bishan";
	public static final String TEXT_6 = "Chua Chu Kang";
	
	public static final String NUM_1 = "1. ";
	public static final String NUM_2 = "2. ";
	public static final String NUM_3 = "3. ";
	public static final String NUM_4 = "4. ";
	public static final String NUM_5 = "5. ";
	
	public static final int START_INDEX = 0;
	public static final int START_INDEX_OF_TEXT = 3;
	public static final int NUMBER_OF_TEXTS_CONTAINING_WORD_EAST = 2;
}
