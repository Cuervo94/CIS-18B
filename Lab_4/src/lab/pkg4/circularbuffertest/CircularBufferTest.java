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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CircularBufferTest {
   public static void main(String[] args) throws InterruptedException {
      // create new thread pool 
      ExecutorService executorService = Executors.newCachedThreadPool();

      // create CircularBuffer to store ints               
      CircularBuffer sharedLocation = new CircularBuffer();

      // display the initial state of the CircularBuffer
      sharedLocation.displayState("Initial State");

      // execute the Producer and Consumer tasks
      executorService.execute(new Producer(sharedLocation));
      executorService.execute(new Consumer(sharedLocation));

      executorService.shutdown();
      executorService.awaitTermination(1, TimeUnit.MINUTES); 
   }
}
