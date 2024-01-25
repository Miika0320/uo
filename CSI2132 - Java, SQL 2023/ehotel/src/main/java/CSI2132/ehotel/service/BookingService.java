package CSI2132.ehotel.service;

import java.util.List;
import CSI2132.ehotel.entity.Booking;

public interface BookingService {
    List<Booking> getAllBookings();
	
	Booking saveBooking(Booking booking);
	
	Booking getBookingById(Long id);
	
	Booking updateBooking(Booking booking);
	
	void deleteBookingById(Long id);

	List<Booking> findByKeyword(String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String keyword6);
}
