import java.util.NoSuchElementException;
public interface Map< K, V> {

    /* Make the necessary abstract method definitions */
	public V get(K key) throws NullPointerException, NoSuchElementException;
	public boolean contains(K key)throws NullPointerException;
	public void put(K key, V value)throws NullPointerException;
	public void replace(K key, V value)throws NullPointerException, NoSuchElementException;
	public V remove(K key)throws NullPointerException, NoSuchElementException;
}