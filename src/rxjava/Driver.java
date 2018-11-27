package rxjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;

public class Driver {
	
	private static final String SPLIT = " ";
	private static final String PATH = "C:\\Users\\vaibh\\Desktop\\Java\\Web Page Update Notifier\\src\\rxjava\\input.txt";

	public static void main(String[] args) {
		Notifier notifier = new Notifier();
		File file = null;
		BufferedReader bufferedReader = null;

		try {
			file = new File(PATH);
			bufferedReader = new BufferedReader(new FileReader(file));
			String data;
			while ((data = bufferedReader.readLine()) != null) {
				subscribeInObservable(notifier, Arrays.asList(data.split(SPLIT)));
			}

			bufferedReader.close();

			while (true) {
				Thread.sleep(60000); // wait before checking for updates
				notifier.notifyObservers();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param notifier
	 * @param contactInformation
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	protected static void subscribeInObservable(Notifier notifier, List<String> contactInformation) throws MalformedURLException, IOException {
		Observer mode = getObserver(contactInformation);
		notifier.subscribe(mode);
		URLResource urlResource = getUrlResource(contactInformation);
		notifier.subscribeObservable(urlResource);
	}
	
	/**
	 * @param contactInformation
	 * @return
	 */
	protected static Observer getObserver(List<String> contactInformation) {
		return ContactModeFactory.getContactMode(contactInformation);
	}
	
	/**
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private static long getLastModified(URL url) throws IOException {
		URLConnection urlConnection = url.openConnection();
		return urlConnection.getLastModified();  
	}
	
	/**
	 * @param contactInformation
	 * @return
	 * contactDetails.get(0) represents URL
	 * contactDetails.get(1) represents mode of contact
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	protected static URLResource getUrlResource(List<String> contactInformation) throws MalformedURLException, IOException {
		long lastModified = getLastModified(new URL((String) contactInformation.get(0)));
		String url = (String) contactInformation.get(0);
		String contactMode = (String) contactInformation.get(1);
		return new URLResource(url, contactMode, lastModified);
	}

}