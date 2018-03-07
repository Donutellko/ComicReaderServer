package xyz.donutellko.comicreaderserver;

import java.io.*;

public class FileWorker {
	static void save(File file, String text) {
		//System.out.println(file.getAbsolutePath());
		file.getParentFile().mkdirs();

		try (FileWriter fw = new FileWriter(file, false)) {
			fw.write(text);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void append(File file, String text) {
		//System.out.println(file.getAbsolutePath());
		file.getParentFile().mkdirs();

		try (FileWriter fw = new FileWriter(file, true)) {
			fw.write(text);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static String getCurrentPath() {
		return new File("").getAbsolutePath();
	}

	public static String read(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder result = new StringBuilder();
			String s = "";
			do {
				result.append("\n").append(s);
				s = br.readLine();
			} while (s != null);
			return result.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
