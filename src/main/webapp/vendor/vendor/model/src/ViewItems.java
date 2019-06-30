package com.ShopApp;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import java.awt.Button;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewItems extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ViewItems() {
		setBackground(new Color(255, 204, 153));
		setLayout(null);

		JPanel bodyPanel = new JPanel();
		bodyPanel.setBackground(new Color(204, 153, 153));
		bodyPanel.setBounds(10, 70, 818, 591);
		add(bodyPanel);
		bodyPanel.setLayout(null);

		Button button = new Button("View All");
		button.setBackground(new Color(204, 153, 204));
		button.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		button.setBounds(10, 10, 180, 32);
		bodyPanel.add(button);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 792, 532);
		bodyPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBackground(new Color(0, 102, 102));
		headerPanel.setBounds(0, 11, 868, 60);
		add(headerPanel);

		Label headlbl = new Label("               VIEW STOCK");
		headlbl.setForeground(Color.WHITE);
		headlbl.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		headlbl.setBackground(new Color(0, 102, 102));
		headlbl.setBounds(45, 10, 516, 36);
		headerPanel.add(headlbl);

	}
}