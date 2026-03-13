import java.util.ArrayList;
import java.util.List;

class Aircraft {
    private String name;
    private String model;
    private int manufacturingYear;
    private List<Seat> seats;
    private List<FlightInstance> flights; // flight instances using this aircraft

    public Aircraft(String name, String model, int manufacturingYear) {
        this.name = name;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
        this.seats = new ArrayList<>();
        this.flights = new ArrayList<>();
    }

    public List<FlightInstance> getFlights() {
        return flights;
    }

    public void addFlightInstance(FlightInstance flightInstance) {
        if (!flights.contains(flightInstance)) {
            flights.add(flightInstance);
        }
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getManufacturingYear() { return manufacturingYear; }
    public void setManufacturingYear(int manufacturingYear) { this.manufacturingYear = manufacturingYear; }
    public List<Seat> getSeats() { return seats; }
}