package CSI2132.ehotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CSI2132.ehotel.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query(value="select * from booking where CAST(booking.hotel_id as TEXT) like %:keywordb1% and CAST(booking.room_id as TEXT) like %:keywordb2% and CAST(booking.customer_id as TEXT) like %:keywordb3% and CAST(booking.date_in as TEXT) like %:keywordb4% and CAST(booking.date_out as TEXT) like %:keywordb5% and CAST(booking.payment as TEXT) like %:keywordb6%", nativeQuery=true)

    List<Booking> findByKeyword(@Param("keywordb1") String keywordb1, @Param("keywordb2") String keywordb2, @Param("keywordb3") String keywordb3,
    @Param("keywordb4") String keywordb4,@Param("keywordb5") String keywordb5,@Param("keywordb6") String keywordb6);
}
