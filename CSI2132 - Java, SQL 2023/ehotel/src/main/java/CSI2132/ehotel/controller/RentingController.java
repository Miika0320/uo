package CSI2132.ehotel.controller;

import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import CSI2132.ehotel.entity.Renting;
import CSI2132.ehotel.service.RentingService;

@Controller
public class RentingController {
    
    private RentingService rentingService;

	public RentingController(RentingService rentingService) {
		super();
		this.rentingService = rentingService;
	}
	@RequestMapping("/rentings")
      public String rentings(){
          return "rentings";
      }

	  @GetMapping("/rentings")
	  public String listrentings(Model model, String keywordr1, String keywordr2, String keywordr3, String keywordr4, String keywordr5, String keywordr6) {
  
		if(keywordr1 != null || keywordr2 !=null || keywordr3 !=null || keywordr4 !=null || keywordr5 !=null || keywordr6 !=null){
		  model.addAttribute("rentings", rentingService.findByKeyword(keywordr1, keywordr2, keywordr3, keywordr4, keywordr5, keywordr6));
		}else{
		  model.addAttribute("rentings", rentingService.getAllRentings());
	   
  
		}
		return "rentings";

	  }

    

	@GetMapping("/createRenting") // add
	public String createRentingForm(Model model) {
		Renting renting = new Renting();
		model.addAttribute("renting", renting);
		return "createRenting";
	}

	@PostMapping("/rentings") // save to repo?
	public String saveRenting(@ModelAttribute("rentings") Renting renting) {
		rentingService.saveRenting(renting);
		return "redirect:/rentings";
	}

	@GetMapping("/editRentings/{rental_id}")
	public String editRentingForm(@PathVariable Long rental_id, Model model) {
		model.addAttribute("renting", rentingService.getRentingById(rental_id));
		return "editRenting";
	}

	@PostMapping("/rentings/{rental_id}")
	public String updateRenting(@PathVariable Long rental_id, @ModelAttribute("renting") Renting newrenting, Model model) {
		
        Renting existingRenting = rentingService.getRentingById(rental_id);
		existingRenting.setRental_id(rental_id);
		existingRenting.setBooking_id(newrenting.getBooking_id());
		existingRenting.setDate_in(newrenting.getDate_in());
		existingRenting.setDate_out(newrenting.getDate_out());
		existingRenting.setPayment(newrenting.getPayment());
        existingRenting.setCustomer_id(newrenting.getCustomer_id());
        existingRenting.setHotel_id(newrenting.getHotel_id());
        existingRenting.setRoom_id(newrenting.getRoom_id());

		rentingService.updateRenting(existingRenting);
		return "redirect:/rentings";
	}

	@GetMapping("/rentings/{rental_id}")
	public String deleteRenting(@PathVariable Long rental_id) {
		rentingService.deleteRentingById(rental_id);
		return "redirect:/rentings";
	}

}
