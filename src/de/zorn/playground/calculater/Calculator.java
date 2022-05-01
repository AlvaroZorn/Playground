package de.zorn.playground.calculater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private static JTextField textField;

    private String storage0;
    private String storage1;
    private String storage2;

    Calculator() {
        storage0 = storage1 = storage2 = "";
    }

    public static void buildUi(Calculator calculator) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        JFrame frame = new JFrame("calculator");

        textField = new JTextField(16);
        textField.setEditable(false);

        JButton b0 = new JButton("0");
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");
        JButton buttonEqual = new JButton("=");
        JButton buttonPlus = new JButton("+");
        JButton buttonMinus = new JButton("-");
        JButton buttonDivide = new JButton("/");
        JButton buttonMultiply = new JButton("*");
        JButton buttonClear = new JButton("C");
        JButton buttonPoint = new JButton(".");

        b0.addActionListener(calculator);
        b1.addActionListener(calculator);
        b2.addActionListener(calculator);
        b3.addActionListener(calculator);
        b4.addActionListener(calculator);
        b5.addActionListener(calculator);
        b6.addActionListener(calculator);
        b7.addActionListener(calculator);
        b8.addActionListener(calculator);
        b9.addActionListener(calculator);
        buttonEqual.addActionListener(calculator);
        buttonPlus.addActionListener(calculator);
        buttonMinus.addActionListener(calculator);
        buttonDivide.addActionListener(calculator);
        buttonMultiply.addActionListener(calculator);
        buttonClear.addActionListener(calculator);
        buttonPoint.addActionListener(calculator);

        JPanel panel = new JPanel();

        panel.add(textField);
        panel.add(buttonPlus);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(buttonMinus);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(buttonMultiply);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);
        panel.add(buttonDivide);
        panel.add(buttonPoint);
        panel.add(b0);
        panel.add(buttonClear);
        panel.add(buttonEqual);

        panel.setBackground(Color.gray);

        frame.add(panel);
        frame.setSize(200, 220);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String actionCommand = actionEvent.getActionCommand();
        char enteredValue = actionCommand.charAt(0);

        if ((enteredValue >= '0' && enteredValue <= '9') || enteredValue == '.') {

            if (!storage1.equals("")) {
                storage2 = storage2 + actionCommand;
            } else {
                storage0 = storage0 + actionCommand;
            }

            textField.setText(storage0 + storage1 + storage2);

        } else if (enteredValue == 'C') {

            storage0 = storage1 = storage2 = "";

            textField.setText(storage0 + storage1 + storage2);

        } else if (enteredValue == '=') {

            textField.setText(storage0 + storage1 + storage2 + "=" + getResult());

            storage0 = Double.toString(getResult());

            storage1 = storage2 = "";

        } else {
            if (storage1.equals("") || storage2.equals(""))
                storage1 = actionCommand;
            else {
                storage0 = Double.toString(getResult());

                storage1 = actionCommand;

                storage2 = "";
            }
            textField.setText(storage0 + storage1 + storage2);
        }

    }

    private double getResult() {
        if (storage1.equals("+")) {
            return (Double.parseDouble(storage0) + Double.parseDouble(storage2));
        } else if (storage1.equals("-")) {
            return (Double.parseDouble(storage0) - Double.parseDouble(storage2));
        } else if (storage1.equals("/")) {
            return (Double.parseDouble(storage0) / Double.parseDouble(storage2));
        } else {
            return (Double.parseDouble(storage0) * Double.parseDouble(storage2));
        }
    }

    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        buildUi(calculator);
    }
}
