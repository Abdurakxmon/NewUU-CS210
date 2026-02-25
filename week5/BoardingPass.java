public class BoardingPass {
    private String seatNumber;
    private String gate;

    public BoardingPass(String seatNumber, String gate){
        this.gate = gate;
        this.seatNumber = seatNumber;
    }

    public String toString(){
        return "seatNumber: "+seatNumber+"\ngate: "+gate;
    }
}
