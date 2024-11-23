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

/**
 * GUI and backend operations for editing and saving a sudoku puzzle/grid.
 */
public class EditGui extends GridGui {
    JButton button;
    public EditGui(Grid grid) {
        super(grid);
    }

    /**
     * updates GUI as well as the stored grid's isHardCoded values.
     */
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

    /**
     * adds interface under the grid GUI, to save grid or exit editor mode. also allows to give new name to the grid (save as... function)
     */
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
            button = new JButton("Save");
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

    /**
     * gets called upon save button getting clicked: saves current state with the given name (id).
     */
    @Override
    protected void buttonClicked() {
        String saveID = this.bottomTextField.getText();
        this.grid.setID(saveID);
        GridJsonIO io = new GridJsonIO();
        if(!io.saveToJSON(this.grid)) {
            button.setText("Save failed...");
            button.setEnabled(false);
        }
    }
}
