/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg4.circularbuffertest;

/**
 *
 * @author Pedro Longo
 */
import java.security.SecureRandom;

public class Consumer implements Runnable {
   private static final SecureRandom generator = new SecureRandom();
   private final Buffer sharedLocation;

   public Consumer(Buffer sharedLocation) {
      this.sharedLocation = sharedLocation;
   }

   @Override
   public void run() {                                       
      int sum = 0;

      for (int count = 1; count <= 10; count++) {
         try {
            Thread.sleep(generator.nextInt(3000));
            sum += sharedLocation.blockingGet();          
         } 
         catch (InterruptedException exception) {
            Thread.currentThread().interrupt(); 
         } 
      } 

      System.out.printf("%n%s %d%n%s%n", 
         "Consumer read values totaling", sum, "Terminating Consumer");
   } 
} 
