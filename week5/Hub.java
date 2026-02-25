public class Hub<T> {
    private T device;

    public void saveDevice(T obj){
        this.device = obj;
    }

    public T getDevice() {
        return device;
    }

    public String getConnectedDevice(){
        if(device==null) return "No device connected";
        return device.toString();
    }
}
