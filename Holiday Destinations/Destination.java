public class Destination {
    private String name;
    private int capacity;
    private double latitude;
    private double longitude;

    public Destination(String name, int capacity, double latitude, double longitude) {
        this.name = name;
        this.capacity = capacity;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public String toString(){
        return (name + " Capacity: " + capacity + ", Latitude: " + latitude + ", Longitude: " + longitude);
    }
}
