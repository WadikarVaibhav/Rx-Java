package rxjava;
import java.util.List;

import io.reactivex.Observer;

public class ContactModeFactory {
	
	private static final String CONSOLE = "console";
	private static final String SMS = "sms";
	private static final String MAIL = "mail";

	/**
	 * @param contactDetails
	 * contactDetails.get(0) represents URL
	 * contactDetails.get(1) represents mode of contact
	 * contactDetails.get(2) represents contact details Email address if mode is mail, phone number if mode is SMS
	 * contactDetails.get(3) represents phone number carrier in case of SMS as a contact mode
	 * @return
	 */
	public static Observer<URLResource> getContactMode(List<String> contactDetails) {
		
		
		String url = String.valueOf(contactDetails.get(0));
		String contactMode = String.valueOf(contactDetails.get(1));
		
		switch (contactMode) {
			case MAIL: {
				String receiverEmail = String.valueOf(contactDetails.get(2));
				return new EmailContact(receiverEmail, url);			
			}
			case SMS: {
				String phoneNumber = String.valueOf(contactDetails.get(2));
				String carrier = String.valueOf(contactDetails.get(3));
				return new PhoneContact(phoneNumber, carrier, url);
			}
			case CONSOLE: {
				return new ConsoleContact(url);
			}
			default: {
				return null;
			}
		}
	}
}