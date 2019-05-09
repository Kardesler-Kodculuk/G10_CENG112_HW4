package internals;

public class Book implements IMedia {

	private static final String type = "Book";
	private String bookName;
	private int price;
	private int publishYear;
	private String authorName;

	@Override
	public String toString() {
		return "Book [type=" + type + ", bookName=" + bookName + ", price=" + price + ", publishYear=" + publishYear
				+ ", authorName=" + authorName + "]";
	}

	/**
	 * Default constructor for Book class
	 * 
	 * @param bookname - name of the book
	 * @param price - price of the book
	 * @param publishYear - publish year of the book
	 * @param authorName - name of the book's author
	 */
	public Book(String bookname, int price, int publishYear, String authorName) {
		this.bookName = bookname;
		this.price = price;
		this.publishYear = publishYear;
		this.authorName = authorName;
	}

	/**
	 * Returns the name of the author
	 * 
	 * @return name surname
	 */
	public String getAuthor() {
		return this.authorName;
	}

	@Override
	public String mediaName() {
		return this.bookName;
	}

	@Override
	public String mediaType() {
		return Book.type;
	}

	@Override
	public int mediaPrice() {
		return this.price;
	}

	@Override
	public int mediaYear() {
		return this.publishYear;
	}

	@Override
	public int compareTo(IMedia o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
