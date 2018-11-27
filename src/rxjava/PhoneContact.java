package rxjava;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PhoneContact implements Observer<URLResource>  {
	
	private final String phoneNumber;
	private final String serviceProvider;
	private final String url;
	
	/**
	 * @param phoneNumber
	 * @param serviceProvider
	 * @param url
	 */
	public PhoneContact(String phoneNumber, String serviceProvider, String url) {
		super();
		this.phoneNumber = phoneNumber;
		this.serviceProvider = serviceProvider;
		this.url = url;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public String getUrl() {
		return url;
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
		EmailBroadcast sender = new EmailBroadcast("rohitkulkarni8395@gmail.com", "vaibhav123");
		if (urlResource.getUrl().equals(url)) {
//			boolean status = sender.send(phoneNumber.concat(PhoneCarriers.getCarrierEmail(serviceProvider)), "Changes in "+url, "Testing changes");
//			if (status) {
//				System.out.println("SMS sent to: "+phoneNumber);				
//			}
		}
	}

	@Override
	public void onSubscribe(Disposable arg0) {
		// TODO Auto-generated method stub
		
	}

}
