package com.ShopApp;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PanelTest extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTextField username;
	private JTextField idNumber;
	private JTextField passwrd;

	/**
	 * Create the panel.
	 */
	public PanelTest() {
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 204));
		setLayout(null);

		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(0, 102, 102));
		headerPanel.setBounds(95, 16, 579, 52);
		add(headerPanel);

		Label label_1 = new Label("The below fields are used to create a new user");
		label_1.setBackground(new Color(0, 102, 102));
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		label_1.setForeground(new Color(0, 0, 0));
		headerPanel.add(label_1);

		JPanel createSubPanel = new JPanel();
		createSubPanel.setBackground(new Color(204, 153, 153));
		createSubPanel.setBounds(95, 79, 579, 323);
		add(createSubPanel);
		createSubPanel.setLayout(null);

		Label kName = new Label("      KEEPER'S NAME");
		kName.setForeground(Color.BLACK);
		kName.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		kName.setBackground(new Color(204, 255, 255));
		kName.setBounds(28, 25, 216, 50);
		createSubPanel.add(kName);

		Label idNumb = new Label("      ID NUMBER");
		idNumb.setForeground(Color.BLACK);
		idNumb.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		idNumb.setBackground(new Color(204, 255, 255));
		idNumb.setBounds(28, 100, 216, 50);
		createSubPanel.add(idNumb);

		Label password = new Label("     PASSWORD");
		password.setForeground(Color.BLACK);
		password.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		password.setBackground(new Color(204, 255, 255));
		password.setBounds(28, 191, 216, 50);
		createSubPanel.add(password);

		username = new JTextField();
		username.setForeground(new Color(204, 0, 0));
		username.setFont(new Font("Times New Roman", Font.BOLD, 18));
		username.setBounds(304, 25, 239, 50);
		createSubPanel.add(username);
		username.setColumns(10);

		idNumber = new JTextField();
		idNumber.setForeground(new Color(204, 0, 0));
		idNumber.setFont(new Font("Times New Roman", Font.BOLD, 17));
		idNumber.setColumns(10);
		idNumber.setBounds(304, 100, 239, 50);
		createSubPanel.add(idNumber);

		passwrd = new JTextField();
		passwrd.setFont(new Font("Times New Roman", Font.BOLD, 17));
		passwrd.setForeground(new Color(204, 0, 0));
		passwrd.setColumns(10);
		passwrd.setBounds(304, 191, 239, 50);
		createSubPanel.add(passwrd);

		JButton btnNewButton = new JButton("CREATE");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(51, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(320, 266, 165, 46);
		createSubPanel.add(btnNewButton);

	}
}