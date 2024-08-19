package employee.management.system;

import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class ProjectView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    JTable table;
    Choice cProjectId;
    JButton search, print, update, back;

    // Labels and text areas for the right-side panel
    JLabel lblProjectId, lblProjectName, lblTeamName, lblProjectManager, lblLeadName, lblMembers, lblDescription;
    JTextField txtProjectId, txtProjectName, txtTeamName, txtProjectManager, txtLeadName;
    JTextArea txtMembers, txtDescription;

    @SuppressWarnings("serial")
	ProjectView() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel searchlbl = new JLabel("Search by Project Id");
        searchlbl.setBounds(20, 20, 150, 20);
        add(searchlbl);

        cProjectId = new Choice();
        cProjectId.setBounds(180, 20, 150, 20);
        add(cProjectId);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from project");
            while (rs.next()) {
                cProjectId.add(rs.getString("project_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };

        // Set a custom row height for the table
        table.setRowHeight(50); // Increase this value to make the rows taller

        // Populate the table with data
        loadTableData();

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 600, 600); // Adjust width to make space for the details panel
        add(jsp);

        // Right-side panel to show full data
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(8, 2, 10, 10));
        detailsPanel.setBounds(620, 100, 260, 600);
        add(detailsPanel);

        // Initialize labels and text fields/areas
        lblProjectId = new JLabel("Project ID:");
        lblProjectName = new JLabel("Project Name:");
        lblTeamName = new JLabel("Team Name:");
        lblProjectManager = new JLabel("Project Manager:");
        lblLeadName = new JLabel("Lead Name:");
        lblMembers = new JLabel("Members:");
        lblDescription = new JLabel("Description:");

        txtProjectId = new JTextField();
        txtProjectName = new JTextField();
        txtTeamName = new JTextField();
        txtProjectManager = new JTextField();
        txtLeadName = new JTextField();
        txtMembers = new JTextArea();
        txtMembers.setLineWrap(true);
        txtMembers.setWrapStyleWord(true);
        txtDescription = new JTextArea();
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);

        txtProjectId.setEditable(false);
        txtProjectName.setEditable(false);
        txtTeamName.setEditable(false);
        txtProjectManager.setEditable(false);
        txtLeadName.setEditable(false);
        txtMembers.setEditable(false);
        txtDescription.setEditable(false);

        // Add components to the details panel
        detailsPanel.add(lblProjectId);
        detailsPanel.add(txtProjectId);
        detailsPanel.add(lblProjectName);
        detailsPanel.add(txtProjectName);
        detailsPanel.add(lblTeamName);
        detailsPanel.add(txtTeamName);
        detailsPanel.add(lblProjectManager);
        detailsPanel.add(txtProjectManager);
        detailsPanel.add(lblLeadName);
        detailsPanel.add(txtLeadName);
        detailsPanel.add(lblMembers);
        JScrollPane membersScrollPane = new JScrollPane(txtMembers);
        detailsPanel.add(membersScrollPane); // Add scroll pane for members
        detailsPanel.add(lblDescription);
        JScrollPane descriptionScrollPane = new JScrollPane(txtDescription);
        detailsPanel.add(descriptionScrollPane);

        // Add mouse listener to the table for click events
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    txtProjectId.setText(table.getValueAt(selectedRow, 0).toString());
                    txtProjectName.setText(table.getValueAt(selectedRow, 1).toString());
                    txtTeamName.setText(table.getValueAt(selectedRow, 2).toString());
                    txtProjectManager.setText(table.getValueAt(selectedRow, 3).toString());
                    txtLeadName.setText(table.getValueAt(selectedRow, 4).toString());

                    // Combine all members into one string
                    StringBuilder members = new StringBuilder();
                    for (int i = 5; i <= 12; i++) {
                        String member = table.getValueAt(selectedRow, i).toString();
                        if (!member.isEmpty()) {
                            members.append(member).append(", ");
                        }
                    }
                    // Remove the last comma and space
                    if (members.length() > 0) {
                        members.setLength(members.length() - 2);
                    }
                    txtMembers.setText(members.toString());

                    txtDescription.setText(table.getValueAt(selectedRow, 13).toString());
                }
            }
        });

        // Button setup
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBounds(220, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(320, 70, 80, 20);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    private void loadTableData() {
        try {
            Conn c = new Conn();
            String query = "SELECT project_id, projectName, teamName, projectManager, leadName, member1, member2, member3, member4, member5, member6, member7, member8, description FROM project";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            loadTableData();
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateProject(cProjectId.getSelectedItem());
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Projecthome();
        }
    }

    public static void main(String[] args) {
        new ProjectView();
    }
}
