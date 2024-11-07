package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import grid.Grid;
import grid.GridJsonIO;

import gui.*;

public class GuiMenu extends JFrame {
    public GuiMenu() {
        // Set the title of the window
        super("Sudoku Master 2000");

        // Set layout manager for the frame (using BorderLayout)
        setLayout(new BorderLayout(10, 10));

        // Create a panel to hold the buttons (using FlowLayout to manage their positioning)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));  // Adds horizontal and vertical spacing

        // Create buttons
        JButton button1 = new JButton("EDIT");
        JButton button2 = new JButton("SOLVE");

        // Add buttons to the panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Create a label to display the text (non-editable and centered)
        JLabel displayLabel = new JLabel("<html>Enter map name below to solve / edit grids.<br>If it does not exist yet, it will be created now.</html>", JLabel.CENTER);
        
        // Optionally set some padding or border for visual clarity
        displayLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  // Add padding


        // Create a panel to hold the text field, to give it padding from the edges
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BorderLayout());
        textFieldPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Top, left, bottom, right padding

        // Create the text field and center the text inside it
        JTextField textField = new JTextField(20);  // Adjust size as needed
        textField.setHorizontalAlignment(JTextField.CENTER);  // Center the text inside the input field

        // Add the text field to the textFieldPanel
        textFieldPanel.add(textField, BorderLayout.CENTER);

        // Add ActionListener to button1 to update the displayLabel when clicked
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the label with new text when button1 is clicked
                edit(textField.getText());
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the label with new text when button1 is clicked
                solve(textField.getText());
            }
        });

        // Add components to the frame
        add(buttonPanel, BorderLayout.NORTH);       // Buttons at the top
        add(displayLabel, BorderLayout.CENTER);         // Text area in the middle
        add(textFieldPanel, BorderLayout.SOUTH);    // Padded text field at the bottom


        // Set default close operation and window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);  // Width, height
        setVisible(true);
    }


    public void solve(String id) {
        GridJsonIO io = new GridJsonIO();
        Grid b = io.loadFromJSON(id);
		GridGui sg = new SolveGui(b);
		sg.makeWindow();
    }

    public void edit(String id) {
        GridJsonIO io = new GridJsonIO();
        Grid b = io.loadFromJSON(id);
		GridGui eg = new EditGui(b);
		eg.makeWindow();
    }
}