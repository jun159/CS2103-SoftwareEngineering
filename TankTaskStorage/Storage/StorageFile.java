package Storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import Tasks.CategoryWrapper;

/**
 * Add JSON objects into file
 * 
 * @author BAOJUN
 *
 */

public class StorageFile {

	private static final String DEFAULT_FILE_NAME = "mytasklist.txt";
	private static final String TEMP_FILE_NAME = "temp.txt";
	private static String INPUT_FILE_NAME;
	
	private File textFile;
	private File tempFile;

	private FileReader textFileReader;
	private BufferedReader bufferedReader;

	private FileWriter textFileWriter;
	private BufferedWriter bufferedWriter;
	
	public StorageFile() throws FileNotFoundException, IOException { 
		this(DEFAULT_FILE_NAME);
	}
	
	public StorageFile(String fileName) 
			throws FileNotFoundException, IOException {
		setFileName(fileName);
		initializeFile();
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
	}
	
	private void createFile(File file) throws IOException {
		if(!file.exists()) {
			file.createNewFile();
			initializeReader(textFile);
			initializeWriter(textFile);
		}
	}

	/**
	 * This operation initialize all temporary file objects for the text file. 
	 * 
	 * @throws IOException				 Input/Output operation failed.
	 * @throws FileNotFoundException	 File not found.
	 */
	private void initializeTempFile() 
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
	
	/**
	 * 
	 */
	public boolean isFileEmpty() throws IOException {    
		initializeFile();
		initializeReader(textFile);
		
		if (bufferedReader.readLine() == null) {
		    return true;
		}
		
		return false;
	}
	
	/**
	 * This operation retrieves all the texts from target file.
	 * 
	 * @throws IOException				Input/Output operation failed.
	 */
	private String getAllTextsFromFile() throws IOException {
		return new String(Files.readAllBytes(Paths.get(INPUT_FILE_NAME)), StandardCharsets.UTF_8);
	}
	
	public void setFileName(String fileName) {
		INPUT_FILE_NAME = fileName;
	}

	public String getFileName() {
		return INPUT_FILE_NAME;
	}

	public File getFile() {
		return textFile;
	}
	
	public ArrayList<CategoryWrapper> getAllCategoriesFromFile() 
			throws JsonParseException, JsonMappingException, IOException {
		
		if(isFileEmpty()) {
			return new ArrayList<CategoryWrapper>();
		} else {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			ArrayList<CategoryWrapper> allTasks = 
					mapper.readValue(getAllTextsFromFile(), new TypeReference<ArrayList<CategoryWrapper>>() {});
			return allTasks;
		} 
	}
	
	public void setAllCategoriesToFile(ArrayList<CategoryWrapper> categoryWrapper) 
			throws JsonParseException, JsonMappingException, IOException {
		clearTextFromFile();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.writeValue(textFile, categoryWrapper);
		bufferedWriter.write(mapper.writeValueAsString(categoryWrapper));
		bufferedWriter.flush();
		System.out.println(mapper.writeValueAsString(categoryWrapper));
	}
	
	public void clearTextFromFile() throws IOException {
		initializeWriter(textFile);
	}
	
	/*
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
	*/
}