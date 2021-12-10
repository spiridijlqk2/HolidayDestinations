import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Booking {
    public static Scanner in = new Scanner(System.in);
    public static ArrayList<Continent> continents = new ArrayList<>(Arrays.asList(new Continent("Europe"),
            new Continent("Asia"),
            new Continent("North America"), new Continent("South America"), new Continent("Oceania"),
            new Continent("Africa"), new Continent("Antarctica")));
    public static void main(String[] args){
        int option = 0;
        while (option != 4){
            printMenu();
            while (!in.hasNextInt()){
                printMenu();
                in.nextLine();
            }
            option = in.nextInt();
            in.nextLine();
            if (option == 1){
                addCountry();
            } else if (option == 2){
                addDestination();
            } else if (option == 3){
                if (numOfDestinations() == 0){
                    System.out.println("No destinations. Choose another option.");
                    continue;
                }
                System.out.println("Enter the minimum capacity of destinations that you want to see: ");
                while (!in.hasNextInt()){
                    System.out.println("Incorrect input. Enter capacity: ");
                    in.nextLine();
                }
                int minCapacity = in.nextInt();
                in.nextLine();
                System.out.println("The country that has recorded the highest average destinations capacity is: "
                + highestAvgCountry().getName() + ", with an average capacity of " + highestAvgCountry().averageCapacity());
                System.out.println("The highest capacity destination recorded is: " + highestCapDestination().getName()
                + ", with a capacity of " + highestCapDestination().getCapacity());
                System.out.println("The destinations with a capacity higher than " + minCapacity + " are:");
                biggerThan(minCapacity);
            }
        }
    }
    public static void printMenu (){       //prints out the main menu which user interface
        System.out.println("Please choose an option. ");
        System.out.println("1. Add country to continent. ");
        System.out.println("2. Add details of a destination to a country.");
        System.out.println("3. Provide statistics for countries.");
        System.out.println("4. Exit.");
    }
    public static void addCountry(){   //adds a country to the chosen continent
        Continent continent;
        String countryName;
        String countryLanguage;
        String countryFlag;

        continent = getContinent();
        System.out.println("Enter name of country:");
        countryName = in.nextLine();
        while(checkCountry(countryName)){   //checks whether a country already exists so it can't be added again
            System.out.println("Enter name of country:");
            countryName = in.nextLine();
        }
        System.out.println("Enter language of country:");
        countryLanguage = in.nextLine();
        System.out.println("Enter flag of country. Green or red:");
        countryFlag = in.nextLine();
        while (!countryFlag.equalsIgnoreCase("Green") && !countryFlag.equalsIgnoreCase("Red")){
            System.out.println("Incorrect input. Country flag can be only Green or Red: ");
            countryFlag = in.nextLine();
        }
        continent.addCountry(new Country(countryName, countryLanguage, countryFlag));
        System.out.println("Thank you. Country added.");
    }
    public static Continent getContinent(){   //allows user to choose a continent to add a country to
        System.out.println("Choose continent:");
        System.out.println("1. Europe\n2. Asia\n3. North America\n4. South America\n5. Oceania\n6. Africa\n" +
                "7.Antarctica");
        while (!in.hasNextInt()){   //checks whether the input is Int so program doesnt crash
            System.out.println("1. Europe\n2. Asia\n3. North America\n4. South America\n5. Oceania\n6. Africa\n" +
                    "7.Antarctica");
            in.nextLine();
        }
        int option = in.nextInt();
        in.nextLine();
        while (option < 1 || option > 7){
            System.out.println("Choose a valid option:");
            option = in.nextInt();
            in.nextLine();
        }
        System.out.println("You have chosen " + continents.get(option-1));
        return continents.get(option-1);
    }
    public static Country getCountry(){   //method to choose a country to the list
        boolean found = false;
        System.out.println("Write the name of the country you wish to add a destination to:");
        Country finalChoice = null;
        String choice = in.nextLine();
        while (!found){
            for (Continent continent: continents){
                for (Country country: continent.getCountries()){
                    if (country.getName().equalsIgnoreCase(choice)){
                        finalChoice = country;
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("Country doesn't exist.");
                System.out.println("Write the name of the country you wish to add a destination to:");
                choice = in.nextLine();
            }
        }
        System.out.println("The country you have chosen is: " + finalChoice.getName());
        return finalChoice;
    }
    public static void addDestination(){  //method to add destination to the list
        Country country;
        String destName;
        int destCap;
        double destLatitude;
        double destLongitude;
        if (numOfCountries() == 0){
            System.out.println("There are no countries. Choose another option.");
            return;
        }
        country = getCountry();
        System.out.println("Enter name of destination: ");
        destName = in.nextLine();
        while (checkDestination(destName)){
            System.out.println("Enter name of destination: ");
            destName = in.nextLine();
        }
        System.out.println("Enter capacity of destination: ");
        while (!in.hasNextInt()){
            System.out.println("Incorrect input. Enter capacity: ");
            in.nextLine();
        }
        destCap = in.nextInt();
        in.nextLine();
        System.out.println("Enter latitude of destination: ");
        while (!in.hasNextDouble()){
            System.out.println("Incorrect input. Enter latitude: ");
            in.nextLine();
        }
        destLatitude = in.nextDouble();
        in.nextLine();
        System.out.println("Enter longitude of destination: ");
        while (!in.hasNextDouble()){
            System.out.println("Incorrect input. Enter longitude: ");
            in.nextLine();
        }
        destLongitude = in.nextDouble();
        in.nextLine();
        country.addDestination(new Destination(destName, destCap, destLatitude, destLongitude));
        System.out.println("Thank you. Destination added");
    }
    public static Country highestAvgCountry(){   //method to get the country with highest average capacity of dests in it
        double topCapCountry = 0;
        Country topCountry = null;
        for (Continent continent: continents){
            if (continent.getCountries().isEmpty()){
                continue;
            }
            double highestCapCountry = continent.highestAvgCountry().averageCapacity();
            if (topCapCountry < highestCapCountry){
                topCapCountry = highestCapCountry;
                topCountry = continent.highestAvgCountry();
            }
        }
        return topCountry;
    }
    public static Destination highestCapDestination(){  //finds the destination with the highest capacity everywhere
        int highestCapDest = 0;
        Destination topDestination = null;
        for (Continent continent: continents){
            for (Country country: continent.getCountries()){
                int maxCapDest = country.highestCapacity().getCapacity();
                if (highestCapDest < maxCapDest){
                    highestCapDest = maxCapDest;
                    topDestination = country.highestCapacity();
                }
            }
        }
        return topDestination;
    }
    public static void biggerThan(int bigger){ //method to show all destinations with capacity bigger than a given number
        for (Continent continent: continents){
            for (Country country: continent.getCountries()){
                for (Destination destination: country.biggerThan(bigger)){
                    System.out.println(destination.getName());
                }
            }
        }
    }
    public static int numOfCountries(){ //method to check how many countries there are in a continent
        int num = 0;
        for (Continent continent: continents){
            num += continent.getCountries().size();
        }
        return num;
    }
    public static int numOfDestinations(){  // returns the number of destinations in a country
        int num = 0;
        for (Continent continent: continents){
            for (Country country: continent.getCountries()){
                num += country.getDestinations().size();
            }
        }
        return num;
    }
    public static boolean checkCountry(String name){ // checks whether a country already exists in the list
        for (Continent continent: continents){
            for (Country country: continent.getCountries()){
                if(country.getName().equalsIgnoreCase(name)){
                    System.out.println("Country already exists. Enter another one.");
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean checkDestination(String name){  // checks whether a destination already exists in the list
        for (Continent continent: continents){
            for (Country country: continent.getCountries()){
                for (Destination destination: country.getDestinations()){
                    if (destination.getName().equalsIgnoreCase(name)){
                        System.out.println("Destination already exists. Enter another one.");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
