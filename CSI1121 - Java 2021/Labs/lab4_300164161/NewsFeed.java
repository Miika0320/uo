/* *
 * Use static array for NewsFeed
 * with constant MAX_SIZE
 * */

public class NewsFeed {

    private Post[] messages;
    private int size;
    public static final int MAX_SIZE = 25;

    public NewsFeed() {
    	// Your code here.
		
		size = 0;
		
    }

    public void add(Post message) {
      // Your code here.
	  if (size<25){
		  Post[] messages = new Post[size+1];
		  for (int i = 0; i < size;i++){
			  messages[i] = this.messages[i];
		  }
		  messages[size] = message;
		  size+=1;
		  this.messages = messages;
	  }
	  
    }

    public Post get(int index) {
	     return messages[index];
    }

    public int size() {
	     return size;
    }

	public void sort(){
		int i, j, argMin;
		Post tmp;
		for (i = 0; i < size - 1; i++) {
			argMin = i;
			for (j = i + 1; j < size(); j++) {
				if (messages[j].compareTo(messages[argMin]) < 0) {
					argMin = j;
				}
			}

		tmp = messages[argMin];
		messages[argMin] = messages[i];
		messages[i] = tmp;
		}

	}

  	public NewsFeed getPhotoPost(){
  		// Your code here
		NewsFeed photos = new NewsFeed();
		for (int i = 0; i<size; i++){
			if (messages[i].getClass().getName()=="PhotoPost")
				photos.add(messages[i]);
		}
		return photos;
		
  	}

  	public NewsFeed plus(NewsFeed other){
  	  // Your code here
		NewsFeed both = new NewsFeed();
		Post[] message = new Post[size+other.size()];
		for (int i=0;i<size ;i++){
			message[i] = this.messages[i];
		}
		for (int j=0;j<other.size();j++){
			message[size+j] = other.messages[j];
		}
		  
		for (int k = 0; k<message.length; k++){
			both.add(message[k]);
		}
		both.sort();
		return both;
	}

}
