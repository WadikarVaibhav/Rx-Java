package rxjava;

public class URLResource  {

	private final String url;
	private final String contactMode;
	private long lastModifiedTime;
	
	/**
	 * @param url
	 * @param contactMode
	 * @param lastModifiedTime
	 */
	public URLResource(String url, String contactMode, long lastModifiedTime) {
		super();
		this.url = url;
		this.contactMode = contactMode;
		this.lastModifiedTime = lastModifiedTime;
	}

	/**
	 * @param lastModifiedTime
	 */
	public void setLastModifiedTime(long lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getUrl() {
		return url;
	}

	public String getContactMode() {
		return contactMode;
	}

	public long getLastModifiedTime() {
		return lastModifiedTime;
	}
	
}
