package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class Projectadd extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    JTextField tfProjectId, tfProjectName, tfTeamName, tfProjectManager, tfLeadName;
    JTextField[] tfMembers = new JTextField[8]; // Array to hold text fields for 8 members
    JTextArea taDescription; // Text area for the description

    JButton add, back;

    Projectadd() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Project Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelProjectId = new JLabel("Project ID");
        labelProjectId.setBounds(50, 100, 150, 30);
        labelProjectId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProjectId);

        tfProjectId = new JTextField();
        tfProjectId.setBounds(200, 100, 150, 30);
        add(tfProjectId);

        JLabel labelProjectName = new JLabel("Project Name");
        labelProjectName.setBounds(50, 150, 150, 30);
        labelProjectName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProjectName);

        tfProjectName = new JTextField();
        tfProjectName.setBounds(200, 150, 150, 30);
        add(tfProjectName);

        JLabel labelTeamName = new JLabel("Team Name");
        labelTeamName.setBounds(400, 150, 150, 30);
        labelTeamName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelTeamName);

        tfTeamName = new JTextField();
        tfTeamName.setBounds(600, 150, 150, 30);
        add(tfTeamName);

        JLabel labelProjectManager = new JLabel("Project Manager");
        labelProjectManager.setBounds(50, 200, 150, 30);
        labelProjectManager.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProjectManager);

        tfProjectManager = new JTextField();
        tfProjectManager.setBounds(200, 200, 150, 30);
        add(tfProjectManager);

        JLabel labelLeadName = new JLabel("Lead Name");
        labelLeadName.setBounds(400, 200, 150, 30);
        labelLeadName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelLeadName);

        tfLeadName = new JTextField();
        tfLeadName.setBounds(600, 200, 150, 30);
        add(tfLeadName);

        JLabel labelMembers = new JLabel("Members");
        labelMembers.setBounds(50, 250, 150, 30);
        labelMembers.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelMembers);

        for (int i = 0; i < 8; i++) {
            JLabel labelMember = new JLabel("Member " + (i + 1));
            labelMember.setBounds(50, 300 + (i * 40), 150, 30);
            labelMember.setFont(new Font("serif", Font.PLAIN, 20));
            add(labelMember);

            tfMembers[i] = new JTextField();
            tfMembers[i].setBounds(200, 300 + (i * 40), 150, 30);
            add(tfMembers[i]);
        }

        JLabel labelDescription = new JLabel("Description");
        labelDescription.setBounds(400, 250, 150, 30);
        labelDescription.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelDescription);

        taDescription = new JTextArea();
        taDescription.setBounds(400, 300, 350, 150);
        taDescription.setLineWrap(true);
        taDescription.setWrapStyleWord(true);
        taDescription.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(taDescription);

        add = new JButton("Add Project");
        add.setBounds(250, 650, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 650, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);

        setSize(900, 750);
        setLocation(300, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String projectId = tfProjectId.getText();
            String projectName = tfProjectName.getText();
            String teamName = tfTeamName.getText();
            String projectManager = tfProjectManager.getText();
            String leadName = tfLeadName.getText();
            String description = taDescription.getText();
            String[] members = new String[8];
            for (int i = 0; i < 8; i++) {
                members[i] = tfMembers[i].getText();
            }

            try {
                Conn conn = new Conn();
                String query = "INSERT INTO project (project_id, projectName, teamName, projectManager, leadName, description, member1, member2, member3, member4, member5, member6, member7, member8) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.c.prepareStatement(query);
                pst.setString(1, projectId);
                pst.setString(2, projectName);
                pst.setString(3, teamName);
                pst.setString(4, projectManager);
                pst.setString(5, leadName);
                pst.setString(6, description);
                for (int i = 0; i < 8; i++) {
                    pst.setString(7 + i, members[i]);
                }
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Project added successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new Projectadd();
    }
}
