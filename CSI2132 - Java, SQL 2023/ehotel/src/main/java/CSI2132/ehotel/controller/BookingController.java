package CSI2132.ehotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import CSI2132.ehotel.entity.Booking;
import CSI2132.ehotel.service.BookingService;

@Controller
public class BookingController {
    
    private BookingService bookingService;

	public BookingController(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}

	@RequestMapping("/bookings")
      public String bookings(){
          return "bookings";
      }

	  @GetMapping("/bookings")
	  public String listbookings(Model model, String keywordb1, String keywordb2, String keywordb3, String keywordb4, String keywordb5, String keywordb6) {
  
		if(keywordb1 != null && keywordb2 !=null){
		  model.addAttribute("bookings", bookingService.findByKeyword(keywordb1, keywordb2, keywordb3, keywordb4, keywordb5, keywordb6));
		}else{
		  model.addAttribute("bookings", bookingService.getAllBookings());
	   
  
		}
		return "bookings";

	  }
	@GetMapping("/createBooking") // add
	public String createBookingForm(Model model) {
		Booking booking = new Booking();
		model.addAttribute("booking", booking);
		return "createBooking";
	}

	@PostMapping("/bookings") // save to repo?
	public String saveBooking(@ModelAttribute("booking") Booking booking) {
		bookingService.saveBooking(booking);
		return "redirect:/bookings";
	}

	@GetMapping("/editBooking/{booking_id}")
	public String editBookingForm(@PathVariable Long booking_id, Model model) {
		model.addAttribute("booking", bookingService.getBookingById(booking_id));
		return "editBooking";
	}

	@PostMapping("/bookings/{booking_id}")
	public String updateBooking(@PathVariable Long booking_id, @ModelAttribute("booking") Booking newbooking, Model model) {
		Booking existingBooking = bookingService.getBookingById(booking_id);
		existingBooking.setBookingId(booking_id);
        existingBooking.setRentalId(newbooking.getRentalId());
		existingBooking.setCustomer_id(newbooking.getCustomer_id());
		existingBooking.setDate_in(newbooking.getDate_in());
		existingBooking.setDate_out(newbooking.getDate_out());
		existingBooking.setPayment(newbooking.getPayment());
        existingBooking.setHotel_id(newbooking.getHotel_id());
        existingBooking.setRoom_id(newbooking.getRoom_id());

		bookingService.updateBooking(existingBooking);
		return "redirect:/bookings";
	}

	@GetMapping("/bookings/{booking_id}")
	public String deleteBookingString(@PathVariable Long id) {
		bookingService.deleteBookingById(id);
		return "redirect:/bookings";
	}
}