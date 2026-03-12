import java.util.*;
public class BookMyStayApp {
    static class InvalidBookingException extends Exception {
        InvalidBookingException(String message) {
            super(message);
        }
    }
    static class Reservation {
        String guest;
        String roomType;
        Reservation(String guest, String roomType) {
            this.guest = guest;
            this.roomType = roomType;
        }
    }
    static class Inventory {
        HashMap<String, Integer> rooms = new HashMap<>();
        Inventory() {
            rooms.put("Single Room", 5);
            rooms.put("Double Room", 3);
            rooms.put("Suite Room", 2);
        }
        void validateRoom(String roomType) throws InvalidBookingException {
            if (!rooms.containsKey(roomType)) {
                throw new InvalidBookingException("Invalid Room Type: " + roomType);
            }
            if (rooms.get(roomType) <= 0) {
                throw new InvalidBookingException("No rooms available for " + roomType);
            }
        }
        void bookRoom(String roomType) throws InvalidBookingException {
            validateRoom(roomType);
            int available = rooms.get(roomType);
            if (available - 1 < 0) {
                throw new InvalidBookingException("Inventory cannot be negative");
            }
            rooms.put(roomType, available - 1);
            System.out.println(roomType + " booked successfully");

        }
    }
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Luxury Room");
        try {
            inventory.bookRoom(r1.roomType);
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            inventory.bookRoom(r2.roomType);
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}