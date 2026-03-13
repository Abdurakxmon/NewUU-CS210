class FlightSeat extends Seat {
    private double fare;

    public FlightSeat(String seatNumber, SeatType type, SeatClass seatClass, double fare) {
        super(seatNumber, type, seatClass);
        this.fare = fare;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}