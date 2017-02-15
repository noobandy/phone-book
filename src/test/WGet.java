package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class WGet {

	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("url argumant missing");
			System.exit(1);
		}
		InputStream is = null;
		OutputStream os = null;
		try {
			URL url = new URL(args[0]);
			URLConnection connection = url.openConnection();
			is = connection.getInputStream();
			os = new FileOutputStream(args[0].substring(args[0].lastIndexOf('/') + 1));
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = is.read(buffer)) > 0) {
				os.write(buffer, 0, bytesRead);
			}

		} finally {
			if (is != null) {
				is.close();
			}

			if (os != null) {
				os.close();
			}
		}
	}
}
