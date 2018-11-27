package rxjava;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

class EmailContact implements Observer<URLResource>  {
	
	private final String email;
	private final String url;
	
	public EmailContact(String email, String url) {
		super();
		this.email = email;
		this.url = url;
	}
	
	public String getEmail() {
		return email;
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
		EmailBroadcast sender = new EmailBroadcast("rohitkulkarni8395@gmail.com", "vaibhav123");
		if (url.equals(urlResource.getUrl())) {
//			boolean status = sender.send(email, "Change in " + url, "Changes");
//			if (status) {
//				System.out.println("Email sent to: "+email);				
//			}
		}
	}

	@Override
	public void onSubscribe(Disposable arg0) {
		// TODO Auto-generated method stub
		
	}

}
