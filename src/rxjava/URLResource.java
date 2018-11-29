package rxjava;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.Observer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class URLResource  {

	private final URL url;
	private final String websiteURL;
	private final String contactMode;
	private long lastModifiedTime;
	private Subject<String> subject;
	
	/**
	 * @param websiteURL
	 * @param contactMode
	 * @param lastModifiedTime
	 * @param url 
	 * @throws MalformedURLException 
	 */
	public URLResource(String websiteURL, String contactMode, long lastModifiedTime, URL url) throws MalformedURLException {
		super();
		this.websiteURL = websiteURL;
		this.contactMode = contactMode;
		this.lastModifiedTime = lastModifiedTime;
		this.url = url;
		this.subject = PublishSubject.create();
	}

	public Subject<String> getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 */
	public void setSubject(Subject<String> subject) {
		this.subject = subject;
	}

	/**
	 * @param lastModifiedTime
	 */
	public void setLastModifiedTime(long lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getWebsiteURL() {
		return websiteURL;
	}

	public URL getUrl() {
		return url;
	}

	
	public String getContactMode() {
		return contactMode;
	}

	public long getLastModifiedTime() {
		return lastModifiedTime;
	}

	/**
	 * @param mode
	 */
	public void subScribeObserver(Observer<String> mode) {
		subject.subscribe(mode);
	}

	/**
	 * @throws IOException
	 */
	public void notifyObservers() throws IOException {
		long current = URLTimeTracker.getLastModified(url);
		//long current = new Date().getTime();
		if (current > lastModifiedTime) {
			lastModifiedTime = current;
			subject.onNext(websiteURL);
			subject.onComplete();
		}
	}
	
}
