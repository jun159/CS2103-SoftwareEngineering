package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Logic {

	/* This scanner will be used for the whole class. */
	private static final Scanner scanner = new Scanner(System.in);
	
	private Parser parser;
	private Storage storage;
	
	public Logic() throws IOException {
		storage = new Storage();
	}
	
	public void startUp(String fileName) 
			throws FileNotFoundException, IOException {
		storage.createNewFile(fileName);
		scanCommand();
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
	public void runUILoop() throws IOException {
		parser = new Parser();
		
		while(true) {		
			System.out.print(Constant.MESSAGE_COMMAND);
			try {
				parser.executeCommand(scanner.next(), scanner.nextLine().trim());
			} catch (NoSuchElementException e) {
				break;
			}
		}
	}
	
	public String addText(String inputText, boolean isPrintMessage) throws IOException {
		storage.addTextToFile(inputText);
		if (isPrintMessage) return printMessage(String.format(Constant.MESSAGE_ADD_TEXT, 
				storage.getFileName(), inputText).trim());
		return "";
	}

	public String displayText() throws IOException {
		return printMessage(storage.displayTextFromFile().trim());
	}

	public String deleteText(String index) throws IOException {
		return printMessage(storage.deleteTextFromFile(index).trim());
	}

	public String clearText() throws IOException {
		storage.clearTextFromFile();
		return printMessage(String.format(Constant.MESSAGE_CLEAR_TEXT, storage.getFileName()).trim());
	}
	
	public String sortText() throws IOException {
		List<String> textList = storage.retrieveAllTexts();
		Collections.sort(textList);
		
		// Clear file before adding sorted texts
		storage.clearTextFromFile();
		
		for(String text : textList) {
			addText(text, false);
		}

		return printMessage(String.format(Constant.MESSAGE_SORT_TEXT, storage.getFileName()).trim());
	}
	
	public String searchText(String searchWord) throws IOException {
		List<String> textList = storage.retrieveAllTexts();
		List<String> searchResultList = new ArrayList<String>();
		
		if(searchWord == null) {
			return Constant.ERROR_NULL_SEARCH_INPUT;
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

	public void exit() throws IOException {
		storage.exitProgram();
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
	public void scanCommand() throws IOException {
		printMessage(String.format(Constant.MESSAGE_WELCOME, storage.getFileName()));
		runUILoop();
	}

	public String printMessage(String text) {
		System.out.println(text);
		return text;
	}
	
	public String printSearchResults(List<String> textList, String searchWord) {
		String message = "";
		int numberOfSearchResults = textList.size();
		
		if(numberOfSearchResults == 0) {
			message = printMessage(String.format(Constant.MESSAGE_SEARCH_IS_EMPTY, 
					searchWord, storage.getFileName()));
		} else {
			message += printMessage(String.format(Constant.MESSAGE_SEARCH_TEXT, 
					numberOfSearchResults, searchWord, storage.getFileName()));
			for(int i = 1; i <= numberOfSearchResults; i++) {
				message += printMessage(i + Constant.MESSAGE_DOT + textList.get(i - 1).trim());
			}
		}

		return message;
	}
}
