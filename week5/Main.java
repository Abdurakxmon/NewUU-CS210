public class Main {

    public static void main() {
       Flight flight = new Flight(210, "Tashkent", "1A", "A2");
       System.out.println(flight.getFlight());

       SmartDevice dev = new SmartDevice("iMac", false);
       Hub<SmartDevice> macHub = new Hub<>();
       macHub.saveDevice(dev);
       System.out.println(macHub.getConnectedDevice());

       Professor prof = new Professor("Rasim Mutlu");
       Book book = new Book("Advanced Deep Learning", "Liao Xi");
       LibrarySystem lib = new LibrarySystem();
       lib.checkOut(prof,book);

        
    }
}
