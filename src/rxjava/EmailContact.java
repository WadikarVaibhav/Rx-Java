package rxjava;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

class EmailContact implements Observer<String>  {
	
	private static final String SENDER = "rohitkulkarni8395@gmail.com";
	private final String receiver;
	
	public EmailContact(String email) {
		super();
		this.receiver = email;
	}
	
	public String getEmail() {
		return receiver;
	}

	@Override
	public void onComplete() {
		System.out.println("Notified to: "+receiver);
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
		EmailBroadcast sender = new EmailBroadcast(SENDER, "vaibhav123");
		boolean status = sender.send(receiver, "Change in " + url, "Changes");
		if (status) {
			System.out.println("Email sent to: "+receiver);				
		}
	}

	@Override
	public void onSubscribe(Disposable arg0) {
		// TODO Auto-generated method stub
		
	}

}
