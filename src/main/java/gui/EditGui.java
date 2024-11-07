package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import grid.Grid;
import grid.GridJsonIO;

public class EditGui {
    private Grid grid;
    private final int gridBase;
    private final int gridSize;
    private JTextField[][] sudokuCells;
    private JTextField saveIDInputField;
    private boolean userInput = true;


    public EditGui(Grid grid) {
        this.grid = grid;
        gridBase = this.grid.getGridSizeBase();
        gridSize = gridBase*gridBase;
    }

    public void makeWindow() {
        // Create the main window (JFrame)
        JFrame frame = new JFrame("Sudoku - Grid ID: " + grid.getID());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        
        // Create a panel to hold the Sudoku grid
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(gridSize, gridSize));

        // Create a 9x9 grid of JTextFields
        sudokuCells = new JTextField[gridSize][gridSize];

        DocumentListener cellListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {if (userInput) {handleGridUpdate(e);SwingUtilities.invokeLater(() -> updateGUI());}}
            @Override
            public void removeUpdate(DocumentEvent e) {if (userInput) {handleGridUpdate(e);SwingUtilities.invokeLater(() -> updateGUI());}}
            @Override
            public void changedUpdate(DocumentEvent e) {if (userInput) {handleGridUpdate(e);SwingUtilities.invokeLater(() -> updateGUI());}}
        };

        // Initialize each JTextField and add it to the panel
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER); // Center the text
                cell.setFont(new Font("Arial", Font.BOLD, 35)); // Set font size
                cell.putClientProperty("indexes", new int[]{row, col}); // Use putClientProperty
                cell.getDocument().addDocumentListener(cellListener); // Attach the same DocumentListener to each cell
                cell.getDocument().putProperty("owner", cell); // Store a reference to the cell in its document

                sudokuCells[row][col] = cell;
                panel.add(cell);
                

                // Set the borders to highlight 3x3 subgrids
                Border outerBorder = BorderFactory.createMatteBorder(
                    (row % gridBase == 0 && row != 0) ? 2 : 0, // Top border (black for subgrid)
                    (col % gridBase == 0 && col != 0) ? 2 : 0, // Left border (black for subgrid)
                    0, // Bottom border (no border on the outside)
                    0, // Right border (no border on the outside)
                    Color.BLACK
                );

                Border innerBorder = BorderFactory.createMatteBorder(
                    1, // Top (grey for inner lines)
                    1, // Left (grey for inner lines)
                    (row == gridSize - 1) ? 0 : 1, // Bottom (grey for inner lines, none for last row)
                    (col == gridSize - 1) ? 0 : 1, // Right (grey for inner lines, none for last col)
                    Color.GRAY
                );

                cell.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
            }
        }

        // Add the panel to the main window
        frame.add(panel);

        addControlBar(frame);   
        

        // Set the window to be visible
        frame.setVisible(true);

        this.updateGUI();
    }

    private void updateGUI() {
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

    //updates only grid.values. updating the gui is another step!
    private void handleGridUpdate (DocumentEvent e) {
        // Get the updated component (JTextField)
        JTextField updatedCell = (JTextField) e.getDocument().getProperty("owner");

        // Get the new value entered in the cell
        String newValue = updatedCell.getText();

        int[] indexes = (int[]) updatedCell.getClientProperty("indexes");
        int col = indexes[0];
        int row = indexes[1];

        if (newValue.length() == 0) {this.grid.setValue(row, col, 0); return;}
        char c = newValue.charAt(newValue.length() - 1);
        if (!Character.isDigit(c)) {this.grid.setValue(row, col, 0); return;}
        this.grid.setValue(row, col, c - '0');
    }

    private void addControlBar(JFrame frame) {
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
            saveIDInputField = new JTextField(10);
            saveIDInputField.setText(grid.getID());
            controlPanel.add(saveIDInputField);

            // Add the control panel to the bottom of the frame
            frame.add(controlPanel, BorderLayout.SOUTH);
    }

    private void buttonClicked() {
        String saveID = this.saveIDInputField.getText();
        this.grid.setID(saveID);
        GridJsonIO io = new GridJsonIO();
        io.saveToJSON(this.grid);
    }
}
