import java.util.ArrayList;

public class Continent {
    private String name;

    public Continent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    private ArrayList<Country> countries = new ArrayList<>();     //creates arraylist for countries
    public void addCountry(Country name){
        countries.add(name);
    } //adds a country to the arraylist
    public boolean isEmpty(){
        return this.isEmpty();
    }
    public Country highestAvgCountry(){         //returns the country with highest average capacity of destinations
        double topHighCap = 0;
        Country highestAvgCap = null;
        for (Country country: countries){
            double topCapCountry = country.averageCapacity();
            if (topHighCap < topCapCountry){
                topHighCap = topCapCountry;
                highestAvgCap = country;
            }
        }
        return highestAvgCap;
    }
    public ArrayList<Country> getCountries() {
        return countries;
    }
    public Destination highestCapDest(){   //returns the destination with the highest capacity from all countries
        int highestCap = 0;
        Destination highestCapDest = null;
        for (Country country: countries){
            for (Destination destination: country.getDestinations()){
                int topCountry = destination.getCapacity();
                if (highestCap < topCountry){
                    highestCap = topCountry;
                    highestCapDest = destination;
                }
            }
        }
        return highestCapDest;
    }
    public ArrayList<Country> countriesBiggerThan(int bigger){    //returns an arraylist of all countries with
                                                                    //average capacity bigger than a given number
        ArrayList<Country> countryBiggerThan = new ArrayList<>();
        for (Country country: countries){
            if (country.averageCapacity() > bigger){
                countryBiggerThan.add(country);
            }
        }
        return countryBiggerThan;
    }
    public String toString(){
        return (name);
    }
}
