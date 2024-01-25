public class Book {

    // Your variables declaration here
	private String author;
	private String title;
	private int year;

    public Book (String author, String title, int year) {
        // Your code here
		this.author = author;
		this.title = title;
		this.year = year;
    }

    public String getAuthor() {
        // Your code here
		return author;
    }

    public String getTitle() {
        // Your code here
		return title;
    }

    public int getYear() {
        // Your code here
		return year;
    }

	@Override
    public boolean equals(Object other) {
        // Your code here
		if (this == other){
			return true;
		}
		if (other == null || getClass() != other.getClass()){
			return false;
		}
		
		Book b = (Book)other;
		if (author!=null&&b.getAuthor()!=null&&title!=null&&b.getTitle()!=null){
			return year == b.getYear() && author.equals(b.getAuthor()) && title.equals(b.getTitle());
		}
		else
			return year == b.getYear() && author == b.getAuthor() && title == b.getTitle();
    }

    public String toString() {
        // Your code here
		String str;
		str = "Author: "+author+". Title: "+title+" ("+Integer.toString(year)+").";
		return str;
    }
}