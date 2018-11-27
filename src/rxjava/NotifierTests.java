package rxjava;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import org.junit.jupiter.api.Test;

import io.reactivex.Observer;
 

class NotifierTests {
	
	private static final String EMAIL_ADDRESS = "vaibhavwadikar8@gmail.com";
	private static final String CONTACT_MODE_MAIL = "mail";
	private static final String WEBSITE_URL = "http://www.eli.sdsu.edu/courses/fall18/cs635/notes/index.html";
	private static final String PHONE = "6196065063";
	private static final String PHONE_CARRIER = "Sprint";

	@Test
	public void testNotifyObserversNoChangeInWebsite() throws Exception {
		Notifier notifier = new Notifier();
		URLConnection urlConnection = mock(URLConnection.class);
		URL websiteURL = mock(URL.class);
		when(websiteURL.openConnection()).thenReturn(urlConnection);
		when(urlConnection.getLastModified()).thenReturn(30L).thenReturn(30L);
		@SuppressWarnings("rawtypes")
		Observer observer = mock(Observer.class);
		URLResource urlResource = new URLResource(WEBSITE_URL, CONTACT_MODE_MAIL, 30L);
		notifier.subscribeObservable(urlResource);
		notifier.subscribe(observer);
		notifier.notifyObservers();
	}
	
}
