/**
 * @author Tobias & Christoffer
 */
package Main;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Control implements WordPairControlInterface {

    LinkedList<WordPair> wordList;
    //String filename = "wordPairsLibrary.txt";

    public Control() {
        wordList = new LinkedList<WordPair>();
        //  load("wordPairsLibrary.txt");
        System.out.println(size());
    }

    public void add(String question, String answer) {
        WordPair wordpair = new WordPair(question, answer, 1);
        wordList.add(wordpair);
        System.out.println(wordpair);
    }

    @Override
    public int size() {
        int size = wordList.size();
        return size;
    }

    @Override
    public String getRandomQuestion() {
        Random random = new Random();
        int randomIndex = random.nextInt(size());
        WordPair wordpair = wordList.get(randomIndex);
        String text = wordpair.toString();
        return text;
    }

    @Override
    public boolean checkGuess(String question, String guess) {
        String input = question + "," + guess;
        Boolean bool = false;
        for (int i = 0; i < size(); i++) {
            if (wordList.get(i).toString().equals(input)) {
                bool = true;
            }
        }
        return bool;
    }

    @Override
    public String lookup(String input) {
        String question;
        String answer;
        String output = "";
        for (int i = 0; i < size(); i++) {
            String wordpair = wordList.get(i).toString();
            Scanner sc = new Scanner(wordpair).useDelimiter(",");
            question = sc.next();
            answer = sc.next();
            if (input.equals(question)) {
                System.out.println(answer);
                output = answer;
            } else if (input.equals(answer)) {
                System.out.println(question);
                output = question;
            }
        }
        return output;
    }

    @Override
    public boolean load(String input) {
        if (FileHandler.loadFile(input) != null) {
            wordList = FileHandler.loadFile(input);
            System.out.println("Loading to wordList");
            System.out.println(size());
            return true;
        } else {
            System.out.println("Not loading to wordList");
            return false;
        }
    }

    @Override
    public boolean save(String input) {
        if (FileHandler.saveFile(wordList, input) == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        while (!wordList.isEmpty()) {
            wordList.remove(0);
        }
        size();
    }

    public void test() {

    }

    ///testsadasas
}
