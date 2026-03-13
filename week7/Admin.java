import java.util.ArrayList;
import java.util.List;

class Admin extends Person {
    private static List<Aircraft> aircraftRegistry = new ArrayList<>();
    private static List<Flight> flightRegistry = new ArrayList<>();

    public Admin(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
    }

    public void addAircraft(Aircraft aircraft) {
        aircraftRegistry.add(aircraft);
        System.out.println("Aircraft " + aircraft.getName() + " added to system.");
    }

    public void addFlight(Flight flight) {
        flightRegistry.add(flight);
        System.out.println("Flight " + flight.getFlightNumber() + " added to system.");
    }

    public void cancelFlight(FlightInstance flightInstance) {
        flightInstance.cancel();
        System.out.println("Flight instance " + flightInstance.getFlight().getFlightNumber() +
                " on " + flightInstance.getDepartureTime() + " has been cancelled.");
    }

    public static List<Aircraft> getAllAircraft() {
        return aircraftRegistry;
    }

    public static List<Flight> getAllFlights() {
        return flightRegistry;
    }
}