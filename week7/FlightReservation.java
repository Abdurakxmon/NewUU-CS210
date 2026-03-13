import java.util.*;

class FlightReservation {
    private static Map<String, FlightReservation> reservationDatabase = new HashMap<>(); // simple in-memory "DB"
    private String reservationNumber;
    private FlightInstance flight;
    private Map<Passenger, FlightSeat> seatMap;
    private Date creationDate;
    private ReservationStatus status;

    public FlightReservation(String reservationNumber, FlightInstance flight) {
        this.reservationNumber = reservationNumber;
        this.flight = flight;
        this.seatMap = new HashMap<>();
        this.creationDate = new Date();
        this.status = ReservationStatus.PENDING;
        reservationDatabase.put(reservationNumber, this);
    }

    public static FlightReservation fetchReservationDetails(String reservationNumber) {
        return reservationDatabase.get(reservationNumber);
    }

    public List<Passenger> getPassengers() {
        return new ArrayList<>(seatMap.keySet());
    }

    public void assignSeat(Passenger passenger, FlightSeat seat) {
        seatMap.put(passenger, seat);
    }

    public boolean confirmReservation() {
        if (status == ReservationStatus.PENDING) {
            status = ReservationStatus.CONFIRMED;
            return true;
        }
        return false;
    }

    public boolean cancel() {
        if (status == ReservationStatus.PENDING || status == ReservationStatus.CONFIRMED) {
            status = ReservationStatus.CANCELLED;
            return true;
        }
        return false;
    }

    public String getReservationNumber() { return reservationNumber; }
    public void setReservationNumber(String reservationNumber) { this.reservationNumber = reservationNumber; }
    public FlightInstance getFlight() { return flight; }
    public void setFlight(FlightInstance flight) { this.flight = flight; }
    public Map<Passenger, FlightSeat> getSeatMap() { return seatMap; }
    public Date getCreationDate() { return creationDate; }
    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }
}