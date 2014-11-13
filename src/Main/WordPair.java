/**
 * @author Tobias
 */
package Main;

public class WordPair  {
    
    String danish, english;
    //int priority;

    public WordPair(String input1, String input2) {
        this.danish = input1;
        this.english = input2;
        //this.priority = input3;
    }

    @Override
    public String toString() {
        return "" + danish + "," + english;
    }
    
    
    
}