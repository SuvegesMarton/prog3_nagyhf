package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import grid.Grid;

public class SolveGui extends GridGui {
    public SolveGui(Grid grid){
        super(grid);
    }

    @Override
    protected void updateGUI() {
        userInput = false;
        for (int i=0;i<gridSize;i++) {
            for (int j=0;j<gridSize;j++) {
                int value = this.grid.getValue(i, j);
                if (value != 0) {
                    this.sudokuCells[j][i].setText(String.valueOf(value));
                }
                else {
                    this.sudokuCells[j][i].setText("");
                }

                if (this.grid.getIsHardCoded(i, j)) {
                    this.sudokuCells[j][i].setBackground(Color.LIGHT_GRAY);
                    this.sudokuCells[j][i].setEditable(false);
                } else {
                    this.sudokuCells[j][i].setBackground(Color.WHITE);
                    this.sudokuCells[j][i].setEditable(true);
                }
            }
        }
        this.bottomTextField.setText("Untested position.");

        userInput = true;
    }

    protected void addControlBar(JFrame frame) {
        JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS)); // Vertical layout

            // Add a second button
            JButton button2 = new JButton("Exit");
            controlPanel.add(button2);
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });

            // Add a button
            JButton button = new JButton("Check");
            controlPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonClicked();
                }
            });



            // Add a text field
            this.bottomTextField = new JTextField(10);
            this.bottomTextField.setText("Untested position.");
    
            controlPanel.add(this.bottomTextField);

            // Add the control panel to the bottom of the frame
            frame.add(controlPanel, BorderLayout.SOUTH);
    }

    protected void buttonClicked() {
        if (this.grid.isLegal()) {
            this.bottomTextField.setText("This solution breaks no rules.");
        }
        else {
            this.bottomTextField.setText("This solution breaks at least 1 rule.");
        }
    }
}
