package src;

import java.io.IOException;

public class Parser {
	
	/* These are the possible command types */
	enum COMMAND_TYPE {
		COMMAND_ADD, COMMAND_DISPLAY, COMMAND_DELETE, COMMAND_CLEAR, 
		COMMAND_SORT, COMMAND_SEARCH, COMMAND_EXIT, COMMAND_INVALID
	};
	
	private Logic logic;
	
	public Parser() throws IOException { 
		logic = new Logic();
	}

	/**
	 * This operation determines which of the supported 
	 * command types the user wants to perform.
	 * 
	 * @param commandTypeString		First word of the user command.
	 */
	private COMMAND_TYPE checkCommandType(String commandTypeString) {
		if (commandTypeString == null) {
			throw new Error(Constant.ERROR_COMMAND_CANNOT_BE_NULL);
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
	
	/**
	 * This operation runs the program 
	 * according to the command input by user.
	 * 
	 * @param commandTypeString		First word of the user command.
	 * 		  text					Text after command word. 
	 */
	public String executeCommand(String command, String text) throws IOException {
		COMMAND_TYPE commandType = checkCommandType(command);
		
		switch(commandType) {
			case COMMAND_ADD : 
				return logic.addText(text, true); 
			case COMMAND_DISPLAY : 
				return logic.displayText(); 
			case COMMAND_DELETE : 
				return logic.deleteText((text + Constant.MESSAGE_DOT).trim()); 
			case COMMAND_CLEAR : 
				return logic.clearText(); 
			case COMMAND_SORT:
				return logic.sortText();
			case COMMAND_SEARCH:
				return logic.searchText(text);
			case COMMAND_EXIT : 
				logic.exit();
				break;
			default:
				// throw an error if command is not recognized
				throw new Error(Constant.ERROR_COMMAND_NOT_RECOGNIZED);
		}
		
		return null;
	}
}
