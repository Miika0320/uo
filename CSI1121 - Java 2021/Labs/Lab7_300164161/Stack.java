import java.util.EmptyStackException;
public interface Stack<E> {

    public abstract boolean isEmpty();
    public abstract E peek()throws EmptyStackException;
    public abstract E pop()throws EmptyStackException;
    public abstract void push( E element);
    /* Add clear */
	public abstract void clear();
}