import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Itinerary {
    private String customerId;
    private Airport startingAirport;
    private Airport finalAirport;
    private Date creationDate;
    private List<FlightReservation> reservations;
    private Payment payment;

    public Itinerary(String customerId, Airport startingAirport, Airport finalAirport) {
        this.customerId = customerId;
        this.startingAirport = startingAirport;
        this.finalAirport = finalAirport;
        this.creationDate = new Date();
        this.reservations = new ArrayList<>();
    }

    public List<FlightReservation> getReservations() {
        return reservations;
    }

    public boolean makeReservation(FlightReservation reservation) {
        reservations.add(reservation);
        return true;
    }

    public boolean makePayment(Payment payment) {
        this.payment = payment;
        boolean success = payment.process();
        if (success) {
            // Confirm all reservations in this itinerary
            for (FlightReservation res : reservations) {
                res.confirmReservation();
            }
        }
        return success;
    }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public Airport getStartingAirport() { return startingAirport; }
    public void setStartingAirport(Airport startingAirport) { this.startingAirport = startingAirport; }
    public Airport getFinalAirport() { return finalAirport; }
    public void setFinalAirport(Airport finalAirport) { this.finalAirport = finalAirport; }
    public Date getCreationDate() { return creationDate; }
    public Payment getPayment() { return payment; }
}