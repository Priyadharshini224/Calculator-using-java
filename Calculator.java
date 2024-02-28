package practice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends Frame implements ActionListener {
    private TextField inputField, outputField;
    private String operator;
    private double firstOperand;

    public Calculator() {
        // Creating frame:"A Space for the objects"
        setTitle("Calculator");//title of the project
        setSize(400, 400); // size of the frame
        setLayout(new BorderLayout());

        // Panel for input and output text fields it is made in grid layout for positioning
        Panel textFieldsPanel = new Panel();
        textFieldsPanel.setLayout(new GridLayout(2, 1, 5, 5)); // Adjust grid size and spacing

        // Textfield for input
       
        inputField = new TextField();//creating a new text field
        inputField.setEditable(false);
        inputField.setBackground(Color.BLACK);
        inputField.setForeground(Color.WHITE); // Set text color to white
        inputField.setPreferredSize(new Dimension(400, 50)); // Increased the size of the input field
        textFieldsPanel.add(inputField);//adding the text field to the 

        // Textfield for the output
        outputField = new TextField();
        outputField.setEditable(false);
        outputField.setBackground(Color.BLACK);
        outputField.setForeground(Color.WHITE); // Set text color to white
        outputField.setPreferredSize(new Dimension(400, 50)); // Increased the size of the output field
        textFieldsPanel.add(outputField);//adding the output textfield to the panel

        add(textFieldsPanel, BorderLayout.NORTH);//north means adding the elements to top

        // Panel for buttons
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5)); // Adjust grid size and spacing

        // Buttons creating
        //Buttons are created as in a simple calculator
        //row 1
        addButton("7", buttonPanel);
        addButton("8", buttonPanel);
        addButton("9", buttonPanel);
        addButton("/", buttonPanel);
        //row 2
        addButton("4", buttonPanel);
        addButton("5", buttonPanel);
        addButton("6", buttonPanel);
        addButton("*", buttonPanel);
        //row 3
        addButton("1", buttonPanel);
        addButton("2", buttonPanel);
        addButton("3", buttonPanel);
        addButton("-", buttonPanel);
        //row 4
        addButton("0", buttonPanel);
        addButton(".", buttonPanel);
        addButton("=", buttonPanel);
        addButton("+", buttonPanel);
        //C button used for clear
        addButton("C", buttonPanel);
        
        add(buttonPanel, BorderLayout.CENTER);//adding button to center of the panel

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });// used to end the process
        // Set operator to empty initially
        operator = "";
    }

    private void addButton(String label, Panel panel) {
        Button button = new Button(label);
        // adding action to the button
        button.addActionListener(this);
        panel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        // getting result
        switch (command) {
            case "=":
                double secondOperand = Double.parseDouble(inputField.getText());
                double result = 0;

                switch (operator) {
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "*":
                        result = firstOperand * secondOperand;
                        break;
                    case "/":
                        if (secondOperand != 0) {
                            result = firstOperand / secondOperand;
                        } else {
                            outputField.setText("Error");
                            return;
                        }
                        break;
                }
                 //returning text to the output text field
                outputField.setText(String.valueOf(result));
                operator = "";
                break;
            case "C":
                inputField.setText("");
                outputField.setText("");
                operator = "";
                firstOperand = 0;
                break;
            default:
                if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
                    // Append digits or decimal point to the input field
                    inputField.setText(inputField.getText() + command);
                } else {
                    // Handle operators
                    operator = command;
                    firstOperand = Double.parseDouble(inputField.getText());
                    inputField.setText("");
                }
                break;
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);//only visible if true
    }
}
