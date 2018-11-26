package rxjava;

import io.reactivex.disposables.Disposable;

public class Email implements ContactMode {
	
	private final String email;
	private final String url;
	
	public Email(String email, String url) {
		super();
		this.email = email;
		this.url = url;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public void getNotifications(Object obj) {
		
	}

	@Override
	public void onComplete() {
		System.out.println("Notified to: "+email);
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
		if (url.equals(urlResource.getUrl())) {
			Broadcast mailer = new Broadcast("rohitkulkarni8395@gmail.com", "vaibhav123");
			boolean status = mailer.send(email, "Change in " + url, "Changes");
		}
	}

	@Override
	public void onSubscribe(Disposable arg0) {
		// TODO Auto-generated method stub
		
	}

}
