package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateProject extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    JTextField tfProjectName, tfTeamName, tfProjectManager, tfLeadName, tfMember1, tfMember2, tfMember3, tfMember4, tfMember5, tfMember6, tfMember7, tfMember8;
    JTextArea taDescription;
    JButton update, back;
    String projectId;

    UpdateProject(String projectId) {
        this.projectId = projectId;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel labelProjectName = new JLabel("Project Name");
        labelProjectName.setBounds(50, 50, 150, 30);
        labelProjectName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProjectName);

        tfProjectName = new JTextField();
        tfProjectName.setBounds(200, 50, 200, 30);
        add(tfProjectName);

        JLabel labelTeamName = new JLabel("Team Name");
        labelTeamName.setBounds(50, 100, 150, 30);
        labelTeamName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelTeamName);

        tfTeamName = new JTextField();
        tfTeamName.setBounds(200, 100, 200, 30);
        add(tfTeamName);

        JLabel labelProjectManager = new JLabel("Project Manager");
        labelProjectManager.setBounds(50, 150, 150, 30);
        labelProjectManager.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProjectManager);

        tfProjectManager = new JTextField();
        tfProjectManager.setBounds(200, 150, 200, 30);
        add(tfProjectManager);

        JLabel labelLeadName = new JLabel("Lead Name");
        labelLeadName.setBounds(50, 200, 150, 30);
        labelLeadName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelLeadName);

        tfLeadName = new JTextField();
        tfLeadName.setBounds(200, 200, 200, 30);
        add(tfLeadName);

        JLabel labelMembers = new JLabel("Members");
        labelMembers.setBounds(50, 250, 150, 30);
        labelMembers.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelMembers);

        tfMember1 = new JTextField();
        tfMember1.setBounds(200, 250, 200, 30);
        add(tfMember1);

        tfMember2 = new JTextField();
        tfMember2.setBounds(200, 290, 200, 30);
        add(tfMember2);

        tfMember3 = new JTextField();
        tfMember3.setBounds(200, 330, 200, 30);
        add(tfMember3);

        tfMember4 = new JTextField();
        tfMember4.setBounds(200, 370, 200, 30);
        add(tfMember4);

        tfMember5 = new JTextField();
        tfMember5.setBounds(200, 410, 200, 30);
        add(tfMember5);

        tfMember6 = new JTextField();
        tfMember6.setBounds(200, 450, 200, 30);
        add(tfMember6);

        tfMember7 = new JTextField();
        tfMember7.setBounds(200, 490, 200, 30);
        add(tfMember7);

        tfMember8 = new JTextField();
        tfMember8.setBounds(200, 530, 200, 30);
        add(tfMember8);

        JLabel labelDescription = new JLabel("Description");
        labelDescription.setBounds(50, 570, 150, 30);
        labelDescription.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelDescription);

        taDescription = new JTextArea(5, 15); // 5 rows, 15 columns
        taDescription.setLineWrap(true);
        taDescription.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(taDescription);
        scrollPane.setBounds(200, 570, 200, 100); // Adjusted height for the scrollable area
        add(scrollPane);

        update = new JButton("Update");
        update.setBounds(430, 650, 100, 30); // Positioned at the full right side corner
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(540, 650, 100, 30); // Positioned at the full right side corner, next to the Update button
        back.addActionListener(this);
        add(back);

        setSize(700, 800);
        setLocation(300, 100);
        setVisible(true);




        populateFields();
    }

    private void populateFields() {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM project WHERE project_id = ?";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, projectId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tfProjectName.setText(rs.getString("projectName"));
                tfTeamName.setText(rs.getString("teamName"));
                tfProjectManager.setText(rs.getString("projectManager"));
                tfLeadName.setText(rs.getString("leadName"));
                tfMember1.setText(rs.getString("member1"));
                tfMember2.setText(rs.getString("member2"));
                tfMember3.setText(rs.getString("member3"));
                tfMember4.setText(rs.getString("member4"));
                tfMember5.setText(rs.getString("member5"));
                tfMember6.setText(rs.getString("member6"));
                tfMember7.setText(rs.getString("member7"));
                tfMember8.setText(rs.getString("member8"));
                taDescription.setText(rs.getString("description"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == update) {
            try {
                Conn c = new Conn();
                String query = "UPDATE project SET projectName = ?, teamName = ?, projectManager = ?, leadName = ?, member1 = ?, member2 = ?, member3 = ?, member4 = ?, member5 = ?, member6 = ?, member7 = ?, member8 = ?, description = ? WHERE project_id = ?";
                PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, tfProjectName.getText());
                ps.setString(2, tfTeamName.getText());
                ps.setString(3, tfProjectManager.getText());
                ps.setString(4, tfLeadName.getText());
                ps.setString(5, tfMember1.getText());
                ps.setString(6, tfMember2.getText());
                ps.setString(7, tfMember3.getText());
                ps.setString(8, tfMember4.getText());
                ps.setString(9, tfMember5.getText());
                ps.setString(10, tfMember6.getText());
                ps.setString(11, tfMember7.getText());
                ps.setString(12, tfMember8.getText());
                ps.setString(13, taDescription.getText());
                ps.setString(14, projectId);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Project Updated Successfully");
                setVisible(false);
                new Projecthome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Projecthome();
        }
    }

    public static void main(String[] args) {
        new UpdateProject("some_project_id"); // Pass a default or test project ID
    }
}
