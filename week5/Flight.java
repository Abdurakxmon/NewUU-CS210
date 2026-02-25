public class Flight {
    private Integer flightId;
    private String destination;
    private BoardingPass obj;

    public Flight(Integer flightId, String destination, String seatNumber, String gate){
        this.flightId = flightId;
        this.destination = destination;
        this.obj = new BoardingPass(seatNumber, gate);
    }

    public String getFlight(){
        return "FlightId: "+flightId+"\nDestination: "+destination+"\n---BoardingPass---\n"+obj;
    }
}
