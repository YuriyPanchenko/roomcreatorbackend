package yuriy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import yuriy.model.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, Long>{

}
