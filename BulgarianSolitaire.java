import java.util.Random;
import java.util.Arrays;
import java.io.*;
/**
 * Simulates a game of BulgarianSolitaire.
 * 
 * @author Dominique Gould
 * @version 4-27-17
 */
public class BulgarianSolitaire
{
    private int[] deckOfCards;
    private int initialNumberOfPiles;
    private int currentNonzeroPiles;
    private boolean correctConfiguration;
    
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
     */
    public void play(PrintWriter out)
    {
        while (correctConfiguration == false)
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
            deckOfCards = Arrays.copyOf(deckOfCards, (deckOfCards.length + 1));
            deckOfCards[deckOfCards.length - 1] = cardsInNewPile;
        }
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
        correctConfiguration = false;
        boolean[] sequence = new boolean[10];
        for (int i = 0; i < deckOfCards.length; i++)
        {
            for (int j = 1; j < sequence.length; j++)
            {
                if (deckOfCards[i] == j)
                {
                    sequence[j] = true;
                }
            }
        }
        
        //Checks whether all values in the boolean array are true.
        if ((sequence[0] == true) && (sequence[1] == true) && (sequence[2] == true) && 
        (sequence[3] == true) && (sequence[4] == true) && (sequence[5] == true) &&
        (sequence[6] == true) && (sequence[7] == true) && (sequence[8] == true) &&
        (sequence[9] == true)&& (sequence[10] == true))
        {
            correctConfiguration = true;
        }
        return correctConfiguration;
    }
}
