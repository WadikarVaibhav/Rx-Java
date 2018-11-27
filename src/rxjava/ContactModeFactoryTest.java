package rxjava;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import io.reactivex.Observer;

public class ContactModeFactoryTest {

	private static final String EMAIL_ADDRESS = "vaibhavwadikar8@gmail.com";
	private static final String CONTACT_MODE_MAIL = "mail";
	private static final String CONTACT_MODE_PHONE = "sms";
	private static final String CONTACT_MODE_CONSOLE = "console";
	private static final String URL = "http://www.eli.sdsu.edu/courses/fall18/cs635/notes/index.html";
	private static final String PHONE = "6196065063";
	private static final String PHONE_CARRIER = "Sprint";

	@Test
	public void getContactModeEmailTest() {
		List<String> contactInformation = Arrays.asList(URL, CONTACT_MODE_MAIL, EMAIL_ADDRESS);
		Observer observer = ContactModeFactory.getContactMode(contactInformation);
		assertTrue(observer instanceof EmailContact);
	}

	@Test
	public void getContactModeConsoleTest() {
		List<String> contactInformation = Arrays.asList(URL, CONTACT_MODE_CONSOLE);
		Observer observer = ContactModeFactory.getContactMode(contactInformation);
		assertTrue(observer instanceof ConsoleContact);
	}

	@Test
	public void getContactModePhoneTest() {
		List<String> contactInformation = Arrays.asList(URL, CONTACT_MODE_PHONE, PHONE, PHONE_CARRIER);
		Observer observer = ContactModeFactory.getContactMode(contactInformation);
		assertTrue(observer instanceof PhoneContact);
	}

}
