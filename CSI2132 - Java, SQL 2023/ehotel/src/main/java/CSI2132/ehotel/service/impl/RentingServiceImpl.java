package CSI2132.ehotel.service.impl;

import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import CSI2132.ehotel.service.RentingService;
import CSI2132.ehotel.entity.Renting;
import CSI2132.ehotel.repository.RentingRepository;

@Service
public class RentingServiceImpl implements RentingService {
    
    private RentingRepository rentingRepository;

    public RentingServiceImpl(RentingRepository rentingRepository) {
		super();
		this.rentingRepository = rentingRepository;
	}

	@Override
	public List<Renting> getAllRentings() {
		return rentingRepository.findAll();
	}

	@Override
	public Renting saveRenting(Renting renting) {
		return rentingRepository.save(renting);
	}

	@Override
	public Renting getRentingById(Long id) {
		return rentingRepository.findById(id).get();
	}

	@Override
	public Renting updateRenting(Renting renting) {
		return rentingRepository.save(renting);
	}

	@Override
	public void deleteRentingById(Long id) {
		rentingRepository.deleteById(id);	
	}

	@Override
	public List<Renting> findByKeyword(String keywordr1, String keywordr2, String keywordr3, String keywordr4, String keywordr5, String keywordr6){
		return rentingRepository.findByKeyword(keywordr1, keywordr2, keywordr3, keywordr4, keywordr5, keywordr6);
	}
}
