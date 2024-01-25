package CSI2132.ehotel.service;

import java.util.List;
import CSI2132.ehotel.entity.Hotel;

public interface HotelService {
    List<Hotel> getAllHotels();
	
	Hotel saveHotel(Hotel hotel);
	
	Hotel getHotelById(Long id);
	
	Hotel updateHotel(Hotel hotel);
	
	void deleteHotelById(Long id);
}
