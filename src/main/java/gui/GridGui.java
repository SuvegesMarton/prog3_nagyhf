package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import grid.Grid;

public class GridGui {
    private Grid grid;
    private final int gridBase;
    private final int gridSize;
    private JTextField[][] sudokuCells;


    public GridGui(Grid grid) {
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

        // Initialize each JTextField and add it to the panel
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER); // Center the text
                cell.setFont(new Font("Arial", Font.BOLD, 35)); // Set font size
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

        // Set the window to be visible
        frame.setVisible(true);

        this.updateValues();
    }

    public void updateValues() {
        for (int i=0;i<gridSize;i++) {
            for (int j=0;j<gridSize;j++) {
            int value = this.grid.getValue(i, j);
            if (value != 0) {
                this.sudokuCells[j][i].setText(String.valueOf(value));
            }
            else {
                this.sudokuCells[j][i].setText("");

            }
        }
        }
    }
}