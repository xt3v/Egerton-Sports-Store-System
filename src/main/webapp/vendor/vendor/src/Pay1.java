package com.ShopApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pay1 {

	private JFrame defPayFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pay1 window = new Pay1();
					window.defPayFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pay1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		defPayFrame = new JFrame();
		defPayFrame.setBounds(300, 250, 610, 305);
		defPayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		defPayFrame.getContentPane().setLayout(null);

		JPanel defPay = new JPanel();
		defPay.setLayout(null);
		defPay.setBorder(new EmptyBorder(5, 5, 5, 5));
		defPay.setBackground(new Color(0, 102, 0));
		defPay.setBounds(0, 0, 594, 268);
		defPayFrame.getContentPane().add(defPay);

		JPanel defpayPanel2 = new JPanel();
		defpayPanel2.setLayout(null);
		defpayPanel2.setBackground(new Color(153, 153, 204));
		defpayPanel2.setBounds(10, 11, 551, 236);
		defPay.add(defpayPanel2);

		Label defNameLbl = new Label("       Name");
		defNameLbl.setForeground(Color.WHITE);
		defNameLbl.setFont(new Font("Dialog", Font.PLAIN, 25));
		defNameLbl.setBackground(new Color(0, 102, 102));
		defNameLbl.setBounds(10, 10, 210, 37);
		defpayPanel2.add(defNameLbl);

		Label dfctLbd = new Label("Amount owed");
		dfctLbd.setForeground(Color.WHITE);
		dfctLbd.setFont(new Font("Dialog", Font.PLAIN, 25));
		dfctLbd.setBackground(new Color(0, 102, 102));
		dfctLbd.setBounds(10, 125, 210, 37);
		defpayPanel2.add(dfctLbd);

		Label defAmtLble = new Label("   Amount");
		defAmtLble.setForeground(Color.WHITE);
		defAmtLble.setFont(new Font("Dialog", Font.PLAIN, 25));
		defAmtLble.setBackground(new Color(0, 102, 102));
		defAmtLble.setBounds(10, 176, 210, 37);
		defpayPanel2.add(defAmtLble);

		TextField defName = new TextField();
		defName.setForeground(new Color(204, 0, 102));
		defName.setFont(new Font("Dialog", Font.PLAIN, 19));
		defName.setBounds(274, 10, 255, 37);
		defpayPanel2.add(defName);

		TextField defAmount = new TextField();
		defAmount.setForeground(new Color(204, 0, 102));
		defAmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		defAmount.setBounds(274, 120, 168, 37);
		defpayPanel2.add(defAmount);

		TextField defPayment = new TextField();
		defPayment.setForeground(new Color(204, 0, 102));
		defPayment.setFont(new Font("Dialog", Font.PLAIN, 19));
		defPayment.setBounds(274, 176, 168, 37);
		defpayPanel2.add(defPayment);

		Label defIDlbl = new Label("     ID");
		defIDlbl.setForeground(Color.WHITE);
		defIDlbl.setFont(new Font("Dialog", Font.PLAIN, 25));
		defIDlbl.setBackground(new Color(0, 102, 102));
		defIDlbl.setBounds(10, 67, 210, 37);
		defpayPanel2.add(defIDlbl);

		TextField defID = new TextField();
		defID.setForeground(new Color(204, 0, 102));
		defID.setFont(new Font("Dialog", Font.PLAIN, 19));
		defID.setBounds(274, 67, 168, 37);
		defpayPanel2.add(defID);

		Button defPayBtn = new Button("PAY");
		defPayBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		defPayBtn.setForeground(Color.WHITE);
		defPayBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		defPayBtn.setBackground(new Color(102, 0, 0));
		defPayBtn.setBounds(463, 183, 78, 30);
		defpayPanel2.add(defPayBtn);
	}
}