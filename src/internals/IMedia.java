package internals;

public interface IMedia extends Comparable<IMedia> {
	/**
	 * Returns the name of the media
	 * 
	 * @return name
	 */
	public String mediaName();
	/**
	 * Returns the media type
	 * 
	 * @return type
	 */
	public String mediaType();
	/**
	 * Returns the price of the media
	 * 
	 * @return price
	 */
	public int mediaPrice();
	/**
	 * Returns the publish year of the media
	 * 
	 * @return year
	 */
	public int mediaYear(); 
	/**
	 * Check the media's price is equal or not
	 * 
	 * @param media - media to compare
	 * @return true if equal, false if not
	 */
	public boolean equals(IMedia media);

}
