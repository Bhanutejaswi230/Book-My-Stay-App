import java.io.*;
import java.util.*;
public class BookMyStayApp {
    static class Reservation implements Serializable {
        String guest;
        String roomType;
        String roomId;
        Reservation(String guest, String roomType, String roomId) {
            this.guest = guest;
            this.roomType = roomType;
            this.roomId = roomId;
        }
    }
    static class SystemState implements Serializable {
        HashMap<String, Integer> inventory = new HashMap<>();
        List<Reservation> bookings = new ArrayList<>();
    }
    static class PersistenceService {
        static void save(SystemState state) {
            try {
                ObjectOutputStream out =
                        new ObjectOutputStream(new FileOutputStream("system_state.dat"));
                out.writeObject(state);
                out.close();
                System.out.println("System state saved successfully");
            } catch (Exception e) {
                System.out.println("Error saving state: " + e.getMessage());
            }

        }
        static SystemState load() {
            try {
                ObjectInputStream in =
                        new ObjectInputStream(new FileInputStream("system_state.dat"));
                SystemState state = (SystemState) in.readObject();
                in.close();
                System.out.println("System state restored successfully");
                return state;
            } catch (Exception e) {
                System.out.println("No previous state found. Starting fresh.");
                return new SystemState();
            }

        }
    }
    public static void main(String[] args) {
        SystemState state = PersistenceService.load();
        if (state.inventory.isEmpty()) {
            state.inventory.put("Single Room", 5);
            state.inventory.put("Double Room", 3);
            state.inventory.put("Suite Room", 2);
        }
        Reservation r1 = new Reservation("Alice", "Single Room", "SR101");
        state.bookings.add(r1);
        int available = state.inventory.get("Single Room");
        state.inventory.put("Single Room", available - 1);
        System.out.println("Current Bookings:");
        for (Reservation r : state.bookings) {
            System.out.println(r.guest + " booked " + r.roomType + " ID: " + r.roomId);
        }
        System.out.println("Inventory:");
        for (String room : state.inventory.keySet()) {
            System.out.println(room + " : " + state.inventory.get(room));
        }
        PersistenceService.save(state);
    }
}