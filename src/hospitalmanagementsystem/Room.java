/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

/**
 *
 * @author lamashuhail
 */

public class Room {
    private int roomNo;
    private RoomType type;
    private boolean occupied;

    public Room() {
    }

    public Room(int roomNo, RoomType type, boolean occupied) {
        this.roomNo = roomNo;
        this.type = type;
        this.occupied = occupied;
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
}

