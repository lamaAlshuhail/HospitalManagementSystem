package hospitalmanagementsystem;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Room {
    private int roomNo;
    private RoomType type;
    private boolean occupied;

    public Room(int roomNo, RoomType type, boolean occupied) {
        this.roomNo = roomNo;
        this.type = type;
        this.occupied = occupied;
    }

    public Room(int roomNo, RoomType roomType) {
        this.roomNo = roomNo;
        this.type = roomType;
        this.occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public RoomType getType() {
        return type;
    }

    public enum RoomType {
        SINGLE,
        SHARED,
        SUITE
        // Add more room types as needed
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNo + "\nRoom Type: " + type;
    }

    private static final List<Room> AVAILABLE_ROOMS = new ArrayList<>();

    static {
        for (int i = 1; i <= 5; i++) {
            int roomNumber = i;
            Room suite = new Room(roomNumber, Room.RoomType.SUITE);
            AVAILABLE_ROOMS.add(suite);
        }

        for (int i = 1; i <= 10; i++) {
            int roomNumber = i;
            Room single = new Room(roomNumber, Room.RoomType.SINGLE);
            AVAILABLE_ROOMS.add(single);
        }

        for (int i = 1; i <= 10; i++) {
            int roomNumber = i;
            Room shared = new Room(roomNumber, Room.RoomType.SHARED);
            AVAILABLE_ROOMS.add(shared);
        }
    }

    public static List<Room> getAvailableRooms() {
        return Collections.unmodifiableList(AVAILABLE_ROOMS);
    }
}