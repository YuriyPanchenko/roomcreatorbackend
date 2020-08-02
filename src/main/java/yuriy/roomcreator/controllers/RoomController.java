package yuriy.roomcreator.controllers;

import org.springframework.web.bind.annotation.*;
import yuriy.roomcreator.models.Point;
import yuriy.roomcreator.models.Room;
import yuriy.roomcreator.repository.RoomRepository;

import java.util.LinkedHashSet;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping(path = "/")
    public List<Room> allRooms(){
        return (List<Room>) roomRepository.findAll();
    }

    @PostMapping(path = "/")
    public void addNewRoom(@RequestBody Room room){
        roomRepository.save(room);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteRoom(@PathVariable Long id){
        roomRepository.deleteById(id);
    }
}
