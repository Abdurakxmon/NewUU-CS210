import java.util.ArrayList;
import java.util.List;

class Airport {
    private String name;
    private Address address;
    private String code;
    private List<Flight> flights; // flights departing from or arriving at this airport

    public Airport(String name, Address address, String code) {
        this.name = name;
        this.address = address;
        this.code = code;
        this.flights = new ArrayList<>();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        if (!flights.contains(flight)) {
            flights.add(flight);
        }
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}