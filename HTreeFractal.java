package hw13;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class HTreeFractal extends JPanel {

    private int order; // The order of the H-tree

    public void setOrder(int order) {
        this.order = order;
    }

    // Recursive function to draw the H-tree
    public void drawHTree(Graphics g, int x, int y, int length, int depth) {
        if (depth == 0) {
            return;
        }
        
        // Draw the current 'H'
        int x0 = x - length / 2;
        int x1 = x + length / 2;
        int y0 = y - length / 2;
        int y1 = y + length / 2;

        g.drawLine(x0, y0, x0, y1); // Left vertical line
        g.drawLine(x1, y0, x1, y1); // Right vertical line
        g.drawLine(x0, y, x1, y);  // Horizontal line

        // Recursively draw 4 smaller H-trees
        int newLength = length / 2;
        drawHTree(g, x0, y0, newLength, depth - 1); // Top-left
        drawHTree(g, x0, y1, newLength, depth - 1); // Bottom-left
        drawHTree(g, x1, y0, newLength, depth - 1); // Top-right
        drawHTree(g, x1, y1, newLength, depth - 1); // Bottom-right
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHTree(g, getWidth() / 2, getHeight() / 2, 200, order);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("H-Tree Fractal");
        HTreeFractal hTreeFractal = new HTreeFractal();
        frame.add(hTreeFractal, BorderLayout.CENTER);

        JTextField textField = new JTextField(5);
        JButton button = new JButton("Draw");
        JLabel label = new JLabel("Enter order:");
        
        JPanel controlPanel = new JPanel();
        controlPanel.add(label);
        controlPanel.add(textField);
        controlPanel.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int order = Integer.parseInt(textField.getText());
                hTreeFractal.setOrder(order);
                hTreeFractal.repaint();
            }
        });

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.setSize(500, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
