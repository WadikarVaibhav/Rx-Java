package rxjava;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class URLTimeTracker {
	
	/**
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static long getLastModified(URL url) throws IOException {
		if (url != null) {
			URLConnection con = url.openConnection();
	        return con.getLastModified();
		}
		return 0L;
	}
	
}
