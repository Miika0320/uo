package CSI2132.ehotel.entity;

import jakarta.persistence.Column;
// renamed from javax
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "booking")

    
   
public class Booking {

    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    public long booking_id =(long) 0;

    @Column(name = "customer_id", nullable = false)
    public Long customer_id;
    
    @Column(name = "date_in")
    public Date date_in;

    @Column(name = "date_out")
    public Date date_out;

    @Column(name = "hotel_id")
    public int hotel_id;

    @Column(name = "payment")
    public Double payment = null;

    @Column(name = "rental_id")
    public Long rental_id = null;

    @Column(name = "room_id")
    public Long room_id;

    public Booking(){

    }


    public Booking(Date in, Date out, Double pay) {
        super();
        this.date_in = in;
        this.date_out = out;
        this.payment = pay;
    }

    public Long getRentalId(){
        return rental_id;
    }

    public void setRentalId(Long rent){
        rental_id = rent;
    }

    public Long getBookingId(){
        return booking_id;
    }

    public void setBookingId(Long book){
        booking_id = book;
    }

    public int getHotel_id(){
        return hotel_id;
    }

    public void setHotel_id(int hotel){
        hotel_id = hotel;
    }

    public Long getRoom_id(){
        return room_id;
    }

    public void setRoom_id(Long room){
        room_id = room;
    }

    public Long getCustomer_id(){
        return customer_id;
    }

    public void setCustomer_id(Long cust){
        customer_id = cust;
    }

    public Date getDate_in(){
        return date_in;
    }

    public void setDate_in(Date in){
        date_in = in;
    }

    public Date getDate_out(){
        return date_out;
    }

    public void setDate_out(Date out){
        date_out = out;
    }

    public Double getPayment(){
        return payment;
    }

    public void setPayment(Double pay){
        payment = pay;
    }


}
