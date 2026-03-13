import java.time.LocalTime;

class WeeklySchedule {
    private int dayOfWeek;
    private LocalTime departureTime;

    public WeeklySchedule(int dayOfWeek, LocalTime departureTime) {
        this.dayOfWeek = dayOfWeek;
        this.departureTime = departureTime;
    }

    public int getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(int dayOfWeek) { this.dayOfWeek = dayOfWeek; }
    public LocalTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalTime departureTime) { this.departureTime = departureTime; }
}