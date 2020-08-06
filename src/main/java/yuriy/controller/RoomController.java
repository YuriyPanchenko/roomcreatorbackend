package yuriy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yuriy.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yuriy.exception.ResourceNotFoundException;
import yuriy.repository.RoomRepository;
import yuriy.service.CheckRoomService;
import yuriy.service.SequenceGeneratorService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("")
public class RoomController {
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping("/rooms")
	public List<Room> getAllEmployees() {
		return roomRepository.findAll();
	}

	@GetMapping("/rooms/{id}")
	public ResponseEntity<Room> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Room room = roomRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(room);
	}

	@PostMapping("/rooms")
	public String createEmployee(@RequestBody Room room) {
		String message = CheckRoomService.message(room.getPoints());
		if(message.equals("Legal.")){
			room.setId(sequenceGeneratorService.generateSequence(Room.SEQUENCE_NAME));
			roomRepository.save(room);
		}
		return message;
	}

	@PutMapping("/rooms/{id}")
	public String updateEmployee(@PathVariable(value = "id") Long roomId,
											   @RequestBody Room roomDetails) throws ResourceNotFoundException {
		String message = CheckRoomService.message(roomDetails.getPoints());
		if(message.equals("Legal.")){
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + roomId));
		room.setId(roomDetails.getId());
		room.setName(roomDetails.getName());
		room.setPoints(roomDetails.getPoints());

		final Room updatedRoom = roomRepository.save(room);
		}
		return message;
	}

	@DeleteMapping("/rooms/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long roomId)
			throws ResourceNotFoundException {
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + roomId));

		roomRepository.delete(room);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
