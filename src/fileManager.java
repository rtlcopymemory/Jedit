import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class fileManager {
	private static String path;
	
	public static String openFile(String fileName) {
		path = fileName;
		File file = new File(fileName);
		Scanner sc;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		}
		String result = "";
		while (sc.hasNextLine()) {
			result += sc.nextLine() + "\n";
		}
		return result;
	}
	
	public static void saveFile(String content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
