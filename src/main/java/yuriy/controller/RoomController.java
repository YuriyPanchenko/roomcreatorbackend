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
	public Room createEmployee(@RequestBody Room room) {
		room.setId(sequenceGeneratorService.generateSequence(Room.SEQUENCE_NAME));
		return roomRepository.save(room);
	}

	@PutMapping("/rooms/{id}")
	public ResponseEntity<Room> updateEmployee(@PathVariable(value = "id") Long employeeId,
											   @RequestBody Room roomDetails) throws ResourceNotFoundException {
		Room room = roomRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + employeeId));


		final Room updatedRoom = roomRepository.save(room);
		return ResponseEntity.ok(updatedRoom);
	}

	@DeleteMapping("/rooms/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Room room = roomRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Room not found for this id :: " + employeeId));

		roomRepository.delete(room);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
