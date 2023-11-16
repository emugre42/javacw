package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import domain.Task;
import domain.TaskManager;

// launch the application
public class App {

    JFrame frame;
    private JTextField textField;
    private JTextField textField1;
    private JTextField textField2;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton add;
    private JButton update;
    private JButton delete;
    private JButton clear;
    DefaultTableModel model;

    private TaskManager taskManager;

    // create the application

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App window = new App();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public App() {
        initialize();
        taskManager = new TaskManager(model);

    }

    public void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 545, 447);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(176, 196, 222));
        panel.setBounds(0, 0, 531, 410);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Project: ");
        label.setBounds(21, 83, 50, 14);
        panel.add(label);

        JLabel label_1 = new JLabel("Tasks: ");
        label_1.setBounds(21, 105, 46, 14);
        panel.add(label_1);

        JLabel label_2 = new JLabel("Task No: ");
        label_2.setBounds(21, 127, 55, 14);
        panel.add(label_2);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(84, 82, 132, 17);
        panel.add(textField);

        textField1 = new JTextField();
        textField1.setColumns(10);
        textField1.setBounds(84, 104, 132, 17);
        panel.add(textField1);

        textField2 = new JTextField();
        textField2.setColumns(10);
        textField2.setBounds(84, 125, 132, 17);
        panel.add(textField2);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(224, 48, 297, 339);
        panel.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int i = table.getSelectedRow();
                textField.setText(model.getValueAt(i, 0).toString());
                textField1.setText(model.getValueAt(i, 1).toString());
                textField2.setText(model.getValueAt(i, 2).toString());
            }
        });
        table.setBackground(new Color(176, 196, 222));
        model = new DefaultTableModel();
        Object[] column = { "Project", "Tasks", "Task No" };
        final Object[] row = new Object[3];
        model.setColumnIdentifiers(column);
        table.setModel(model);
        scrollPane.setViewportView(table);

        add = new JButton("Add");
        add.setBounds(21, 280, 80, 23);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                String projectName = textField.getText();
                String taskName = textField1.getText();
                String taskNumber = textField2.getText();

                if (textField.getText().equals(" ") || textField1.getText().equals(" ")
                        || textField2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!");
                } else {

                    Task newTask = new Task(projectName, taskName, taskNumber);
                    taskManager.addTask(newTask);

                    textField.setText(" ");
                    textField1.setText(" ");
                    textField2.setText(" ");

                    JOptionPane.showMessageDialog(null, "Saved Successfully");
                }

            }
        });
        table.setModel(model);
        panel.add(add);

        delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int i = table.getSelectedRow();
                if (i >= 0) {
                    taskManager.deleteTask(i);
                    JOptionPane.showMessageDialog(null, "Deleted Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row!");
                }
            }
        });

        delete.setBounds(21, 324, 80, 23);
        panel.add(delete);

        update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int i = table.getSelectedRow();

                if (i >= 0) {

                    String projectName = textField.getText();
                    String taskName = textField1.getText();
                    String taskNumber = textField2.getText();

                    Task updateTask = new Task(projectName, taskName, taskNumber);
                    taskManager.updateTask(updateTask, i);
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row!");
                }
            }
        });
        update.setBounds(133, 280, 80, 23);
        panel.add(update);

        clear = new JButton("Clear");
        clear.setBounds(133, 324, 80, 23);
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                taskManager.clearTask();
            }
        });
        panel.add(clear);
    }

}
