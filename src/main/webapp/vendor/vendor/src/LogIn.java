package com.ShopApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LogIn extends Connections {
	private JFrame mainLogInFrame;
	private JPasswordField passwordField;
	private TextField IdField;

	public static final String USERNAME="root";
	public static final String PASSWORD="Nyabuto8";
	public static final  String CONN_STRING ="jdbc:mysql://localhost/psales";
	Connection connection;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.mainLogInFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LogIn() {
		initialize();
		authentication();
	}

	private void authentication() {

		IdField.addKeyListener(new KeyAdapter() {
		 	@Override
			public void keyTyped(KeyEvent eu){
				char ch = eu.getKeyChar();
				if(!Character.isDigit(ch)){
					eu.consume();
				}

			}
		 	public void keyPressed(KeyEvent e) {
		 		int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					passwordField.requestFocus();
				}
		 	}
		 });
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			//restrict the type if keywords to be used in the ID field
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
				try {
					// fetch data from  salerID and password fields data and convert them to strings respectively
					String value1=IdField.getText().toString();
					@SuppressWarnings("deprecation")
					String value2=passwordField.getText().toString();

					//retrieve database data
					connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD );
					Statement st=connection.createStatement();
					String ded = "SELECT * FROM users WHERE UserID='"+value1+"'";
					ResultSet rs2 = st.executeQuery(ded);
					while(rs2.next()){
					String idNumber = rs2.getString("UserID");
					String password = rs2.getString("Password");
					if (value1.equals(idNumber) && value2.equals(password) ){
						Duka1 window1 = new Duka1();
						window1.frame.setVisible(true);
						mainLogInFrame.dispose();
						mainLogInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
					else{
						JOptionPane.showMessageDialog(null,"The ID or password entered is wrong");
						passwordField.setText("");IdField.setText("");


					}
					}}
	    		catch (SQLException e1) {
	    			e1.printStackTrace();
	    			JOptionPane.showMessageDialog(null,"There were no records found");

	    		}
			}
			}});

	}
	private void initialize() {
		mainLogInFrame = new JFrame();
		mainLogInFrame.setTitle("LOGIN");
		mainLogInFrame.getContentPane().setBackground(new Color(255, 204, 204));
		mainLogInFrame.getContentPane().setLayout(null);

		Label labelSAlerID = new Label("SalerID       :");
		labelSAlerID.setFont(new Font("Dialog", Font.PLAIN, 22));
		labelSAlerID.setBounds(50, 61, 145, 40 );
		mainLogInFrame.getContentPane().add(labelSAlerID);

		Label labelPassword = new Label("Password    :");
		labelPassword.setFont(new Font("Dialog", Font.PLAIN, 22));
		labelPassword.setBounds(50, 146, 145, 40);
		mainLogInFrame.getContentPane().add(labelPassword);

		 IdField = new TextField();
		 IdField.setBackground(new Color(255, 255, 255));
		IdField.setFont(new Font("Dialog", Font.PLAIN, 25));
		IdField.setBounds(211, 61, 224, 40);
		mainLogInFrame.getContentPane().add(IdField);

		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		passwordField.setBounds(211, 146, 224, 40);
		mainLogInFrame.getContentPane().add(passwordField);
		mainLogInFrame.setBounds(100, 100, 526, 284);

	}
}