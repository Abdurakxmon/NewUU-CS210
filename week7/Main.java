import java.time.LocalTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("--------Airline Management System--------\n");

        Address tasAddress = new Address("13-uy, Qumariq koʻchasi, Tashkent", "Tashkent", "TAS", "100167", "Uzbekistan");
        Address laxAddress = new Address("1 World Way", "Los Angeles", "CA", "90045", "USA");
        Address chiAddress = new Address("10000 W O'Hare Ave", "Chicago", "IL", "60666", "USA");

        Airport tas = new Airport("Tashkent International Airport", tasAddress, "TAS");
        Airport lax = new Airport("Los Angeles International Airport", laxAddress, "LAX");
        Airport ord = new Airport("O'Hare International Airport", chiAddress, "ORD");

        Aircraft boeing737 = new Aircraft("Boeing 737-800", "B738", 2020);
        boeing737.addSeat(new Seat("1A", SeatType.REGULAR, SeatClass.BUSINESS));
        boeing737.addSeat(new Seat("1B", SeatType.REGULAR, SeatClass.BUSINESS));
        boeing737.addSeat(new Seat("10A", SeatType.REGULAR, SeatClass.ECONOMY));
        boeing737.addSeat(new Seat("10B", SeatType.REGULAR, SeatClass.ECONOMY));
        boeing737.addSeat(new Seat("10C", SeatType.REGULAR, SeatClass.ECONOMY));
        boeing737.addSeat(new Seat("11A", SeatType.EMERGENCY_EXIT, SeatClass.ECONOMY_PLUS));
        boeing737.addSeat(new Seat("11B", SeatType.EMERGENCY_EXIT, SeatClass.ECONOMY_PLUS));

        Flight flight101 = new Flight("BA101", tas, lax, 360); // 6 hours

        WeeklySchedule weeklyMon = new WeeklySchedule(1, LocalTime.of(9, 0));
        flight101.addWeeklySchedule(weeklyMon);
        CustomSchedule custom = new CustomSchedule(new Date(2025 - 1900, 5, 15), LocalTime.of(14, 30)); // June 15, 2025
        flight101.addCustomSchedule(custom);

        Date instanceDate = new Date(2025 - 1900, 5, 15, 14, 30);
        FlightInstance instance101 = new FlightInstance(flight101, instanceDate, "GATE A12", boeing737);

        System.out.println("Flight " + flight101.getFlightNumber() + " created from " + tas.getCode() + " to " + lax.getCode());

        Account customerAccount = new Account("abdurakhmon", "12345678!!");
        Address customerAddress = new Address("Beshyagach 20", "Tashkent", "TAS", "100066", "UZB");
        Customer abdurakhmon = new Customer("Abdurakhmon Mukhibbillaev", customerAddress, "a.muxibbillayev@newuu.uz", "+998(90)142-02-25", customerAccount, "FA1234567");

        Passenger umidjon = new Passenger("Umidjon Xusanov", "AB1234567", new Date(2005, 5, 20));
        Passenger bob = new Passenger("Bobur Yoqubov", "CD7890123", new Date(2005, 2, 10));

        Itinerary itinerary = new Itinerary(abdurakhmon.getAccount().getId(), tas, lax);

        FlightReservation reservation = new FlightReservation("RES12345", instance101);
        FlightSeat seat1A = new FlightSeat("1A", SeatType.REGULAR, SeatClass.BUSINESS, 500.00);
        FlightSeat seat10A = new FlightSeat("10A", SeatType.REGULAR, SeatClass.ECONOMY, 200.00);
        reservation.assignSeat(umidjon, seat1A);
        reservation.assignSeat(bob, seat10A);

        itinerary.makeReservation(reservation);





        Payment payment = new Payment("PAY001", 700.00);
        boolean paid = itinerary.makePayment(payment);
        if (paid) {
            System.out.println("Payment successful. Reservation confirmed.");
        } else {
            System.out.println("Payment failed.");
        }

        Notification notification = new Notification(
                "NOTIF1",
                abdurakhmon.getEmail(),
                "Your reservation " + reservation.getReservationNumber() + " has been confirmed."
        );
        notification.send();

        Person personRef = abdurakhmon;
        System.out.println("Person: " + personRef.getName() + " is a customer with frequent flyer number: " + ((Customer)personRef).getFrequentFlyerNumber());

        System.out.println("\nReservation Details:");
        System.out.println("Reservation number: " + reservation.getReservationNumber());
        System.out.println("Flight: " + reservation.getFlight().getFlight().getFlightNumber() + " on " + reservation.getFlight().getDepartureTime());
        System.out.println("Passengers:");
        for (Passenger p : reservation.getPassengers()) {
            System.out.println(" - " + p.getName() + " (Passport: " + p.getPassportNumber() + ")");
        }
        System.out.println("Status: " + reservation.getStatus());

        Account adminAccount = new Account("admin", "adminpass");
        Address adminAddress = new Address("1 Admin Plaza", "NYC", "NY", "10000", "USA");
        Admin admin = new Admin("Admin User", adminAddress, "admin@airline.com", "1234567890", adminAccount);
        admin.addAircraft(boeing737);
        admin.addFlight(flight101);

        System.out.println("\nAdmin cancels flight instance...");
        admin.cancelFlight(instance101);
        System.out.println("Flight instance status: " + instance101.getStatus());

        System.out.println("\nAircraft " + boeing737.getName() + " has flown " + boeing737.getFlights().size() + " flight instance(s).");
    }
}
