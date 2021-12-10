import java.util.Scanner;
/*
    !!!LEVEL 3!!! Included cmd commands to compile the program overriding the start date
    If an incorrect value for day or month is entered in the console, the game will start from the default dates of 1.
    Game with 2 players taking turns which ends when a player reaches 31st of December.
    When the current day number is higher than the maximum possible day of the month a player wants to select,
the game will give an invalid input.
    It is not possible for a player to attempt to roll back the date to a previous date.
 */

public class DaysOfTheYear {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) { /*This is my array to call arguments from the cmd */
        // Declaring some variables and array
        int day = 1;
        int month = 1;
        if (args.length > 1){  //gives arguments to the cmd array
            try{  // if value other than int is entered in cmd the game will start with default values
                day = Integer.parseInt(args[0]);
                month = Integer.parseInt(args[1]);
            }catch (NumberFormatException e){
                System.out.println("String is not valid input.");
            }
        }
        if (month > 12 || month < 1){//if no arguments given starts as normal
            month = 1;
        }
        if (!dayAllowed(day, month)){
            day = 1;
        }
        String[] monthss = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"};
        System.out.println("It is " + day + getSuffix(day) + " of " + monthss[month-1]);
        String player2;
        String player1;
        String agent1 = "Player 1";
        String agent2 = "Player 2";
        String winner = agent2;
        while (day != 31 || month != 12) {// the main loop which makes the players turns rotate.
            boolean canMonth = month < 12;
            boolean canDay = dayAllowed(day+1, month);

            System.out.print(agent1 + " 's turn. Choose ");
            if (canDay){
                System.out.print("Day ");
            }
            if (canMonth && canDay){
                System.out.print("or ");
            }
            if (canMonth){
                System.out.print("Month ");
            }
            player1 = in.nextLine().toLowerCase().trim();    ////prevents values different to day and month
            while (!player1.equals("day") && !player1.equals("month")  ||
                    (player1.equals("month") && !canMonth) || player1.equals("day") && !canDay){
                System.out.println("Invalid input. Try again: ");
                player1 = in.nextLine().toLowerCase().trim();  //trims and makes into lower case
            }
            if (player1.equals("day")) {
                day = getDay(day, month);  // calls method getDay
            } else {
                month = getMonth(day, month);  // calls method getMonth
            }
            System.out.println("The new date is " + day + getSuffix(day) + " of " + monthss[month-1]);
            if (day == 31 && month == 12){ /* in the case of player1 winning, this ends the lop halfway through,
                                                otherwise player 2 wins by default */
                winner = agent1;
                break;
            }
            canDay = dayAllowed(day+1, month);
            canMonth = month < 12;
            //Same things apply here
            System.out.print(agent2 + " 's turn. Choose ");
            if (canDay){
                System.out.print("Day ");
            }
            if (canMonth && canDay){
                System.out.print("or ");
            }
            if (canMonth){
                System.out.print("Month ");
            }
            player2 = in.nextLine().toLowerCase().trim();
            while (!player2.equals("day") && !player2.equals("month") ||
                    (player2.equals("month") && !canMonth) || player2.equals("day") && !canDay){
                System.out.println("Invalid input. Try again: ");
                player2 = in.nextLine().toLowerCase().trim();
            }
            if (player2.equals("day")) {
                day = getDay(day, month);
            } else {
                month = getMonth(day, month);
            }
            System.out.println("The new date is " + day + getSuffix(day) + " of " + monthss[month-1]);
        }
        System.out.println(winner + " is the winner of the game!");
    }
    static int getNumber(String msg, int min, int max){   /*this method creates the basic framework of constraints for
                                                            inputs of the different variables */
        System.out.println(msg);
        int value;
        while (!in.hasNextInt()){          //prevents the program crashing if input is not int
            System.out.println("Invalid input. Try again: ");
            in.nextLine();
        }
        value = in.nextInt();
        in.nextLine();
        while (value <= min || value > max) {       /*creating the actual constraint, the values are defined above when
                                                        calling the function */
            System.out.println("Invalid input. " + msg);
            while (!in.hasNextInt()){
                System.out.println("Invalid input. Try again: ");
                in.nextLine();
            }
            value = in.nextInt();
            in.nextLine();
        }

        return value;
    }
    static int getDay(int day, int month){   //method to create case specific constraints for days

        int newDay = getNumber("What day do you choose?", day, 31);
        while (!dayAllowed(newDay, month)){
            newDay = getNumber("Invalid input. Try again. ", day, 31);
        }
        return newDay;
    }
    static String getSuffix(int n){   //method that adds suffix at the end of each day
        if (n > 3 && n < 20){
            return "th";
        }
        return switch (n % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }
    static int getMonth( int day, int month){             /* method that makes it impossible to jump to a month which */
        /*  has less maximum days than the current day */
        int newMonth = getNumber("What month do you chose?", month, 12);
        while (!monthAllowed(day, newMonth)){
            newMonth = getNumber("Invalid Input. Try again.", month, 12);
        }
        return newMonth;
    }
    static boolean monthAllowed(int day, int month){
        return !((day > 28 && month == 2) || day == 31 && (month == 4 || month == 6 || month == 9 || month == 11));
    }
    static boolean dayAllowed(int day, int month){
        int max;
        if (month == 8){
            max = 31;
        } else if (month == 2){
            max = 28;
        } else if (month == 9 || month == 11){
            max = 30;
        } else if (month == 12){
            max = 31;
        }
        else {
            max = month % 2 == 0 ? 30 : 31;
        }
        return day <= max;
    }
}
