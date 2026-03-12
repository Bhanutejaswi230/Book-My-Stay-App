import java.util.*;
public class BookMyStayApp {
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
        synchronized void allocateRoom(String roomType, String guest) {
            int available = rooms.getOrDefault(roomType, 0);
            if (available > 0) {
                rooms.put(roomType, available - 1);
                System.out.println(guest + " booked " + roomType +
                        " | Remaining: " + (available - 1));
            } else {
                System.out.println("No rooms available for " + guest + " (" + roomType + ")");
            }
        }
    }
    static class BookingProcessor extends Thread {
        Queue<Reservation> queue;
        Inventory inventory;
        BookingProcessor(Queue<Reservation> queue, Inventory inventory) {
            this.queue = queue;
            this.inventory = inventory;
        }
        public void run() {
            while (true) {
                Reservation r;
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        break;
                    }
                    r = queue.poll();
                }
                if (r != null) {
                    inventory.allocateRoom(r.roomType, r.guest);
                }
            }
        }
    }
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Queue<Reservation> queue = new LinkedList<>();
        queue.add(new Reservation("Alice", "Single Room"));
        queue.add(new Reservation("Bob", "Single Room"));
        queue.add(new Reservation("Charlie", "Double Room"));
        queue.add(new Reservation("David", "Suite Room"));
        queue.add(new Reservation("Eva", "Single Room"));
        BookingProcessor t1 = new BookingProcessor(queue, inventory);
        BookingProcessor t2 = new BookingProcessor(queue, inventory);
        t1.start();
        t2.start();
    }
}