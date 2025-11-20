import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {
	public static void main(String[] args) {

		// Check if command is missing
		if (args.length == 0) {
            System.out.println("Please provide a command: a, r, ?, +, or c");
            return;
        }

		// Load student list from file
		String fileContents = LoadData("students.txt");

		// Command: Show all students
		if (args[0].equals("a")) {

			System.out.println("Loading data ...");

			try {
				// Split names by comma
				String words[] = fileContents.split(",");
				for (String word : words) {
					System.out.println(word);
				}
			} catch (Exception e) {}

			System.out.println("Data Loaded.");
		}

		// Command: Show random student
		else if (args[0].equals("r")) {

			System.out.println("Loading data ...");

			try {
				String words[] = fileContents.split(",");
				// Generate random index
				Random random = new Random();
				int randomIndex = random.nextInt(0, words.length);
				System.out.println(words[randomIndex]);
			} catch (Exception e) {}

			System.out.println("Data Loaded.");
		}

		// Command: Add new student
		else if (args[0].contains("+")) {

			System.out.println("Loading data ...");

			try {
				// Open file in append mode
				BufferedWriter fileStream = new BufferedWriter(
						new FileWriter("students.txt", true));

				// Extract name after '+'
				String argValue = args[0].substring(1);

				// Format date-time
				Date date = new Date();
				String dateFormatObj = "dd/mm/yyyy-hh:mm:ss a";
				DateFormat dateFormat = new SimpleDateFormat(dateFormatObj);
				String formatDate = dateFormat.format(date);

				// Write name + update time
				fileStream.write(", " + argValue + "\nList last updated on " + formatDate);
				fileStream.close();

			} catch (Exception e) {}

			System.out.println("Data Loaded.");
		}

		// Command: Search for a student
		else if (args[0].contains("?")) {

			System.out.println("Loading data ...");

			try {
				String words[] = fileContents.split(",");
				boolean done = false;

				// Extract name after '?'
				String argValue = args[0].substring(1);

				// Loop through list
				for (int idx = 0; idx < words.length && !done; idx++) {
					if (words[idx].equals(argValue)) {
						System.out.println("We found it!");
						done = true;
					}
				}

			} catch (Exception e) {}

			System.out.println("Data Loaded.");
		}

		// Command: Count words
		else if (args[0].contains("c")) {

			System.out.println("Loading data ...");

			try {
				// Convert file content to characters
				char characters[] = fileContents.toCharArray();
				boolean in_word = false;
				int count = 0;

				// Simple space-based word count logic
				for (char c : characters) {
					if (c == ' ') {
						if (!in_word) {
							count++;
							in_word = true;
						} else {
							in_word = false;
						}
					}
				}

				System.out.println(count + " word(s) found " + characters.length);

			} catch (Exception e) {}

			System.out.println("Data Loaded.");
		}

		else {
            System.out.println("Invalid argument! Use a, r, ?, +, or c");
        }
	}

	// Function to load file content
	public static String LoadData(String fileName) {
		BufferedReader fileStream = null;
		try {
			// Open file for reading
			fileStream = new BufferedReader(
					new InputStreamReader(
							new FileInputStream("students.txt")));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		String reader = null;
		try {
			// Read first line
			reader = fileStream.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return reader;
	}

}
