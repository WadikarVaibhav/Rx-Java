package rxjava;
import java.util.List;

public class ContactModeFactory {
	
	/**
	 * @param contactDetails
	 * @return
	 */
	public static ContactMode getContactMode(List contactDetails) {
		
		String mode = String.valueOf(contactDetails.get(1));
		
		switch (mode) {
			case "mail": {
				return new Email(String.valueOf(contactDetails.get(2)), String.valueOf(contactDetails.get(0)));			
			}
			case "sms": {
				return new Phone(String.valueOf(contactDetails.get(2)), String.valueOf(contactDetails.get(3)), String.valueOf(contactDetails.get(0)));
			}
			case "console": {
				return new Console(String.valueOf(contactDetails.get(0)));
			}
			default: {
				return null;
			}
		}
	}
}