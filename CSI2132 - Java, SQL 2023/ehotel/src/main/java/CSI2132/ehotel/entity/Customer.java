 package CSI2132.ehotel.entity;

import java.sql.Date;

import jakarta.persistence.Column;
// // renamed from javax
 import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// /* ******************************************************** 

// Title: Java class for customer entity in ehotel system
// Course: CSI2132 Databases I
// Author: Jodi Qiao
// Email: dqiao100@uottawa.ca
// Instructor: Wail Mardini

// */ 

 @Entity
@Table(name = "customers")
public class Customer {
    
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public long customer_id = (long)0;

	@Column(name = "address")
	public String address;

	@Column(name = "name")
	public String name;

	@Column(name = "password")
	public String password;

	@Column(name = "registration_date")
	public Date registration_date;

	@Column(name = "sin")
	public String sin;


     public Customer(String name, String address, String sin, Date date, String password) {
        super();
         this.name = name;
         this.address = address;
         this.sin = sin;
 		 this.registration_date = date;
         this.password = password;
     }

     public Customer() {
    }

    public long getId() {
 		return customer_id;
 	}
 	public void setId(int id) {
 		this.customer_id = id;
 	}

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
	public long getCustomerId() {
		return  customer_id;
	}
	public void setCustomerId(long id) {
		this.customer_id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFirstName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    public String getSin() {
        return sin;
    }
    public void setSin(String sin) {
        this.sin = sin;
     }
 	public Date getRegistration_date() {
 		return registration_date;
 	}
 	public void setRegistration_date(Date date) {
 		this.registration_date = date;
 	}
 }
