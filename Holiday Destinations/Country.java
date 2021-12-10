import java.util.ArrayList;
import java.util.stream.StreamSupport;

public class Country {
    private String name;
    private String language;
    private String flag;

    public Country(String name, String language, String flag) {
        this.name = name;
        this.language = language;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    public String getFlag() {
        return flag;
    }

    private ArrayList<Destination> destinations = new ArrayList<>();   //create arraylist for destinations
    public void addDestination(Destination name){
        destinations.add(name);
    }  //add destinations to arraylist
    public ArrayList<Destination> getDestinations() {
        return destinations;
    }
    public void numOfDestinations(){
        System.out.println(destinations.size());
    }

    public Destination highestCapacity(){   //returns the destination with the highest capacity in the coutnry
        int biggestCapacity = 0;
        Destination highestCapacityDestination = null;
        for (Destination destination: destinations){
            int topCapacity = destination.getCapacity();
            if (biggestCapacity < topCapacity){
                biggestCapacity = topCapacity;
                highestCapacityDestination = destination;
            }
        }
        return highestCapacityDestination;
    }
    public double averageCapacity(){   //returns the average capacity of the destinations in a country
        int avgCap = 0;
        for (int i = 0; i < destinations.size();i++){
            avgCap += destinations.get(i).getCapacity();
        }
        double finalCap = avgCap / destinations.size();
        return finalCap;
        //System.out.println(finalCap);
    }
    public ArrayList<Destination> biggerThan(int bigger){  //returns an arraylists of all destinations with capacity
                                                            //bigger than a given number
        ArrayList<Destination> biggerThanDest = new ArrayList<>();
        for (Destination destination: destinations) {
            if (destination.getCapacity() > bigger){
                biggerThanDest.add(destination);
            }
        }
        return biggerThanDest;
    }
    public String toString(){
        return (name + " " + language + " " + flag);
    }
}
