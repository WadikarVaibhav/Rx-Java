package rxjava;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.reactivex.Observer;

class WebPageNotifierTest {

	private static final String SPEC = "index.html";
	private static final String EMAIL_ADDRESS = "vaibhavwadikar8@gmail.com";
	private static final String CONTACT_MODE_MAIL = "mail";
	private static final String WEBSITE_URL = "http://www.eli.sdsu.edu/courses/fall18/cs635/notes/index.html";
	private static final String PHONE = "6196065063";
	private static final String PHONE_CARRIER = "Sprint";
	private static final String CONTACT_MODE_CONSOLE = "console";
	private static final String CONTACT_MODE_PHONE = "sms";
	
	@Test
	public void getContactModeEmailTest() {
		List<String> contactInformation = Arrays.asList(WEBSITE_URL, CONTACT_MODE_MAIL, EMAIL_ADDRESS);
		Observer<String> observer = ContactModeFactory.getContactMode(contactInformation);
		assertTrue(observer instanceof EmailContact);
	}

	@Test
	public void getContactModeConsoleTest() {
		List<String> contactInformation = Arrays.asList(WEBSITE_URL, CONTACT_MODE_CONSOLE);
		Observer<String> observer = ContactModeFactory.getContactMode(contactInformation);
		assertTrue(observer instanceof ConsoleContact);
	}

	@Test
	public void getContactModePhoneTest() {
		List<String> contactInformation = Arrays.asList(WEBSITE_URL, CONTACT_MODE_PHONE, PHONE, PHONE_CARRIER);
		Observer<String> observer = ContactModeFactory.getContactMode(contactInformation);
		assertTrue(observer instanceof PhoneContact);
	}
	
	/**
	 * @param url
	 * @param index
	 * @param mockUrlConnection
	 * @return
	 * @throws MalformedURLException
	 */
	private URL getMockURL(String url, String index, URLConnection mockUrlConnection) throws MalformedURLException {
		URLStreamHandler urlStreamHandler = new URLStreamHandler() {
			@Override
			protected URLConnection openConnection(URL u) throws IOException {
				// TODO Auto-generated method stub
				return mockUrlConnection;
			}
		};
		return new URL(new URL(url), index, urlStreamHandler);
	}
	
	@Test
	public void testNotifyObserversWhenNoChangeInWebpage() throws IOException {
		URLConnection urlConnection = mock(URLConnection.class);
		when(urlConnection.getLastModified()).thenReturn(30L);
		URL mockURL = getMockURL(WEBSITE_URL, SPEC, urlConnection);
		URLResource urlResource = new URLResource(WEBSITE_URL, CONTACT_MODE_MAIL, 30L, mockURL);
		Observer<String> observer = mock(Observer.class);
		urlResource.subScribeObserver(observer);
		urlResource.notifyObservers();
		verify(observer, times(1)).onNext(mockURL.toString());
	}
	
	@Test
	public void testNotifyObserversWhenChangeInWebpage() throws IOException {
		URLConnection urlConnection = mock(URLConnection.class);
		when(urlConnection.getLastModified()).thenReturn(40L);
		URL mockURL = getMockURL(WEBSITE_URL, SPEC, urlConnection);
		URLResource urlResource = new URLResource(WEBSITE_URL, CONTACT_MODE_MAIL, 30L, mockURL);
		Observer<String> observer = mock(Observer.class);
		urlResource.subScribeObserver(observer);
		urlResource.notifyObservers();
		verify(observer, times(1)).onNext(mockURL.toString());
	}
	
	//@Test
	public void testNotifyObserversNullURL() throws IOException {
		URLConnection urlConnection = mock(URLConnection.class);
		when(urlConnection.getLastModified()).thenReturn(0L);
		URL mockURL = getMockURL(WEBSITE_URL, SPEC, urlConnection);
		URLResource urlResource = new URLResource(WEBSITE_URL, CONTACT_MODE_MAIL, 30L, mockURL);
		Observer<String> observer = mock(Observer.class);
		urlResource.subScribeObserver(observer);
		urlResource.notifyObservers();
		verify(observer, times(1)).onNext(mockURL.toString());
	}
	
}
