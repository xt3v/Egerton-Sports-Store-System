package com.ShopApp;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JSeparator;
import java.awt.Button;

public class Unsettled extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTable unsettledTable;

	/**
	 * Create the panel.
	 */
	public Unsettled() {
		setBackground(new Color(255, 204, 153));
		setLayout(null);

		JPanel hPanel = new JPanel();
		hPanel.setBackground(new Color(0, 102, 102));
		hPanel.setBounds(10, 11, 794, 60);
		add(hPanel);
		hPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("                      Unsettled");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblNewLabel.setBounds(10, 11, 689, 42);
		hPanel.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(10, 71, 794, 555);
		add(panel);
		panel.setLayout(null);

		JScrollPane unsettledScrollPane = new JScrollPane();
		unsettledScrollPane.setBounds(10, 11, 774, 358);
		panel.add(unsettledScrollPane);

		unsettledTable = new JTable();
		unsettledTable.setBackground(new Color(204, 204, 255));
		unsettledScrollPane.setViewportView(unsettledTable);

		Label totlUnstldlbl = new Label("Total Amount");
		totlUnstldlbl.setBackground(new Color(0, 102, 102));
		totlUnstldlbl.setForeground(new Color(255, 255, 255));
		totlUnstldlbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		totlUnstldlbl.setBounds(71, 375, 168, 30);
		panel.add(totlUnstldlbl);

		TextField unsettledField = new TextField();
		unsettledField.setForeground(new Color(204, 0, 102));
		unsettledField.setFont(new Font("Dialog", Font.PLAIN, 19));
		unsettledField.setBounds(400, 383, 279, 32);
		panel.add(unsettledField);

		JSeparator unstldSprt = new JSeparator();
		unstldSprt.setBounds(10, 421, 774, 17);
		panel.add(unstldSprt);

		Label uNameLbl = new Label("      Name");
		uNameLbl.setForeground(Color.WHITE);
		uNameLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		uNameLbl.setBackground(new Color(0, 102, 102));
		uNameLbl.setBounds(71, 444, 168, 30);
		panel.add(uNameLbl);

		TextField uNametfld = new TextField();
		uNametfld.setForeground(new Color(204, 0, 102));
		uNametfld.setFont(new Font("Dialog", Font.PLAIN, 19));
		uNametfld.setBounds(402, 444, 277, 32);
		panel.add(uNametfld);

		Label idLbl = new Label("      ID");
		idLbl.setForeground(Color.WHITE);
		idLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		idLbl.setBackground(new Color(0, 102, 102));
		idLbl.setBounds(71, 491, 121, 30);
		panel.add(idLbl);

		TextField idFld = new TextField();
		idFld.setForeground(new Color(204, 0, 102));
		idFld.setFont(new Font("Dialog", Font.PLAIN, 19));
		idFld.setBounds(222, 491, 71, 32);
		panel.add(idFld);

		Label cshLbl = new Label("      Amount");
		cshLbl.setForeground(Color.WHITE);
		cshLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		cshLbl.setBackground(new Color(0, 102, 102));
		cshLbl.setBounds(400, 491, 121, 30);
		panel.add(cshLbl);

		TextField cashFld = new TextField();
		cashFld.setForeground(new Color(204, 0, 102));
		cashFld.setFont(new Font("Dialog", Font.PLAIN, 19));
		cashFld.setBounds(574, 491, 71, 32);
		panel.add(cashFld);

		Button moreBtn = new Button("MORE");
		moreBtn.setForeground(new Color(255, 255, 255));
		moreBtn.setBackground(new Color(102, 0, 0));
		moreBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		moreBtn.setBounds(686, 491, 78, 30);
		panel.add(moreBtn);

	}
}