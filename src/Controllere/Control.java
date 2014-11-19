/**
 * @author Tobias & Christoffer
 * @todo i add() handle spaces, small/big letters etc.
 */
package Controllere;

import Filehandling.FileHandler;
import Interfaces.WordPairControlInterface;
import Entities.WordPair;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Control implements WordPairControlInterface {

    private LinkedList<WordPair> wordList;
    private String pickedQuestion;
    private String chosenQuestion;

    final private double INCREASE_PRIORITY = 1.1;
    final private double DECREASE_PRIORITY = 0.90;

    public Control() {
        wordList = new LinkedList<WordPair>();

    }

    @Override
    public void add(String question, String answer) {
        //remove spaces and set all characters to lowercase
        question = question.replaceAll(" ", "").toLowerCase();
        answer = answer.replaceAll(" ", "").toLowerCase();

        //we only add a new wordpair if both input-fields are used
        //and the wordpair doesn't exist in the list 
        if (!question.isEmpty() && !answer.isEmpty()) {
            Boolean pairExists = false;
            String pair = question + "," + answer;
            for (int i = 0; i < wordList.size(); i++) {
                if (wordList.get(i).getWordpair().equals(pair)) {
                    pairExists = true;
                }
            }
            if (pairExists != true) {
                //we always want to add a new wordpair to wordlist with weight 0.5 
                WordPair wordpair = new WordPair(question, answer, 0.5, 0.0);
                wordList.add(wordpair);
                //sum again and assign new normalized weights
                normalizeWeights();
            }
        }
    }

    @Override
    public int size() {
        int size = wordList.size();
        return size;
    }
//----------------------------------------------------------------------------
//New implementation of getRandomQuestion() using normalized priority weights
//roll a double between 0 and 1, fx. 0.2345645 
//We run through the list and sum the normalized weights from each wordpair.
//when sum equals or above prob we pick out that particular wordpair
//OBS: The method has been tested in "Distribution_Test" running 1million times
//the distribution corresponds to the normalized weights, so everything works as intended. Very nice!
//if you want to test, make sure to make proper corrections to the code in FileHandler (see comments there)

    @Override
    public String getRandomQuestion() {
        Random roll = new Random();
        double prob = roll.nextDouble();
        double sum = 0;
        String question = null;
        for (int i = 0; i < size(); i++) {
            sum = sum + wordList.get(i).getNormalizedWeight();
            if (sum >= prob) {
                question = wordList.get(i).getQuestion();
                break; //no need to go through the entire list
            }
        }
        return question;
    }

//-------------------------------------------------------------------------------------
//Old implementation of getRandomQuestion() based on suggestions from assignment-text
//Does not work properly, unless you have an even number of wordpairs in each priority
//-------------------------------------------------------------------------------------
//    @Override
//    public String getRandomQuestion() {
//        if (!wordList.isEmpty()) {
//            Boolean foundIt = false;
//            LinkedList<WordPair> randomList = new LinkedList<WordPair>();
//            Random roll = new Random();
//            int randomPriority;
//            do {
//                do {
////----------------------------------------------------------------            
////find a random number between 1-15 and assign to 'randomPriority'
////----------------------------------------------------------------
//                    randomPriority = roll.nextInt(15) + 1;
//                    switch (randomPriority) {
//                        case 1:
//                        case 2:
//                        case 3:
//                        case 4:
//                        case 5: //1-5 high priority
//                            randomPriority = 1;
//                            break;
//                        case 6:
//                        case 7:
//                        case 8:
//                        case 9:
//                            randomPriority = 2;
//                            break;
//                        case 10:
//                        case 11:
//                        case 12:
//                            randomPriority = 3;
//                            break;
//                        case 13:
//                        case 14:
//                            randomPriority = 4;
//                            break;
//                        case 15:
//                            randomPriority = 5; //low priority
//                            break;
//                    }
////---------------------------------------------------------------------
////run through wordList and find all wordpairs equal to 'randomPriority'
////put those in 'randomList' and set foundIt to true
////---------------------------------------------------------------------
//                    for (int i = 0; i < wordList.size(); i++) {
//                        int index = wordList.get(i).getPriority();
//                        if (index == randomPriority) {
//                            randomList.add(wordList.get(i));
//                            foundIt = true;
//                        }
//                    }
//                } while (foundIt == false);
////---------------------------------------------------------------------------------
////assign a random index-position to arrayIndex, based on the size of the randomList
////take a wordpair from this index and assign to a wordpair object
////assign that particular question to 'chosenWordpair'
////assign string wordpair to text
////----------------------------------------------------------------------------------
//                int arrayIndex = roll.nextInt(randomList.size());
//                WordPair wordpair = randomList.get(arrayIndex);
//                pickedQuestion = wordpair.getQuestion();
//            } while (pickedQuestion == chosenQuestion); //we don't want repeat questions!
//            chosenQuestion = pickedQuestion;
//        } else {
//            pickedQuestion = null;
//        }
//        return pickedQuestion;
//    }
    @Override
    public boolean checkGuess(String question, String guess) {
        //again remove spaces and set all chars to lowercase
        question = question.toLowerCase().replaceAll(" ", "");
        guess = guess.toLowerCase().replaceAll(" ", "");
        String wordpair = question + "," + guess;
        Boolean foundWordpair = false;
        String foundQuestion;
        for (int i = 0; i < size(); i++) {
//-----------------------------------------------------------------------
//if inputted wordpair matches a wordpair in the list
//set foundWordpair to true and add 1 to priority if priority is below 5
//-----------------------------------------------------------------------
            if (wordList.get(i).getWordpair().equals(wordpair)) {
                foundWordpair = true;
                System.out.println("Decrease priority!");
                wordList.get(i).incrementWeight(DECREASE_PRIORITY);
                normalizeWeights();
                break;
            }
//----------------------------------------------------------
//if inputted question matches a question in the list
//and inputte guess is not empty and foundWordpair is false
//then we know the user has guessed something wrong
//the particular question is then moved up in priority
//----------------------------------------------------------
            if (wordList.get(i).getQuestion().equals(question) && !guess.isEmpty() && foundWordpair == false) {
                System.out.println("Increase priority!");
                wordList.get(i).incrementWeight(INCREASE_PRIORITY);
                normalizeWeights();
                break;
            }
        }
        return foundWordpair;
    }

    @Override
    public String lookup(String input
    ) {
//-----------------------------------------------------------
//run through the list and pick out wordpairs
//if input equals either a question or answer from the list, 
//then return matching question/answer
//-----------------------------------------------------------
        String output = null;
        for (int i = 0; i < size(); i++) {
            String wordpair = wordList.get(i).toString();
            Scanner sc = new Scanner(wordpair).useDelimiter(",");
            String question = sc.next();
            String answer = sc.next();
            if (input.equals(question)) {
                output = answer;
            } else if (input.equals(answer)) {
                output = question;
            }
        }
        return output;
    }

    @Override
    public boolean load(String input
    ) {
        if (FileHandler.loadFile(input) != null) {
            wordList = FileHandler.loadFile(input);
            normalizeWeights();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean save(String input) {
//        if (FileHandler.saveFileWithWeights(wordList, input) == true) {
        if (FileHandler.saveFile(wordList, input) == true) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        wordList.clear();
    }

//------------------------------------------------
//sum all absolute weights
//the normalized value is then found by dividing 
//the absolute weight with the sum
//------------------------------------------------    
    private void normalizeWeights() {
        double sum = 0;
        for (int i = 0; i < size(); i++) {
            sum = sum + wordList.get(i).getWeight();
        }
        //normalize priorities
        for (int i = 0; i < size(); i++) {
            double normalizedValue = wordList.get(i).getWeight() / sum;
            wordList.get(i).setNormalizedWeight(normalizedValue);
        }
    }
}
