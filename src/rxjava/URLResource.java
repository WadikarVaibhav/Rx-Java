package rxjava;

import java.util.Date;

public class URLResource  {

	private final String url;
	private final String contactMode;
	private Date lastModifiedTime;
	
	/**
	 * @param url
	 * @param contactMode
	 * @param lastModifiedTime
	 */
	public URLResource(String url, String contactMode, Date lastModifiedTime) {
		super();
		this.url = url;
		this.contactMode = contactMode;
		this.lastModifiedTime = lastModifiedTime;
	}

	/**
	 * @param lastModifiedTime
	 */
	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public String getUrl() {
		return url;
	}

	public String getContactMode() {
		return contactMode;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}
	
}
