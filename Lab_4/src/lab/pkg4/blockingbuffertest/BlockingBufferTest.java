/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg4.blockingbuffertest;

/**
 *
 * @author Pedro Longo
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockingBufferTest {
   public static void main(String[] args) throws InterruptedException {

      ExecutorService executorService = Executors.newCachedThreadPool();
  
      Buffer sharedLocation = new BlockingBuffer();

      executorService.execute(new Producer(sharedLocation));
      executorService.execute(new Consumer(sharedLocation));

      executorService.shutdown();
      executorService.awaitTermination(1, TimeUnit.MINUTES); 
   }
} 
