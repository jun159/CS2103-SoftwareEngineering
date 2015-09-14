package src;

/**
 * This class is used to store and retrieve the texts input by user
 * The user will enter the desired command to add, display and delete texts
 * or clear all texts from the text file. 
 * 
 * The command format is given by the example interaction below:

	Welcome to TextBuddy. mytextfile.txt is ready for use
	command: add little brown fox
	added to mytextfile.txt: “little brown fox”
	command: display
	1. little brown fox
	command: add jumped over the moon
	added to mytextfile.txt: “jumped over the moon”
	command: display
	1. little brown fox
	2. jumped over the moon
	command: delete 2
	deleted from mytextfile.txt: “jumped over the moon”
	command: display
	1. little brown fox
	command: clear
	all content deleted from mytextfile.txt
	command: display
	mytextfile.txt is empty
	command: exit

 * @author LUAH BAO JUN
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Formatter;

public class TextBuddy {

	public static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %1$s is ready for use";
	public static final String MESSAGE_COMMAND = "command: ";
	public static final String MESSAGE_EMPTY_FILE = "%1$s is empty";
	public static final String MESSAGE_ADD_TEXT = "added to %1$s: \"%2$s\"";
	public static final String MESSAGE_DELETE_TEXT = "deleted from %1$s: \"%2$s\"";
	public static final String MESSAGE_CLEAR_TEXT = "all content deleted from %1$s";
	public static final String MESSAGE_SORT_TEXT = "%1$s content sorted in alphabetical order";
	public static final String MESSAGE_SEARCH_TEXT = "%1$s results containing word \"%2$s\" found in %3$s:";
	public static final String MESSAGE_SEARCH_IS_EMPTY = "there is no text containing word \"%1$s\" found in %2$s";
	public static final String MESSAGE_DOT = ". ";
	public static final String MESSAGE_NEW_LINE = "\n";

	public static final String ERROR_CANNOT_DELETE_FILE = "unable to delete file";
	public static final String ERROR_CANNOT_RENAME_FILE = "unable to rename file";
	public static final String ERROR_COMMAND_NOT_RECOGNIZED = "unrecognized command type";
	public static final String ERROR_COMMAND_CANNOT_BE_NULL = "command type string cannot be null";
	public static final String ERROR_LIST_IS_EMPTY = "unable to sort list as the list is empty";
	public static final String ERROR_NULL_SEARCH_INPUT = "please ensure that your input is not empty";

	private static final String TEMP_FILE_NAME = "tempfile.txt";
	private static String INPUT_FILE_NAME;
	
	private static final int START_INDEX = 0;
	private static final int INCREMENT_INDEX = 1;
	private static final int START_INDEX_OF_TEXT = 3;

	/* These are the possible command types */
	enum COMMAND_TYPE {
		COMMAND_ADD, COMMAND_DISPLAY, COMMAND_DELETE, COMMAND_CLEAR, 
		COMMAND_SORT, COMMAND_SEARCH, COMMAND_EXIT, COMMAND_INVALID
	};

	/* This scanner will be used for the whole class. */
	private static final Scanner scanner = new Scanner(System.in);

	/* This integer will represent the index of each text. */
	private static int textIndex = 1;

	/*
	 *  These are the file objects to be used to interact with the text file
	 *  such as adding, deleting or clearing the texts from the text file.
	 */
	private static File textFile;
	private static File tempFile;

	private static FileReader textFileReader;
	private static BufferedReader bufferedReader;

	private static FileWriter textFileWriter;
	private static BufferedWriter bufferedWriter;
	
	public TextBuddy(String fileName) throws FileNotFoundException, IOException {
		setFileName(fileName);
		initializeFile();
		scanCommand();
	}

	/*
	 * ========================== FILE METHODS =============================
	 * The methods below represents the file operations.
	 * ====================================================================
	 */

	/**
	 * This operation sets the file name input by user
	 * to the string variable INPUT_FILE_NAME.
	 * 
	 * @param fileName			File name input by the user.
	 */
	public static void setFileName(String fileName) {
		INPUT_FILE_NAME = fileName;
	}
	
	/**
	 * This operation creates the file (file name is provided by the user)
	 * if the file does not exist yet.
	 * 
	 * @param inputTextFile		File to be created.
	 */
	private static void createFile(File inputTextFile) throws IOException {
		if (!inputTextFile.exists()) {
			inputTextFile.createNewFile();
		}
	}

	/**
	 * This operation initialize all file objects for the text file. 
	 * 
	 * @throws IOException				 Input/Output operation failed.
	 * @throws FileNotFoundException	 File not found.
	 */
	public static void initializeFile() 
			throws IOException, FileNotFoundException {
		
		textFile = new File(INPUT_FILE_NAME);
		createFile(textFile);
		initializeReader(textFile);
		initializeWriter(textFile);
	}

	/**
	 * This operation initialize all temporary file objects for the text file. 
	 * 
	 * @throws IOException				 Input/Output operation failed.
	 * @throws FileNotFoundException	 File not found.
	 */
	private static void initializeTempFile() 
			throws IOException, FileNotFoundException {
		
		tempFile = new File(TEMP_FILE_NAME);
		initializeReader(textFile);
		initializeWriter(tempFile);
	}

	/**
	 * This operation initialize all reader objects to read text to text file.
	 * 
	 * @throws IOException				 Input/Output operation failed.
	 * @throws FileNotFoundException	 File not found.
	 */
	private static void initializeReader(File inputTextFile) 
			throws IOException, FileNotFoundException {
		
		textFileReader = new FileReader(inputTextFile);
		bufferedReader = new BufferedReader(textFileReader);
	}

	/**
	 * This operation initialize all writer objects to read text to text file.
	 * 
	 * @throws IOException				 Input/Output operation failed.
	 * @throws FileNotFoundException	 File not found.
	 */
	private static void initializeWriter(File inputTextFile) 
			throws IOException, FileNotFoundException {
		
		textFileWriter = new FileWriter(inputTextFile);
		bufferedWriter = new BufferedWriter(textFileWriter);
	}

	/**
	 * This operation closes the readers after finished using.
	 * 
	 * @throws IOException				 Input/Output operation failed.
	 */
	private static void closeReader() throws IOException {
		textFileReader.close();
		bufferedReader.close();
	}

	/**
	 * This operation flushes and closes the writers after finished using.
	 * 
	 * @throws IOException				 Input/Output operation failed.
	 */
	private static void closeWriter() throws IOException {
		bufferedWriter.flush();
		textFileWriter.close();
		bufferedWriter.close();
	}

	/**
	 * This operation deletes the older version of textFile and
	 * rename the latest tempFile back to filename provided by the user.
	 * 
	 * @throws IOException				Input/Output operation failed.
	 */
	private static void deleteAndRenameFile() throws IOException {
		if (!textFile.delete()) {
			printMessage(ERROR_CANNOT_DELETE_FILE);
		} 

		if (!tempFile.renameTo(textFile)) {
			printMessage(ERROR_CANNOT_RENAME_FILE);
		}
	}

	/*
	 * ======================= TEXT INDEX METHODS =========================
	 * The methods below represents the text index operations.
	 * ====================================================================
	 */

	private static void setTextIndex(int index) {
		textIndex = index;
	}

	private static void resetTextIndex() {
		textIndex = 1;
	}

	/*
	 * ======================= COMMAND METHODS =========================
	 * The methods below represents the command operations.
	 * ====================================================================
	 */

	/**
	 * This operation add, display, delete, clear texts 
	 * or exit program according to user's command.
	 * 
	 * @throws IOException		Input/Output operation failed.
	 */
	private static void runUILoop() throws IOException {
		while(true) {		
			System.out.print(MESSAGE_COMMAND);
			try {
				executeCommand(scanner.next(), scanner.nextLine().trim());
			} catch (NoSuchElementException e) {
				break;
			}
		}
	}
	
	public static String executeCommand(String command, String text) throws IOException {
		COMMAND_TYPE commandType = checkCommandType(command);
		
		switch(commandType) {
			case COMMAND_ADD : 
				return addText(text, true); 
			case COMMAND_DISPLAY : 
				return displayText(); 
			case COMMAND_DELETE : 
				return deleteText((text + MESSAGE_DOT).trim()); 
			case COMMAND_CLEAR : 
				return clearText(); 
			case COMMAND_SORT:
				return sortText();
			case COMMAND_SEARCH:
				return searchText(text);
			case COMMAND_EXIT : 
				exit();
				break;
			default:
				// throw an error if command is not recognized
				throw new Error(ERROR_COMMAND_NOT_RECOGNIZED);
		}
		
		return null;
	}

	/**
	 * This operation determines which of the supported 
	 * command types the user wants to perform.
	 * 
	 * @param commandTypeString		First word of the user command.
	 */
	private static COMMAND_TYPE checkCommandType(String commandTypeString) {
		if (commandTypeString == null) {
			throw new Error(ERROR_COMMAND_CANNOT_BE_NULL);
		}

		if (commandTypeString.equalsIgnoreCase("add")) {
			return COMMAND_TYPE.COMMAND_ADD;
		} else if (commandTypeString.equalsIgnoreCase("display")) {
			return COMMAND_TYPE.COMMAND_DISPLAY;
		} else if (commandTypeString.equals("delete")) {
			return COMMAND_TYPE.COMMAND_DELETE;
		} else if (commandTypeString.equals("clear")) {
			return COMMAND_TYPE.COMMAND_CLEAR;
		} else if (commandTypeString.equals("sort")) {
			return COMMAND_TYPE.COMMAND_SORT;
		} else if (commandTypeString.equals("search")) {
			return COMMAND_TYPE.COMMAND_SEARCH;
		} else if (commandTypeString.equalsIgnoreCase("exit")) {
			return COMMAND_TYPE.COMMAND_EXIT;
		} else {
			return COMMAND_TYPE.COMMAND_INVALID;
		}
	}

	private static String addText(String inputText, boolean isPrintMessage) throws IOException {
		bufferedWriter.write(textIndex + MESSAGE_DOT + inputText + MESSAGE_NEW_LINE);
		bufferedWriter.flush();
		setTextIndex(++textIndex);
		if (isPrintMessage) return printMessage(String.format(MESSAGE_ADD_TEXT, INPUT_FILE_NAME, inputText));
		return "";
	}

	private static String displayText() throws IOException {
		String message = "";
		initializeReader(textFile);
		String currentText;

		if ((currentText = bufferedReader.readLine()) == null) {
			message = printMessage(String.format(MESSAGE_EMPTY_FILE, INPUT_FILE_NAME));
		} else {
			do {
				message += printMessage(currentText);
			} while((currentText = bufferedReader.readLine()) != null);
		}
		
		return message;
	}

	private static String deleteText(String index) throws IOException {
		String message = "";
		
		initializeTempFile();
		resetTextIndex();

		String currentText;

		while((currentText = bufferedReader.readLine()) != null) {
			String currentIndex = (currentText.split(" ") [START_INDEX]).trim();

			if(currentIndex.equals(index)) {
				message = printMessage(String.format(MESSAGE_DELETE_TEXT, 
						INPUT_FILE_NAME, currentText.substring(START_INDEX_OF_TEXT)));
			} else {
				bufferedWriter.write(textIndex + MESSAGE_DOT + 
						currentText.substring(START_INDEX_OF_TEXT) + MESSAGE_NEW_LINE);
				setTextIndex(++textIndex);
				bufferedWriter.flush();
			}
		}

		deleteAndRenameFile();
		
		return message;
	}

	public static String clearText() throws IOException {
		resetTextIndex();
		initializeWriter(textFile);
		return printMessage(String.format(MESSAGE_CLEAR_TEXT, INPUT_FILE_NAME));
	}
	
	private static String sortText() throws IOException {
		List<String> textList = retrieveAllTexts();
		Collections.sort(textList);
		
		// Clear file before adding sorted texts
		initializeWriter(textFile);
		resetTextIndex();
		
		for(String text : textList) {
			addText(text, false);
		}

		return printMessage(String.format(MESSAGE_SORT_TEXT, INPUT_FILE_NAME));
	}
	
	private static String searchText(String searchWord) throws IOException {
		List<String> textList = retrieveAllTexts();
		List<String> searchResultList = new ArrayList<String>();
		
		if(searchWord == null) {
			return ERROR_NULL_SEARCH_INPUT;
		} else {
			int numberOfTexts = textList.size();
			
			for(int i = 0; i < numberOfTexts; i++) {
				String text = textList.get(i);
				if(text.contains(searchWord)) {
					searchResultList.add(text);
				}
			}
		}
		
		return printSearchResults(searchResultList, searchWord);
	}
	
	private static List<String> retrieveAllTexts() throws IOException {
		List<String> textList = new ArrayList<String>();
		String currentText;
		
		initializeReader(textFile);
		
		while((currentText = bufferedReader.readLine()) != null) {
			textList.add(currentText.substring(START_INDEX_OF_TEXT));
		}
		
		return textList;
	}

	/**
	 * This operation closes file writer and buffered writer
	 * and exits the program.
	 * 
	 * @throws IOException		Input/Output operation failed.
	 */
	private static void exit() throws IOException {
		closeWriter();
		System.exit(START_INDEX);
	}
	
	/*
	 * ======================= TESTING METHODS ======================
	 * The methods below represents the testing operations.
	 * ====================================================================
	 */
	
	public static boolean isFound(List<String> textList, String searchWord) {
		if(textList == null) {
			return false;
		}
		
		return textList.get(0).contains(searchWord);
	}

	/*
	 * ======================= PRINT MESSAGE METHODS ======================
	 * The methods below represents the print message operations.
	 * ====================================================================
	 */

	/**
	 * 
	 * This operation prints the welcome message 
	 * and scan the command entered by user.
	 * 
	 * @throws IOException 		Input/Output operation failed.	
	 */
	private static void scanCommand() throws IOException {
		printMessage(String.format(MESSAGE_WELCOME, INPUT_FILE_NAME));
		runUILoop();
	}

	private static String printMessage(String text) {
		System.out.println(text);
		return text;
	}
	
	private static String printSearchResults(List<String> textList, String searchWord) {
		String message = "";
		int numberOfSearchResults = textList.size();
		
		if(numberOfSearchResults == 0) {
			message = printMessage(String.format(MESSAGE_SEARCH_IS_EMPTY, searchWord, INPUT_FILE_NAME));
		} else {
			message += printMessage(String.format(MESSAGE_SEARCH_TEXT, numberOfSearchResults, searchWord, INPUT_FILE_NAME));
			for(int i = 1; i <= numberOfSearchResults; i++) {
				message += printMessage(i + MESSAGE_DOT + textList.get(i - 1));
			}
		}

		return message;
	}
	
	public static void main(String[] args) throws IOException {
		setFileName(args[START_INDEX]);
		initializeFile();
		scanCommand();
	}
}