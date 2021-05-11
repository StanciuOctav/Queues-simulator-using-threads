package View;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;

public class View {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTextField textField_12;
    private JButton Button;

    public View() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        textFields();
        labels();

        Button = new JButton("Enter Input");
        Button.setFont(new Font("Tahoma", Font.PLAIN, 16));
        Button.setBounds(180, 142, 294, 53);
        frame.getContentPane().add(Button);

        frame.setVisible(true);
    }

    public void textFields() {
        textField = new JTextField();
        textField.setBounds(35, 42, 86, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(180, 42, 99, 20);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(338, 42, 86, 20);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(35, 104, 86, 20);
        frame.getContentPane().add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(193, 104, 86, 20);
        frame.getContentPane().add(textField_4);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(338, 104, 86, 20);
        frame.getContentPane().add(textField_5);

        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(41, 173, 86, 20);
        frame.getContentPane().add(textField_6);

        textField_7 = new JTextField();
        textField_7.setBounds(96, 311, 107, 20);
        frame.getContentPane().add(textField_7);
        textField_7.setColumns(10);

        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(96, 400, 107, 20);
        frame.getContentPane().add(textField_8);

        textField_9 = new JTextField();
        textField_9.setColumns(10);
        textField_9.setBounds(341, 311, 133, 20);
        frame.getContentPane().add(textField_9);

        textField_10 = new JTextField();
        textField_10.setColumns(10);
        textField_10.setBounds(341, 400, 133, 20);
        frame.getContentPane().add(textField_10);

        textField_11 = new JTextField();
        textField_11.setColumns(10);
        textField_11.setBounds(134, 233, 340, 20);
        frame.getContentPane().add(textField_11);

        textField_12 = new JTextField();
        textField_12.setColumns(10);
        textField_12.setBounds(193, 264, 133, 20);
        frame.getContentPane().add(textField_12);
    }

    public void labels() {
        JLabel label = new JLabel("minProcessingTime:");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setBounds(10, 11, 117, 20);
        frame.getContentPane().add(label);

        JLabel label_1 = new JLabel("maxProcessingTime:");
        label_1.setHorizontalAlignment(SwingConstants.RIGHT);
        label_1.setBounds(134, 11, 145, 20);
        frame.getContentPane().add(label_1);

        JLabel label_3 = new JLabel("maxServiceTime:");
        label_3.setHorizontalAlignment(SwingConstants.RIGHT);
        label_3.setBounds(4, 73, 117, 20);
        frame.getContentPane().add(label_3);

        JLabel label_2 = new JLabel("minServiceTime:");
        label_2.setHorizontalAlignment(SwingConstants.RIGHT);
        label_2.setBounds(307, 11, 117, 20);
        frame.getContentPane().add(label_2);

        JLabel label_5 = new JLabel("Clients:");
        label_5.setHorizontalAlignment(SwingConstants.RIGHT);
        label_5.setBounds(307, 73, 92, 20);
        frame.getContentPane().add(label_5);

        JLabel label_4 = new JLabel("Queues:");
        label_4.setHorizontalAlignment(SwingConstants.RIGHT);
        label_4.setBounds(180, 73, 82, 20);
        frame.getContentPane().add(label_4);

        JLabel label_6 = new JLabel("Time Limit:");
        label_6.setHorizontalAlignment(SwingConstants.RIGHT);
        label_6.setBounds(0, 142, 117, 20);
        frame.getContentPane().add(label_6);

        JLabel label_7 = new JLabel("Queue 1:");
        label_7.setHorizontalAlignment(SwingConstants.RIGHT);
        label_7.setBounds(0, 311, 86, 20);
        frame.getContentPane().add(label_7);

        JLabel label_9 = new JLabel("Queue 2:");
        label_9.setHorizontalAlignment(SwingConstants.RIGHT);
        label_9.setBounds(0, 403, 86, 20);
        frame.getContentPane().add(label_9);

        JLabel label_8 = new JLabel("Queue 3:");
        label_8.setHorizontalAlignment(SwingConstants.RIGHT);
        label_8.setBounds(254, 314, 77, 20);
        frame.getContentPane().add(label_8);

        JLabel label_10 = new JLabel("Queue 4:");
        label_10.setHorizontalAlignment(SwingConstants.RIGHT);
        label_10.setBounds(245, 403, 86, 20);
        frame.getContentPane().add(label_10);

        JLabel label_11 = new JLabel("Remaining Clients:");
        label_11.setBounds(10, 233, 133, 20);
        frame.getContentPane().add(label_11);

        JLabel label_12 = new JLabel("TIME: ");
        label_12.setHorizontalAlignment(SwingConstants.CENTER);
        label_12.setBounds(112, 267, 133, 20);
        frame.getContentPane().add(label_12);
    }

    public int getMinProcessingTime() {
        return Integer.parseInt(textField.getText());
    }

    public int getMaxProcessingTime() {
        return Integer.parseInt(textField_1.getText());
    }

    public int getMinServiceTime() {
        return Integer.parseInt(textField_2.getText());
    }

    public int getMaxServiceTime() {
        return Integer.parseInt(textField_3.getText());
    }

    public int getNrOfQueues() {
        return Integer.parseInt(textField_4.getText());
    }

    public int getNrOfClients() {
        return Integer.parseInt(textField_5.getText());
    }

    public int getTimeLimit() {
        return Integer.parseInt(textField_6.getText());
    }

    public void setQueue1(String x) {
        textField_7.setText(x);
    }

    public void setQueue2(String x) {
        textField_8.setText(x);
    }

    public void setQueue3(String x) {
        textField_9.setText(x);
    }

    public void setQueue4(String x) {
        textField_10.setText(x);
    }

    public void setRemainingClients(String x) {
        textField_11.setText(x);
    }

    public void setTime(int x) {
        textField_12.setText("" + x);
    }

    public void addActionListener(ActionListener a) {
        this.Button.addActionListener(a);
    }

}
