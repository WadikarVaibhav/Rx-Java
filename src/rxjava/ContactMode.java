package rxjava;

import io.reactivex.Observer;

public interface ContactMode extends Observer<URLResource> {
	public void getNotifications(Object obj);
}
