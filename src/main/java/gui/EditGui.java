package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import grid.Grid;
import grid.GridJsonIO;

public class EditGui extends GridGui {
    public EditGui(Grid grid) {
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
                    this.grid.setIsHardCoded(i, j, true);
                }
                else {
                    this.sudokuCells[j][i].setText("");
                    this.grid.setIsHardCoded(i, j, false);
                }
            }
        }
        userInput = true;
    }

    @Override
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
            JButton button = new JButton("Save");
            controlPanel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonClicked();
                }
            });

            // Add a label
            JLabel label = new JLabel("         With grid ID: ");
            controlPanel.add(label);

            // Add a text field
            bottomTextField = new JTextField(10);
            bottomTextField.setText(grid.getID());
            controlPanel.add(bottomTextField);

            // Add the control panel to the bottom of the frame
            frame.add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void buttonClicked() {
        String saveID = this.bottomTextField.getText();
        this.grid.setID(saveID);
        GridJsonIO io = new GridJsonIO();
        io.saveToJSON(this.grid);
    }
}
