package com.ShopApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gg {

	private JFrame entryFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gg window = new Gg();
					window.entryFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		entryFrame = new JFrame();
		entryFrame.getContentPane().setBackground(new Color(0, 102, 51));
		entryFrame.setTitle("Debts");
		entryFrame.setBounds(100, 100, 306, 416);
		entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		entryFrame.getContentPane().setLayout(null);

		Label iDnumb = new Label("         ID number");
		iDnumb.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		iDnumb.setBounds(24, 26, 197, 22);
		entryFrame.getContentPane().add(iDnumb);

		Label cstmrLbl = new Label("         Customer's ID");
		cstmrLbl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cstmrLbl.setBounds(24, 151, 197, 22);
		entryFrame.getContentPane().add(cstmrLbl);

		Label amntbl = new Label("            Amount\r\n");
		amntbl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		amntbl.setBounds(24, 262, 197, 22);
		entryFrame.getContentPane().add(amntbl);

		TextField tidField = new TextField();
		tidField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		tidField.setEditable(false);
		tidField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		tidField.setForeground(new Color(204, 0, 204));
		tidField.setBounds(24, 72, 219, 41);
		entryFrame.getContentPane().add(tidField);

		TextField cstmrFld = new TextField();
		cstmrFld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		cstmrFld.setForeground(new Color(204, 0, 204));
		cstmrFld.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cstmrFld.setEditable(false);
		cstmrFld.setBounds(24, 195, 219, 41);
		entryFrame.getContentPane().add(cstmrFld);

		TextField amntFld = new TextField();
		amntFld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		amntFld.setForeground(new Color(204, 0, 204));
		amntFld.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		amntFld.setEditable(false);
		amntFld.setBounds(24, 310, 219, 41);
		entryFrame.getContentPane().add(amntFld);
	}
}