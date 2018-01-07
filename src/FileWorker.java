import java.io.*;

public class FileWorker {
	static void save(File file, String text) {
		//System.out.println(file.getAbsolutePath());

		try (FileWriter fw = new FileWriter(file)) {
			fw.write(text);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void append(File file, String text) {
		//System.out.println(file.getAbsolutePath());

		try (FileWriter fw = new FileWriter(file, true)) {
			fw.write(text);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
