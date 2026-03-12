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
        SingleRoom() { super("Single Room",1,3000); }
    }
    static class DoubleRoom extends Room {
        DoubleRoom() { super("Double Room",2,5000); }
    }
    static class SuiteRoom extends Room {
        SuiteRoom() { super("Suite Room",3,9000); }
    }
    static class RoomInventory {
        private HashMap<String,Integer> inventory = new HashMap<>();
        RoomInventory() {
            inventory.put("Single Room",5);
            inventory.put("Double Room",3);
            inventory.put("Suite Room",2);
        }
        int getAvailability(String roomType){
            return inventory.getOrDefault(roomType,0);
        }
        void decrementRoom(String roomType){
            inventory.put(roomType,inventory.get(roomType)-1);
        }
    }
    static class Reservation {
        String guestName;
        String roomType;
        String reservationId;
        Reservation(String guestName,String roomType,String reservationId){
            this.guestName = guestName;
            this.roomType = roomType;
            this.reservationId = reservationId;
        }
    }
    static class Service {
        String serviceName;
        double price;
        Service(String serviceName,double price){
            this.serviceName = serviceName;
            this.price = price;
        }
    }
    // Add-On Service Manager
    static class AddOnServiceManager {
        HashMap<String,List<Service>> reservationServices = new HashMap<>();
        void addService(String reservationId, Service service){
            reservationServices.putIfAbsent(reservationId,new ArrayList<>());
            reservationServices.get(reservationId).add(service);

        }
        double calculateTotal(String reservationId){
            double total = 0;
            if(reservationServices.containsKey(reservationId)){

                for(Service s : reservationServices.get(reservationId)){
                    total += s.price;
                }

            }
            return total;
        }
        void displayServices(String reservationId){
            if(!reservationServices.containsKey(reservationId)){
                System.out.println("No services selected");
                return;
            }
            System.out.println("Services for Reservation "+reservationId);
            for(Service s : reservationServices.get(reservationId)){
                System.out.println(s.serviceName + " - ₹" + s.price);
            }
            System.out.println("Total Add-On Cost: ₹" + calculateTotal(reservationId));
        }
    }
    public static void main(String[] args) {
        Reservation reservation =
                new Reservation("Alice","Single Room","RES101");
        AddOnServiceManager serviceManager = new AddOnServiceManager();
        serviceManager.addService("RES101", new Service("Breakfast",500));
        serviceManager.addService("RES101", new Service("Airport Pickup",1200));
        serviceManager.addService("RES101", new Service("Extra Bed",800));
        serviceManager.displayServices("RES101");
    }
}