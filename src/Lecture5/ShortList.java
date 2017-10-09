package Lecture5;
/**
 * ShortList.java
 * 
 * Class demo to demonstrate arrays and for loops
 * 
 * @author Don Kreider  
 * @author Modified by THC and Glen Frank.
 * @author Scot Drysdale converted it to Java
 */
public class ShortList {
  public static void main(String args[]) {
    final int N = 10; // How many array elements we want

    int[] list; // Allocate the reference for an int array
    list = new int[N]; // Allocate the space for N ints

    for (int i = 0; i < N; i++)
      // Fill the array
      list[i] = i + 1;

    for (int i = 0; i < N; i++)
      // Print it out in increasing order
      System.out.print(list[i] + "  ");

    System.out.println();

    for (int element : list) {
      // Print it out in increasing order using a foreach-loop
      System.out.print(element + "  ");
    }

    System.out.println();

    for (int i = N - 1; i >= 0; i--)
      // Print it out in decreasing order
      System.out.print(list[i]+ "  ");

    System.out.println();

    for (int i = 0; i < N; i++)
      // Print it out in decreasing order again
      System.out.print(list[N - i - 1]+ "  ");

    System.out.println();

    // Demonstrate initializer list

    int[] list2 = { 1, 3, 5, 7 };

    for (int i = 0; i < list2.length; i++)
      // Print it out
      System.out.print(list2[i] + "  ");
    
    System.out.println();

    for (int value : list2)
      // Print it out again using a foreach-loop
      System.out.print(value + "  ");

    System.out.println();
  }
}