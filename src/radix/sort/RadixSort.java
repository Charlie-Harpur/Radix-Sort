/*
 * 
 */

package radix.sort;

/**
 * @author Charlie Harpur and Ferris Dietrich
 */
public class RadixSort {
    static int[] array = new int[1000];
    
    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++)
        {
            array[i] = random(0, 1000);
        }
        radixSort(array);
        for (int i = 0; i < array.length; i++)
        {
            System.out.println(array[i]);
        }
    }
    
    public static void radixSort(int[] array)
    {
        int numberOfDigits = getMaxDigits(array), digit, nextDigit, buffer;
        for (int digitNumber = 1; digitNumber <= numberOfDigits; digitNumber++)
        {
            nextDigit = getDigit(array[0], digitNumber);
            for (int i = 0; i < array.length - 1; i++)
            {
                digit = nextDigit;
                nextDigit = getDigit(array[i + 1], digitNumber);
                
                if (digit > nextDigit)
                {
                    buffer = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = buffer;
                }
            }
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
        int digit;
        digit = (int) (number % Math.pow(10, digitNumber));
        digit = (int) (digit / Math.pow(10, digitNumber - 1));
        return digit;
    }
    
    /**
     * Gets number of digits in largest number of the array
     * @param array Array to search
     * @return Number of digits in largest number of array
     */
    public static int getMaxDigits(int[] array)
    {
        int numberOfDigits = 0;
        for (int element : array)
        {
            int testNum = element, tempDigits = 0;
            while(testNum != 0) 
            {
                testNum /= 10;
                tempDigits ++;
            }
            numberOfDigits = tempDigits > numberOfDigits ? tempDigits : numberOfDigits;
        }
        return numberOfDigits;
    }
    
    /**
     * Gets random number within parameters
     * @param min Smallest number
     * @param max Largest number
     * @return Random number that's > min and < max
     */
    public static int random(int min, int max)
    {
        return (int)(Math.random() * max + min);
    }
}
