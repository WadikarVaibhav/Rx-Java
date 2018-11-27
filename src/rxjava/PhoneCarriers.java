package rxjava;

import java.util.HashMap;
import java.util.Map;

public class PhoneCarriers {

	private static final Map<String, String> PHONE_CARRIER = new HashMap<>();
	
	static {
		PHONE_CARRIER.put("Verizon", "@vzwpix.com");
		PHONE_CARRIER.put("Sprint", "@pm.sprint.com");
		PHONE_CARRIER.put("T-Mobile", "@tmomail.net");
		PHONE_CARRIER.put("AT&T", "@mms.att.net");
		PHONE_CARRIER.put("Cricket Wireless", "@mms.cricketwireless.net");
		PHONE_CARRIER.put("U.S. Cellular", "@mms.uscc.net");
	}
	
	public static String getCarrierEmail(String carrier) {
		if (PHONE_CARRIER.get(carrier) != null) {
			return PHONE_CARRIER.get(carrier);
		}
		return null;
	}
	
}
