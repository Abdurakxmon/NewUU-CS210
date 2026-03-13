import java.util.ArrayList;
import java.util.List;

class Flight {
    private String flightNumber;
    private Airport departure;
    private Airport arrival;
    private int durationInMinutes;
    private List<WeeklySchedule> weeklySchedules;
    private List<CustomSchedule> customSchedules;
    private List<FlightInstance> flightInstances;

    public Flight(String flightNumber, Airport departure, Airport arrival, int durationInMinutes) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.durationInMinutes = durationInMinutes;
        this.weeklySchedules = new ArrayList<>();
        this.customSchedules = new ArrayList<>();
        this.flightInstances = new ArrayList<>();

        // Add this flight to the airports' flight lists
        departure.addFlight(this);
        arrival.addFlight(this);
    }

    public void addWeeklySchedule(WeeklySchedule ws) {
        weeklySchedules.add(ws);
    }

    public void addCustomSchedule(CustomSchedule cs) {
        customSchedules.add(cs);
    }

    public void addFlightInstance(FlightInstance fi) {
        flightInstances.add(fi);
        // Also add the instance to the aircraft's list
        fi.getAircraft().addFlightInstance(fi);
    }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public Airport getDeparture() { return departure; }
    public void setDeparture(Airport departure) { this.departure = departure; }
    public Airport getArrival() { return arrival; }
    public void setArrival(Airport arrival) { this.arrival = arrival; }
    public int getDurationInMinutes() { return durationInMinutes; }
    public void setDurationInMinutes(int durationInMinutes) { this.durationInMinutes = durationInMinutes; }
    public List<WeeklySchedule> getWeeklySchedules() { return weeklySchedules; }
    public List<CustomSchedule> getCustomSchedules() { return customSchedules; }
    public List<FlightInstance> getFlightInstances() { return flightInstances; }
}