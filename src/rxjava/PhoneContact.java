package rxjava;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PhoneContact implements Observer<String>  {
	
	private final String phoneNumber;
	private final String serviceProvider;
	private static final Map<String, String> PHONE_CARRIERS = new HashMap<>();
	
	static {
		PHONE_CARRIERS.put("Verizon", "@vzwpix.com");
		PHONE_CARRIERS.put("Sprint", "@pm.sprint.com");
		PHONE_CARRIERS.put("T-Mobile", "@tmomail.net");
		PHONE_CARRIERS.put("AT&T", "@mms.att.net");
		PHONE_CARRIERS.put("Cricket Wireless", "@mms.cricketwireless.net");
		PHONE_CARRIERS.put("U.S. Cellular", "@mms.uscc.net");
	}
	
	/**
	 * @param carrier
	 * @return
	 */
	public String getCarrierEmail(String carrier) {
		if (PHONE_CARRIERS.get(carrier) != null) {
			return PHONE_CARRIERS.get(carrier);
		}
		return null;
	}
	
	/**
	 * @param phoneNumber
	 * @param serviceProvider
	 * @param url
	 */
	public PhoneContact(String phoneNumber, String serviceProvider) {
		super();
		this.phoneNumber = phoneNumber;
		this.serviceProvider = serviceProvider;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	@Override
	public void onComplete() {
		System.out.println("Notified to: "+phoneNumber);
	}

	@Override
	public void onError(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see io.reactivex.Observer#onNext(java.lang.Object)
	 */
	@Override
	public void onNext(String url) {
		EmailBroadcast sender = new EmailBroadcast("rohitkulkarni8395@gmail.com", "vaibhav123");
		boolean status = sender.send(phoneNumber.concat(getCarrierEmail(serviceProvider)), "Changes in "+url, "Testing changes");
		if (status) {
			System.out.println("SMS sent to: "+phoneNumber);				
		}
	}

	@Override
	public void onSubscribe(Disposable arg0) {
		// TODO Auto-generated method stub
		
	}

}
