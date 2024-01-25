package CSI2132.ehotel.entity;

import org.springframework.core.style.ToStringCreator;

import jakarta.persistence.*;


// // renamed from javax
// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;


@Entity
@Table(name = "rooms")
public class Room {
    
    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	
	//@GeneratedValue(strategy = GenerationType.AUTO)
    public Long room_id =(long) 3 ;

    @Column(name = "capacity", nullable = true)
    public int capacity;

    @Column(name = "hotel_id")
    public int hotel_id;

    @Column(name = "amenities")
    public String amenities;

    @Column(name = "view_type")
    public String view_type = "mountain";

    @Column(name = "problems")
    public String problems;

    @Column(name = "extendability")
    public String extendability;

    @Column(name = "price", nullable = false)
    public double price;

   
	public Room(){

	}

    public Room(int capacity, int hotel_id, String amenities, String view_type, String problems, String extendability, double price) {
         super();
        
         this.capacity = capacity;
		 this.hotel_id = hotel_id;
		 this.amenities = amenities;
		 this.view_type = view_type;
		 this.problems = problems;
         this.extendability = extendability;
		 this.price = price;
      
     }
     public Long getId() {
		return room_id;
	}

	public void setId(Long room_id){
		this.room_id=room_id;
	}

	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price = price;
	}

	public int getHotel_id(){
		return hotel_id;
	}
	public void setHotel_id(int hotel_id){
		this.hotel_id=hotel_id;

	}

	public int getCapacity(){
		return capacity;
	}
	public void setCapacity(int capacity){
		this.capacity=capacity;
	}

	public String getAmenities(){
		return amenities;
	}
	public void setAmenities(String amenities){
		this.amenities=amenities;
	}

	public String getView_type(){
		return view_type;
	}
	public void setView_type(String view_type){
		this.view_type=view_type;
	}

	public String getProblems(){
		return problems;
	}
	public void setProblems(String problems){
		this.problems=problems;
	}

	public String getExtendability(){
		return extendability;
	}
	public void setExtendability(String extendability){
		this.extendability=extendability;
	}

	public String toString(){//overriding the toString() method  
		return view_type + " ";  
	   }  

	


 }
