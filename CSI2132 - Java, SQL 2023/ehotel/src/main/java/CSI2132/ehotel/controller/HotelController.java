 package CSI2132.ehotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import CSI2132.ehotel.entity.Hotel;
import CSI2132.ehotel.service.HotelService;

// import org.springframework.stereotype.Controller;

// import CSI2132.ehotel.entity.Hotel;
// import CSI2132.ehotel.service.HotelService;

// import CSI2132.ehotel.entity.HotelChain;

 @Controller
 public class HotelController {
   
	
    private HotelService hotelService;

	public HotelController(HotelService hotelService) {
		super();
		this.hotelService = hotelService;
	}

    @GetMapping("/hotels")
	public String listHotels(Model model) {
		model.addAttribute("hotels", hotelService.getAllHotels());
		return "hotels";
	}

	@GetMapping("/hotels/new") // add
	public String createHotelForm(Model model) {
		Hotel hotel = new Hotel(null, 0, 0, null, null, null, 0);
		model.addAttribute("hotel", hotel);
		return "create_hotel";
	}

	@PostMapping("/hotels/") // save to repo?
	public String saveHotel(@ModelAttribute("hotels") Hotel hotel) {
		hotelService.saveHotel(hotel);
		return "redirect:/hotels";
	}

	@PostMapping("/hotels/edit/{id}")
	public String editHotelForm(@PathVariable Long id, Model model) {
		model.addAttribute("hotel", hotelService.getHotelById(id));
		return "edit_hotel";
	}

	@PostMapping("/hotels/{id}")
	public String updateHotel(@PathVariable Long id, @ModelAttribute("hotel") Hotel newhotel, Model model) {
		Hotel existingHotel = hotelService.getHotelById(id);
		existingHotel.setId(newhotel.getId());
		existingHotel.setName(newhotel.getName());
		existingHotel.setAddress(newhotel.getAddress());
		existingHotel.setEmail(newhotel.getName());
		existingHotel.setPhone(newhotel.getPhone());
        existingHotel.setNumRooms(0);
        existingHotel.setRating(0);
        existingHotel.setChainId(0);
        existingHotel.setManagerId(0);

		hotelService.updateHotel(existingHotel);
		return "redirect:/hotels";
	}

	@GetMapping("/hotels/{id}")
	public String deleteHotelchain(@PathVariable Long id) {
		hotelService.deleteHotelById(id);
		return "redirect:/hotels";
	}

}
