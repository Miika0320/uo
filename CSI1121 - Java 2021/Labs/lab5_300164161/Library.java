import java.util.ArrayList;

public class Library {

    private ArrayList<Book> library = new ArrayList<Book>();

    public Book getBook(int i) {
      return library.get(i);
    }

    public int getSize() {
      return library.size();
    }

    public void addBook (Book b) {
        // Your code here
		library.add(b);
    }

    public void sort() {
        // Your code here
		int bool;
		Book temp = new Book("m","m",0);
		BookComparator bc = new BookComparator();
		for (int i = 0; i<library.size()-1; i++){
			for (int j = 0; j<library.size()-1-i; j++){
				bool = bc.compare(library.get(j), library.get(j+1));
				if (bool > 0)
					temp = library.get(j);
					library.set(j, library.get(j+1));
					library.set(j+1, temp);
			}
		}
    }


    public void printLibrary() {
        // Your code here
		for (int i = 0; i<library.size(); i++){
			System.out.println(library.get(i));
		}
    }
}