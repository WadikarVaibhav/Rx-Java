package rxjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observer;

public class Driver {
	
	private static final String SPLIT = " ";
	private static final String PATH = "C:\\Users\\vaibh\\Desktop\\Java\\Web Page Update Notifier\\src\\rxjava\\input.txt";

	public static void main(String[] args) {
		File file = null;
		BufferedReader bufferedReader = null;
		List<URLResource> urls = new ArrayList<>();
		try {
			file = new File(PATH);
			bufferedReader = new BufferedReader(new FileReader(file));
			String data;
			while ((data = bufferedReader.readLine()) != null) {
				subscribeInObservable(Arrays.asList(data.split(SPLIT)), urls);
			}

			bufferedReader.close();

			while (true) {
				for (URLResource urlResource: urls) {
					urlResource.notifyObservers();
				}
				Thread.sleep(60000); // wait before checking next time for updates 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param notifier
	 * @param contactInformation
	 * @param urls 
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	protected static void subscribeInObservable(List<String> contactInformation, List<URLResource> urls) throws MalformedURLException, IOException {
		Observer<String> mode = getObserver(contactInformation);
		URLResource urlResource = getUrlResource(contactInformation);
		urlResource.subScribeObserver(mode);
		urls.add(urlResource);
	}
	
	/**
	 * @param contactInformation
	 * @return
	 */
	protected static Observer getObserver(List<String> contactInformation) {
		return ContactModeFactory.getContactMode(contactInformation);
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
		long lastModified = URLTimeTracker.getLastModified(new URL((String) contactInformation.get(0)));
		String url = (String) contactInformation.get(0);
		String contactMode = (String) contactInformation.get(1);
		return new URLResource(url, contactMode, lastModified, new URL(url));
	}

}