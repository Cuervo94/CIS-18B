/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.pkg4.sharedbuffertest;

/**
 *
 * @author Pedro Longo
 */
public interface Buffer {

   public void blockingPut(int value) throws InterruptedException; 

   public int blockingGet() throws InterruptedException; 
} 
