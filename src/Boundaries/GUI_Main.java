/**
 * @author Tobias & Christoffer
 */
package Boundaries;

import Interfaces.WordPairControlInterface;
import Controllere.Control;
import java.util.Scanner;

public class GUI_Main extends javax.swing.JFrame {

    WordPairControlInterface control;

    public GUI_Main() {
        initComponents();
        this.setLocationRelativeTo(null);
        jLabel_result.setVisible(false);
        control = new Control();
        control.load("wordPairsLibrary.txt");
        displayRandomQuestion();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_title = new javax.swing.JLabel();
        jLabel_question = new javax.swing.JLabel();
        jLabel_answer = new javax.swing.JLabel();
        jTextField_answer = new javax.swing.JTextField();
        jTextField_question = new javax.swing.JTextField();
        jButton_lookUp = new javax.swing.JButton();
        jButton_next = new javax.swing.JButton();
        jButton_guess = new javax.swing.JButton();
        jLabel_result = new javax.swing.JLabel();
        jButton_new = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Language Trainer v 0.1");
        setMinimumSize(new java.awt.Dimension(480, 320));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_title.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        jLabel_title.setText("Language Trainer");
        getContentPane().add(jLabel_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 310, -1));

        jLabel_question.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel_question.setText("Question");
        getContentPane().add(jLabel_question, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        jLabel_answer.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel_answer.setText("Answer");
        getContentPane().add(jLabel_answer, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        jTextField_answer.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        getContentPane().add(jTextField_answer, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 120, -1));

        jTextField_question.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jTextField_question.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_questionActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_question, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 120, -1));

        jButton_lookUp.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton_lookUp.setText("Look Up");
        jButton_lookUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_lookUpActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_lookUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 140, -1));

        jButton_next.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton_next.setText("Next");
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 90, -1));

        jButton_guess.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton_guess.setText("Guess");
        jButton_guess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guessActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_guess, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 90, -1));

        jLabel_result.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel_result.setText("Correct/False");
        getContentPane().add(jLabel_result, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, -1, -1));

        jButton_new.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton_new.setText("New");
        jButton_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_newActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_new, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_lookUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_lookUpActionPerformed
        jLabel_result.setText("");
        String output;
        if (jTextField_question.getText().equals("")) {
            output = control.lookup(jTextField_answer.getText());
            jTextField_question.setText(output);
        } else {
            output = control.lookup(jTextField_question.getText());
            jTextField_answer.setText(output);
        }

    }//GEN-LAST:event_jButton_lookUpActionPerformed

    private void jButton_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_newActionPerformed
        String question = jTextField_question.getText();
        String answer = jTextField_answer.getText();
        control.add(question, answer);
        jTextField_question.setText("");
        jTextField_answer.setText("");
        control.save("wordPairsLibrary.txt");
    }//GEN-LAST:event_jButton_newActionPerformed

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        displayRandomQuestion();
    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jButton_guessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guessActionPerformed
        boolean checkGuess = control.checkGuess(jTextField_question.getText(), jTextField_answer.getText());
        if (checkGuess == true) {
            jLabel_result.setText("Correct!");
            jTextField_answer.setText("");
            jTextField_question.setText("");
            jLabel_result.setVisible(true);
            displayRandomQuestion();
        } else {
            jLabel_result.setText("Wrong!");
            jTextField_answer.setText("");
            jLabel_result.setVisible(true);
        }
    }//GEN-LAST:event_jButton_guessActionPerformed

    private void jTextField_questionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_questionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_questionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Main().setVisible(true);
            }
        });
    }

    private void hideInterface() {
        jTextField_question.setVisible(false);
        jTextField_answer.setVisible(false);
        jButton_guess.setVisible(false);
        jButton_lookUp.setVisible(false);
        jButton_next.setVisible(false);

        jLabel_answer.setVisible(false);
        jLabel_question.setVisible(false);
        jLabel_result.setVisible(false);
    }

    private void showInterface() {
        jTextField_question.setVisible(true);
        jTextField_answer.setVisible(true);
        jButton_guess.setVisible(true);
        jButton_lookUp.setVisible(true);
        jButton_next.setVisible(true);
        jButton_new.setVisible(false);
        jLabel_answer.setVisible(true);
        jLabel_question.setVisible(true);
        jLabel_result.setVisible(true);
    }

    private void displayRandomQuestion() {
        String text = control.getRandomQuestion();
        String question;
        Scanner sc = new Scanner(text).useDelimiter(",");
        question = sc.next();
        jTextField_question.setText(question);
        jTextField_answer.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_guess;
    private javax.swing.JButton jButton_lookUp;
    private javax.swing.JButton jButton_new;
    private javax.swing.JButton jButton_next;
    private javax.swing.JLabel jLabel_answer;
    private javax.swing.JLabel jLabel_question;
    private javax.swing.JLabel jLabel_result;
    private javax.swing.JLabel jLabel_title;
    private javax.swing.JTextField jTextField_answer;
    private javax.swing.JTextField jTextField_question;
    // End of variables declaration//GEN-END:variables
}