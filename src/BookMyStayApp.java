public class BookMyStayApp {

    // Abstract Room Class
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

    public static void main(String[] args) {

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("===== Room Availability =====");

        single.displayRoomDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println();

        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}