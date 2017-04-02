/**
 * Driver module for Bulgarian Solitarire game
 *
 * @author CS 140 Instructors
 * @version 3/27/2017
 */
import java.util.Scanner;
import java.io.*;

public class Project6 {
      public static void main(String args[]) throws IOException
      {
          PrintWriter out = new PrintWriter(new FileWriter("Project6_Output.txt"));
          Scanner console = new Scanner(System.in);

          // Set up the game.  This method creates a random number of piles
          // in the range 4-8 and puts random number of cards in each pile and
          // makes sure that the total number of cards in all the piles is 45.
          BulgarianSolitaire game = new BulgarianSolitaire();

          game.play(out);   // play the game recording each move in an output file

          out.close();   // do not forget to close the output file

          System.out.println("Output is written to Project6_Output.txt file");
          System.out.println("Good bye!");
      }
}