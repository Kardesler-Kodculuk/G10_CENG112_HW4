package internals;

public interface IMedia<T extends Comparable<? super T>> {
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

}
