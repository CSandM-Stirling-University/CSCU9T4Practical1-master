// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(20);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField where = new JTextField(10);
    private JTextField terrain = new JTextField(10);
    private JTextField tempo = new JTextField(10);
    private JTextField repetitions = new JTextField(2);
    private JTextField recovery = new JTextField(2);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JLabel labwhere = new JLabel(" Location:");
    private JLabel labterrain = new JLabel(" Terrain:");
    private JLabel labtempo = new JLabel(" Tempo:");
    private JLabel labrepetitions = new JLabel(" Repetitions:");
    private JLabel labrecovery = new JLabel(" Recovery: ");

    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("Find All By Date");
    private JButton remove = new JButton("Remove");

    String[] gscr = {"generic", "swim", "run", "cycle"};
    private JComboBox box = new JComboBox(gscr);
    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());

        box.addActionListener(this);
        add(box);

        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);

        add(labwhere);
        add(where);
        add(labterrain);
        add(terrain);
        add(labtempo);
        add(tempo);
        add(labrepetitions);
        add(repetitions);
        add(labrecovery);
        add(recovery);
        labwhere.setVisible(false);
        where.setVisible(false);
        where.setEditable(true);
        labterrain.setVisible(false);
        terrain.setVisible(false);
        terrain.setEditable(true);
        labtempo.setVisible(false);
        tempo.setVisible(false);
        tempo.setEditable(true);
        labrepetitions.setVisible(false);
        repetitions.setVisible(false);
        repetitions.setEditable(true);
        labrecovery.setVisible(false);
        recovery.setVisible(false);
        recovery.setEditable(true);


        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(findAllByDate);
        findAllByDate.addActionListener(this);
        add(remove);
        remove.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);

        setSize(720, 200);
        setVisible(true);
        blankDisplay();
    }

    // To save typing in new entries while testing, uncomment
    // the following lines (or add your own test cases)

    // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry((String) box.getSelectedItem());
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == findAllByDate) {
            message = findAllByDate();
        }
        if (event.getSource() == box) {
            JComboBox jcb = (JComboBox) event.getSource();
            createGUI((String) jcb.getSelectedItem());
        }
        if (event.getSource() == remove) {
            message = remove();
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String record) {
        String message = "Record added\n";
        System.out.println("Adding " + record + " entry to the records");
        int m, d, y, h, mm, s, rep, rec;
        float km;
        String n = "", ter = "", tem = "", wr = "";
        Entry entry = null;
        try {
            m = Integer.parseInt(month.getText());
            d = Integer.parseInt(day.getText());
            y = Integer.parseInt(year.getText());
            km = java.lang.Float.parseFloat(dist.getText());
            h = Integer.parseInt(hours.getText());
            mm = Integer.parseInt(mins.getText());
            s = Integer.parseInt(secs.getText());
            n = name.getText();
            switch (record) {
                case "cycle":
                    ter = terrain.getText();
                    tem = tempo.getText();
                    entry = new CycleEntry(n, d, m, y, h, mm, s, km, ter, tem);
                    if (tem.isEmpty() || ter.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Text field(s) must be filled in.\nPlease enter data again");
                        message = "Input error. Insert data again";
                    }
                    break;
                case "run":
                    rep = Integer.parseInt(repetitions.getText());
                    rec = Integer.parseInt(recovery.getText());
                    entry = new SprintEntry(n, d, m, y, h, mm, s, km, rep, rec);
                    outputArea.setText("looking up record ...");
                    //int cannot be null
                    break;
                case "swim":
                    wr = where.getText();
                    entry = new SwimEntry(n, d, m, y, h, mm, s, km, wr);
                    if (wr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Location field must be filled in.\nPlease enter data again");
                        message = "Input error. Insert data again";
                    }
                    break;
                case "generic":
                    entry = new Entry(n, d, m, y, h, mm, s, km);
            }
            myAthletes.addEntry(entry);
        } catch (NumberFormatException nfe) {
            System.err.println("Wrong input");
            JOptionPane.showMessageDialog(null, "Wrong number format or empty cells.\nPlease enter data again");
            message = "Input error. Insert data again";
        }
        if (n.isEmpty()) {
            message = "Input error. Insert data again";
        }

        return message;
    }

    public String lookupEntry() {
        String message;
        String n = "";
        try {
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            n = name.getText();
            message = myAthletes.lookupEntry(d, m, y);
        } catch (NumberFormatException nfe) {
            System.err.println("Wrong input");
            JOptionPane.showMessageDialog(null, "Wrong number format or empty cells.\nPlease enter data again");
            message = "Input error. Insert data again";
        }
        if (n.isEmpty()) {
            message = "Input error. Insert data again";
        }

        return message;
    }

    public String findAllByDate() {
        String message;
        try {
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            outputArea.setText("Not implemented yet ...");
            message = myAthletes.findAllByDate(d, m, y);
        } catch (NumberFormatException nfe) {
            System.err.println("Wrong input");
            JOptionPane.showMessageDialog(null, "Wrong number format or empty cells.\nPlease enter data again");
            message = "Input error. Insert data again";
        }
        if (message.isEmpty()) {
            message = "Input error. Insert data again";
        }
        return message;
    }

    public String remove() {
        String s = name.getText();
        String message;
        try {
            int m = Integer.parseInt(month.getText());
            int d = Integer.parseInt(day.getText());
            int y = Integer.parseInt(year.getText());
            myAthletes.remove(s, d, m, y);
            message = "Entry removed";
        } catch (NumberFormatException nfe) {
            System.err.println("Wrong input");
            JOptionPane.showMessageDialog(null, "Wrong number format or empty cells.\nPlease enter data again");
            message = "Input error. Insert data again";
        }
        return message;
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        where.setText("");
        repetitions.setText("");
        recovery.setText("");
        terrain.setText("");
        tempo.setText("");

    }// blankDisplay

    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

    public void createGUI(String s) {
        switch (s) {
            case "generic":
                where.setVisible(false);
                labwhere.setVisible(false);
                repetitions.setVisible(false);
                labrepetitions.setVisible(false);
                recovery.setVisible(false);
                labrecovery.setVisible(false);
                terrain.setVisible(false);
                labterrain.setVisible(false);
                tempo.setVisible(false);
                labtempo.setVisible(false);
                blankDisplay();
                break;

            case "swim":
                where.setVisible(true);
                labwhere.setVisible(true);
                repetitions.setVisible(false);
                labrepetitions.setVisible(false);
                recovery.setVisible(false);
                labrecovery.setVisible(false);
                terrain.setVisible(false);
                labterrain.setVisible(false);
                tempo.setVisible(false);
                labtempo.setVisible(false);
                blankDisplay();
                break;

            case "run":
                where.setVisible(false);
                labwhere.setVisible(false);
                repetitions.setVisible(true);
                labrepetitions.setVisible(true);
                recovery.setVisible(true);
                labrecovery.setVisible(true);
                terrain.setVisible(false);
                labterrain.setVisible(false);
                tempo.setVisible(false);
                labtempo.setVisible(false);

                blankDisplay();
                break;

            case "cycle":
                where.setVisible(false);
                labwhere.setVisible(false);
                repetitions.setVisible(false);
                labrepetitions.setVisible(false);
                recovery.setVisible(false);
                labrecovery.setVisible(false);
                terrain.setVisible(true);
                labterrain.setVisible(true);
                tempo.setVisible(true);
                labtempo.setVisible(true);

                blankDisplay();
                break;
        }

    } // TrainingRecordGUI
}
