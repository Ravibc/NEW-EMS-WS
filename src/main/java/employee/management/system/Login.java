package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JTextField tfusername;
	JPasswordField tfpassword; // Use JPasswordField for password input

	Login() {
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblusername = new JLabel("Username");
		lblusername.setBounds(40, 20, 100, 30);
		add(lblusername);

		tfusername = new JTextField();
		tfusername.setBounds(150, 20, 150, 30);
		add(tfusername);

		JLabel lblpassword = new JLabel("Password");
		lblpassword.setBounds(40, 70, 100, 30);
		add(lblpassword);

		tfpassword = new JPasswordField(); // Use JPasswordField for password
		tfpassword.setBounds(150, 70, 150, 30);
		add(tfpassword);

		JButton login = new JButton("LOGIN");
		login.setBounds(150, 140, 150, 30);
		login.setBackground(Color.BLACK);
		login.setForeground(Color.WHITE);
		login.addActionListener(this);
		add(login);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
		Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(350, 0, 200, 200);
		add(image);

		setSize(600, 300);
		setLocation(450, 200);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		try {
			String username = tfusername.getText();
			String password = new String(tfpassword.getPassword()); // Get password from JPasswordField

			// Check if fields are empty
			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Username or Password cannot be empty");
				return;
			}

			Conn c = new Conn();
			String query = "SELECT * FROM login WHERE username = ? AND password = ?";

			PreparedStatement pst = c.c.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Login successful");
				setVisible(false);
				new Home(); // Assuming Home is another JFrame class
			} else {
				JOptionPane.showMessageDialog(null, "Invalid username or password");
			}

			// Close resources
			rs.close();
			pst.close();
			c.c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Login();
	}
}
