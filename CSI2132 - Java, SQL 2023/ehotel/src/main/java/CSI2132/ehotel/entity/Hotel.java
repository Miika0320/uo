package CSI2132.ehotel.entity;

// // renamed from javax
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

 @Entity
 @Table(name = "hotel")
 public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int hotel_id;
    
    @Column(name = "chain_id")
    public int chain_id;
    
    @Column(name = "address")
    public String address;
    
    @Column(name = "email")
    public String email;

    @Column(name = "hotelName")
    public String hotelName;
     
    @Column(name = "managerId")
    public int managerId;

    @Column(name = "numRooms")
    public int numRooms;
     
    @Column(name = "phone")
    public String phone;

    @Column(name = "rating")
    public int rating;
    

    public Hotel(String name, int numRooms, int rating, String address, String email, String phone, int id) {
        super();
        this.hotelName = name;
        this.numRooms = numRooms;
        this.rating = rating;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.hotel_id = id;
    }

    public String getName() {
		return hotelName;
	}
	public void setName(String name) {
		this.hotelName = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    public int getNumRooms() {
        return numRooms;
    }
    public void setNumRooms(int rooms) {
        this.numRooms = rooms;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getManagerId() {
        return managerId;
    }
    public void setManagerId(int id) {
        this.managerId = id;
    }

    public int getChainId() {
        return chain_id;
    }
    public void setChainId(int id) {
        this.chain_id = id;
    }

    public int getId() {
        return hotel_id;
    }
    public void setId(int id) {
        this.hotel_id = id;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rate) {
        this.rating = rate;
    }
}


