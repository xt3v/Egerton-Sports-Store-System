package com.ShopApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pay extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static final String USERNAME="root";
	public static final String PASSWORD="Nyabuto8";
	public static final  String CONN_STRING ="jdbc:mysql://localhost/psales";
	Connection connection;
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pay window = new Pay();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Pay() {
		setResizable(false);
		setTitle("LOGIN WINDOW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 180, 587, 344);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label welcome_label = new Label("WELCOME TO HEARTSPRING SHOP");
		welcome_label.setFont(new Font("Modern No. 20", Font.BOLD, 25));
		welcome_label.setForeground(new Color(0, 0, 153));
		welcome_label.setAlignment(Label.CENTER);
		welcome_label.setBounds(10, 10, 540, 48);
		contentPane.add(welcome_label);

		Label instruction_label = new Label("    Enter your ID number and password in the fields below to login");
		instruction_label.setForeground(new Color(255, 0, 204));
		instruction_label.setFont(new Font("Elephant", Font.PLAIN, 17));
		instruction_label.setAlignment(Label.CENTER);
		instruction_label.setBounds(20, 64, 489, 22);
		contentPane.add(instruction_label);

		Label idLbl = new Label("  ID number   :");
		idLbl.setForeground(new Color(0, 0, 0));
		idLbl.setFont(new Font("Constantia", Font.PLAIN, 19));
		idLbl.setAlignment(Label.CENTER);
		idLbl.setBounds(151, 102, 139, 29);
		contentPane.add(idLbl);

		Label psssword_lbl = new Label("Password  :");
		psssword_lbl.setForeground(Color.BLACK);
		psssword_lbl.setFont(new Font("Constantia", Font.PLAIN, 19));
		psssword_lbl.setAlignment(Label.CENTER);
		psssword_lbl.setBounds(151, 195, 139, 29);
		contentPane.add(psssword_lbl);

		TextField idField = new TextField();
		idField.setFont(new Font("Dialog", Font.PLAIN, 20));
		idField.setForeground(new Color(102, 0, 255));
		idField.setBounds(128, 137, 249, 37);
		contentPane.add(idField);

		JPasswordField passwordFld = new JPasswordField();
		passwordFld.setFont(new Font("Dialog", Font.PLAIN, 20));
		passwordFld.setBounds(128, 230, 249, 37);
		contentPane.add(passwordFld);

		Label label = new Label("#Alex Ogendo");
		label.setForeground(new Color(255, 0, 51));
		label.setFont(new Font("Lucida Handwriting", Font.PLAIN, 12));
		label.setBounds(482, 282, 89, 22);
		contentPane.add(label);
		idField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					passwordFld.requestFocus();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(!Character.isDigit(ch)){
					e.consume();
				}
			}
		});

		passwordFld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
				try {
					// fetch data from  salerID and password fields data and convert them to strings respectively
					String value1=idField.getText().toString();
					@SuppressWarnings("deprecation")
					String value2=passwordFld.getText().toString();

					//retrieve database data
					connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD );
					Statement st=connection.createStatement();
					String ded = "SELECT * FROM users WHERE UserID='"+value1+"'";
					ResultSet rs2 = st.executeQuery(ded);
					while(rs2.next()){
					String idNumber = rs2.getString("UserID");
					String password = rs2.getString("Password");
					if (value1.equals(idNumber) && value2.equals(password) ){
						System window1 = new System();
						window1.frame.setVisible(true);
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null,"The ID or password entered is wrong");
						passwordFld.setText("");idField.setText("");
						idField.requestFocus();

					}
					}}
	    		catch (SQLException e1) {
	    			e1.printStackTrace();
	    			JOptionPane.showMessageDialog(null,"There waere no records found");

	    		}
			}
			}
		});

	}
}