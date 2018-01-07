import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpWorker {

	public static String getHtml(String url_s) {
		System.out.println("Getting " + url_s);

		String result = null;

		try {
			BufferedReader reader = null;
			URLConnection uc = null;

			try {
				URL url = new URL(url_s);
				uc = url.openConnection();
				uc.setConnectTimeout(1000);
				uc.connect();
				reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
				StringBuilder buffer = new StringBuilder();
				int read;
				char[] chars = new char[1024];
				while ((read = reader.read(chars)) != -1)
					buffer.append(chars, 0, read);

				result = buffer.toString();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (reader != null)
					reader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
