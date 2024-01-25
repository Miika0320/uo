import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    // Implement the comparator method for books.
	public int compare(Book a, Book b){
		
		char aa = a.getAuthor().charAt(0);
		char ba = b.getAuthor().charAt(0);
		char at = a.getTitle().charAt(0);
		char bt = b.getTitle().charAt(0);
		
		if(a.getAuthor().equals(b.getAuthor()))
			if (a.getTitle().equals(b.getTitle()))
				if (a.getYear()<b.getYear())
					return -1;
				else if (a.getYear()>b.getYear())
					return 1;
				else
					return 0;
			else if (Character.compare(at,bt)<0)
				return -1;
			else if (Character.compare(at,bt)>0)
				return 1;
		if (Character.compare(aa,ba)<0)
			return -1;
		else
			return 1;
		
	}

}