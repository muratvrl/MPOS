package model;



import manager.Berth;
import manager.BerthStatus;
import manager.DockReservation;
import datastruct.MyHashMap;
import datastruct.MyLinkedList;
public class BerthManager {
    private MyHashMap<String, Berth> berths;
    private MyLinkedList<DockReservation> reservations;

    public BerthManager() {
        berths = new MyHashMap<>();
        reservations = new MyLinkedList<>();
    }

    public void addBerth(String berthID, String category,double fee) {
        Berth b = new Berth(berthID, category, fee);
        berths.put(berthID, b);
    }

    public Berth getBerth(String berthID) {
        return berths.get(berthID);
    }

    public void updateBerthStatus(String berthID, BerthStatus status) {
        Berth berthToUpdate = berths.get(berthID);
        if (berthToUpdate != null) berthToUpdate.setStatus(status);
    }

    public void displayAllBerths() {
        for (Berth displayedBerth : berths.values()) {
            displayedBerth.displayInfo();
        }
    }

    public void addReservation(DockReservation newReservation) {
        reservations.add(newReservation);
    }

    public DockReservation removeReservationByShipName(String shipName) {
        for (java.util.Iterator<DockReservation> ReIt = reservations.iterator(); ReIt.hasNext(); ) {
            DockReservation r = ReIt.next();
            if (r.getShipName().equalsIgnoreCase(shipName)) {
                ReIt.remove();
                return r;
            }
        }
        return null;
    }


    public boolean isReservationListEmpty() {
        return reservations.isEmpty();
    }

    public MyHashMap<String, Berth> getBerths() {
        return berths;
    }
}