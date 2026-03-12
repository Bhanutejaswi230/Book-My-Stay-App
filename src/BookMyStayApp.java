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
        void displayRoomDetails(int available) {
            System.out.println("Room Type: " + roomType);
            System.out.println("Beds: " + beds);
            System.out.println("Price: $" + price);
            System.out.println("Available: " + available);
            System.out.println();
        }
    }
    static class SingleRoom extends Room {
        SingleRoom() {
            super("Single Room", 1, 100);
        }
    }
    static class DoubleRoom extends Room {
        DoubleRoom() {
            super("Double Room", 2, 180);
        }
    }
    static class SuiteRoom extends Room {
        SuiteRoom() {
            super("Suite Room", 3, 300);
        }
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
    static class SearchService {
        RoomInventory inventory;
        SearchService(RoomInventory inventory) {
            this.inventory = inventory;
        }
        void searchRooms(Room[] rooms) {
            System.out.println(" Available Rooms ");
            for (Room room : rooms) {
                int available = inventory.getAvailability(room.roomType);
                if (available > 0) { // Filter unavailable rooms
                    room.displayRoomDetails(available);
                }
            }
        }
    }

    public static void main(String[] args) {
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        Room[] rooms = {single, doubleRoom, suite};
        RoomInventory inventory = new RoomInventory();
        SearchService search = new SearchService(inventory);
        search.searchRooms(rooms);
    }
}