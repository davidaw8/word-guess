import java.util.ArrayList;

/**
 * Genetic that defines what each word is and then scores the total value for the word
 * @author dwhite
 * @version 1.0
 */
public class genetic
{
    ArrayList<String> word;
    int score;
    String answr;

    /**
     * The default constructor for the genetic
     * @param chart The characters of the word that is the genetic
     * @param answer the answer that the genetic settles down on
     */
    public genetic(ArrayList<String> chart, String answer)
    {
        System.out.println(chart + "answer");
        word = chart;
        answr = answer;
        score = score(word, answr);
    }

    /**
     * Based on the string and the answer determines that the fitness score fo the word should be
     * @param wordy the word that is being scored
     * @param ans the answer that is being used to score the word
     * @return The score
     */
    public int score(ArrayList<String> wordy, String ans) {
        int scr = 1;
        try {
            // determines the fitness score of each creature
            for (int y = 0; y < wordy.size(); y++) {
                if (wordy.get(y) == (ans.charAt(y) + "")) {
                    scr += 100;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Done");
        }
        if(scr > 100) //prints out the scores that are likely to reproduce
        {
            System.out.println(scr);
        }
        return scr;
    }
}