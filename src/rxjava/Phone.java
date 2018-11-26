package rxjava;

import io.reactivex.disposables.Disposable;

public class Phone implements ContactMode {
	
	private final String phoneNumber;
	private final String serviceProvider;
	private final String url;
	
	/**
	 * @param phoneNumber
	 * @param serviceProvider
	 * @param url
	 */
	public Phone(String phoneNumber, String serviceProvider, String url) {
		super();
		this.phoneNumber = phoneNumber;
		this.serviceProvider = serviceProvider;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	@Override
	public void getNotifications(Object obj) {
		// TODO Auto-generated method stub
		
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
	public void onNext(URLResource urlResource) {
		if (urlResource.getUrl().equals(url)) {
			Broadcast mailer = new Broadcast("rohitkulkarni8395@gmail.com", "vaibhav123");
			boolean smsStatus = mailer.send(phoneNumber.concat(getEmailIds(serviceProvider)), "Changes in "+url, "Testing changes");
		}
	}

	@Override
	public void onSubscribe(Disposable arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @param subScriber
	 * @return
	 */
	private String getEmailIds(String subScriber) {
		switch (subScriber) {
			case "Verizon": {
				return "@vzwpix.com";
			}
			case "Sprint": {
				return "@pm.sprint.com";
			}
			case "T-Mobile": {
				return "@tmomail.net";
			}
			case "AT&T": {
				return "mms.att.net";
			}
			case "Cricket Wireless": {
				return "@mms.cricketwireless.net";
			}
			case "U.S. Cellular": {
				return "@mms.uscc.net";
			}
			default: {
				return null;
			}
		}
	}

}
