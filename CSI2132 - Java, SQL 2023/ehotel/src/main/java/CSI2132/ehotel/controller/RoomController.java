package CSI2132.ehotel.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import CSI2132.ehotel.entity.Booking;
import CSI2132.ehotel.entity.Room;
//import CSI2132.ehotel.service.BookingService;
import CSI2132.ehotel.service.RoomService;

  @Controller
  public class RoomController {

      private RoomService roomService;

    public RoomController(RoomService roomService) {
      super();
      this.roomService = roomService;
    }

/* 
      @RequestMapping("/rooms")
      public String rooms(){
          return "rooms";
      }
      */

      @GetMapping("/rooms")
    public String listRooms(Model model, String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String keyword6, String keyword7) {

      if(keyword1 != null && keyword2 !=null){
        model.addAttribute("rooms", roomService.findByKeyword(keyword1, keyword2, keyword3, keyword4, keyword5, keyword6, keyword7));
      }else{
        model.addAttribute("rooms", roomService.getAllRooms());
     

      }
      return "rooms";

      
      
    }



      @GetMapping("/editRoom/{room_id}")
    public String editRoomForm(@PathVariable Long room_id, Model model) {
      model.addAttribute("room", roomService.getRoomByID(room_id));
      return "editRoom";
    }

    @PostMapping("/rooms/{room_id}")
    public String updateRoom(@PathVariable Long room_id, @ModelAttribute("room") Room room, Model model){
      Room existingRoom = roomService.getRoomByID(room_id);
      existingRoom.setId(room_id);
      existingRoom.setAmenities(room.getAmenities());
      existingRoom.setCapacity(room.getCapacity());
      existingRoom.setExtendability(room.getExtendability());
      existingRoom.setHotel_id(room.getHotel_id());
      existingRoom.setPrice(room.getPrice());
      existingRoom.setProblems(room.getProblems());

      roomService.updateRoom(existingRoom);
      return "redirect:/rooms";


    }

      @GetMapping("/createRoom")
    public String createRoom(Model model) {
      // create room object to hold room form data
      Room room = new Room();


      model.addAttribute("room", room);
      //System.out.println(room.toString());

      //System.out.println("(*&(*&(*&(*&(*&(*&(*&(*&(*&(*&&(*(&))))))))))))");



      return "createRoom";

    }

    @PostMapping("/rooms")
    public String saveRoom(@ModelAttribute("room") Room room){
     // System.out.println(room.toString());

      //System.out.println("lskdjflskdjflsdkjflskdfjlskdfjlskdfjlskdfjlskdjflskdfjlsdkfjlsdkjflskdjflskdfjlksdfj");

      //room = new Room( 12, 23, "food", "mountain", "none", "yes", 100.0);
      //System.out.println(room.toString());
      roomService.saveRoom(room);

      return "redirect:/rooms";
    }


    @GetMapping("/rooms/{room_id}")
    public String deleteRoom (@PathVariable Long room_id){
      roomService.deleteRoomByID(room_id);
      return "redirect:/rooms";
    }



}
