public class Sensor {
    private String location;
    private double reading;

    public Sensor(String location, double reading) {
        setLocation(location);
        setReading(reading);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setReading(double reading) {
        if (reading < -50 || reading > 50) {
            reading = 0.0;
        }
        this.reading = reading;
    }

    public double getReading() {
        return reading;
    }

    public String getLocation() {
        return location;
    }


    public static double calculateAverage(Sensor[] sensors) {
        double sum = 0;

        for (Sensor s : sensors) {
            sum += s.getReading();
        }

        return sum / sensors.length;
    }


}