import java.util.Calendar;
import java.util.Date;

public class Post implements Likeable, Comparable<Post> {

    protected int likes;
    private Date timeStamp;
    private String userName;

    public Post(String userName) {
      // Your code here
	  this.userName = userName;
	  timeStamp = java.util.Calendar.getInstance().getTime();
	  likes = 0;
    }

    public String getUserName() {
	     return userName;
    }

    public Date getTimeStamp() {
	     return timeStamp;
    }

    // Implement the methods required by the interface Likeable.
    // This file will not compile unless they are present with the correct name and signature.

	public void like(){
		likes +=1;
	}
	
	public int getLikes(){
		return likes;
	}
	
    public String toString() {
    	String str = new String();
    	str = getClass().getName() + ": " + timeStamp + ", " + userName + ", likes = " + likes;
    	return  str;
    }


  	public int compareTo(Post other){
  		// Your code here.
		if (timeStamp.before(other.timeStamp)){
			return -1;
		}
		else if (timeStamp.equals(other.timeStamp)){
			return 0;
		}
		else
			return 1;
		
  	}

  	public boolean isPopular(){
  		// Your code here.
		return likes>100;
  	}

}
