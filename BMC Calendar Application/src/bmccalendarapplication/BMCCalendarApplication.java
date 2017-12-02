/*
 * This is a program which calculates and displays a formatted calendar.
 * The application takes the input from the user including year, month, day and day of week.
 * It then displays the calendar for the month which was entered when the option from the menu is selected.
 * It can also display the day of week, day, month, year and whether the year is a leap year when the option from the menu is selected.
 */
package bmccalendarapplication;

import java.util.Scanner;

/**
 * @author MCC15097633 - Ross McCully
 * @version 3.0, 02/12/2016
 */
public class BMCCalendarApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Scanner for input
        Scanner input = new Scanner(System.in);
        //Initialising variables
        // Quit variable is changed to true when menu option 4 is selected which closes the program
        boolean quit = false;
        //isLeapYear used to determine whether a year entered is a leap year
        //Put to use in menu option 3 when printing whether the year is a leap year or not
        boolean isLeapYear = false;
        //isDateSet used to check if menu option 1 has been set when trying to select option 2 and 3
        boolean isDateSet = false;
        //option used for when user selects menu option. Is then set depending on their input
        int option = 0;
        //year, month, day, dayOfWeek used when setting date
        int year = 0;
        int month = 0;
        int day = 0;
        int dayOfWeek = 0;
        //startDayOfMonth used to work out the day in which the month starts
        int startDayOfMonth;
        //MAX_COLS sets the maximum columns for the calendar
        final int MAX_COLS = 7;
        //maxRows is changed depending on the amount of days in a month
        //Set in option case 2 of switch
        int maxRows;

        //monthName used to display the month name according to month number input in case 3
        String[] monthName = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        //weekDay used to print out the day of the week according to the dayOfWeek input from the user
        String[] weekDay = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        //Displays menu for the 4 basic functions of the program
        //A Do-While loop is used so the contents of the Do loop will be executed 
        //atleast once as long as boolean statement is true found in the while
        do {
            System.out.println("BMC Calendar Application");
            System.out.println();
            System.out.println("Please choose from the following options:");
            System.out.println();
            System.out.println("1. Set Date");
            System.out.println("2. Display calendar for this month");
            System.out.println("3. Display date information");
            System.out.println("4. Quit");
            boolean ok = false;
            //This do-while statement is used for the user entering their option
            //in the menu
            //The do-while loop will execute as long as the boolean ok is false
            do {
                //Try and catch is used to test the input on whether it is valid using exception handling
                //If the user input is invalid it will display the message 'Error - numbers only'
                try {
                    System.out.print("Option: ");
                    option = input.nextInt();
                    System.out.println("");
                    ok = true;
                } catch (Exception e) {
                    System.out.println("Error - numbers only");
                    //Resets the input
                    input.nextLine();
                }
            } while (ok == false);
            //Option switch is used to carry out a function depending on the user's menu option selection
            //using input above
            switch (option) {
                //Option 1
                //When Option 1 is selected the user is asked to enter a year after 1999
                //Exception handling is used
                case 1:

                    //Set to false so if user goes to re-enter date after already entering it, 
                    //it'll reset isLeapYear and isDateSet
                    isDateSet = false;
                    isLeapYear = false;

                    try {
                        System.out.print("Enter Year After 1999: ");
                        year = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("You must enter a year after 1999");
                        System.out.println();
                        break;
                    }
                    //Validates the input from the user and works out whether it's a leap year
                    if ((year >= 2000) && (year % 400 == 0) || (year >= 2000) && (year % 4 == 0) && (year % 100 != 0)) {
                        //Setting boolean for leap year to true if above statement true
                        isLeapYear = true;
                    } else if (year <= 1999) {
                        //If year is before or equal to 1999 then prints following statement
                        System.out.println("Problem with year entry: " + year + "\n");
                        break;
                        //At this point it will send them back to the menu
                    } else ;
                    //User enters the month and month variable initialised from input
                    try {
                        System.out.print("Enter Month (1 to 12): ");
                        month = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("General: " + e.toString());
                        System.out.println();
                        break;
                    }
                    //Validating if month is between 1 and 12 as requested
                    if (month < 1 || month > 12) {
                        System.out.println("Problem with month entry: " + month + "\n");
                        isLeapYear = false;
                        break;
                    }
                    //User enters the day and day variable initialised from input
                    try {
                        System.out.print("Enter Day: ");
                        day = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("General: " + e.toString());
                        System.out.println();
                        break;
                    }
                    //Validating days depending on the month entered
                    if (month == 6 || month == 9 || month == 4 || month == 11) {
                        if (day < 1 || day > 30) {
                            System.out.println("Problem with day entry: " + day);
                            System.out.println("Must be between 1 and 30\n");
                            break;
                        }
                    } else if (month == 2) {
                        //Validates day input for February as it's a leap year - compares against isLeapYear variable
                        if (isLeapYear) {
                            if (day < 1 || day > 29) {
                                System.out.println("Problem with entry: " + day);
                                System.out.println("Must be between 1 and 29\n");
                                break;
                            }
                            //If it's not a leap year prints the following below
                        } else if (isLeapYear == false) {
                            if (day < 1 || day > 28) {
                                System.out.println("Problem with entry: " + day);
                                System.out.println("Must be between 1 and 28\n");
                                break;
                            }
                        }
                    } else //If month is 1, 3, 5, 7, 8, 10, 12 then days are 31 and validates entry
                    {
                        if (day < 1 || day > 31) {
                            System.out.println("Problem with entry: " + day);
                            System.out.println("Must be between 1 and 31\n");
                            break;
                        }
                    }

                    //User enters the day of week variable and initialised to the dayOfWeek variable
                    try {
                        System.out.print("Enter Day of week (1(Sun)-7(Sat)): ");
                        dayOfWeek = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("General: " + e.toString());
                        System.out.println();
                        break;
                    }
                    //Validates day of week input 
                    if (dayOfWeek < 1 || dayOfWeek > 7) {
                        System.out.println("Problem with entry: " + dayOfWeek + "\n");
                        break;
                    }

                    //Sets the date set to true as the user has successfully input
                    //valid data
                    isDateSet = true;
                    System.out.println();
                    break;

                case 2:
                    /* The IF statement checks whether date is set and if it has
                     not been set then it prints out the message, then sends the
                     user back to the menu
                     The reason for this is that if the user has not set the 
                     date in the first menu option it will not be able to 
                     display the calendar
                     */
                    if (!isDateSet) {
                        System.out.print("Please set the date first");
                        System.out.println("\n");
                        break;
                    }

                    //Sets the first day of the calendar to one.
                    //Also, each time the user selects option 2 in the menu it will reset the 
                    //calendar date (if it has been set before) back to one.
                    int setDayCal = 1;

                    //Setting the maximum days which each month can have
                    int maxDays;
                    if (month == 4 || month == 6 || month == 9 || month == 11) {
                        maxDays = 30;
                    } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                        maxDays = 31;
                    } else if (month == 2 && isLeapYear == true) {
                        maxDays = 29;
                    } else {
                        maxDays = 28;
                    }

                    //Work out first day for calender
                    int daysInMonth = day % 7;
                    if (daysInMonth >= dayOfWeek) {
                        daysInMonth = daysInMonth - dayOfWeek;
                        dayOfWeek = 7;
                    }
                    startDayOfMonth = ((dayOfWeek - daysInMonth) % 7);

                    //Making first day a positive number and makes the day equal to the array
                    //If first day was a negative number you would get an array out of bounds error
                    if (startDayOfMonth < 0) {
                        startDayOfMonth = startDayOfMonth + 7;
                    }

                    //Setting the max rows depending on the month input
                    if (month != 2) {
                        switch (startDayOfMonth) {
                            case 5:
                                if (maxDays == 30) {
                                    maxRows = 5;
                                } else {
                                    maxRows = 6;
                                }
                                break;
                            case 6:
                                maxRows = 6;
                                break;
                            default:
                                maxRows = 5;
                                break;
                        }

                    } else if (isLeapYear == false) {
                        if (startDayOfMonth > 0) {
                            maxRows = 5;
                        } else {
                            maxRows = 4;
                        }
                    } else {
                        maxRows = 5;
                    }

                    //Setting up a 2D array for the calendar
                    String[][] bmccalendar = new String[maxRows][MAX_COLS];

                    /*
                    Filling first row
                    If statement used to fill dashes up to the first day
                    of the month
                    */
                    for (int i = 0; i < 7; i++) {
                        if (i < startDayOfMonth) {
                            bmccalendar[0][i] = "-";
                        } else {
                            bmccalendar[0][i] = setDayCal + "";
                            setDayCal++;
                        }
                    }

                    //Filling middle rows
                    for (int i = 1; i < 4; i++) {
                        for (int j = 0; j < 7; j++) {
                            bmccalendar[i][j] = setDayCal + "";
                            setDayCal++;
                        }

                    }

                    /*
                    Filling the last row
                    Dashes are used to fill the days after the last day of
                    the month
                    */
                    for (int i = 4; i < maxRows; i++) {
                        for (int j = 0; j < MAX_COLS; j++) {
                            if (setDayCal <= maxDays) {
                                bmccalendar[i][j] = setDayCal + "";
                                setDayCal++;
                            } else {
                                bmccalendar[i][j] = "-";
                            }

                        }
                    }

                    //Setting format for printing out the completed calendar
                    System.out.println("Calendar for " + monthName[month - 1] + " " + year);
                    System.out.println("Su \tMo \tTu \tWe \tTh \tFr \tSa");

                    //These for loops print the completed calendar with the values filled from above
                    for (int i = 0; i < maxRows; i++) {
                        for (int j = 0; j < MAX_COLS; j++) {
                            System.out.print(bmccalendar[i][j] + "\t");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    input.nextLine();

                    break;

                case 3:
                    /* The IF statement checks whether date is set and if it has
                     not been set then it prints out the message, then sends the
                     user back to the menu
                     The reason for this is that if the user has not set the 
                     date in the first menu option it will not be able to 
                     display the calendar
                     */
                    if (!isDateSet) {
                        System.out.println("Please set the date first\n");
                        break;
                    }

                    //Formatting for printing out the third option - date information
                    System.out.println("****************");
                    /*weekDay is an array found at the top of the document which
                     has the names of all the possible days. dayOfWeek was declared
                     from the users input.
                     monthName is also an array found at the top of the document
                     which contains the names of all the possible months. month was declared
                     from the users input.
                     We -1 from both dayOfWeek and monthName to find position in
                     the array as an array starts at 0 while users input is based off
                     of 1-12 for months and 1-7 for day of week.
                     */
                    System.out.println(weekDay[dayOfWeek - 1] + ", " + day + " " + monthName[month - 1] + " " + year);
                    if (isLeapYear == false) {
                        System.out.println(year + " is not a leap year.");
                    } else {
                        System.out.println(year + " is a leap year.");
                    }

                    System.out.println("****************\n");
                    break;

                case 4:
                    /*If the variable quit is set to true then it will close the
                     program as it agrees with the while from the do of the 
                     switch
                     */
                    System.out.println("Thank you for visiting the BMC Calendar Application");
                    quit = true;
                    break;

                //Default set as to ensure that the user knows to enter a value
                //from 1 to 4
                default:
                    System.out.println("Please select option 1, 2, 3 or 4");
                    break;

            }

        } while (!quit); //End of do-while
    } //End of main

} //End of class
