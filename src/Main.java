import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

/**
 * A class to run and mangage the code
 *
 *
 */

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Integer>  charUsed = new ArrayList<Integer>();
        int last = 0;
        int numword = 50;
        int wordnum;
        ArrayList<genetic> nextgen = new ArrayList<genetic>();
        ArrayList<Integer> reproduce = new ArrayList<Integer>();
        ArrayList<String> nuword = new ArrayList<String>();
        ArrayList<genetic> gen = new ArrayList<genetic>();
        System.out.println(Math.random());
        int gennum = 0;
        String ans = "hello";
        ans = ans.toLowerCase();
        int length = ans.length();
        if (length == 0)
        {
            System.exit(0);
        }
        for(int x = 0; x < length; x++)
        {
            nuword.add("");
        }
        for (int y = 0; y < ans.length(); y++) {
            charUsed.add(((int)ans.charAt(y)));
            last = y;
            System.out.println(last);
        }
        Collections.sort(charUsed);
        System.out.println(charUsed);
        for (int u = 0; u < numword; u++) {
            wordnum = (int)(Math.random() * 26) + 97;
            for (int a = 0; a < ans.length(); a++) {
                nuword.set(a, (((char)((int)(Math.random() * 26) + 97) + "")));
                System.out.println(nuword);
            }
            System.out.println(gen);
            gen.add(new genetic(nuword, ans));
            System.out.println(gen.get(u).word);
            System.out.println(gen.get(u).word);
        }
        gennum++;
        System.out.println(gen);
        String[] t5 = new String[5];
        String name = "";
        int first = 0;
        int second = 0;
        int third = 0;
        int fourth = 0;
        int fifth = 0;
        for (genetic g : gen) {

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
        while(!(t5[0].equals(ans)))
        {
            System.out.println(gen.get(0).word);
            int sepPlace;
            while(reproduce.size() < 25)
            {
                for (int q = 0; q < gen.size() / 2; q++) {
                    if ((gen.get(q).score + gen.get(q + 25).score) / (length) < Math.random()) {
                        reproduce.add(q);
                    }
                }
            }
            nextgen = new ArrayList<genetic>();
            System.out.println(gen);
            do {
                for (int parents : reproduce) {
                    System.out.println(parents);
                    sepPlace = (int)(Math.random() * length);
                    System.out.println(sepPlace + "sepplace");

                    for (int p = 0; p < length; p++) {
                        if (Math.random() * 100 > 95) {
                            nuword.set(p, (((char)((int)(Math.random() * 26) + 97) + "") + ""));
                        } else if(p <= sepPlace) {
                            System.out.println(p + "71");
                            nuword.set(p, gen.get(parents).word.get(p));
                        }
                        else
                        {
                            System.out.println(p);
                            System.out.println(gen.size() + "gensize");
                            System.out.println(gen.get(parents + 25).word);
                            nuword.set(p, gen.get(parents).word.get(p));
                        }
                    }
                    nextgen.add(new genetic(nuword, ans));;
                    for (int i = 0; i < length; i++) {

                        if (Math.random() * 100> 95) {
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
            } while (nextgen.size() < 50);
            gennum++;
            gen = nextgen;
            System.out.println(gen.get(0));
            first = 0;
            second = 0;
            third = 0;
            fourth = 0;
            fifth = 0;
            for (genetic g : gen) {

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
            System.out.println(t5);
            System.out.println("The current best guesses");
            System.out.println(gennum + " This is the current generation");
        }
    }
}
