import java.util.*;
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
    }
    static class SingleRoom extends Room {
        SingleRoom() { super("Single Room", 1, 100); }
    }
    static class DoubleRoom extends Room {
        DoubleRoom() { super("Double Room", 2, 180); }
    }
    static class SuiteRoom extends Room {
        SuiteRoom() { super("Suite Room", 3, 300); }
    }
    static class RoomInventory {
        private HashMap<String, Integer> inventory = new HashMap<>();
        RoomInventory() {
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 2);
        }
        int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }
        void decrementRoom(String roomType) {
            inventory.put(roomType, inventory.get(roomType) - 1);
        }
        void displayInventory() {
            System.out.println("\nUpdated Inventory:");
            for (String room : inventory.keySet()) {
                System.out.println(room + " : " + inventory.get(room));
            }
        }
    }
    static class Reservation {
        String guestName;
        String roomType;
        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }
    static class BookingService {
        Queue<Reservation> requestQueue = new LinkedList<>();
        HashMap<String, Set<String>> allocatedRooms = new HashMap<>();
        Set<String> allRoomIds = new HashSet<>();
        RoomInventory inventory;
        BookingService(RoomInventory inventory) {
            this.inventory = inventory;
        }
        void addRequest(Reservation r) {
            requestQueue.add(r);
        }
        void processRequests() {
            while (!requestQueue.isEmpty()) {
                Reservation r = requestQueue.poll();
                if (inventory.getAvailability(r.roomType) > 0) {
                    String roomId = generateRoomId(r.roomType);
                    allRoomIds.add(roomId);
                    allocatedRooms.putIfAbsent(r.roomType, new HashSet<>());
                    allocatedRooms.get(r.roomType).add(roomId);
                    inventory.decrementRoom(r.roomType);
                    System.out.println("Reservation Confirmed");
                    System.out.println("Guest: " + r.guestName);
                    System.out.println("Room Type: " + r.roomType);
                    System.out.println("Allocated Room ID: " + roomId);
                    System.out.println();
                } else {
                    System.out.println("Reservation Failed for " + r.guestName +
                            " (No rooms available for " + r.roomType + ")");
                }
            }
        }
        String generateRoomId(String roomType) {
            String prefix = roomType.substring(0,2).toUpperCase();
            String roomId;
            do {
                roomId = prefix + new Random().nextInt(1000);
            } while (allRoomIds.contains(roomId));
            return roomId;
        }
    }
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService(inventory);
        bookingService.addRequest(new Reservation("Alice", "Single Room"));
        bookingService.addRequest(new Reservation("Bob", "Double Room"));
        bookingService.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingService.processRequests();
        inventory.displayInventory();
    }
}