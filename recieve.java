package HangMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class recieve extends JPanel {
    private final String WORD = "receive";
    private StringBuilder guessedWord = new StringBuilder("_ _ _ _ _ _ _");
    private StringBuilder missedLetters = new StringBuilder();
    private int part = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawArc(75, 190, 100, 50, 0, 180);
        g.drawLine(125, 190, 125, 50);
        g.drawLine(125, 50, 200, 50);
        g.drawLine(200, 50, 200, 70);
        if (part >= 1) g.drawOval(175, 70, 50, 50);
        if (part >= 2) g.drawLine(200, 120, 200, 170);
        if (part >= 3) g.drawLine(200, 120, 175, 150);
        if (part >= 4) g.drawLine(200, 120, 225, 150);
        if (part >= 5) g.drawLine(200, 170, 175, 200);
        if (part >= 6) g.drawLine(200, 170, 225, 200);
    }
    private void checkWinCondition() {
        if (!guessedWord.toString().contains("_")) {
            JOptionPane.showMessageDialog(null, "You won!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hangman Game");
        recieve receive = new recieve();
        frame.add(receive, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Guess a word:");
        JTextField textField = new JTextField(5);
        JLabel wordLabel = new JLabel(receive.guessedWord.toString());
        JLabel missedLettersLabel = new JLabel("Missed Letters: ");

        panel.add(label);
        panel.add(textField);
        panel.add(wordLabel);
        panel.add(missedLettersLabel);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText().toLowerCase();
                textField.setText("");
                if (input.length() != 1) {
                    return;
                }
                char guess = input.charAt(0);
                if (receive.WORD.contains(String.valueOf(guess))) {
                    for (int i = 0; i < receive.WORD.length(); i++) {
                        if (receive.WORD.charAt(i) == guess) {
                            receive.guessedWord.setCharAt(2 * i, guess);
                        }
                    }
                    wordLabel.setText(receive.guessedWord.toString());
                    receive.checkWinCondition();  // Call the checkWinCondition method here
                } else {
                    receive.part++;
                    receive.missedLetters.append(guess).append(" ");
                    missedLettersLabel.setText("Missed Letters: " + receive.missedLetters.toString());
                    receive.repaint();
                    if (receive.part > 6) {
                        System.exit(0);
                    }
                }
            }
        });


        frame.add(panel, BorderLayout.SOUTH);
        frame.setSize(469, 469);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
