import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Creates the class genetics which is a group of genetic of words and then runs the overall code
 * @author David White
 * @version 1.0
 */

public class Genetics {
    String answer = "hello";
    String ans = answer.toLowerCase();
    int length = answer.length();
    ArrayList<String> word = new ArrayList<String>();
    ArrayList<genetic> gen = new ArrayList<genetic>();
    ArrayList<String> allword = new ArrayList<String>();
    ArrayList<String> nuword = new ArrayList<String>();
    ArrayList<genetic> nextgen = new ArrayList<genetic>();
    ArrayList<Integer> reproduce = new ArrayList<Integer>();
    ArrayList<Integer> charUsed = new ArrayList<Integer>();
    int gennum;
    int numword = 100;
    int wordnum;

    /**
     * Base constructor for the genetics class
     * @param correct the string that is the answer
     */
    public Genetics(String correct)
    {
        answer = correct.toLowerCase();
        length = correct.length();
        gen = create(correct);
    }

    /**
     * Creates an initial population
     * @param ans the answer that the code will be based on
     * @return the new generation
     */
    public ArrayList<genetic> create(String ans) {
        int last = 0;
        int length = ans.length();
        if (length == 0)
        {
            System.exit(0); //exits the code if there isn't a word
        }

        //fills in new word with a set of empty strings
        for(int x = 0; x < length; x++)
        {
            nuword.add("");
        }

        //gets the index of the characters used in the answer
        for (int y = 0; y < ans.length(); y++) {
            charUsed.add(((int)ans.charAt(y)));
            last = y;
            System.out.println(last);
        }
        Collections.sort(charUsed);
        System.out.println(charUsed);
        //random generates the word
        for (int u = 0; u < numword; u++) {
            wordnum = (int)(Math.random() * 26) + 97;
            for (int a = 0; a < ans.length(); a++) {
                nuword.set(a, (((char)((int)(Math.random() * 26) + 97) + "")));
                System.out.println(nuword);
            }
            System.out.println(gen);
            System.out.println(u);
            gen.add(new genetic(nuword, ans)); //adds the new word to the genetic
        }
        gennum++;
        System.out.println(gen);
        return gen;
    }

    /**
     * The code that will create the next generation based on the previous one
     * @param geez the previous generation that will be used to create the new one
     */
    public void reproduce(Genetics geez) {
        System.out.println(gen.get(0).word);
        int sepPlace;
        while(reproduce.size() < 25)
        {
            for (int q = 0; q < gen.size() / 2; q++) {
                // figures out which set of parts should be able to reproduce)
                if ((((gen.get(q).score + gen.get(q + 25).score)) / (((length * 100) + 1) * 1.0)) > Math.random()) {
                    System.out.println((((gen.get(q).score + gen.get(q + 25).score)) / (((length * 100) + 1) * 1.0)));
                    reproduce.add(q);
                }
            }
        }
        nextgen = new ArrayList<genetic>();
        System.out.println(gen);
        // does the actual create of the next generation
        do {
            for (int parents : reproduce) {
                System.out.println(parents);
                sepPlace = (int)((Math.random() * length) -1); //sets the point where each word will be seperated based on its parents
                System.out.println(sepPlace + "sepplace");

                for (int p = 0; p < length; p++) {
                    if (Math.random() * 100 > 99) {
                        nuword.set(p, (((char)((int)(Math.random() * 26) + 97) + "") + "")); //adds a random letter if a mutation occurs
                    } else if(p <= sepPlace) {
                        nuword.set(p, gen.get(parents).word.get(p));
                    }
                    else
                    {
                        System.out.println(p);
                        System.out.println(gen.size() + "gensize");
                        System.out.println(gen.get(parents + 25).word);
                        nuword.set(p, gen.get(parents).word.get(p)); //adds a the new char the word
                    }
                }
                nextgen.add(new genetic(nuword, ans)); //adds the new word to the generation
                // does the same thing as above again, but with a different point of seperation
                sepPlace = (int)((Math.random() * length) -1);
                for (int i = 0; i < length; i++) {

                    if (Math.random() * 100> 99) {
                        nuword.set(i, (((((char)((int)(Math.random() * 26) + 97) + "")) + "")));
                    } else if(i <= sepPlace)
                    {
                        nuword.set(i, gen.get(parents).word.get(i));
                    }
                    else
                    {
                        System.out.println(i);
                        nuword.set(i, gen.get(parents + 25).word.get(i));
                    }
                }
                nextgen.add(new genetic(nuword, ans));
            }
            reproduce.clear();
        } while (nextgen.size() < 50); //makes sure the next generation will not decrease in six
    }

    /**
     * Sorts the correct generation and prints the top 5 answers so far
     * @param geni the generation that is being sorted
     * @return the string for the top 5 words of that generation
     */
    public String[] sort(ArrayList<genetic> geni) {
        String[] t5 = new String[5];
        String name = "";
        int first = 0;
        int second = 0;
        int third = 0;
        int fourth = 0;
        int fifth = 0;
        //makes a word out of the characters that it is sent and then sees if it is one of the top 5 scores for that generation
        for (genetic g : geni) {

            for (String w : g.word) {
                name += w;
            }
            if (g.score > first) {
                first = g.score;
                t5[0] = name;
            } else if (g.score > second) {
                second = g.score;
                t5[1] = name;
            } else if (g.score > third) {
                third = g.score;
                t5[2] = name;
            } else if (g.score > fourth) {
                fourth = g.score;
                t5[3] = name;
            } else if (g.score > fifth) {
                fifth = g.score;
                t5[4] = name;
            }
            name = "";
        }
        //prints out the words
        for(String x: t5){
            System.out.println(x);
        }
        return t5;
    }
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); //intiates a character
        System.out.println("What word would you like to find? ");
        String word = scan.next();
        System.out.println(word);
        Genetics genn = new Genetics(word);//creates the new generation
        System.out.println(genn.gen.get(0).word.toString()); //makes sure that the generation was greated
        System.out.println("hello");
        System.out.println(genn.sort(genn.gen));//prints out what randomly were the top 5 scores
        //repeats the cycle of create over and over again until the correct answer is reached
        while(!(genn.sort(genn.gen)[0].equals(genn.answer)))
        {
            System.out.println("It got this far?");
            genn.reproduce(genn);
            System.out.println("Working");
            System.out.println(genn.sort(genn.gen).toString());//prints out the top 5 scores for that generation
            System.out.println(genn.gennum);
            System.out.println(genn.sort(genn.gen));
            System.out.println("The current best guesses" );
            System.out.println(genn.gennum + " This is the current generation");
        }
        System.out.println(genn.gennum); //prints out the number of generations that it took to reach the correct answer
    }
}
