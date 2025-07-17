package app;

import manager.Berth;
import manager.BerthStatus;
import manager.DockReservation;
import model.BerthManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static BerthManager berthManager = new BerthManager();

    public static void main(String[] args) {
        berthManager.addBerth("B101", "Container",  500.0);
        berthManager.addBerth("B102", "Passenger",  300.0);
        berthManager.addBerth("B103", "Passenger",  300.0);
        berthManager.addBerth("B104", "Container",  500.0);
        berthManager.addBerth("B105", "Passenger",  300.0);
        berthManager.addBerth("B106", "Container",  500.0);

        int choice;
        do {
            displayMenu();
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    berthManager.displayAllBerths();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    vesselDocking();
                    break;
                case 4:
                    vesselUndocking();
                    break;
            }
        } while (choice != 5);
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("--- MPOS ---");
        System.out.println("1. Display all berths");
        System.out.println("2. Make a berth reservation");
        System.out.println("3. Vessel docking");
        System.out.println("4. Vessel undocking");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void makeReservation() {
        System.out.print("Ship name: ");
        String ship = scanner.nextLine();
        System.out.print("Berth type: ");
        String type = scanner.nextLine();
        String resID = "R" + System.currentTimeMillis();
        DockReservation r = new DockReservation(resID, ship, type, LocalDateTime.now());
        berthManager.addReservation(r);
        System.out.println("Created: " + resID);
    }

    private static void vesselDocking() {
        if (berthManager.isReservationListEmpty()) {
            System.out.println("No reservations.");
            return;
        }
        System.out.print("Ship name for docking: ");
        String ship = scanner.nextLine();
        DockReservation r = berthManager.removeReservationByShipName(ship);
        if (r == null) {
            System.out.println("No reservation found.");
            return;
        }
        Berth b = findAvailable(r.getBerthID());
        if (b != null) {
            berthManager.updateBerthStatus(b.getBerthID(), BerthStatus.OCCUPIED);
            System.out.println("Docked at " + b.getBerthID());
        } else {
            System.out.println("No free berth.");
        }
    }

    private static void vesselUndocking() {
        System.out.print("Berth ID for undocking: ");
        String id = scanner.nextLine();
        Berth b = berthManager.getBerth(id);
        if (b != null && b.getStatus() == BerthStatus.OCCUPIED) {
            berthManager.updateBerthStatus(id, BerthStatus.FREE);
            System.out.println("Berth " + id + " is now FREE.");
        } else {
            System.out.println("Invalid or already free.");
        }
    }

    private static Berth findAvailable(String type) {
        for (String id : berthManager.getBerths().keySet()) {
            Berth b = berthManager.getBerths().get(id);
            if (b.getCategory().equalsIgnoreCase(type)
                    && b.getStatus() == BerthStatus.FREE) {
                return b;
            }
        }
        return null;
    }
}