package com.ShopApp;
	//Imports from eclipse repositories

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JFrame;

		public class Connections {
			//declare variables for database connection
			public static final String USERNAME="root";
			public static final String PASSWORD="Nyabuto8";
			public static final  String CONN_STRING ="jdbc:mysql://localhost/psales";
			Connection connection;


		private JFrame frame;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Connections window = new Connections();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the application.
		 */
		public Connections() {
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			frame = new JFrame();
			frame.setBounds(100, 100, 450, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}