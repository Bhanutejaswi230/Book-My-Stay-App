import java.util.HashMap;
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
        void displayRoomDetails() {
            System.out.println("Room Type: " + roomType);
            System.out.println("Beds: " + beds);
            System.out.println("Price: $" + price);
        }
    }
    // Single Room
    static class SingleRoom extends Room {
        SingleRoom() {
            super("Single Room", 1, 100);
        }
    }
    // Double Room
    static class DoubleRoom extends Room {
        DoubleRoom() {
            super("Double Room", 2, 180);
        }
    }
    // Suite Room
    static class SuiteRoom extends Room {
        SuiteRoom() {
            super("Suite Room", 3, 300);
        }
    }
    // UC3-Centralized Inventory Management
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
        void updateAvailability(String roomType, int count) {
            inventory.put(roomType, count);
        }
        void displayInventory() {
            System.out.println("===== Room Inventory =====");
            for (String room : inventory.keySet()) {
                System.out.println(room + " : " + inventory.get(room));
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        RoomInventory inventory = new RoomInventory();
        single.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability("Single Room"));
        System.out.println();
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability("Double Room"));
        System.out.println();
        suite.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability("Suite Room"));
        System.out.println();
        inventory.displayInventory();
    }
}