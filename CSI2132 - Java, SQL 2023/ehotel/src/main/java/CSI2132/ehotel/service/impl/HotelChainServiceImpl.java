package CSI2132.ehotel.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import CSI2132.ehotel.entity.HotelChain;

import CSI2132.ehotel.repository.HotelChainRepository;
import CSI2132.ehotel.service.HotelChainService;

@Service
public class HotelChainServiceImpl implements HotelChainService {
    
    private HotelChainRepository hotelChainRepository;

    public HotelChainServiceImpl(HotelChainRepository hotelChainRepository) {
		super();
		this.hotelChainRepository = hotelChainRepository;
	}

	@Override
	public List<HotelChain> getAllHotelChains() {
		return hotelChainRepository.findAll();
	}

	@Override
	public HotelChain saveHotelChain(HotelChain hotelchain) {
		return hotelChainRepository.save(hotelchain);
	}

	@Override
	public HotelChain getHotelChainById(Long id) {
		return hotelChainRepository.findById(id).get();
	}

	@Override
	public HotelChain updateHotelChain(HotelChain hotelchain) {
		return hotelChainRepository.save(hotelchain);
	}

	@Override
	public void deleteHotelChainById(Long id) {
		hotelChainRepository.deleteById(id);	
	}

}
