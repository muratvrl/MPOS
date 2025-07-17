package manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class DockReservation {
    private String reservationID;
    private String shipName;
    private String berthID;
    private LocalDateTime timestamp;
    private static final DateTimeFormatter FMT =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //constructor
    public DockReservation(String reservationID, String shipName, String berthID, LocalDateTime timestamp) {
        this.reservationID = reservationID;
        this.shipName = shipName;
        this.berthID = berthID;
        this.timestamp = timestamp;
    }

    public String getReservationID() { return reservationID; }
    public String getShipName(){ return shipName; }
    public String getBerthID(){ return berthID; }

    @Override
    public String toString() {
        return String.format("Reservation[id=%s, ship=%s, berth=%s, time=%s]",
                reservationID, shipName, berthID, timestamp.format(FMT));
    }
}
