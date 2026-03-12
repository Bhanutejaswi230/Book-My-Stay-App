import java.util.*;

public class BookMyStayApp {

    static class Reservation {
        String guest;
        String roomType;
        String roomId;

        Reservation(String guest, String roomType, String roomId) {
            this.guest = guest;
            this.roomType = roomType;
            this.roomId = roomId;
        }
    }

    static class Inventory {

        HashMap<String, Integer> rooms = new HashMap<>();

        Inventory() {
            rooms.put("Single Room", 5);
            rooms.put("Double Room", 3);
            rooms.put("Suite Room", 2);
        }

        void decrement(String roomType) {
            rooms.put(roomType, rooms.get(roomType) - 1);
        }

        void increment(String roomType) {
            rooms.put(roomType, rooms.get(roomType) + 1);
        }

        void display() {
            System.out.println("Inventory Status:");
            for (String room : rooms.keySet()) {
                System.out.println(room + " : " + rooms.get(room));
            }
        }
    }

    static class CancellationService {

        Stack<String> rollbackStack = new Stack<>();
        HashMap<String, Reservation> confirmedBookings = new HashMap<>();
        Inventory inventory;

        CancellationService(Inventory inventory) {
            this.inventory = inventory;
        }

        void confirmBooking(Reservation r) {

            confirmedBookings.put(r.roomId, r);
            rollbackStack.push(r.roomId);
            inventory.decrement(r.roomType);

            System.out.println("Booking Confirmed: " + r.roomId);

        }

        void cancelBooking(String roomId) {

            if (!confirmedBookings.containsKey(roomId)) {
                System.out.println("Cancellation Failed: Booking does not exist");
                return;
            }

            Reservation r = confirmedBookings.remove(roomId);

            rollbackStack.push(roomId);

            inventory.increment(r.roomType);

            System.out.println("Booking Cancelled: " + roomId);

        }
    }

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        CancellationService service = new CancellationService(inventory);

        Reservation r1 = new Reservation("Alice", "Single Room", "SR101");
        Reservation r2 = new Reservation("Bob", "Double Room", "DR201");

        service.confirmBooking(r1);
        service.confirmBooking(r2);

        inventory.display();

        service.cancelBooking("SR101");

        inventory.display();

    }
}