/*
 * 
 */

package radix.sort;

import javax.swing.JOptionPane;

/**
 * @author Charlie Harpur and Ferris Dietrich
 */
public class RadixSort {
    // Input section to determine the size of the array
    static int input = Integer.parseInt(JOptionPane.showInputDialog("How many numbers do you want sorted ?"));
    // Input to determine the maximum size of one of the numbers in the array
    static int maxRando = Integer.parseInt(JOptionPane.showInputDialog("Enter the maximum value of the numbers ?\nEntering 1000 will allow random numbers ranging from 1 - 1000"));
    static int[] array = new int[input];
    
    public static void main(String[] args) {
        //Fills array with random numbers
        for (int i = 0; i < array.length; i++)
        {
            array[i] = random(0, maxRando);
        }
        System.out.println("Start!");
        
        //Gets system time in nanoseconds
        double startTime = System.nanoTime();
        
        radixSort(array);
        
        //Prints out time to sort (in milliseconds)
        System.out.println("Sort time: " + ((System.nanoTime() - startTime) / 1000000) + "ms");
        
        //Prints sorted array
        System.out.print("\nSorted Array: ");
        for (int i = 0; i < array.length; i++)
        {
            if(i != 0) System.out.print(", ");
            if (i % 20 == 0) System.out.println();
            System.out.print(array[i]);
        }
        System.out.println();
    }
    
    public static void radixSort(int[] array)
    {
        int largestValue = getLargestValue(array);
        
        for (int exponent = 1; largestValue / exponent > 0; exponent *= 10)
        {
            int[] sortedArray = new int[array.length], numberOfNumbers = new int[10];
            
            //Gets number of digits with the same value (number of 0's, 1's, ...)
            for (int i = 0; i < array.length; i++)
                numberOfNumbers[getDigit(array[i], exponent)]++;
            
            //Get's real position of numbers in sortedArray 
            for (int i = 1; i < 10; i++)
                numberOfNumbers[i] += numberOfNumbers[i - 1];
            
            //Writes to sortedArray (in reverse order because position in numberOfNumbers is the furthest position of that digit
            for(int i = array.length - 1; i >= 0; i--)
            {
                sortedArray[numberOfNumbers[getDigit(array[i], exponent)] - 1] = array[i];
                numberOfNumbers[getDigit(array[i], exponent)]--;
            }
            
            //Updates array with sorted digits
            for (int i = 0; i < array.length; i++)
                array[i] = sortedArray[i];
        }
    }
    
    /**
     * Gets value of a digit of a number
     * @param number Number to get digit of
     * @param digitNumber Number of digit
     * @return Value of digit at digitNumber in number
     */
    public static int getDigit(int number, int digitNumber)
    {
        return (int) (number / digitNumber) % 10;
    }
    
    /**
     * Gets the largest number in the array
     * @param array Array to search
     * @return Value of largest number in array
     */
    public static int getLargestValue(int[] array)
    {
        int largestValue = 0;
        for (int element : array)
        {
            largestValue = element > largestValue ? element : largestValue;
        }
        return largestValue;
    }
    
    /**
     * Gets random number within parameters
     * @param min Smallest number
     * @param max Largest number
     * @return Random number that's > min and < max
     */
    public static int random(int min, int max)
    {
        return (int)(Math.random() * (max + 1) + min);
    }
}
