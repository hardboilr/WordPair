/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Controllere.Control;
import Interfaces.WordPairControlInterface;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tobias
 */
public class NewEmptyJUnitTest {

    private WordPairControlInterface wordPairDemo;
    private static String fileName = getFilenameGuid();

    private static String getFilenameGuid() {
//         Value to use a Windows or Unix like filesystem.
//         Set to false if you use a Mac or linux variant.
        boolean WindowsOS = true;

        String path = System.getProperty("user.home");

        if (WindowsOS) {
            return path + "\\" + UUID.randomUUID().toString() + ".txt";
        } else {
            return path + "/" + UUID.randomUUID().toString() + ".txt";
        }
    }

    @Before
    public void setUp() {
        wordPairDemo = new Control();
        assertTrue(wordPairDemo.size() == 0);
        wordPairDemo.load("wordPairsLibrary_test.txt");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDistribution() {
        int testTries = 1000000; // 1 million!!

        String bil = "bil";
        String hest = "hest";
        String hej = "hej";
        String cykel = "cykel";
        String spil = "spil";
        String fransk = "fransk";
        String dansk = "dansk";
        String krig = "krig";
        String mus = "mus";
        String kaffe = "kaffe";
        int bilOccur = 0;
        int hestOccur = 0;
        int hejOccur = 0;
        int cykelOccur = 0;
        int spilOccur = 0;
        int franskOccur = 0;
        int danskOccur = 0;
        int krigOccur = 0;
        int musOccur = 0;
        int kaffeOccur = 0;

        for (int i = 0; i < testTries; i++) {
            String question = wordPairDemo.getRandomQuestion();
            if (question.equals(bil)) {
                bilOccur++;
            } else if (question.equals(hest)) {
                hestOccur++;
            } else if (question.equals(hej)) {
                hejOccur++;
            } else if (question.equals(cykel)) {
                cykelOccur++;
            } else if (question.equals(spil)) {
                spilOccur++;
            } else if (question.equals(fransk)) {
                franskOccur++;
            } else if (question.equals(dansk)) {
                danskOccur++;
            } else if (question.equals(krig)) {
                krigOccur++;
            } else if (question.equals(mus)) {
                musOccur++;
            } else if (question.equals(kaffe)) {
                kaffeOccur++;
            }
        }
        System.out.println("Bil occurs: " + bilOccur + " times.");
        System.out.println("Hest occurs: " + hestOccur + " times.");
        System.out.println("Hej occurs: " + hejOccur + " times.");
        System.out.println("Cykel occurs: " + cykelOccur + " times.");
        System.out.println("Spil occurs: " + spilOccur + " times.");
        System.out.println("Fransk occurs: " + franskOccur + " times.");
        System.out.println("Dansk occurs: " + danskOccur + " times.");
        System.out.println("Krig occurs: " + krigOccur + " times.");
        System.out.println("Mus occurs: " + musOccur + " times.");
        System.out.println("Kaffe occurs: " + kaffeOccur + " times.");

    }
}
