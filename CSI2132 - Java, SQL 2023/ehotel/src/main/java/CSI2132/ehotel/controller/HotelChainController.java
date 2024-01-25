package CSI2132.ehotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import CSI2132.ehotel.entity.HotelChain;
import CSI2132.ehotel.service.HotelChainService;

@Controller
public class HotelChainController {
    
	private HotelChainService hotelChainService;

	public HotelChainController(HotelChainService hotelChainService) {
		super();
		this.hotelChainService = hotelChainService;
	}

    @GetMapping("/hotelchains")
	public String listHotelChains(Model model) {
		model.addAttribute("hotelChains", hotelChainService.getAllHotelChains());
		return "hotelchains";
	}

	@GetMapping("/hotelchains/new") // add
	public String createHotelChainForm(Model model) {
		HotelChain hotelchain = new HotelChain(null, null, 0, null, null);
		model.addAttribute("hotelchain", hotelchain);
		return "create_hotelchain";
	}

	@PostMapping("/hotelchains/") // save to repo?
	public String saveHotelChain(@ModelAttribute("hotelchains") HotelChain hotelChain) {
		hotelChainService.saveHotelChain(hotelChain);
		return "redirect:/hotelchains";
	}

	@PostMapping("/hotelchains/edit/{id}")
	public String editHotelChainForm(@PathVariable Long id, Model model) {
		model.addAttribute("hotelchain", hotelChainService.getHotelChainById(id));
		return "edit_hotelchain";
	}

	@PostMapping("/hotelchains/{id}")
	public String updateHotelchain(@PathVariable Long id, @ModelAttribute("hotelchain") HotelChain newhotelchain, Model model) {
		
		HotelChain existingHotelChain = hotelChainService.getHotelChainById(id);
		existingHotelChain.setId(newhotelchain.getId());
		existingHotelChain.setChainName(newhotelchain.getName());
		existingHotelChain.setAddress(newhotelchain.getAddress());
		existingHotelChain.setEmail(newhotelchain.getName());
		existingHotelChain.setNumHotels(newhotelchain.getNumHotels());
		existingHotelChain.setPhone(newhotelchain.getPhone());

		hotelChainService.updateHotelChain(existingHotelChain);
		return "redirect:/hotelchains";
	}

	@GetMapping("/hotelchains/{id}")
	public String deleteHotelChain(@PathVariable Long id) {
		hotelChainService.deleteHotelChainById(id);
		return "redirect:/hotelchains";
	}
}
