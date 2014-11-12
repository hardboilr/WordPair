package Main;

/**
 * @author CHU
 */

public interface WordPairControlInterface
{
    
/**
     * Author: Tobias
     * Pre: Post: A new word pair is added to the existing collection of word
     * pairs. This method does not save to file!
     */
    public void add(String question, String answer);

    /**
     * Author: Tobias
     * Pre: Post: Returns the number of wordpairs in the collection (not the file).
     * Author: Tobias
     */
    int size();

    /**
     * Author: Tobias
     * Pre: At least one word pair must be present Post: Returns a question
     * randomly selected from the collection of word pairs.
     */
    String getRandomQuestion();

    /**
     * Author: Tobias
     * Pre: Post: Returns true if (question, quess) exists as a word pair in the
     * collection, otherwise false.
     */
    boolean checkGuess(String question, String quess);

    /**
     * Pre: Post: Returns the answer corresponding to the question if this
     * exists in the collection. Otherwise it returns null.
     */
    String lookup(String question);

    /**
     * Author: Christoffer
     * Pre: Post: Word pairs are read from the file "filename" and added to the
     * collection of word pairs. Returns true if successfully done. Otherwise it
     * returns false.
     */
    boolean load(String filename);

    /**
     * Author: Christoffer
     * Pre: Post: All word pairs from the collection has been written to the
     * file "filename" Returns true if successfully done. Otherwise false.
     */
    boolean save(String filename);

    /**
     * Pre: Post: The existing collection of word pairs is cleared
     * Author: Tobias
     */
    void clear();
}
