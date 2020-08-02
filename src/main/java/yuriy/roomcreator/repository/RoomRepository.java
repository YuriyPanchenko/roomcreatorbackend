package yuriy.roomcreator.repository;

import org.springframework.data.repository.CrudRepository;
import yuriy.roomcreator.models.Room;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {

}
