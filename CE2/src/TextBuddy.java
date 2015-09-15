package src;

import java.io.FileNotFoundException;
import java.io.IOException;

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

public class TextBuddy {
	
	public static String checkFile(String fileName) {
		if(fileName == null) {
			return Constant.INPUT_FILE_NAME;
		} else {
			return fileName;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Logic logic = new Logic();
		logic.startUp("textfile.txt");
		//logic.startUp(checkFile(args[Constant.START_INDEX]));
	}
}