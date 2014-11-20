/**
 * @author Tobias & Christoffer
 */
package Entities;

public class WordPair  {
    
    private String danish, english;
    private double weight, normalized;

    public WordPair(String question, String answer, double priority, double normalized) {
        this.danish = question;
        this.english = answer;
        this.weight = priority;
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
    
    public double getWeight() {
        return weight;
    }
    
    public void incrementWeight(double input) {
        weight = weight * input;
    }
    
    public void setNormalizedWeight(double input) {
        normalized = input;
    }
    
    public double getNormalizedWeight() {
        return normalized;
    }


    @Override
    public String toString() {
        return "" + danish + "," + english + "," + weight + "," + normalized;
    }
    
    
    
}