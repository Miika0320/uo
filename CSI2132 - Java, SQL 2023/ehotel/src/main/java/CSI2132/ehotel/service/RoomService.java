package CSI2132.ehotel.service;

import java.util.List;



import CSI2132.ehotel.entity.Room;

public interface RoomService {
    List<Room> getAllRooms();

	Room saveRoom(Room room);
	
	Room getRoomByID(Long room_id);
	
	Room updateRoom(Room room);
	
	void deleteRoomByID(Long room_id);

	List<Room> findByKeyword(String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String keyword6, String keyword7);

}
