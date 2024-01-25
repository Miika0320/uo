package CSI2132.ehotel.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import CSI2132.ehotel.service.BookingService;
import CSI2132.ehotel.repository.BookingRepository;
import CSI2132.ehotel.entity.Booking;

@Service
public class BookingServiceImpl implements BookingService {
    
    private BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
		super();
		this.bookingRepository = bookingRepository;
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public Booking getBookingById(Long id) {
		return bookingRepository.findById(id).get();
	}

	@Override
	public Booking updateBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	@Override
	public void deleteBookingById(Long id) {
		bookingRepository.deleteById(id);	
	}

	@Override
	public List<Booking> findByKeyword(String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String keyword6){
		return bookingRepository.findByKeyword(keyword1, keyword2, keyword3, keyword4, keyword5, keyword6);
	}
}
