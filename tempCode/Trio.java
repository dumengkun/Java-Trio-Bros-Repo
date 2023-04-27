import java.util.Random;
import java.util.Scanner;

public class Trio{

    public static void main(String[] args) {

        int[] arrayDate = new int[3];
        int[][] randomDateArray;
        

        arrayDate[0] = 5;
        arrayDate[1] = 24;
        arrayDate[2] = 1994;        

        if (!isValidDate(arrayDate)) {
            System.out.println("You enter a invalid birthday. Goodbye!");
        } else {
            System.out.println("Your birthday in Unix Epoch time is "+dateToEpochTime(arrayDate)+".");            
            
        }
    }
    
    /**
     * Check the year of input is leap year or not.
     * 
     * @author Shijun Yang
     * @return return true if it is leap year, otherwise return false
     */
    public static boolean isLeapYear(int year) {
        // The leap year represents the year of number can be divisible by 4, 
        // and the whole hundred years need to be divisible by 400 to be a leap year.
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check the input is a valid date or not
     * 
     * @author Shijun Yang
     * @return return true if it is a valid date, otherwise return false
     */
    public static boolean isValidDate(int[] arrayDate) {
        // The February of not leap year have 28 days.
        // The February of leap year have 29 days.
        if (!isLeapYear(arrayDate[2])) {
            if (arrayDate[0] == 2) {
                if (arrayDate[1] >= 1 && arrayDate[1] <= 28) {
                    return true;
                }
            }
        } else {
            if (arrayDate[0] == 2) {
                if (arrayDate[1] >= 1 && arrayDate[1] <= 29) {
                    return true;
                }
            }
        }
        // January, March, May, July, August, October, December have 31 days.
        if (arrayDate[0] == 1 || arrayDate[0] == 3 || arrayDate[0] == 5 || arrayDate[0] == 7 || arrayDate[0] == 8
                || arrayDate[0] == 10 || arrayDate[0] == 12) {
            if (arrayDate[1] >= 1 && arrayDate[1] <= 31) {
                return true;
            }
        }
        // April, June, September, November have 30 days
        if (arrayDate[0] == 4 || arrayDate[0] == 6 || arrayDate[0] == 9 || arrayDate[0] == 11) {
            if (arrayDate[1] >= 1 && arrayDate[1] <= 30) {
                return true;
            }
        }
        return false;
    }

    /**
     * Input a array of size 3 as the date and return the day number of the date.
     * 
     * @param Ex. [2, 1, 1994] represent February 1, 1994. Input [2, 1, 1994].
     * @return then return 32.
     * @author Shijun Yang
     */
    public static int dateToDayNumber(int[] arrayDate) {
        // A leap year has 366 days.
        if (isLeapYear(arrayDate[2])) {
            if (arrayDate[0] == 2) {
                return arrayDate[1] + 31;
            } else if (arrayDate[0] == 3) {
                return arrayDate[1] + 29 + 31;
            } else if (arrayDate[0] == 4) {
                return arrayDate[1] + 31 + 29 + 31;
            } else if (arrayDate[0] == 5) {
                return arrayDate[1] + 30 + 31 + 29 + 31;
            } else if (arrayDate[0] == 6) {
                return arrayDate[1] + 31 + 30 + 31 + 29 + 31;
            } else if (arrayDate[0] == 7) {
                return arrayDate[1] + 30 + 31 + 30 + 31 + 29 + 31;
            } else if (arrayDate[0] == 8) {
                return arrayDate[1] + 31 + 30 + 31 + 30 + 31 + 29 + 31;
            } else if (arrayDate[0] == 9) {
                return arrayDate[1] + 31 + 31 + 30 + 31 + 30 + 31 + 29 + 31;
            } else if (arrayDate[0] == 10) {
                return arrayDate[1] + 30 + 31 + 31 + 30 + 31 + 30 + 31 + 29 + 31;
            } else if (arrayDate[0] == 11) {
                return arrayDate[1] + 31 + 30 + 31 + 31 + 30 + 31 + 30 + 31 + 29 + 31;
            } else if (arrayDate[0] == 12) {
                return arrayDate[1] + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + 31 + 29 + 31;
            }
        } else if (!isLeapYear(arrayDate[2])) {
            if (arrayDate[0] == 2) {
                return arrayDate[1] + 31;
            } else if (arrayDate[0] == 3) {
                return arrayDate[1] + 28 + 31;
            } else if (arrayDate[0] == 4) {
                return arrayDate[1] + 31 + 28 + 31;
            } else if (arrayDate[0] == 5) {
                return arrayDate[1] + 30 + 31 + 28 + 31;
            } else if (arrayDate[0] == 6) {
                return arrayDate[1] + 31 + 30 + 31 + 28 + 31;
            } else if (arrayDate[0] == 7) {
                return arrayDate[1] + 30 + 31 + 30 + 31 + 28 + 31;
            } else if (arrayDate[0] == 8) {
                return arrayDate[1] + 31 + 30 + 31 + 30 + 31 + 28 + 31;
            } else if (arrayDate[0] == 9) {
                return arrayDate[1] + 31 + 31 + 30 + 31 + 30 + 31 + 28 + 31;
            } else if (arrayDate[0] == 10) {
                return arrayDate[1] + 30 + 31 + 31 + 30 + 31 + 30 + 31 + 28 + 31;
            } else if (arrayDate[0] == 11) {
                return arrayDate[1] + 31 + 30 + 31 + 31 + 30 + 31 + 30 + 31 + 28 + 31;
            } else if (arrayDate[0] == 12) {
                return arrayDate[1] + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + 31 + 28 + 31;
            }
        }
        return arrayDate[1];
    }

    /**
     * Input two array of size 3 representing two dates and return how many days between two dates.
     * 
     * @param Ex. [2, 1, 2015]
     * @param Ex. [2, 2, 2015]
     * @return then return 1.
     * @author Shijun Yang
     */
    public static int daysBetweenDates(int[] arrayDate1, int[] arrayDate2) {
        int yearPeriod;
        int numberOfLeapYear;
        int daysEndTheYear;
        int daysPassTheYear;
        numberOfLeapYear = 0;
        
        // The year of arrayDate1 is later than arrayDate2.
        if (arrayDate1[2] > arrayDate2[2]) {
            daysEndTheYear = 366 - dateToDayNumber(arrayDate2);
            daysPassTheYear = dateToDayNumber(arrayDate1);
            yearPeriod = arrayDate1[2] - arrayDate2[2] - 1;
            
            if (!isLeapYear(arrayDate2[2])) {
                daysEndTheYear = daysEndTheYear - 1;
            }
            // Calculate how many leap year between the year of two dates, but not include the year of two dates.
            for (int i = arrayDate2[2] + 1; i < arrayDate1[2]; i++) {
                if (isLeapYear(i)) {
                    numberOfLeapYear = numberOfLeapYear + 1;
                }
            }
            return yearPeriod*365 + numberOfLeapYear + daysPassTheYear + daysEndTheYear;
            
        // The year of arrayDate2 is later than arrayDate1.
        } else if (arrayDate1[2] < arrayDate2[2]) {
            daysEndTheYear = 366 - dateToDayNumber(arrayDate1);
            daysPassTheYear = dateToDayNumber(arrayDate2);
            yearPeriod = arrayDate2[2] - arrayDate1[2] - 1;
            
            if (!isLeapYear(arrayDate1[2])) {
                daysEndTheYear = daysEndTheYear - 1;
            }
            for (int i = arrayDate1[2] + 1; i < arrayDate2[2]; i++) {
                if (isLeapYear(i)) {
                    numberOfLeapYear = numberOfLeapYear + 1;
                }
            }
            return yearPeriod*365 + numberOfLeapYear + daysPassTheYear + daysEndTheYear;
        }
        
        // Two dates in same year.
        return Math.abs(dateToDayNumber(arrayDate1) - dateToDayNumber(arrayDate2));
    }

    /**
     * Input a date to calculate the epoch time.
     * 
     * @param Date represent by an array of size 3. Ex. [2, 1, 1988]
     * @return Epoch time
     * @author Shijun Yang
     */
    public static long dateToEpochTime(int[] arrayDate) {
        // The calculation of epoch time base on January 1, 1970. 
        int[] epochDate = new int[3];
        epochDate[0] = 1;
        epochDate[1] = 1;
        epochDate[2] = 1970;
        
        if (arrayDate[2] < 1970) {
            return daysBetweenDates(epochDate, arrayDate) * -86400L;
        } else if (arrayDate[2] > 1970) {
            return daysBetweenDates(epochDate, arrayDate) * 86400L;
        }
        return (dateToDayNumber(arrayDate) - 1) * 86400L;
    }

    /**
     * Take a day number (1-366 or 1-365) and a year 
     * and return which date that day number corresponds to.
     * 
     * @param dateNumber Ex. 61 or 60
     * @param year Ex. 2000 or 2001
     * @return March 1, 2000 or March 1, 2001
     * @author Shijun Yang
     */
    public static int[] dayNumberToDate(int dateNumber, int year) {
        int[] correspondsDateNumber = new int[3];
        correspondsDateNumber[2] = year;
        // The day of February in leap year have 29 days.
        if (isLeapYear(year)) {
            if (dateNumber <= 31) {
                correspondsDateNumber[1] = dateNumber;
                correspondsDateNumber[0] = 1;
            } else if (dateNumber > 31 && dateNumber <= 60) {
                correspondsDateNumber[1] = dateNumber - 31;
                correspondsDateNumber[0] = 2;
            } else if (dateNumber > 60 && dateNumber <= 91) {
                correspondsDateNumber[1] = dateNumber - 31 - 29;
                correspondsDateNumber[0] = 3;
            } else if (dateNumber > 91 && dateNumber <= 121) {
                correspondsDateNumber[1] = dateNumber - 31 - 29 - 31;
                correspondsDateNumber[0] = 4;
            } else if (dateNumber > 121 && dateNumber <= 152) {
                correspondsDateNumber[1] = dateNumber - 31 - 29 - 31 - 30;
                correspondsDateNumber[0] = 5;
            } else if (dateNumber > 152 && dateNumber <= 182) {
                correspondsDateNumber[1] = dateNumber - 31 - 29 - 31 - 30 - 31;
                correspondsDateNumber[0] = 6;
            } else if (dateNumber > 182 && dateNumber <= 213) {
                correspondsDateNumber[1] = dateNumber - 31 - 29 - 31 - 30 - 31 - 30;
                correspondsDateNumber[0] = 7;
            } else if (dateNumber > 213 && dateNumber <= 244) {
                correspondsDateNumber[1] = dateNumber - 31 - 29 - 31 - 30 - 31 - 30 - 31;
                correspondsDateNumber[0] = 8;
            } else if (dateNumber > 244 && dateNumber <= 274) {
                correspondsDateNumber[1] = dateNumber - 31 - 29 - 31 - 30 - 31 - 30 - 31 - 31;
                correspondsDateNumber[0] = 9;
            } else if (dateNumber > 274 && dateNumber <= 305) {
                correspondsDateNumber[1] = dateNumber - 31 - 29 - 31 - 30 - 31 - 30 - 31 - 31 - 30;
                correspondsDateNumber[0] = 10;
            } else if (dateNumber > 305 && dateNumber <= 335) {
                correspondsDateNumber[1] = dateNumber - 31 - 29 - 31 - 30 - 31 - 30 - 31 - 31 - 30 - 31;
                correspondsDateNumber[0] = 11;
            } else if (dateNumber > 335 && dateNumber <= 366) {
                correspondsDateNumber[1] = dateNumber - 31 - 29 - 31 - 30 - 31 - 30 - 31 - 31 - 30 - 31 - 30;
                correspondsDateNumber[0] = 12;
            }
        // The day of February in not leap year have 28 days.
        } else {
            if (dateNumber <= 31) {
                correspondsDateNumber[1] = dateNumber;
                correspondsDateNumber[0] = 1;
            } else if (dateNumber > 31 && dateNumber <= 59) {
                correspondsDateNumber[1] = dateNumber - 31;
                correspondsDateNumber[0] = 2;
            } else if (dateNumber > 59 && dateNumber <= 90) {
                correspondsDateNumber[1] = dateNumber - 31 - 28;
                correspondsDateNumber[0] = 3;
            } else if (dateNumber > 90 && dateNumber <= 120) {
                correspondsDateNumber[1] = dateNumber - 31 - 28 - 31;
                correspondsDateNumber[0] = 4;
            } else if (dateNumber > 120 && dateNumber <= 151) {
                correspondsDateNumber[1] = dateNumber - 31 - 28 - 31 - 30;
                correspondsDateNumber[0] = 5;
            } else if (dateNumber > 151 && dateNumber <= 181) {
                correspondsDateNumber[1] = dateNumber - 31 - 28 - 31 - 30 - 31;
                correspondsDateNumber[0] = 6;
            } else if (dateNumber > 181 && dateNumber <= 212) {
                correspondsDateNumber[1] = dateNumber - 31 - 28 - 31 - 30 - 31 - 30;
                correspondsDateNumber[0] = 7;
            } else if (dateNumber > 212 && dateNumber <= 243) {
                correspondsDateNumber[1] = dateNumber - 31 - 28 - 31 - 30 - 31 - 30 - 31;
                correspondsDateNumber[0] = 8;
            } else if (dateNumber > 243 && dateNumber <= 273) {
                correspondsDateNumber[1] = dateNumber - 31 - 28 - 31 - 30 - 31 - 30 - 31 - 31;
                correspondsDateNumber[0] = 9;
            } else if (dateNumber > 273 && dateNumber <= 304) {
                correspondsDateNumber[1] = dateNumber - 31 - 28 - 31 - 30 - 31 - 30 - 31 - 31 - 30;
                correspondsDateNumber[0] = 10;
            } else if (dateNumber > 304 && dateNumber <= 334) {
                correspondsDateNumber[1] = dateNumber - 31 - 28 - 31 - 30 - 31 - 30 - 31 - 31 - 30 - 31;
                correspondsDateNumber[0] = 11;
            } else if (dateNumber > 334 && dateNumber <= 365) {
                correspondsDateNumber[1] = dateNumber - 31 - 28 - 31 - 30 - 31 - 30 - 31 - 31 - 30 - 31 - 30;
                correspondsDateNumber[0] = 12;
            }
        }
        return correspondsDateNumber;
    }

    /**
     * Take a number of students as input 
     * and return a double with an estimate of the probability of a collision.
     * 
     * @param The number of student. Ex. 11
     * @return 13.988134773980887%
     * @author Shijun Yang
     */
    public static double computeApproximation(int numberOfStudent) {
        double approximation;
        approximation = 1 - (Math.exp(-numberOfStudent * (numberOfStudent - 1) / 730D));
        return approximation;
    }

    /**
     * Generate a random of the given year.
     * 
     * @param year
     * @return
     * @author Shijun Yang
     */
    public static int randomDate(int year) {
        Random generator = new Random();
        int dayNumber;
        if (isLeapYear(year)) {
            dayNumber = generator.nextInt(366) + 1;
        } else {
            dayNumber = generator.nextInt(365) + 1;
        }
        return dayNumber;
    }

    /**
     * Take a number of student and a birth year,
     * then return a 2d array of birthday and have a size of the number of the student.
     * 
     * @param numberOfStudent Ex. 2
     * @param year Ex. 2011
     * @return Ex. [[5, 6, 2011], [9, 4, 2011]]
     * @author Shijun Yang
     */
    public static int[][] generateBirthdayList(int numberOfStudent, int year) {
        // Every date is an array of size 3.
        int[][] birthdayList = new int [numberOfStudent][3];
        
        for (int i = 0; i < numberOfStudent; i++) {
            birthdayList[i] = dayNumberToDate(randomDate(year), year);
        }
        return birthdayList;
    }

    /**
     * Take an array of dates and return how many times the specific date occurs in the array.
     * 
     * @param birthdayList
     * @return
     * @author Shijun Yang
     */
    public static int countDate(int[][] birthdayList, int[] specificDate) {

        int counter;
        counter = 0;        
        for (int i = 0; i < birthdayList.length; i++) {
            if ( dateToDayNumber(specificDate) == dateToDayNumber(birthdayList[i]) ) {
                if (specificDate[2] == birthdayList[i][2]) {
                    counter = counter + 1;
                }                
            }
        }
        return counter;
    }

    /**
     * Take a array of size 3 which represent a date
     * and return a string representation of the date in the form of month/day/year.
     * 
     * @param arrayDate Ex. [1, 24, 1820]
     * @return Ex. 1/24/1820
     * @author Shijun Yang
     */
    public static String dateToString(int[] arrayDate) {
        return arrayDate[0] + "/" + arrayDate[1] + "/" + arrayDate[2];
    }

}