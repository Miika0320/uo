package CSI2132.ehotel.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CSI2132.ehotel.entity.Renting;

public interface RentingRepository extends JpaRepository<Renting, Long> {
    @Query(value="select * from renting where CAST(renting.hotel_id as TEXT) like %:keywordr1% and CAST(renting.room_id as TEXT) like %:keywordr2% and CAST(renting.customer_id as TEXT) like %:keywordr3% and CAST(renting.date_in as TEXT) like %:keywordr4% and CAST(renting.date_out as TEXT) like %:keywordr5% and CAST(renting.payment as TEXT) like %:keywordr6%", nativeQuery=true)

    List<Renting> findByKeyword(@Param("keywordr1") String keywordr1, @Param("keywordr2") String keywordr2, @Param("keywordr3") String keywordr3,
    @Param("keywordr4") String keywordr4,@Param("keywordr5") String keywordr5,@Param("keywordr6") String keywordr6);
}
