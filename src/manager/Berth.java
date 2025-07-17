package manager;
public class Berth {
    private String berthID;
    private String category;
    private double dockingFee;
    private BerthStatus status;

    public Berth(String berthID, String category,  double dockingFee) {
        this.berthID = berthID;
        this.category = category;
        this.dockingFee = dockingFee;
        this.status = BerthStatus.FREE;
    }

    public String getBerthID() { return berthID; }
    public String getCategory() { return category; }
    public double getDockingFee() { return dockingFee; }
    public BerthStatus getStatus() { return status; }
    public void setStatus(BerthStatus s) { this.status = s; }

    public void displayInfo() {
        System.out.println("Berth " + berthID +
                " | Category: " + category +
                " | Fee: " + dockingFee +
                " | Status: " + status);
    }
}