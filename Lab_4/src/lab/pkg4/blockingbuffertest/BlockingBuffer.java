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
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer {
   private final ArrayBlockingQueue<Integer> buffer;

   public BlockingBuffer() {
      buffer = new ArrayBlockingQueue<Integer>(1);
   }
   
   // place value into buffer
   @Override
   public void blockingPut(int value) throws InterruptedException {
      buffer.put(value);
      System.out.printf("%s%2d\t%s%d%n", "Producer writes ", value,
         "Buffer cells occupied: ", buffer.size());
   }

   @Override
   public int blockingGet() throws InterruptedException {
      int readValue = buffer.take();
      System.out.printf("%s %2d\t%s%d%n", "Consumer reads ",
         readValue, "Buffer cells occupied: ", buffer.size());

      return readValue;
   } 
} 
