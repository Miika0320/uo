package CSI2132.ehotel.service;

import java.util.List;
import CSI2132.ehotel.entity.HotelChain;

public interface HotelChainService {
    List<HotelChain> getAllHotelChains();
	
	HotelChain saveHotelChain(HotelChain hotelChain);
	
	HotelChain getHotelChainById(Long id);
	
	HotelChain updateHotelChain(HotelChain hotelChain);
	
	void deleteHotelChainById(Long id);
}
