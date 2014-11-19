/**
 * @author Tobias
 */
package Entities;

public class WordPair  {
    
    private String danish, english;
    private double priority, normalized;

    public WordPair(String question, String answer, double priority, double normalized) {
        this.danish = question;
        this.english = answer;
        this.priority = priority;
        this.normalized = normalized;
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
    
    public double getPriority() {
        return priority;
    }
    
    public void incrementPriority(double input) {
        priority = priority * input;
    }
    
    public void setNormalizedPriority(double input) {
        normalized = input;
    }
    
    public double getNormalizedPriority() {
        return normalized;
    }


    @Override
    public String toString() {
        return "" + danish + "," + english + "," + priority + "," + normalized;
    }
    
    
    
}