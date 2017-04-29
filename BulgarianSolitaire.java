import java.util.Random;
import java.util.Arrays;
import java.io.*;
/**
 * Simulates a game of BulgarianSolitaire.
 * 
 * @author Dominique Gould
 * @version 4-29-17
 */
public class BulgarianSolitaire
{
    private int[] deckOfCards;
    private int initialNumberOfPiles;
    private int currentNonzeroPiles;
    
    /**
     * Creates a new game of BulgarianSolitaire
     */
    public BulgarianSolitaire()
    {
        //Generates a random number of initial piles between 4 and 8
        Random generator = new Random();
        initialNumberOfPiles = generator.nextInt(5)+4;
        deckOfCards =  new int[initialNumberOfPiles];
        
        //Fills the array
        int cardsInFirstPiles = 45 / (deckOfCards.length - 1);
        int cardsInFinalPile = 45 - (cardsInFirstPiles*(deckOfCards.length - 1));
        for (int i = 0; i < (deckOfCards.length - 1); i++)
        {
            deckOfCards[i] = cardsInFirstPiles;
        }
        deckOfCards[deckOfCards.length - 1] = cardsInFinalPile;
    }
    
    /**
     * Plays a game of Bulgarian Solitaire.
     * @param a PrintWriter object to print the results of each turn to an output file.
     */
    public void play(PrintWriter out)
    {
        boolean stopGame = checkArray();
        int numberOfTries = 0;
        
        //Prints the initial number of piles and the initial configuration.
        out.print("Initial number of piles: ");
        out.println(initialNumberOfPiles);
        for (int i = 0; i < deckOfCards.length; i++)
            {
                out.print(deckOfCards[i] + " ");
            }
        out.println();
        
        //Plays the game until the correct configuration is reached.
        while (!stopGame)
        {
            //Removes any zeroes if they happen to be in the starting configuration.
            removeZeroes();
            //Subtracts a card from each pile in the array.
            for (int i = 0; i < deckOfCards.length; i++)
            {
                deckOfCards[i] = (deckOfCards[i] - 1);
            }
            
            int cardsInNewPile = deckOfCards.length;

            //Removes any zeroes from the new configuration.
            removeZeroes();
            //Adds new pile at the end.
            deckOfCards = Arrays.copyOf(deckOfCards, (deckOfCards.length + 1));
            deckOfCards[deckOfCards.length - 1] = cardsInNewPile;
            //Prints the new configuration.
            for (int i = 0; i < deckOfCards.length; i++)
            {
                out.print(deckOfCards[i] + " ");
            }
            out.println();
            
            numberOfTries++;
            stopGame = checkArray();
        }
        
        out.print("Game is over. It took " + numberOfTries + " tries.");
    }
    
    /**
     * Removes zeroes from the array.
     */
    private void removeZeroes()
    {
        int nonzeroElements = 0;
        int[] deckOfCardsCopy = new int[deckOfCards.length];
        for (int i = 0; i < deckOfCards.length; i++)
        {
            if (deckOfCards[i] != 0)
            {
                deckOfCardsCopy[nonzeroElements] = deckOfCards[i];
                nonzeroElements++;
            }
        }
        deckOfCards = Arrays.copyOf(deckOfCardsCopy, (nonzeroElements));
    }
    
    /**
     * Checks whether the deckOfCards array is in the correct configuration.
     * @return whether the array is in the correct configuration yet.
     */
    private boolean checkArray()
    {
        //Constructs an array of booleans. All values will be set to true if deckOfCards[]
        //has the correct configuration.
        boolean correctConfiguration = false;
        boolean[] sequence = new boolean[9];
        for (int i = 0; i < deckOfCards.length; i++)
        {
            for (int j = 0; j < sequence.length; j++)
            {
                if (deckOfCards[i] == (j + 1))
                {
                    sequence[j] = true;
                }
            }
        }
        
        //Checks whether all values in the boolean array are true.
        if ((sequence[0] == true) && (sequence[1] == true) && (sequence[2] == true) && 
        (sequence[3] == true) && (sequence[4] == true) && (sequence[5] == true) &&
        (sequence[6] == true) && (sequence[7] == true) && (sequence[8] == true))
        {
            correctConfiguration = true;
        }
        return correctConfiguration;
    }
}
