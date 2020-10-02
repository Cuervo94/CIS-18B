/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg4.sharedarraytest;

/**
 *
 * @author Pedro Longo
 */
import java.lang.Runnable;

public class ArrayWriter implements Runnable {
   private final SimpleArray sharedSimpleArray;
   private final int startValue;

   public ArrayWriter(int value, SimpleArray array) {
      startValue = value;
      sharedSimpleArray = array;
   }

   @Override
   public void run() {
      for (int i = startValue; i < startValue + 3; i++) {
         sharedSimpleArray.add(i);
      } 
   }
} 
