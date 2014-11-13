/**
 * @author Tobias
 */
package Main;

public class WordPair  {
    
    String danish, english;
    //int priority;

    public WordPair(String question, String answer /* int priority */) {
        this.danish = question;
        this.english = answer;
        //this.priority = priority;
    }


    @Override
    public String toString() {
        return "" + danish + "," + english;
    }
    
    
    
}