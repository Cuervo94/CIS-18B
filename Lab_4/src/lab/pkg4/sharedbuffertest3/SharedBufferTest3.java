/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg4.sharedbuffertest3;

/**
 *
 * @author Pedro Longo
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SharedBufferTest3 {
   public static void main(String[] args) throws InterruptedException {
      ExecutorService executorService = Executors.newCachedThreadPool();
    
      Buffer sharedLocation = new SynchronizedBuffer();

      System.out.printf("%-40s%s\t\t%s%n%-40s%s%n%n", "Operation", 
         "Buffer", "Occupied", "---------", "------\t\t--------");

      executorService.execute(new Producer(sharedLocation));
      executorService.execute(new Consumer(sharedLocation));

      executorService.shutdown();
      executorService.awaitTermination(1, TimeUnit.MINUTES); 
   } 
} 
