import java.util.*;
public class BookMyStayApp {
    static class Reservation {
        String guest;
        String roomType;
        String id;
        Reservation(String guest, String roomType, String id) {
            this.guest = guest;
            this.roomType = roomType;
            this.id = id;
        }
    }
    static class BookingHistory {
        List<Reservation> history = new ArrayList<>();
        void add(Reservation r) {
            history.add(r);
        }
        List<Reservation> get() {
            return history;
        }
    }
    static class BookingReportService {
        void show(List<Reservation> list) {
            for (Reservation r : list) {
                System.out.println("Guest: " + r.guest + " Room: " + r.roomType + " ID: " + r.id);
            }
        }
        void summary(List<Reservation> list) {
            HashMap<String, Integer> count = new HashMap<>();
            for (Reservation r : list) {
                count.put(r.roomType, count.getOrDefault(r.roomType, 0) + 1);
            }
            System.out.println("Report:");
            for (String room : count.keySet()) {
                System.out.println(room + " Bookings: " + count.get(room));
            }

        }
    }
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();
        history.add(new Reservation("Alice", "Single Room", "SR101"));
        history.add(new Reservation("Bob", "Double Room", "DR201"));
        history.add(new Reservation("Charlie", "Suite Room", "SU301"));
        BookingReportService report = new BookingReportService();
        report.show(history.get());
        report.summary(history.get());
    }
}