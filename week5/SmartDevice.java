public class SmartDevice {
    private boolean isPoweredOn;
    private String brand;

    public SmartDevice(String brand, boolean flag){
        this.brand = brand;
        this.isPoweredOn = flag;
    }

    public String toString(){
        return "Brand: "+brand+"\nStatus: "+(isPoweredOn ? "On" : "Off");
    }


}
