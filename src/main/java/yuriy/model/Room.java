package yuriy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashSet;

@Document (collection = "room")
public class Room {

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private long id;
	
	private String name;

	private LinkedHashSet<Point> points;
	
	public Room() {
		
	}
	
	public Room(String name, LinkedHashSet<Point> points) {
		this.name = name;
		this.points = points;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedHashSet<Point> getPoints() {
		return points;
	}

	public void setPoints(LinkedHashSet<Point> points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Room{" +
				"id=" + id +
				", name='" + name + '\'' +
				", points=" + points +
				'}';
	}
}
