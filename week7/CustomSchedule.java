import java.time.LocalTime;
import java.util.Date;

class CustomSchedule {
    private Date customDate;
    private LocalTime departureTime;

    public CustomSchedule(Date customDate, LocalTime departureTime) {
        this.customDate = customDate;
        this.departureTime = departureTime;
    }

    public Date getCustomDate() { return customDate; }
    public void setCustomDate(Date customDate) { this.customDate = customDate; }
    public LocalTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalTime departureTime) { this.departureTime = departureTime; }
}