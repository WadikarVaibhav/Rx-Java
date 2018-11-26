package rxjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
	
	private static Date getLastModifiedTime(String url) {
		try {
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			return new Date(uc.getLastModified());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static void main(String[] args) {
		Notifier notifier = new Notifier();
		File file = null;
		BufferedReader bufferedReader = null;

		try {
			file = new File("C:\\Users\\vaibh\\Desktop\\Java\\Web Page Update Notifier\\src\\rxjava\\input.txt");
			bufferedReader = new BufferedReader(new FileReader(file));
			String data;
			while ((data = bufferedReader.readLine()) != null) {
				List<String> contactInformation = Arrays.asList(data.split(" "));
				ContactMode mode = ContactModeFactory.getContactMode(contactInformation);
				notifier.subscribe(mode);
				URLResource url = new URLResource((String) contactInformation.get(0),
						(String) contactInformation.get(1), getLastModifiedTime((String) contactInformation.get(0)));
				notifier.subscribeURL(url);
			}

			bufferedReader.close();

			while (true) {
				notifier.notifyObservers();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
