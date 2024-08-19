package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class RemoveProject extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    Choice cProjectId;
    JButton delete, back;

    RemoveProject() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel labelProjectId = new JLabel("Project ID");
        labelProjectId.setBounds(50, 50, 100, 30);
        labelProjectId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProjectId);

        cProjectId = new Choice();
        cProjectId.setBounds(200, 50, 150, 30);
        add(cProjectId);

        try {
			Conn c = new Conn();
			String query = "select * from project";
			ResultSet rs = c.s.executeQuery(query);
			while (rs.next()) {
				cProjectId.add(rs.getString("project_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        

        JLabel labelProjectName = new JLabel("Project Name");
        labelProjectName.setBounds(50, 100, 150, 30);
        labelProjectName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProjectName);

        JLabel lblProjectName = new JLabel();
        lblProjectName.setBounds(200, 100, 300, 30);
        add(lblProjectName);

        JLabel labelTeamName = new JLabel("Team Name");
        labelTeamName.setBounds(50, 150, 150, 30);
        labelTeamName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelTeamName);

        JLabel lblTeamName = new JLabel();
        lblTeamName.setBounds(200, 150, 300, 30);
        add(lblTeamName);

        JLabel labelProjectManager = new JLabel("Project Manager");
        labelProjectManager.setBounds(50, 200, 150, 30);
        labelProjectManager.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelProjectManager);

        JLabel lblProjectManager = new JLabel();
        lblProjectManager.setBounds(200, 200, 300, 30);
        add(lblProjectManager);

        JLabel labelLeadName = new JLabel("Lead Name");
        labelLeadName.setBounds(50, 250, 150, 30);
        labelLeadName.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelLeadName);

        JLabel lblLeadName = new JLabel();
        lblLeadName.setBounds(200, 250, 300, 30);
        add(lblLeadName);

        
        try {
			Conn c = new Conn();
			String query = "select * from project where project_id = '" + cProjectId.getSelectedItem() + "'";
			ResultSet rs = c.s.executeQuery(query);
			while (rs.next()) {
				lblProjectName.setText(rs.getString("Projectname"));
				lblTeamName.setText(rs.getString("TeamName"));
				lblProjectManager.setText(rs.getString("ProjectManager"));
				lblLeadName.setText(rs.getString("LeadName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        
        
        cProjectId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
            	 try {
         			Conn c = new Conn();
         			String query = "select * from project where project_id = '" + cProjectId.getSelectedItem() + "'";
         			ResultSet rs = c.s.executeQuery(query);
         			while (rs.next()) {
         				lblProjectName.setText(rs.getString("Projectname"));
         				lblTeamName.setText(rs.getString("TeamName"));
         				lblProjectManager.setText(rs.getString("ProjectManager"));
         				lblLeadName.setText(rs.getString("LeadName"));
         			}
         		} catch (Exception e) {
         			e.printStackTrace();
         		}
            }
        });

        delete = new JButton("Delete");
        delete.setBounds(100, 300, 100, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(250, 300, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setSize(600, 400);
        setLocation(300, 150);
        setVisible(true);
    }

//    

   
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                Conn c = new Conn();
                String query = "DELETE FROM project WHERE project_id = ?";
                PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, cProjectId.getSelectedItem());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Project Deleted Successfully");
                setVisible(false);
                new Projecthome();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
			setVisible(false);
            new Projecthome();
        }
    }

    public static void main(String[] args) {
        new RemoveProject();
    }
}
