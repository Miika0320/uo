package CSI2132.ehotel.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import org.yaml.snakeyaml.events.Event.ID;

import CSI2132.ehotel.service.RoomService;
import CSI2132.ehotel.entity.Room;
import CSI2132.ehotel.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService{

    private RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
		super();
		this.roomRepository = roomRepository;
	}

	@Override

	public List<Room> getAllRooms(){

		return roomRepository.findAll();
	}

	@Override
	public Room saveRoom(Room room) {
		return roomRepository.save(room);
	}

	@Override

	public Room getRoomByID(Long room_id) {
		return roomRepository.findById(room_id).get();

	}

	@Override
	public Room updateRoom(Room room) {
		return roomRepository.save(room);
	}

	@Override

	public void deleteRoomByID(Long room_id) {
		roomRepository.deleteById(room_id);	
	}
	@Override
	public List<Room> findByKeyword(String keyword1, String keyword2, String keyword3, String keyword4, String keyword5, String keyword6, String keyword7){
		return roomRepository.findByKeyword(keyword1, keyword2, keyword3, keyword4, keyword5, keyword6, keyword7);
	}

}
