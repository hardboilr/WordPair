/**
 * @author Tobias & Christoffer
 * @todo handle spaces, small/big letters etc.
 */
package Main;

import Interface.WordPairControlInterface;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Control implements WordPairControlInterface {

    //declare 5 word lists. 1 is high priority and down to 5 which is low priority 
    LinkedList<WordPair> wordList;

    String pickedQuestion;
    String chosenQuestion;
    String wordpairText;

    public Control() {
        wordList = new LinkedList<WordPair>();
    }

    public void add(String question, String answer) {
        WordPair wordpair = new WordPair(question, answer, 1);
        //we always want to add a new wordpair to wordlist with priority 1
        wordList.add(wordpair);
    }

    @Override
    public int size() {
        int size = wordList.size();
        return size;
    }

    @Override
    public String getRandomQuestion() {
        if (!wordList.isEmpty()) {
        Boolean foundIt = false;
        LinkedList<WordPair> randomList = new LinkedList<WordPair>();
        Random roll = new Random();
        int randomPriority;
        do {
            do {
//----------------------------------------------------------------            
//find a random number between 1-15 and assign to 'randomPriority'
//----------------------------------------------------------------
                randomPriority = roll.nextInt(15) + 1;
                switch (randomPriority) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5: //1-5 high priority
                        randomPriority = 1;
                        break;
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        randomPriority = 2;
                        break;
                    case 10:
                    case 11:
                    case 12:
                        randomPriority = 3;
                        break;
                    case 13:
                    case 14:
                        randomPriority = 4;
                        break;
                    case 15:
                        randomPriority = 5; //low priority
                        break;
                }
                System.out.println("1. priority is: " + randomPriority);
//---------------------------------------------------------------------
//run through wordList and find all wordpairs equal to 'randomPriority'
//put those in 'randomList' and set foundIt to true
//---------------------------------------------------------------------
                for (int i = 0; i < wordList.size(); i++) {
                    int index = wordList.get(i).getPriority();
                    if (index == randomPriority) {
                        randomList.add(wordList.get(i));
                        foundIt = true;
                    }
                }
            } while (foundIt == false);
//---------------------------------------------------------------------------------
//assign a random index-position to arrayIndex, based on the size of the randomList
//take a wordpair from this index and assign to a wordpair object
//assign that particular question to 'chosenWordpair'
//assign string wordpair to text
//----------------------------------------------------------------------------------
            int arrayIndex = roll.nextInt(randomList.size());
            WordPair wordpair = randomList.get(arrayIndex);
            wordpairText = wordpair.getWordpair();
            pickedQuestion = wordpair.getQuestion();
        } while (pickedQuestion == chosenQuestion); //we don't want repeat questions!
        chosenQuestion = pickedQuestion;
        } else {
            System.out.println("List is empty!");
            pickedQuestion = null;
        }
        return pickedQuestion;
    }

    @Override
    public boolean checkGuess(String question, String guess) {
        String wordpair = question + "," + guess;
        System.out.println("Entered wordpair is: " + wordpair);
        Boolean foundWordpair = false;

        for (int i = 0; i < size(); i++) {
            if (wordList.get(i).getWordpair().equals(wordpair)) {
                foundWordpair = true;
                if (wordList.get(i).getPriority() < 5) {
                    wordList.get(i).incrementPriority(1);
                    /* DELETE TESTING*/System.out.println("Moving wordpair 'down' in priority!");
                }
            }
            if (wordList.get(i).getQuestion().equals(question) && guess != "" && foundWordpair == false) {
                if (wordList.get(i).getPriority() >= 2) {
                    wordList.get(i).incrementPriority(-1);
                    /* DELETE TESTING*/System.out.println("Moving wordpair 'up' in priority!");
                }
            }
        } 
        /* DELETE TESTING*/System.out.println("Found wordpair: " + foundWordpair);
        return foundWordpair;
       
    }

    @Override
    public String lookup(String input) {
        String question;
        String answer;
        String output = null;
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
}
