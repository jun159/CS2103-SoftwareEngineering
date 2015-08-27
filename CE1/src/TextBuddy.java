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
import java.util.Scanner;
import java.util.logging.Formatter;

public class TextBuddy {

	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy. %1$s is ready for use";
	private static final String MESSAGE_COMMAND = "command: ";
	private static final String MESSAGE_EMPTY_FILE = "%1$s is empty";
	private static final String MESSAGE_ADD_TEXT = "added to %1$s: \"%2$s\"";
	private static final String MESSAGE_DELETE_TEXT = "deleted from %1$s: \"%2$s\"";
	private static final String MESSAGE_CLEAR_TEXT = "all content deleted from %1$s";
	private static final String MESSAGE_DOT = ". ";
	private static final String MESSAGE_NEW_LINE = "\n";

	private static final String ERROR_CANNOT_DELETE_FILE = "Unable to delete file";
	private static final String ERROR_CANNOT_RENAME_FILE = "Unable to rename file";
	private static final String ERROR_COMMAND_NOT_RECOGNIZED = "Unrecognized command type";
	private static final String ERROR_COMMAND_CANNOT_BE_NULL = "command type string cannot be null";

	private static final String TEMP_FILE_NAME = "tempfile.txt";
	private static String INPUT_FILE_NAME;

	/* These are the possible command types */
	enum COMMAND_TYPE {
		COMMAND_ADD, COMMAND_DISPLAY, COMMAND_DELETE, 
		COMMAND_CLEAR, COMMAND_EXIT, COMMAND_INVALID
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
	private static void setFileName(String fileName) {
		INPUT_FILE_NAME = fileName;
	}

	/**
	 * This operation initialize all file objects for the text file. 
	 * 
	 * @throws IOException				 Input/Output operation failed.
	 * @throws FileNotFoundException	 File not found.
	 */
	private static void initializeFile() 
			throws IOException, FileNotFoundException {
		
		textFile = new File(INPUT_FILE_NAME);
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
		if(!textFile.delete()) {
			printMessage(ERROR_CANNOT_DELETE_FILE);
		} 

		if(!tempFile.renameTo(textFile)) {
			printMessage(ERROR_CANNOT_RENAME_FILE);
		}
	}

	/*
	 * ======================= TEXT INDEX METHODS =========================
	 * The methods below represents the text index operations.
	 * ====================================================================
	 */

	/**
	 * This operation sets each text with an index.
	 * 
	 * @param index		Position of the text.
	 */
	private static void setTextIndex(int index) {
		textIndex = index;
	}

	/**
	 * This operation resets the text index to 1
	 * after the text file is cleared.
	 */
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
			COMMAND_TYPE commandType = checkCommandType(scanner.next());

			switch(commandType) {
				case COMMAND_ADD : 
					addText(scanner.nextLine().trim()); 
					break;
				case COMMAND_DISPLAY : 
					displayText(); 
					break;
				case COMMAND_DELETE : 
					deleteText((scanner.next() + MESSAGE_DOT).trim()); 
					break;
				case COMMAND_CLEAR : 
					clearText(); 
					break;
				case COMMAND_EXIT : 
					exit();
					break;
				default:
					// throw an error if command is not recognized
					throw new Error(ERROR_COMMAND_NOT_RECOGNIZED);
			}
		}
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
		} else if (commandTypeString.equalsIgnoreCase("exit")) {
			return COMMAND_TYPE.COMMAND_EXIT;
		} else {
			return COMMAND_TYPE.COMMAND_INVALID;
		}
	}

	/**
	 * This operation adds text entered by user when the command is "add".
	 * 
	 * @param inputText			Text entered by user.
	 * @throws IOException		Input/Output operation failed.
	 */
	private static void addText(String inputText) throws IOException {
		bufferedWriter.write(textIndex + MESSAGE_DOT + inputText + MESSAGE_NEW_LINE);
		bufferedWriter.flush();
		printMessage(String.format(MESSAGE_ADD_TEXT, INPUT_FILE_NAME, inputText));
		setTextIndex(textIndex + 1);
	}

	/**
	 * This operation displays all texts tagged with index 
	 * in the text file when the command is "display".
	 * 
	 * @throws IOException		Input/Output operation failed.
	 */
	private static void displayText() throws IOException {
		initializeReader(textFile);
		String currentText;

		if((currentText = bufferedReader.readLine()) == null) {
			printMessage(String.format(MESSAGE_EMPTY_FILE, INPUT_FILE_NAME));
		} else {
			do {
				printMessage(currentText);
			} while((currentText = bufferedReader.readLine()) != null);
		}
	}

	/**
	 * This operation deletes the targeted text (by checking the index) 
	 * from the text file when the command is "delete".
	 * 
	 * @throws IOException		Input/Output operation failed.
	 */
	private static void deleteText(String index) throws IOException {
		initializeTempFile();
		resetTextIndex();

		String currentText;

		while((currentText = bufferedReader.readLine()) != null) {
			String currentIndex = (currentText.split(" ") [0]).trim();

			if(currentIndex.equals(index)) {
				printMessage(String.format(MESSAGE_DELETE_TEXT, 
						INPUT_FILE_NAME, currentText.substring(3)));
			} else {
				bufferedWriter.write(textIndex + MESSAGE_DOT + 
						currentText.substring(3) + MESSAGE_NEW_LINE);
				setTextIndex(textIndex + 1);
				bufferedWriter.flush();
			}
		}

		deleteAndRenameFile();
	}

	/**
	 * This operation clears all texts in the text file if command is "clear".
	 * 
	 * @throws IOException		Input/Output operation failed.
	 */
	private static void clearText() throws IOException {
		printMessage(String.format(MESSAGE_CLEAR_TEXT, INPUT_FILE_NAME));
		resetTextIndex();
		initializeWriter(textFile);
	}


	/**
	 * This operation closes file writer and buffered writer
	 * and exits the program.
	 * 
	 * @throws IOException		Input/Output operation failed.
	 */
	private static void exit() throws IOException {
		closeWriter();
		System.exit(0);
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
	private static void welcomeUser() throws IOException {
		printMessage(String.format(MESSAGE_WELCOME, INPUT_FILE_NAME));
		runUILoop();
	}

	/**
	 * This operation prints the message to the user.
	 * 
	 * @param text		Message to be printed out to the user.
	 */
	private static void printMessage(String text) {
		System.out.println(text);
	}

	public static void main(String[] args) throws IOException {
		setFileName(args[0]);
		initializeFile();
		welcomeUser();
	}
}