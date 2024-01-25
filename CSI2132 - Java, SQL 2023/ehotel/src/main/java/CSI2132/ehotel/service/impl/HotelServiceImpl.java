package CSI2132.ehotel.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import CSI2132.ehotel.entity.Hotel;

import CSI2132.ehotel.repository.HotelRepository;
import CSI2132.ehotel.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
    
    private HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotelById(Long id) {
		return hotelRepository.findById(id).get();
	}

	@Override
	public Hotel updateHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}

	@Override
	public void deleteHotelById(Long id) {
		hotelRepository.deleteById(id);	
	}
}
