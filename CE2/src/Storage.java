package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

	private static String INPUT_FILE_NAME;
	
	private static File textFile;
	private static File tempFile;

	private static FileReader textFileReader;
	private static BufferedReader bufferedReader;

	private static FileWriter textFileWriter;
	private static BufferedWriter bufferedWriter;
	
	public Storage() { }
	
	/**
	 * This operation creates the file (file name is provided by the user)
	 * if the file does not exist yet.
	 * 
	 * @param inputTextFile		File to be created.
	 */
	private void createFile(File inputTextFile) throws IOException {
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
	private void initializeFile() 
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
	private void initializeTempFile() 
			throws IOException, FileNotFoundException {
		
		tempFile = new File(Constant.TEMP_FILE_NAME);
		initializeReader(textFile);
		initializeWriter(tempFile);
	}

	/**
	 * This operation initialize all reader objects to read text to text file.
	 * 
	 * @throws IOException				 Input/Output operation failed.
	 * @throws FileNotFoundException	 File not found.
	 */
	private void initializeReader(File inputTextFile) 
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
	private void initializeWriter(File inputTextFile) 
			throws IOException, FileNotFoundException {
		
		textFileWriter = new FileWriter(inputTextFile);
		bufferedWriter = new BufferedWriter(textFileWriter);
	}

	/**
	 * This operation closes the readers after finished using.
	 * 
	 * @throws IOException				 Input/Output operation failed.
	 */
	private void closeReader() throws IOException {
		textFileReader.close();
		bufferedReader.close();
	}

	/**
	 * This operation flushes and closes the writers after finished using.
	 * 
	 * @throws IOException				 Input/Output operation failed.
	 */
	private void closeWriter() throws IOException {
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
	private void deleteAndRenameFile() throws IOException {
		textFile.delete();
		tempFile.renameTo(textFile);
	}
	
	public void setFileName(String fileName) {
		INPUT_FILE_NAME = fileName;
	}

	public String getFileName() {
		return INPUT_FILE_NAME;
	}
	
	public void createNewFile(String fileName) throws FileNotFoundException, IOException {
		setFileName(fileName);
		initializeFile();
	}
	
	public void addTextToFile(String inputText) throws IOException {
		bufferedWriter.write((retrieveAllTexts().size() + 1) + Constant.MESSAGE_DOT + inputText + Constant.MESSAGE_NEW_LINE);
		bufferedWriter.flush();
	}

	public String displayTextFromFile() throws IOException {
		String message = "";
		initializeReader(textFile);
		String currentText;

		if ((currentText = bufferedReader.readLine()) == null) {
			message = String.format(Constant.MESSAGE_EMPTY_FILE, INPUT_FILE_NAME) + Constant.MESSAGE_NEW_LINE;
		} else {
			do {
				message += currentText + Constant.MESSAGE_NEW_LINE;
			} while((currentText = bufferedReader.readLine()) != null);
		}
		
		return message;
	}

	public String deleteTextFromFile(String index) throws IOException {
		String message = "";
		
		initializeTempFile();
		int textIndex = 1;

		String currentText;

		while((currentText = bufferedReader.readLine()) != null) {
			String currentIndex = (currentText.split(" ") [Constant.START_INDEX]).trim();

			if(currentIndex.equals(index)) {
				message = String.format(Constant.MESSAGE_DELETE_TEXT, 
						INPUT_FILE_NAME, currentText.substring(Constant.START_INDEX_OF_TEXT));
			} else {
				bufferedWriter.write(textIndex + Constant.MESSAGE_DOT + 
						currentText.substring(Constant.START_INDEX_OF_TEXT) + Constant.MESSAGE_NEW_LINE);
				textIndex++;
				bufferedWriter.flush();
			}
		}

		deleteAndRenameFile();
		
		return message;
	}

	public void clearTextFromFile() throws IOException {
		initializeWriter(textFile);
	}
	
	public List<String> retrieveAllTexts() throws IOException {
		List<String> textList = new ArrayList<String>();
		String currentText;
		
		initializeReader(textFile);
		
		while((currentText = bufferedReader.readLine()) != null) {
			textList.add(currentText.substring(Constant.START_INDEX_OF_TEXT));
		}
		
		return textList;
	}
	
	public void exitProgram() throws IOException {
		closeReader();
		closeWriter();
		System.exit(Constant.START_INDEX);
	}
}
