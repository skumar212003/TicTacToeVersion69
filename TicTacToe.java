package HangMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe {

    private JFrame frame;
    private JButton[] buttons;
    private char turn;
    private Random rand;

    public TicTacToe() {
        frame = new JFrame("Tic Tac Toe");
        buttons = new JButton[9];
        turn = 'X';
        rand = new Random();

        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("-");
            frame.add(buttons[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton btnClicked = (JButton) e.getSource();
                    if (btnClicked.getText().equals("-")) {
                        btnClicked.setText(String.valueOf(turn));
                        if (gameOver('X')) {
                            JOptionPane.showMessageDialog(frame, "Player X Wins!");
                            resetGame();
                        } else if (isDraw()) {
                            JOptionPane.showMessageDialog(frame, "It's a Draw!");
                            resetGame();
                        } else {
                            botTurn();
                            if (gameOver('O')) {
                                JOptionPane.showMessageDialog(frame, "Computer Wins!");
                                resetGame();
                            } else if (isDraw()) {
                                JOptionPane.showMessageDialog(frame, "It's a Draw!");
                                resetGame();
                            }
                        }
                    }
                }
            });
        }

        frame.setVisible(true);
    }

    public boolean gameOver(char player) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(buttons[i + 3].getText())
                    && buttons[i + 3].getText().equals(buttons[i + 6].getText())
                    && !buttons[i].getText().equals("-") && buttons[i].getText().charAt(0) == player) {
                highlightWinningCombo(i, i + 3, i + 6);
                return true;
            }
            if (buttons[3 * i].getText().equals(buttons[3 * i + 1].getText())
                    && buttons[3 * i + 1].getText().equals(buttons[3 * i + 2].getText())
                    && !buttons[3 * i].getText().equals("-") && buttons[3 * i].getText().charAt(0) == player) {
                highlightWinningCombo(3 * i, 3 * i + 1, 3 * i + 2);
                return true;
            }
        }
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[8].getText())
                && !buttons[0].getText().equals("-") && buttons[0].getText().charAt(0) == player) {
            highlightWinningCombo(0, 4, 8);
            return true;
        }
        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[4].getText().equals(buttons[6].getText())
                && !buttons[2].getText().equals("-") && buttons[2].getText().charAt(0) == player) {
            highlightWinningCombo(2, 4, 6);
            return true;
        }
        return false;
    }

    public void highlightWinningCombo(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
    }

    public void resetGame() {
        for (JButton btn : buttons) {
            btn.setText("-");
            btn.setBackground(null);  // reset the color
        }
    }

    public boolean isDraw() {
        for (JButton btn : buttons) {
            if (btn.getText().equals("-")) {
                return false;
            }
        }
        return true;
    }
    public int minimax(boolean isMaximizing) {
        if (gameOver('X')) {
            return -1;
        }
        if (gameOver('O')) {
            return 1;
        }
        if (isDraw()) {
            return 0;
        }

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (JButton btn : buttons) {
                if (btn.getText().equals("-")) {
                    btn.setText("O");
                    int eval = minimax(false);
                    btn.setText("-");
                    maxEval = Math.max(maxEval, eval);
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (JButton btn : buttons) {
                if (btn.getText().equals("-")) {
                    btn.setText("X");
                    int eval = minimax(true);
                    btn.setText("-");
                    minEval = Math.min(minEval, eval);
                }
            }
            return minEval;
        }
    }

    public void botTurn() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("-")) {
                buttons[i].setText("O");
                int currentScore = minimax(false);
                buttons[i].setText("-");
                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    bestMove = i;
                }
            }
        }

        if (bestMove != -1) {
            buttons[bestMove].setText("O");
        }
    } 	
    

  

    public static void main(String[] args) {
        new TicTacToe();
    }
}
