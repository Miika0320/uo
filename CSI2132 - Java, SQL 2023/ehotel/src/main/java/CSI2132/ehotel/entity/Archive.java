package CSI2132.ehotel.entity;

import jakarta.persistence.Column;
// renamed from javax
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
@Table(name = "archive")
@Entity
public class Archive {
    
    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    public Long archive_id = (long) 0;

    @Column(name = "booking_id")
    public Long booking_id;

    @Column(name = "chain_id")
    public Long chain_id;

    @Column(name = "customer_id")
    public Long customer_id;
    
    @Column(name = "date_in")
    public Date date_in;

    @Column(name = "date_out")
    public Date date_out;

    @Column(name = "hotel_id")
    public Long hotel_id;

    @Column(name = "rental_id")
    public Long rental_id;

    @Column(name = "room_id")
    public Long room_id;


    public Archive(Date in, Date out, Long rental_id, Long booking_id, Long hotel_id, Long room_id, Long customer_id, Long chain_id) {
        super();
        this.date_in = in;
        this.date_out = out;
        this.rental_id = rental_id;
        this.booking_id = booking_id;
        this.hotel_id = hotel_id;
        this.room_id = room_id;
        this.customer_id = customer_id;
        this.chain_id = chain_id;
    }

    public Long getId(){
        return archive_id;
    }

    public void setId(Long id){
        this.archive_id = id;
    }

    public Archive(){}

    public Long getBookingId(){
        return booking_id;
    }

    public void setBookingId(Long book){
        booking_id = book;
    }

    public Long getChainId() {
        return chain_id;
    }

    public void setChainId(Long id) {
        chain_id = id;
    }

    public Long getRentalId(){
        return rental_id;
    }

    public void setRentalId(Long renting){
        rental_id = renting;
    }

    public Long getHotelId(){
        return hotel_id;
    }

    public void setHotelId(Long hotel){
        hotel_id = hotel;
    }

    public Long getRoomId(){
        return room_id;
    }

    public void setRoomId(Long room){
        room_id = room;
    }

    public Long getCustomerId(){
        return customer_id;
    }

    public void setCustomerId(Long cust){
        customer_id = cust;
    }

    public Date getDateIn(){
        return date_in;
    }

    public void setDateIn(Date in){
        date_in = in;
    }

    public Date getDateOut(){
        return date_out;
    }

    public void setDateOut(Date out){
        date_out = out;
    }
    
}
