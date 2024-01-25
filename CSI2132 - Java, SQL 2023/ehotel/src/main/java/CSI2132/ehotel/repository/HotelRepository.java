package CSI2132.ehotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import CSI2132.ehotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    
}
