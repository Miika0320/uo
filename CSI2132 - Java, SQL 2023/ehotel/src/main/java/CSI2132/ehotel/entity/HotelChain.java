package CSI2132.ehotel.entity;

import jakarta.persistence.Column;
// renamed from javax
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotel_chain")
public class HotelChain {

    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    public Long chain_id = (long) 0;
 
    @Column(name = "address")
    public String address;

    @Column(name = "chainName")
    public String chainName;
   
    @Column(name = "email")
    public String email;
    
    @Column(name = "numHotels")
    public int numHotels;
    
    @Column(name = "phone")
    public String phone;

    public HotelChain(String name, String address, int numHotels, String email, String phone) {
        super();
        this.chainName = name;
        this.numHotels = numHotels;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public HotelChain(){}

    public Long getId() {
		return chain_id;
	}
	public void setId(Long id) {
		this.chain_id = id;
	}
	public String getName() {
		return chainName;
	}
	public void setChainName(String name) {
		this.chainName = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    public int getNumHotels() {
        return numHotels;
    }
    public void setNumHotels(int num) {
        this.numHotels = num;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
	public String getPhone() {
		return phone;
	}

}