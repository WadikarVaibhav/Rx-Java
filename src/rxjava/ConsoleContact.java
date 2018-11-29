package rxjava;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ConsoleContact implements Observer<String> {
	
	/**
	 * @param url
	 */
	public ConsoleContact() {
		super();
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
	public void onNext(String url) {
		System.out.println("Changes detected in " + url);
	}

	@Override
	public void onSubscribe(Disposable arg0) {
		// TODO Auto-generated method stub
		
	}

}
