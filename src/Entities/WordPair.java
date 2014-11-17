/**
 * @author Tobias
 */
package Entities;

public class WordPair  {
    
    private String danish, english;
    private int priority;

    public WordPair(String question, String answer, int priority) {
        this.danish = question;
        this.english = answer;
        this.priority = priority;
    }
    
    public String getQuestion() {
        return danish;
    }
    
    public String getAnswer() {
        return english;
    }
    
    public String getWordpair() {
        return danish+","+english;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void incrementPriority(int input) {
        priority = priority + input;
    }


    @Override
    public String toString() {
        return "" + danish + "," + english + "," + priority;
    }
    
    
    
}