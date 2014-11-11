/**
 * @author Tobias & Christoffer
 */
package Main;

import java.util.LinkedList;
import java.util.Random;

public class Control implements WordPairControlInterface {

    LinkedList<WordPair> wordList;

    public Control() {
        wordList = new LinkedList<WordPair>();
    }

    @Override
    public void add(String question, String answer) {
        WordPair wordpair = new WordPair(question, answer);
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
//  Pre: Post: Returns true if (question, quess) exists as a word pair in the
//  collection, otherwise false.
//  if answer exists in linkedArray, then true otherwise false
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
    public String lookup(String question) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean load(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    ///testsadasas
}
