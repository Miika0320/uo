package CSI2132.ehotel.service;

import java.sql.Date;
import java.util.List;
import CSI2132.ehotel.entity.Renting;

public interface RentingService {
    List<Renting> getAllRentings();
	
	Renting saveRenting(Renting renting);
	
	Renting getRentingById(Long id);
	
	Renting updateRenting(Renting renting);
	
	void deleteRentingById(Long id);

	List<Renting> findByKeyword(String keywordr1, String keywordr2, String keywordr3, String keywordr4, String keywordr5, String keywordr6);
}
