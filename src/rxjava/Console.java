package rxjava;

import io.reactivex.disposables.Disposable;

public class Console implements ContactMode {
	
	private final String url;
	
	/**
	 * @param url
	 */
	public Console(final String url) {
		super();
		this.url = url;
	}

	@Override
	public void getNotifications(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onComplete() {
		System.out.println("Notified to console");
	}

	@Override
	public void onError(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNext(URLResource urlResource) {
		System.out.println("Changes detected in " + urlResource.getUrl());
	}

	public String getUrl() {
		return url;
	}

	@Override
	public void onSubscribe(Disposable arg0) {
		// TODO Auto-generated method stub
		
	}

}
