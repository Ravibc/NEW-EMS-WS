package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Projecthome extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    JButton createProject, viewProject, deleteProject, updateProject, back;

    Projecthome() {

        setLayout(null);

        JLabel heading = new JLabel("Project Management");
        heading.setBounds(250, 20, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        add(heading);

        createProject = new JButton("Create Project");
        createProject.setBounds(250, 80, 150, 40);
        createProject.addActionListener(this);
        add(createProject);

        viewProject = new JButton("View Projects");
        viewProject.setBounds(410, 80, 150, 40);
        viewProject.addActionListener(this);
        add(viewProject);

        updateProject = new JButton("Update Project");
        updateProject.setBounds(250, 140, 150, 40);
        updateProject.addActionListener(this);
        add(updateProject);

        deleteProject = new JButton("Delete Project");
        deleteProject.setBounds(410, 140, 150, 40);
        deleteProject.addActionListener(this);
        add(deleteProject);

        // Add the Back button
        back = new JButton("Back");
        back.setBounds(330, 200, 150, 40);
        back.addActionListener(this);
        add(back);

        setSize(800, 300); // Increase height to accommodate the Back button
        setLocation(250, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == createProject) {
            setVisible(false);
            new Projectadd();
        } else if (ae.getSource() == viewProject) {
            setVisible(false);
            new ProjectView();
        } else if (ae.getSource() == updateProject) {
            setVisible(false);
            new ProjectView();
        } else if (ae.getSource() == deleteProject) {
            setVisible(false);
            new RemoveProject();
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home(); // Assuming Home is the class that handles the main menu or home screen
        }
    }

    public static void main(String[] args) {
        new Projecthome();
    }
}
