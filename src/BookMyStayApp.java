import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
public class BookMyStayApp {
    abstract static class Room {
        String roomType;
        int beds;
        double price;
        Room(String roomType, int beds, double price) {
            this.roomType = roomType;
            this.beds = beds;
            this.price = price;
        }
        void displayRoomDetails(int available) {
            System.out.println("Room Type: " + roomType);
            System.out.println("Beds: " + beds);
            System.out.println("Price: ₹" + price);
            System.out.println("Available: " + available);
            System.out.println();
        }
    }
    static class SingleRoom extends Room {
        SingleRoom() { super("Single Room", 1, 3000); }
    }
    static class DoubleRoom extends Room {
        DoubleRoom() { super("Double Room", 2, 5000); }
    }

    static class SuiteRoom extends Room {
        SuiteRoom() { super("Suite Room", 3, 9000); }
    }
    static class RoomInventory {
        private HashMap<String, Integer> inventory;
        RoomInventory() {
            inventory = new HashMap<>();
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 2);
        }
        int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }
    }
    static class Reservation {
        String guestName;
        String roomType;
        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
        void displayRequest() {
            System.out.println("Guest: " + guestName + " requested " + roomType);
        }
    }
    // Booking Request Queue (FIFO)
    static class BookingRequestQueue {
        Queue<Reservation> queue = new LinkedList<>();
        void addRequest(Reservation reservation) {
            queue.add(reservation);
            System.out.println("Request added to queue:");
            reservation.displayRequest();
            System.out.println();
        }
        void displayQueue() {
            System.out.println(" Current Booking Requests :");
            for (Reservation r : queue) {
                r.displayRequest();
            }

            System.out.println();
        }
    }
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue requestQueue = new BookingRequestQueue();
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");
        requestQueue.addRequest(r1);
        requestQueue.addRequest(r2);
        requestQueue.addRequest(r3);
        requestQueue.displayQueue();
    }
}
