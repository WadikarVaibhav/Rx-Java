package rxjava;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class Notifier {
	
	List<URLResource> urls = new ArrayList<>();
	
	PublishSubject<URLResource> urlObservable = PublishSubject.create();
	
	/**
	 * @param urlResource
	 */
	public void subscribeURL(URLResource urlResource) {
		urls.add(urlResource);
	}
	
	/**
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private Date getLastModifiedTime(String url) throws MalformedURLException, IOException {
		URL u = new URL(url);
	    URLConnection uc = u.openConnection();
		return new Date(uc.getLastModified());
	}
	
	/**
	 * @param urlResource
	 * @return
	 */
	private boolean checkForWebpageChanges(URLResource urlResource) {
		try {
			//Date current = getLastModifiedTime(urlResource.getUrl());
			Date current = new Date();
			if (current.after(urlResource.getLastModifiedTime())) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	/**
	 * @param mode
	 */
	public void subscribe(ContactMode mode) {
		urlObservable.subscribe(mode);
	}
	
	public void notifyObservers() {
		for (URLResource urlResource : urls) {
        	if (checkForWebpageChanges(urlResource)) {
        		urlObservable.onNext(urlResource);
        	}
        }
		urlObservable.onComplete();
	}
	
}
