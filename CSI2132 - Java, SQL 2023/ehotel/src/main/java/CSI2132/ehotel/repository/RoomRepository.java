package CSI2132.ehotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import CSI2132.ehotel.entity.Room;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {


    @Query(value="select * from rooms where rooms.view_type like %:keyword1% and rooms.problems like %:keyword2% and rooms.amenities like %:keyword3% and rooms.extendability like %:keyword4% and CAST(rooms.capacity as TEXT) like %:keyword5% and CAST(rooms.hotel_id as TEXT) like %:keyword6% and CAST(rooms.price as TEXT) like %:keyword7%", nativeQuery=true)

    List<Room> findByKeyword(@Param("keyword1") String keyword1, @Param("keyword2") String keyword2, @Param("keyword3") String keyword3,
    @Param("keyword4") String keyword4,@Param("keyword5") String keyword5,@Param("keyword6") String keyword6, @Param("keyword7") String keyword7 );

    
}
