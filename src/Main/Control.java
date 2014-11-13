/**
 * @author Tobias & Christoffer
 */
package Main;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Control implements WordPairControlInterface {

    //declare 5 word lists. 1 is high priority and down to 5 which is low priority 
    LinkedList<WordPair> wordList;
//    LinkedList<WordPair> wordList2;
//    LinkedList<WordPair> wordList3;
//    LinkedList<WordPair> wordList4;
//    LinkedList<WordPair> wordList5;

    public Control() {
        wordList = new LinkedList<WordPair>();
//        wordList2 = new LinkedList<WordPair>();
//        wordList3 = new LinkedList<WordPair>();
//        wordList4 = new LinkedList<WordPair>();
//        wordList5 = new LinkedList<WordPair>();
    }

    public void add(String question, String answer) {
        WordPair wordpair = new WordPair(question, answer, 1);
        //we always want to add a new wordpair to wordlist1
        wordList.add(wordpair);
    }

    @Override
    public int size() {
        int size = wordList.size()/*+wordList2.size()+wordList3.size()+wordList4.size()+wordList5.size()*/;
        return size;
    }

    @Override
    public String getRandomQuestion() {
        Boolean foundIt = false;
        LinkedList<WordPair> randomList = new LinkedList<WordPair>();
        Random roll = new Random();
        int randomPriority = 0;
        do {
        randomPriority = roll.nextInt(15)+1;
        switch (randomPriority) {
            case 1: case 2: case 3: case 4: case 5: //1-5 high priority
                randomPriority = 1;
                break;
            case 6: case 7: case 8: case 9:
                randomPriority = 2;
                break;
            case 10: case 11: case 12:
                randomPriority = 3;
                break;
            case 13: case 14: 
                randomPriority = 4;
                break;
            case 15:
                randomPriority = 5; //low priority
                break;
        }
            System.out.println("1. priority is: " + randomPriority);    
        for (int i = 0; i < wordList.size(); i++) {
            int index = wordList.get(i).getPriority();
            if (index == randomPriority) {
            randomList.add(wordList.get(i));
            foundIt = true;
            }
        }
        } while (foundIt == false);
        randomPriority = roll.nextInt(randomList.size());
        WordPair wordpair = randomList.get(randomPriority);
        String text = wordpair.toString();
        System.out.println("2. I picked wordpair at index: " + randomPriority); 
        return text;
    }

    @Override
    public boolean checkGuess(String question, String guess) {
        String input = question + "," + guess;
        Boolean bool = false;
        for (int i = 0; i < size(); i++) {
            if (wordList.get(i).getWordpair().equals(input)) {
                bool = true;
                if (wordList.get(i).getPriority() < 5) {
                    wordList.get(i).incrementPriority(1);    
                }
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
}
