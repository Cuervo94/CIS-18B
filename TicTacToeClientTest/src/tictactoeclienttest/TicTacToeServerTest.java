/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoeclienttest;

/**
 *
 * @author Pedro Longo
 */
import javax.swing.JFrame;

public class TicTacToeServerTest
{
   public static void main(String[] args)
   {
      TicTacToeServer application = new TicTacToeServer();
      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      application.execute();
   } 
}
