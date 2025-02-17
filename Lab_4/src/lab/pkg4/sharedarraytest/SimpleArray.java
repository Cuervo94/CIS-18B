/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg4.sharedarraytest;

import java.security.SecureRandom;
import java.util.Arrays;

public class SimpleArray { 
   private static final SecureRandom generator = new SecureRandom();
   private final int[] array; 
   private int writeIndex = 0; 

   public SimpleArray(int size) {array = new int[size];}

   public void add(int value) {
      int position = writeIndex;

      try {
         Thread.sleep(generator.nextInt(500)); 
      } 
      catch (InterruptedException ex) {
         Thread.currentThread().interrupt();
      } 

      array[position] = value;             
      System.out.printf("%s wrote %2d to element %d.%n", 
         Thread.currentThread().getName(), value, position);

      ++writeIndex;
      System.out.printf("Next write index: %d%n", writeIndex);
   } 

   @Override
   public String toString() {
      return Arrays.toString(array);
   } 
}
