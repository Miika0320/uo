public class Dictionary implements Map<String, Integer> {

    private final static int INITIAL_CAPACITY = 10;
    private final static int INCREMENT = 5;
    private int count;

    private Pair[] elems;

    public int getCount() {
      return count;
    }

    public int getCapacity() {
      return elems.length;
    }

    public Dictionary() {
        /* Your code here */
		count = 0;
		elems = new Pair[ INITIAL_CAPACITY ];
		
    }

    @Override
    public void put(String key, Integer value) {
        /* Your code here */
		if (count+1 < elems.length-1)
			elems [count++] = new Pair (key,value);
		else{
			increaseCapacity();
			elems[ count++ ] = new Pair (key,value);
		}
    }

    private void increaseCapacity() {
        /* Your code here.  Use this in put() where necessary. */
		Pair[] temp = new Pair[elems.length+5];
			for (int i = 0;i<elems.length; i++){
				temp[i] = elems[i];
			}
			elems = temp;
    }

    @Override
    public boolean contains(String key) {
        /* Your code here. */
		for (int i = 0; i < count; i++)
			if (elems[i].getKey() == key)
				return true;
			
		return false;
    }

    @Override
    public Integer get(String key) {
        /* Your code here. */
		for (int i = count-1; i >=0; i--)
			if (elems[i].getKey() == key)
				return elems[i].getValue();
		return null;
    }

    @Override
    public void replace(String key, Integer value) {
        /* Your code here. */
		for (int i = count-1; i >=0; i--)
			if (elems[i].getKey() == key){
				elems[i].setValue(value);
				break;
			}
    }

    @Override
    public Integer remove(String key) {
        /* Your code here. */
		for (int i = count-1; i >= 0; i--)
			if (elems[i].getKey() == key){
				Pair empty = elems[i];
				Pair[] temp = new Pair[elems.length];
				for (int j=0;j<count;j++)
					if (j<i)
						temp[j] = elems[j];
					else
						temp[j] = elems[j+1];
				elems = temp;
				count -=1;
				return empty.getValue();
			}
		return null;
				
    }

    @Override
    public String toString() {
      String res;
      res = "Dictionary: {elems = [";
      for (int i = count-1; i >= 0 ; i--) {
          res += elems[i];
          if(i > 0) {
              res += ", ";
          }
      }
      return res +"]}";
    }

}