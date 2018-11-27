package rxjava;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;

public class Notifier {
	
	private static List<URLResource> urls = new ArrayList<>();
	
	private static PublishSubject<URLResource> urlObservable = PublishSubject.create();
	
	/**
	 * @param urlResource
	 */
	public void subscribeObservable(URLResource urlResource) {
		urls.add(urlResource);
	}
	
	/**
	 * @param url
	 * @return
	 * @throws IOException
	 */
	protected long getLastModifiedTime(URL url) throws IOException {
		URLConnection urlConnection = url.openConnection();
		return urlConnection.getLastModified();  
	}
	
	/**
	 * @param urlResource
	 * @return
	 */
	private boolean checkForWebpageChanges(URLResource urlResource) {
		try {
			long current = getLastModifiedTime(new URL(urlResource.getUrl()));
			if (current > urlResource.getLastModifiedTime()) {
				urlResource.setLastModifiedTime(current); //set the last modified time as current
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * @param mode
	 */
	public void subscribe(Observer mode) {
		urlObservable.subscribe(mode); //subscribe observers to observable
	}
	
	public void notifyObservers() {
		for (URLResource urlResource : urls) {
        	if (checkForWebpageChanges(urlResource)) {
        		urlObservable.onNext(urlResource);
        	}
        }
		urlObservable.onComplete(); //once notified to all observers, emit onComplete
	}
	
}
