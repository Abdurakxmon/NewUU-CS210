import java.util.Date;

class FlightInstance {
    private Date departureTime;
    private String gate;
    private FlightStatus status;
    private Aircraft aircraft;
    private Flight flight;

    public FlightInstance(Flight flight, Date departureTime, String gate, Aircraft aircraft) {
        this.flight = flight;
        this.departureTime = departureTime;
        this.gate = gate;
        this.aircraft = aircraft;
        this.status = FlightStatus.SCHEDULED;
        flight.addFlightInstance(this);
    }

    public boolean cancel() {
        if (status == FlightStatus.SCHEDULED || status == FlightStatus.DELAYED) {
            status = FlightStatus.CANCELLED;
            return true;
        }
        return false;
    }

    public void updateStatus(FlightStatus status) {
        this.status = status;
        System.out.println("Flight " + flight.getFlightNumber() + " status updated to: " + status);
    }

    public Date getDepartureTime() { return departureTime; }
    public void setDepartureTime(Date departureTime) { this.departureTime = departureTime; }
    public String getGate() { return gate; }
    public void setGate(String gate) { this.gate = gate; }
    public FlightStatus getStatus() { return status; }
    public Aircraft getAircraft() { return aircraft; }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }
    public Flight getFlight() { return flight; }
}