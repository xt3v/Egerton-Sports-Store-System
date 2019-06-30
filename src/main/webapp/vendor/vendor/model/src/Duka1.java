package com.ShopApp;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;

public class Duka1 extends LogIn {

	public JFrame frame;
	private JPanel Cpanel,itemPanel;
	private JPanel contentPane,items;
    private JTable tablenotAvailable,unsettledTable,poSaeTable;
	private JTextField addQuantityFld,codeField,searchItems
	,derscriptionField,priceField,qntyField,codeAddtField,priceAddFld,addDescrptnFld,iDField1,nameField2;
	public TextField codFld,qntityFld,totalPesa,balance,givenOut,fieldAmount,fieldID,nmnFld,
	amntFild,dfctFild,nameFld,cIDFld,unsettledField,debitFld,amntFld,tidField,cstmrFld;
	public static String userpassword,comb,dTime,dDate,dateAndTime,pass;
	public static int hr,mnth,dy,sec,min,yr;
	private JButton btnEdit,sezrchButton,addDebitor;
	private Button viewingBtn,payButton,refreshbutton,Debit;
	private Label editSucess,addSuccess,entryFailure,editFailure;
	private JLabel sslbl;
	DefaultTableModel model,model2,stockModel,unsetldModel,keepersTableModel,debitModel;
	private double sum;

	JFrame searchframe,payFrame,mngKpers,delPanel,passwordFrame,entryFrame,defPayFrame;
	public JTable searchTable;
	public DefaultTableModel resultModel;
	public TextField findField;
	public String fCode;
	private JTable findTable;
	private JTable stocktable;
	private JTextField nemFild;
	private JTextField IDfild;
	private JPasswordField userPasswordField,oldPass,newPass,confirmPass;
	private JPasswordField passconf;
	private JTable vKeepersTable;
	private JTable debitTable;

	public Duka1() {
		initialize();
		point_of_sale();
		manageItems();
		search();
		Stock();
		unsetteled();
		Users();
		AvailableKeepers();
		debitors();
	}

	private void debitors() {
		String CtmName,svcProvider,date;
		int CstmID;
		double amount;
		try {
			connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
			Statement debitStmnt = connection.createStatement();
	    	String searchStrng ="SELECT * from debit";
			ResultSet rs = debitStmnt.executeQuery(searchStrng);

			while(rs.next()){
				CtmName= rs.getString("Customer_name");
				CstmID =rs.getInt("Customer_ID");
				svcProvider =rs.getString("Service_provider");
				date =rs.getString("Date");
				amount =rs.getDouble("Amount");
				Object[]row={CstmID,CtmName,date,svcProvider,amount};
				debitModel.addRow(row);
				debitTable.setModel(debitModel);
			}}
		catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,"There are no records found");

		}
		double sum=0;
		//get the total amount you owe customers
		for(int i=0; i<debitTable.getRowCount();i++){
		  String ttlamount= debitTable.getValueAt(i,4).toString();
		  double start=Double.parseDouble(ttlamount);
		   sum+=start;
		   String toatlStr=Double.toString(sum);
		   debitFld.setText(toatlStr);
	}
		debitTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				defPayFrame = new JFrame();
				defPayFrame.setBounds(300, 250, 610, 305);
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
				defNameLbl.setBounds(10, 10, 210, 37);
				defpayPanel2.add(defNameLbl);

				Label dfctLbd = new Label("Amount owed");
				dfctLbd.setForeground(Color.WHITE);
				dfctLbd.setFont(new Font("Dialog", Font.PLAIN, 25));
				dfctLbd.setBounds(10, 125, 210, 37);
				defpayPanel2.add(dfctLbd);

				Label defAmtLble = new Label("   Amount");
				defAmtLble.setForeground(Color.WHITE);
				defAmtLble.setFont(new Font("Dialog", Font.PLAIN, 25));
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
				defIDlbl.setBounds(10, 67, 210, 37);
				defpayPanel2.add(defIDlbl);

				TextField defID = new TextField();
				defID.setForeground(new Color(204, 0, 102));
				defID.setFont(new Font("Dialog", Font.PLAIN, 19));
				defID.setBounds(274, 67, 168, 37);
				defpayPanel2.add(defID);

				Button defPayBtn = new Button("PAY");
				defPayBtn.setForeground(Color.WHITE);
				defPayBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
				defPayBtn.setBackground(new Color(102, 0, 0));
				defPayBtn.setBounds(463, 183, 78, 30);
				defpayPanel2.add(defPayBtn);
				defPayFrame.setVisible(true);

				int deb = debitTable.getSelectedRow();
				defName.setText(debitModel.getValueAt(deb,1).toString());
				defID.setText(debitModel.getValueAt(deb,0).toString());
				defAmount.setText(debitModel.getValueAt(deb,4).toString());
				defPayment.requestFocus();
				defPayBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int deficitCell = debitTable.getSelectedRow();
						double current1=Double.parseDouble(defAmount.getText());
						double tbPaid =Double.parseDouble(defPayment.getText());
						if(current1>tbPaid){
							double newDeficit = current1-tbPaid;
							debitModel.setValueAt(newDeficit,deficitCell ,4);

							 try
							    {
			connection=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
							    	PreparedStatement update = connection.prepareStatement
							        ("UPDATE debit SET Amount=? WHERE Customer_ID=?");
		   update.setDouble(1,newDeficit);
		   update.setString(2,defID.getText());
		   update.executeUpdate();
			  }
							    catch (Exception a)
							        {
							    	a.printStackTrace();
							        }	}
							 else if(current1==tbPaid){
									try{
						connection=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
						debitModel.removeRow(deficitCell);

						PreparedStatement del= connection.prepareStatement ("DELETE FROM debit  WHERE Customer_ID=?");
					    del.setString(1,defID.getText());
						del.executeUpdate();
									}
									catch(Exception ea){
							}

							 }
						defPayFrame.dispose();
							}
				});
			}
		});
	}


	private void AvailableKeepers() {
		String KprsName,KprsID;
		try {
			connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
			Statement searchStmnt = connection.createStatement();
	    	String searchStrng ="SELECT Name,Password, UserID from users";
			ResultSet rs = searchStmnt.executeQuery(searchStrng);

			while(rs.next()){
				KprsName= rs.getString("Name");
				KprsID =rs.getString("UserID");
				pass=rs.getString("Password");
				Object[]row={KprsName,KprsID};
				keepersTableModel.addRow(row);
				vKeepersTable.setModel(keepersTableModel);
			}}
		catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,"There ere no records found");

		}
		vKeepersTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				delPanel = new JFrame();
	    		delPanel.getContentPane().setBackground(new Color(0, 128, 128));
	    		delPanel.setBounds(500, 250, 449, 248);
	    		delPanel.getContentPane().setLayout(null);
	    		delPanel.setVisible(true);

	    		JPanel modificationPAnel = new JPanel();
	    		modificationPAnel.setBackground(new Color(0, 128, 128));
	    		modificationPAnel.setBounds(0, 11, 433, 186);
	    		delPanel.getContentPane().add(modificationPAnel);
	    		modificationPAnel.setLayout(null);

	    		JLabel lblNewLabel = new JLabel("Name");
	    		lblNewLabel.setFont(new Font("Sylfaen", Font.PLAIN, 20));
	    		lblNewLabel.setBounds(20, 11, 88, 25);
	    		modificationPAnel.add(lblNewLabel);

	    		JLabel lblId = new JLabel("     \tID");
	    		lblId.setFont(new Font("Sylfaen", Font.PLAIN, 20));
	    		lblId.setBounds(10, 91, 109, 25);
	    		modificationPAnel.add(lblId);

	    		nameField2 = new JTextField();
	    		nameField2.setForeground(new Color(128, 0, 0));
	    		nameField2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
	    		nameField2.setBounds(153, 14, 183, 34);
	    		modificationPAnel.add(nameField2);
	    		nameField2.setColumns(10);

	    		iDField1 = new JTextField();
	    		iDField1.setForeground(new Color(128, 0, 0));
	    		iDField1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
	    		iDField1.setColumns(10);
	    		iDField1.setBounds(153, 87, 183, 34);
	    		modificationPAnel.add(iDField1);

	    		JButton delButton = new JButton("Delete");
	    		delButton.setBackground(new Color(255, 218, 185));
	    		delButton.setBounds(33, 139, 89, 23);
	    		modificationPAnel.add(delButton);

	    		JButton pasChange = new JButton("Change password");
	    		pasChange.setBackground(new Color(255, 228, 181));
	    		pasChange.setBounds(238, 139, 146, 23);
	    		modificationPAnel.add(pasChange);


				int transfer = vKeepersTable.getSelectedRow();
				nameField2.setText(keepersTableModel.getValueAt(transfer,0).toString());
				iDField1.setText(keepersTableModel.getValueAt(transfer,1).toString());

				delButton.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent e) {
	    				JOptionPane.showMessageDialog(null,"Do you really want to delete this user?");
	    				try{
	    					PreparedStatement keepers= connection.prepareStatement ("DELETE FROM users  WHERE UserID=?");
	    			    	keepers.setString(1,iDField1.getText());
	    			    	keepers.executeUpdate();
	    			    	iDField1.setText(""); nameField2.setText("");
	    			    	int cell = vKeepersTable.getSelectedRow();
	    			    	keepersTableModel.removeRow(cell);
	    				}
	    				catch(Exception cts){
	    					cts.printStackTrace();
	    				}

	    			}
	    		});
				pasChange.addMouseListener(new MouseAdapter() {
	    			@Override
	    			public void mouseClicked(MouseEvent e) {
	    				passwordFrame = new JFrame();
	    				passwordFrame.getContentPane().setBackground(new Color(143, 188, 143));
	    				passwordFrame.setBounds(500, 250, 358, 343);
	    				passwordFrame.getContentPane().setLayout(null);
	    				passwordFrame.setVisible(true);

	    				JLabel lblNewLabel = new JLabel("Current Password");
	    				lblNewLabel.setFont(new Font("Sitka Display", Font.PLAIN, 20));
	    				lblNewLabel.setForeground(new Color(0, 0, 0));
	    				lblNewLabel.setBounds(85, 11, 152, 20);
	    				passwordFrame.getContentPane().add(lblNewLabel);

	    				JLabel lblNewPassword = new JLabel("New Password");
	    				lblNewPassword.setForeground(Color.BLACK);
	    				lblNewPassword.setFont(new Font("Sitka Display", Font.PLAIN, 20));
	    				lblNewPassword.setBounds(85, 97, 152, 20);
	    				passwordFrame.getContentPane().add(lblNewPassword);

	    				oldPass = new JPasswordField();
	    				oldPass.setFont(new Font("Times New Roman", Font.PLAIN, 17));
	    				oldPass.setForeground(new Color(128, 0, 0));
	    				oldPass.setBounds(64, 42, 225, 32);
	    				passwordFrame.getContentPane().add(oldPass);

	    				newPass = new JPasswordField();
	    				newPass.setForeground(new Color(128, 0, 0));
	    				newPass.setFont(new Font("Times New Roman", Font.PLAIN, 17));
	    				newPass.setBounds(64, 128, 225, 32);
	    				passwordFrame.getContentPane().add(newPass);

	    				JButton btnNewButton = new JButton("Change");
	    				btnNewButton.setBounds(64, 257, 89, 23);
	    				passwordFrame.getContentPane().add(btnNewButton);

	    				confirmPass = new JPasswordField();
	    				confirmPass.setForeground(new Color(128, 0, 0));
	    				confirmPass.setFont(new Font("Times New Roman", Font.PLAIN, 17));
	    				confirmPass.setBounds(64, 207, 225, 32);
	    				passwordFrame.getContentPane().add(confirmPass);

	    				JLabel lblConfirmPassword = new JLabel("Confirm Password");
	    				lblConfirmPassword.setForeground(Color.BLACK);
	    				lblConfirmPassword.setFont(new Font("Sitka Display", Font.PLAIN, 20));
	    				lblConfirmPassword.setBounds(85, 176, 152, 20);
	    				passwordFrame.getContentPane().add(lblConfirmPassword);

	    				delPanel.dispose();
	    				btnNewButton.addMouseListener(new MouseAdapter() {
	    					@SuppressWarnings("deprecation")
							@Override
	    					public void mouseClicked(MouseEvent e) {
	    						String a =oldPass.getText().toString();
	    						String b =newPass.getText().toString();
	    						String c =confirmPass.getText().toString();
	    						String d =iDField1.getText().toString();

	    						 try
								    {
	    							 if(b.equals(c) && a.equals(pass) &&(!newPass.getText().equals("") && !confirmPass.getText().equals(""))){
						connection=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

					    // update user password in the users table
								    	PreparedStatement update = connection.prepareStatement
								        ("UPDATE users SET Password=? WHERE UserID=?");
								    	update.setString(1,b);
								    	update.setString(2,d);
								              update.executeUpdate();
								              JOptionPane.showMessageDialog(null,"Password changed successfuly");
								              newPass.setText(""); confirmPass.setText(""); oldPass.requestFocus();
								              oldPass.setText(""); passwordFrame.dispose(); delPanel.setVisible(true);

								              }
	    							 else if(!b.equals(c) && (newPass.getText().equals("") || confirmPass.getText().equals("") ||oldPass.getText().equals("") )){
	    								 JOptionPane.showMessageDialog(null,"not matching!!!");
	    								 newPass.setText(""); confirmPass.setText(""); newPass.requestFocus();
	    							 }
	    							 else{
	    								 JOptionPane.showMessageDialog(null,"The password you are providing does not match "
	    								 		+ "the one in the database");

	    								 oldPass.setText(""); newPass.setText(""); confirmPass.setText(""); oldPass.requestFocus();
	    							 }

								    }
								    catch (Exception au)
								        {
								    	au.printStackTrace();
								        }
	    					}
	    				});
	    			}
	    		});

			}
		});

	}

	private void Users() {
		nemFild.addKeyListener(new KeyAdapter() {
			@Override
			 public void keyTyped(KeyEvent eu){
				char ch = eu.getKeyChar();
				if(!Character.isLetter(ch) && !Character.isISOControl(ch)){
					eu.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					Toolkit.getDefaultToolkit();
					if(nemFild.getText().equals("")){
						JOptionPane.showMessageDialog(null,"The name field cannot be left blank");
					}
					else{
					IDfild.requestFocus();
					}
				}
				}
		});
		IDfild.addKeyListener(new KeyAdapter() {
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
					Toolkit.getDefaultToolkit();
					if(IDfild.getText().equals("")){
						JOptionPane.showMessageDialog(null,"The ID field cannot be left blank");
					}
					else{
						userPasswordField.requestFocus();
					}
				}}
		});
		userPasswordField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					Toolkit.getDefaultToolkit();
					if(userPasswordField.getText().equals("")){
						JOptionPane.showMessageDialog(null,"The ID field cannot be left blank");
					}
					else{
						passconf.requestFocus();
					}
				}}
		});

		passconf.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					Toolkit.getDefaultToolkit();
					if(passconf.getText().equals("")){
						JOptionPane.showMessageDialog(null,"The confirmation field field cannot be left blank");
					}
					else{
						if(passconf.getText().equals(userPasswordField.getText())){
				try
			    {
			    	connection=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			    	String query = "insert into users(Name,UserID,Password) values(?,?,?)";
			    	// create the MYSQL insert prepared statement
			    	 PreparedStatement statement = connection.prepareStatement(query);
			    	 statement.setString(1,nemFild.getText());
			    	 statement.setString(2, IDfild.getText());
			    	 statement.setString(3, userPasswordField.getText());
			    	 statement.executeUpdate();
			    }

			    catch (Exception insert)
			    {

			    }
			   JOptionPane.showMessageDialog(null,"Account created succefully");
			   nemFild.setText(""); IDfild.setText(""); userPasswordField.setText("");
			nemFild.requestFocus();passconf.setText("");
						}
						else if(!passconf.getText().equals(userPasswordField.getText())){
							JOptionPane.showMessageDialog(null,"The the passwords don't match");
							userPasswordField.setText("");
							passconf.setText("");
							userPasswordField.requestFocus();
						}
			}}}


		});

	}


	private void unsetteled() {
		try {
			connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
			Statement searchStmnt = connection.createStatement();
	    	String searchStrng ="SELECT * from creditors";
			ResultSet rs = searchStmnt.executeQuery(searchStrng);

			while(rs.next()){
			String	cd= rs.getString("Name");
			String	dis =rs.getString("Credit_ID");
			double	prc =rs.getDouble("Amount");
			String	qnt =rs.getString("Service_provider");
			String	cdtdate =rs.getString("Date");

				Object[]row={cd,dis,prc,qnt,cdtdate};
				unsetldModel.addRow(row);
				unsettledTable.setModel(unsetldModel);
			}}
		catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,"There were no records found");

		}
		double sum=0;
		//get the total deficit
		for(int i=0; i<unsettledTable.getRowCount();i++){
		  String xote= unsettledTable.getValueAt(i,2).toString();
		  double xote2=Double.parseDouble(xote);
		   sum+=xote2;
		   String xote3=Double.toString(sum);
		   unsettledField.setText(xote3);}
		 refreshbutton.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent e) {
		    		try {
		    			while(unsetldModel.getRowCount() > 0){
		    				unsetldModel.removeRow(0);
						    }
		    			connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
		    			Statement searchStmnt = connection.createStatement();
		    	    	String searchStrng ="SELECT * from creditors";
		    			ResultSet rs = searchStmnt.executeQuery(searchStrng);

		    			while(rs.next()){
		    			String	cd= rs.getString("Name");
		    			String	dis =rs.getString("Credit_ID");
		    			double	prc =rs.getDouble("Amount");
		    			String	qnt =rs.getString("Service_provider");
		    			String	cdtdate =rs.getString("Date");

		    				Object[]row={cd,dis,prc,qnt,cdtdate};
		    				unsetldModel.addRow(row);
		    				unsettledTable.setModel(unsetldModel);
		    			}}
		    		catch (SQLException e1) {
		    			e1.printStackTrace();
		    			JOptionPane.showMessageDialog(null,"There were no records found");

		    		}
		    		double sum=0;
		    		//get the total deficit
		    		for(int i=0; i<unsettledTable.getRowCount();i++){
		    		  String xote= unsettledTable.getValueAt(i,2).toString();
		    		  double xote2=Double.parseDouble(xote);
		    		   sum+=xote2;
		    		   String xote3=Double.toString(sum);
		    		   unsettledField.setText(xote3);}
		    	}
		    });
		unsettledTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				payFrame = new JFrame();
				payFrame.setBounds(300, 250, 610, 305);
				payFrame.getContentPane().setLayout(null);

				JPanel payPanel1 = new JPanel();
				payPanel1.setLayout(null);
				payPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));
				payPanel1.setBackground(new Color(0, 102, 0));
				payPanel1.setBounds(0, 0, 581, 268);
				payFrame.getContentPane().add(payPanel1);

				JPanel payPanel2 = new JPanel();
				payPanel2.setLayout(null);
				payPanel2.setBackground(new Color(153, 153, 204));
				payPanel2.setBounds(10, 11, 551, 236);
				payPanel1.add(payPanel2);

				Label label = new Label("       Name");
				label.setForeground(Color.WHITE);
				label.setFont(new Font("Dialog", Font.PLAIN, 25));
				label.setBackground(new Color(0, 102, 102));
				label.setBounds(10, 10, 210, 37);
				payPanel2.add(label);

				Label dfctLbd = new Label("      Deficit");
				dfctLbd.setForeground(Color.WHITE);
				dfctLbd.setFont(new Font("Dialog", Font.PLAIN, 25));
				dfctLbd.setBackground(new Color(0, 102, 102));
				dfctLbd.setBounds(10, 125, 210, 37);
				payPanel2.add(dfctLbd);

				Label amtLble = new Label("   Amount");
				amtLble.setForeground(Color.WHITE);
				amtLble.setFont(new Font("Dialog", Font.PLAIN, 25));
				amtLble.setBackground(new Color(0, 102, 102));
				amtLble.setBounds(10, 176, 210, 37);
				payPanel2.add(amtLble);

			    nameFld = new TextField();
				nameFld.setForeground(new Color(204, 0, 102));
				nameFld.setFont(new Font("Dialog", Font.PLAIN, 19));
				nameFld.setBounds(274, 10, 255, 37);
				payPanel2.add(nameFld);

				dfctFild = new TextField();
				dfctFild.setForeground(new Color(204, 0, 102));
				dfctFild.setFont(new Font("Dialog", Font.PLAIN, 19));
				dfctFild.setBounds(274, 120, 168, 37);
				payPanel2.add(dfctFild);

				amntFild = new TextField();
				amntFild.setForeground(new Color(204, 0, 102));
				amntFild.setFont(new Font("Dialog", Font.PLAIN, 19));
				amntFild.setBounds(274, 176, 168, 37);
				payPanel2.add(amntFild);

				Label label_3 = new Label("     ID");
				label_3.setForeground(Color.WHITE);
				label_3.setFont(new Font("Dialog", Font.PLAIN, 25));
				label_3.setBackground(new Color(0, 102, 102));
				label_3.setBounds(10, 67, 210, 37);
				payPanel2.add(label_3);

				cIDFld = new TextField();
				cIDFld.setForeground(new Color(204, 0, 102));
				cIDFld.setFont(new Font("Dialog", Font.PLAIN, 19));
				cIDFld.setBounds(274, 67, 168, 37);
				payPanel2.add(cIDFld);

				payButton = new Button("PAY");
				payButton.setForeground(Color.WHITE);
				payButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
				payButton.setBackground(new Color(102, 0, 0));
				payButton.setBounds(463, 183, 78, 30);
				payPanel2.add(payButton);

				int makepmnt = unsettledTable.getSelectedRow();
				nameFld.setText(unsetldModel.getValueAt(makepmnt,0).toString());
				cIDFld.setText(unsetldModel.getValueAt(makepmnt,1).toString());
				dfctFild.setText(unsetldModel.getValueAt(makepmnt,2).toString());

				payButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int cell = unsettledTable.getSelectedRow();
						double difference=Double.parseDouble(dfctFild.getText());
						double dif2 =Double.parseDouble(amntFild.getText());
						if(difference>dif2){
							double newDeficit = difference-dif2;
							unsetldModel.setValueAt(newDeficit,cell ,2);
							 try
							    {
					connection=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

							    	PreparedStatement update = connection.prepareStatement
							        ("UPDATE creditors SET Amount=? WHERE Credit_ID=?");
							    	update.setDouble(1,newDeficit);
							    	update.setString(2,cIDFld.getText());
							              update.executeUpdate();
							    }

							    catch (Exception a)
							        {
							    	a.printStackTrace();
							        }

						}
						else if(difference==dif2){
							try{
								connection=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
								//clears the bin model

									unsetldModel.removeRow(cell);

							  	 //deletes from database bin
						    	PreparedStatement del= connection.prepareStatement ("DELETE FROM creditors  WHERE Credit_ID=?");
						    	del.setString(1,cIDFld.getText());
						    	del.executeUpdate();
							}
							catch(Exception ea){
					}
						}

						payFrame.dispose();
					}
				});

				payFrame.setVisible(true);
				amntFild.requestFocus();
			}
		});

	}

	private void Stock() {

		viewingBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cd,dis,prc,qnt;
				while(stockModel.getRowCount() > 0){
					stockModel.removeRow(0);
				    }

				try {
					connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
					Statement searchStmnt = connection.createStatement();
			    	String searchStrng ="SELECT * from items";
					ResultSet rs = searchStmnt.executeQuery(searchStrng);

					while(rs.next()){
						cd= rs.getString("Code");
						dis =rs.getString("Description");
						prc =rs.getString("Price");
						qnt =rs.getString("Quantity");

						Object[]row={cd,dis,prc,qnt};
						stockModel.addRow(row);
						stocktable.setModel(stockModel);
					}}
				catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"There ere no records found");

				}

			}
		});
	}


	private void search() {


		codFld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				balance.setText("");
				String match3 = null;
				int match1 = 0;
				String match=codFld.getText().toString();
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					try {
						connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
						Statement stmt1 = connection.createStatement();
				    	String sql ="select Quantity,Description from items where Code='"+match+"'";
						ResultSet rs = stmt1.executeQuery(sql);

						while(rs.next()){
							 match1 = rs.getInt("Quantity");
							 match3 = rs.getString("Description");

						}}
						catch (SQLException e1) {
							JOptionPane.showMessageDialog(null,e1);
						}

					//checks if the entered text matches the records in the database.
					if(codFld.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Item code is required");
						codFld.requestFocus(); qntityFld.setEditable(false);
					}
					else if(match1==0){
						JOptionPane.showMessageDialog(null,"There are no more "+match3+" left");
						codFld.setText("");
					}
					else if(match1<5){
						JOptionPane.showMessageDialog(null,match1+"  left");
						qntityFld.requestFocus();qntityFld.setEditable(true);

					}
					else{
						qntityFld.requestFocus();
					}
			}}
		});
		qntityFld.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent eu){
					char ch = eu.getKeyChar();
					if(!Character.isDigit(ch)){
						eu.consume();
					}
				}

				String rCode="", rDescptn="",rPrc="",qntty="";
			@Override
			public void keyPressed(KeyEvent e) {
				String retriever=codFld.getText().toString();
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SHIFT){
					try {

						connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
						Statement stmt1 = connection.createStatement();
				    	String sql ="select * from items where Code='"+retriever+"' ";
						ResultSet rs = stmt1.executeQuery(sql);


						while(rs.next()){
							rCode= rs.getString("Code");
							rDescptn=rs.getString("Description");
							rPrc= rs.getString("Price");
							qntty= rs.getString("Quantity");
									}
						//Calculation for total amount
						double prc=Double.parseDouble(rPrc); int qnTT =Integer.parseInt(qntityFld.getText()) ;
						double total=prc*qnTT; //price  quantity
						//add the retrieved data to the table model
						Object[]row={rCode,rDescptn,prc,qntityFld.getText(),total};
						model.addRow(row);
						poSaeTable.setModel(model);
						}
						catch (SQLException e1) {
							e1.printStackTrace();
						}
					//reduce the quantity as you retrieve
					try {
						int r1=Integer.parseInt(qntityFld.getText().toString());
						connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
						PreparedStatement update = connection.prepareStatement
						        ("UPDATE items SET Quantity=? where Code ='"+retriever+"' ");
						int k = Integer.parseInt(qntty);
						int r = k-r1;
						update.setInt(1,(r));
						update.executeUpdate();
					}catch (SQLException e1) {
						e1.printStackTrace();
					}

					givenOut.requestFocus();


					sum=0;
					//get the overall price for all the items
					for(int i=0; i<poSaeTable.getRowCount();i++){
					  String xote= poSaeTable.getValueAt(i,4).toString();
					  double xote2=Double.parseDouble(xote);
					   sum+=xote2;
					   String xote3=Double.toString(sum);
					   totalPesa.setText(xote3);
				}}

				else if (key == KeyEvent.VK_ENTER){

					 if(qntityFld.getText().equals("")){
						JOptionPane.showMessageDialog(null,"field compulsary");
					}
					else{

						//select an item from the database as specified by the Item code entered in the code field
						try {
							connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
							Statement stmt1 = connection.createStatement();
					    	String sql ="select * from items where Code='"+retriever+"' ";
							ResultSet rs = stmt1.executeQuery(sql);

							while(rs.next()){
								rCode= rs.getString("Code");
								rDescptn=rs.getString("Description");
								rPrc= rs.getString("Price");
								qntty= rs.getString("Quantity");
										}
							//reduce the item quantity as you retrieve
							try {
								int r1=Integer.parseInt(qntityFld.getText());
								connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
								PreparedStatement update = connection.prepareStatement
								        ("UPDATE items SET Quantity=? where Code ='"+retriever+"' ");
								int k = Integer.parseInt(qntty);
								int r = k-r1;
								update.setInt(1,(r));
								update.executeUpdate();
							}catch (SQLException e1) {
								e1.printStackTrace();
							}
							//Calculation for total amount
							double prc=Double.parseDouble(rPrc); int qnTT =Integer.parseInt(qntityFld.getText()) ;
							double total=prc*qnTT; //price  quantity
							//add the retrieved data to the table model
							Object[]row={rCode,rDescptn,prc,qntityFld.getText(),total};
							model.addRow(row);
							poSaeTable.setModel(model);
							}
							catch (SQLException e1) {
								e1.printStackTrace();
							}
						qntityFld.setText(null); codFld.setText(null); codFld.requestFocus();

					}
			}}
		});

		givenOut.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent eu){
				char ch = eu.getKeyChar();
				if(!Character.isDigit(ch)){
					eu.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					//compares the given amount and the total cost of the items
					if(  Double.parseDouble(givenOut.getText())<sum ){
						nmnFld.setEditable(true); fieldID.setEditable(true);
						double blnce = sum - Double.parseDouble(givenOut.getText());
						String blnce1 =Double.toString(blnce);
						nmnFld.requestFocus(); fieldAmount.setText(blnce1);
					}
					else{
						double balnc = Double.parseDouble(givenOut.getText())- sum ;
						balance.setText(Double.toString(balnc));
						while(model.getRowCount() > 0)//clears all the rows from the table and sets the table ready for another task.
						{
						    model.removeRow(0);
						}
						//set all the fields to null
						qntityFld.setText(null); codFld.setText(null);	givenOut.setText(null);
						codFld.requestFocus();totalPesa.setText(null);
					}
			}}
		});
		nmnFld.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent eu){
				char ch = eu.getKeyChar();
				if(!Character.isLetter(ch) && !Character.isISOControl(ch)){
					eu.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					if(nmnFld.getText().equals("")){
						JOptionPane.showMessageDialog(null,"field compulsary");
						}
					else{
						fieldID.requestFocus();
						String creditID = null;
						try {
							connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
							Statement stmt1 = connection.createStatement();
					    	String sql ="select Credit_ID from creditors";
							ResultSet rs = stmt1.executeQuery(sql);

							while(rs.next()){
								creditID = rs.getString("Credit_ID");

							}}
							catch (SQLException e1) {
								JOptionPane.showMessageDialog(null,e1);
							}
						JOptionPane.showMessageDialog(null,"The last credit ID was: "+creditID );
					}
			}}
		});
		fieldID.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent eu){
				char ch = eu.getKeyChar();
				if(!Character.isDigit(ch)){
					eu.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {

				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					if(fieldID.getText().equals("")){ // checks if the ID field is empty
						JOptionPane.showMessageDialog(null,"field compulsary");
						}
					else{
						try
					    {
					    	connection=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
					    	String query = "insert into Creditors(Name,Amount,Date,Credit_ID,Service_provider) values(?,?,?,?,?)";
					    	// create the MYSQL insert prepared statement
					    	 PreparedStatement statement1 = connection.prepareStatement(query);
					    	 statement1.setString(1,nmnFld.getText());
					    	 statement1.setString(2,fieldAmount.getText());
					    	 statement1.setString(3, dDate);
					    	 statement1.setString(4, fieldID.getText());
					    	 statement1.setString(5, "Name");
					    	 statement1.executeUpdate();
					    }

					    catch (Exception insert)
					    {
					    	insert.printStackTrace();
					    }
						nmnFld.setEditable(false); fieldID.setEditable(false);
						qntityFld.setText(null); codFld.setText(null); nmnFld.setText(null);  fieldID.setText(null);
						codFld.requestFocus();givenOut.setText(null);totalPesa.setText(null);  fieldAmount.setText(null);
						while(model.getRowCount() > 0){
						    model.removeRow(0);
						    }

					}
			}

				}
		});

		//////////////////////////////////////////////////////////////////////////////////
		sezrchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				searchframe = new JFrame();
				searchframe.setBounds(100, 100, 750, 438);
				searchframe.setVisible(true);
				searchframe.getContentPane().setLayout(null);

				JPanel searchPanel = new JPanel();
				searchPanel.setBackground(new Color(245, 255, 250));
				searchPanel.setBounds(10, 11, 728, 376);
				searchframe.getContentPane().add(searchPanel);
				searchPanel.setLayout(null);

				searchTable = new JTable();
				JScrollPane searchscrlPane = new JScrollPane();
				searchscrlPane.setBounds(10, 48, 708, 317);
				searchPanel.add(searchscrlPane);
				searchTable.setShowVerticalLines(true);
				searchTable.setShowHorizontalLines(false);
				searchTable.setBackground(new Color(204, 204, 102));
				searchTable.setForeground(new Color(255, 0, 0));
				searchscrlPane.setViewportView(searchTable);
			    resultModel = new DefaultTableModel(new String [] {"Code","Description"},0);

				JTableHeader header = searchTable.getTableHeader();
				header.setFont(new Font("Franklin Gothic Medium Cond", Font.ITALIC, 20));
				header.setForeground(new Color(204, 0, 0));
				  header.setBackground(Color.LIGHT_GRAY); ///color of the column header
				  searchTable.setRowHeight(33);//set the height of the table row
				  searchTable.setModel(resultModel);//adds the model to the table

				   findField = new TextField();
				   findField.addKeyListener(new KeyAdapter() {
				   	@Override
				   	public void keyPressed(KeyEvent e) {
				   		String foundTxt=findField.getText();
				   		int key = e.getKeyCode();
						if (key == KeyEvent.VK_ENTER){

							while(resultModel.getRowCount() > 0){
								resultModel.removeRow(0);
							    }
							Toolkit.getDefaultToolkit();
							String foundCode="",foundDescription="";
							try {
								connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
								Statement searchStmnt = connection.createStatement();
						    	String searchStrng ="SELECT * from items WHERE Description LIKE '%"+foundTxt+"%' ";
								ResultSet rs = searchStmnt.executeQuery(searchStrng);

								while(rs.next()){
									foundCode= rs.getString("Code");
									foundDescription =rs.getString("Description");

									Object[]row={foundCode,foundDescription};
									resultModel.addRow(row);
									searchTable.setModel(resultModel);
											}}
							catch (SQLException e1) {
								e1.printStackTrace();
							}


						}}

				   });
				  findField.setForeground(new Color(204, 0, 102));
				  findField.setFont(new Font("Dialog", Font.PLAIN, 19));
				  findField.setBounds(10, 10, 177, 32);
				  searchPanel.add(findField);
				  searchTable.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e){
							String match3 = null;
							int match1 = 0;
							//get the selected row and display the selected data in various text_fields
							Duka1 d1 = new Duka1();
							d1.initialize();
							int ia = searchTable.getSelectedRow();
							 codFld.setText(resultModel.getValueAt(ia,0).toString());
							 searchframe.dispose();

							 //checks whether the entered item code has items left
							 String match=codFld.getText().toString();
								try {
									connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
									Statement stmt1 = connection.createStatement();
							    	String sql ="select Quantity,Description from items where Code='"+match+"'";
									ResultSet rs = stmt1.executeQuery(sql);

									while(rs.next()){
										 match1 = rs.getInt("Quantity");
										 match3 = rs.getString("Description");

									}}
									catch (SQLException e1) {
										JOptionPane.showMessageDialog(null,e1);
									}
								if(match1==0){
									JOptionPane.showMessageDialog(null,"There are no more "+match3+" left");
									codFld.setText("");
								}
								else{
							 qntityFld.requestFocus();
								}

						}
					});	  }
		});
		 addDebitor.addMouseListener(new MouseAdapter() {
			 int newID,match2;
			 	@Override
			 	public void mouseClicked(MouseEvent e) {

			 		entryFrame = new JFrame();
					entryFrame.getContentPane().setBackground(new Color(0, 102, 51));
					entryFrame.setTitle("Debts");
					entryFrame.setBounds(300, 100, 306, 416);
					//entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					entryFrame.getContentPane().setLayout(null);
					entryFrame.setVisible(true);

					Label iDnumb = new Label("         ID number");
					iDnumb.setFont(new Font("Times New Roman", Font.PLAIN, 17));
					iDnumb.setBounds(24, 26, 197, 22);
					entryFrame.getContentPane().add(iDnumb);

					Label cstmrLbl = new Label(" Customer's Name");
					cstmrLbl.setFont(new Font("Times New Roman", Font.PLAIN, 17));
					cstmrLbl.setBounds(24, 151, 197, 22);
					entryFrame.getContentPane().add(cstmrLbl);

					Label amntbl = new Label("            Amount\r\n");
					amntbl.setFont(new Font("Times New Roman", Font.PLAIN, 17));
					amntbl.setBounds(24, 262, 197, 22);
					entryFrame.getContentPane().add(amntbl);

					tidField = new TextField();
					tidField.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							int key = e.getKeyCode();
							if (key == KeyEvent.VK_ENTER){

							cstmrFld.requestFocus();

							try {
								connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
								Statement stmt1 = connection.createStatement();
						    	String sql ="select Customer_ID from debit";
								ResultSet rs = stmt1.executeQuery(sql);

								while(rs.next()){
									 match2 = rs.getInt("Customer_ID");
								}}
								catch (SQLException e1) {
									JOptionPane.showMessageDialog(null,e1);
								}

							newID = match2+1;
							tidField.setText(Double.toString(newID));
							}
						}
					});
					tidField.setEditable(true);
					tidField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
					tidField.setForeground(new Color(204, 0, 204));
					tidField.setBounds(24, 72, 219, 41);
					entryFrame.getContentPane().add(tidField);

					cstmrFld = new TextField();
					cstmrFld.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							int key = e.getKeyCode();
							if (key == KeyEvent.VK_ENTER){

							amntFld.requestFocus();
							}
						}
					});
					cstmrFld.setForeground(new Color(204, 0, 204));
					cstmrFld.setFont(new Font("Times New Roman", Font.PLAIN, 16));
					cstmrFld.setEditable(true);
					cstmrFld.setBounds(24, 195, 219, 41);
					entryFrame.getContentPane().add(cstmrFld);

					amntFld = new TextField();
					amntFld.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							int key = e.getKeyCode();
							if (key == KeyEvent.VK_ENTER){

								 double amntt=Double.parseDouble(amntFld.getText());
							    try
							    {
							    	connection=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
							    	String query = "insert into debit(ID,Customer_name,Amount,service_provider,Date) values(?,?,?,?,?)";
							    	// create the MYSQL insert prepared statement
							    	 PreparedStatement statement = connection.prepareStatement(query);
							    	 statement.setInt(1,newID);
							    	 statement.setString(2, cstmrFld.getText());
							    	 statement.setDouble(3, amntt);
							    	 statement.setString(4, "service provider");
							    	 statement.setString(5, dDate);
							    	 statement.executeUpdate();
							    }

							    catch (Exception insert)
							    {

							    }
							    entryFrame.dispose(); codFld.requestFocus(); balance.setText("");
							}

							}

					});
					amntFld.setForeground(new Color(204, 0, 204));
					amntFld.setFont(new Font("Times New Roman", Font.PLAIN, 16));
					amntFld.setEditable(true);
					amntFld.setBounds(24, 310, 219, 41);
					entryFrame.getContentPane().add(amntFld);

					 	}
			 			 });
	}


	private void manageItems() {
		searchItems.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					while(model2.getRowCount() > 0){
						model2.removeRow(0);
					    }
					String resultFound=searchItems.getText();
					Toolkit.getDefaultToolkit();
					String foundItemCode1="",foundDescription1="",foundItemPrice1="",foundItemQntity1="";
					try {
						connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
						Statement searchStmnt = connection.createStatement();
				    	String searchStrng ="SELECT * from items WHERE Description LIKE '%"+resultFound+"%' OR Code LIKE '%"+resultFound+"%'";
						ResultSet rs = searchStmnt.executeQuery(searchStrng);

						while(rs.next()){
							foundItemCode1= rs.getString("Code");
							foundDescription1 =rs.getString("Description");
							foundItemPrice1 =rs.getString("Price");
							foundItemQntity1 =rs.getString("Quantity");

							Object[]row={foundItemCode1,foundDescription1,foundItemQntity1,foundItemPrice1};
							model2.addRow(row);
							findTable.setModel(model2);
									}}
					catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,"There waere no records found");

					}


				}

			}
		});
		findTable.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){

				//get the selected row and display the selected data in various text_fields
				int i = findTable.getSelectedRow();
				codeField.setText(model2.getValueAt(i,0).toString());
				derscriptionField.setText(model2.getValueAt(i,1).toString());
				priceField.setText(model2.getValueAt(i,3).toString());
				qntyField.setText(model2.getValueAt(i,2).toString());

			}
		});

		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Update an item
				int i1 = findTable.getSelectedRow();
				if(i1>=0){
					if(derscriptionField.getText().equals("")||codeField.getText().equals("")||priceField.getText().equals("") ||
							qntyField.getText().equals("")){
						JOptionPane.showMessageDialog(null,"field compulsary plz");
						}
					else{
						//update the j_table data
					model2.setValueAt(codeField.getText(),i1 ,0);
					model2.setValueAt(derscriptionField.getText(),i1 ,1);
					model2.setValueAt(priceField.getText(),i1 ,3);
					model2.setValueAt(qntyField.getText(),i1 ,2);
				    try
				    {
				    	if(codeField.getText().equals("")||derscriptionField.getText().equals("")||qntyField.equals("")
				    			||priceField.getText().equals("")){
							JOptionPane.showMessageDialog(null,"field compulsary plz");


						}else{
							connection=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

				    	// update employee details in the items table
				    	PreparedStatement update = connection.prepareStatement
				        ("UPDATE items SET Price=?,Description=?,Quantity=? WHERE Code=?");
				    	update.setString(1,priceField.getText());
				    	update.setString(2,derscriptionField.getText());
				    	update.setString(3,qntyField.getText());
				    	update.setString(4,codeField.getText());
				              update.executeUpdate();
				              itemPanel.add(sslbl);
				              codeAddtField.setText("");
				              while(model2.getRowCount() > 0){
								    model2.removeRow(0);
								    codeField.setText(null); derscriptionField.setText(null); qntyField.setText(null);
								    priceField.setText(null);
								    searchItems.setText(null);
							    	 searchItems.requestFocus();
								    }
				              }
				               }

				    catch (Exception a)
				        {
				    	itemPanel.add(editFailure);
				        }
				}}
			}
		});

		codeAddtField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					//retrieve code from the codeAddtField TextField and assigns it to a string(idDuplicate variable).
					String idDuplicate=codeAddtField.getText().toString();
					String confirmation1 = null;// declare a new string and initialize it to null
					String confirmation2 = null;
					//retrieve item Code from the items' table for comparison with the new entry
					try {
						connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
						Statement stmt = connection.createStatement();

						String sql = "SELECT Code,Description FROM items WHERE Code='"+idDuplicate+"'";
						ResultSet rs = stmt.executeQuery(sql);
						while(rs.next()){
							//assign the retrieved string value to a string variable(confirmation)
							confirmation1=rs.getString("Code");
							confirmation2=rs.getString("Description");
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(codeAddtField.getText().equals("") || idDuplicate.equals(confirmation1)){
						JOptionPane.showMessageDialog(null,"The entered code is already assigned to"
								+ " "+confirmation2+"\n"+"Find another code to assign your new item");
						codeAddtField.setText("");
					}
					else{
						itemPanel.remove(addSuccess); itemPanel.remove(entryFailure);
						addDescrptnFld.requestFocus();
					}
			}}
		});
		addDescrptnFld.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					if(codeAddtField.getText().equals("")){
						//sachFld.requestFocus();
					}
					else{
						priceAddFld.requestFocus();
					}
			}}
		});
		priceAddFld.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent eu){
					char ch = eu.getKeyChar();
					if(!Character.isDigit(ch)){
						eu.consume();
					}
				}
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER){
					if(priceAddFld.getText().equals("")){

					}
					else{
						addQuantityFld.requestFocus();
					}
			}}
		});
		addQuantityFld.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent eu){
				char ch = eu.getKeyChar();
				if(!Character.isDigit(ch)){
					eu.consume();
				}
			}
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_ENTER){
				if(addQuantityFld.getText().equals("")){

				}
				else{

				    try
				    {
				    	connection=DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
				    	String query = "insert into items(Code,Description,Quantity,Price) values(?,?,?,?)";
				    	// create the MYSQL insert prepared statement
				    	 PreparedStatement statement = connection.prepareStatement(query);
				    	 statement.setString(1,codeAddtField.getText());
				    	 statement.setString(2, addDescrptnFld.getText());
				    	 statement.setString(3, addQuantityFld.getText());
				    	 statement.setString(4, priceAddFld.getText());
				    	 statement.executeUpdate();
				    	 itemPanel.add(addSuccess);

				    }

				    catch (Exception insert)
				    {
				    	itemPanel.add(entryFailure);
				    }
					codeAddtField.setText(null); addQuantityFld.setText(null); priceAddFld.setText(null); addDescrptnFld.setText(null);
					codeAddtField.requestFocus();

				}
		}}
		});
	}

	public void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(150, 0, 892, 900);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(204, 204, 153));
		menuPanel.setBounds(10, 11, 858, 44);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		Button newkeeper = new Button("New Keeper");
		newkeeper.setBounds(10, 10, 90, 24);
		menuPanel.add(newkeeper);

		Button availableItems = new Button("Stock");
		availableItems.setBounds(114, 10, 90, 24);
		menuPanel.add(availableItems);

		Button mngKeepers = new Button("Manage Keepers");
		mngKeepers.setBounds(210, 10, 96, 24);
		menuPanel.add(mngKeepers);

		Button mngItems = new Button("Manage Stock");
		mngItems.setBounds(312, 10, 90, 24);
		menuPanel.add(mngItems);

		Button dailySls = new Button("Daily Sales");
		dailySls.setBounds(408, 10, 90, 24);
		menuPanel.add(dailySls);

		Button unsettled = new Button("Creditors");
		unsettled.setBounds(504, 10, 90, 24);
		menuPanel.add(unsettled);

		Button pointOfsale = new Button("Point of Sale");
		pointOfsale.setBounds(600, 10, 90, 24);
		menuPanel.add(pointOfsale);

		Debit = new Button("Debit");
		Debit.setBounds(696, 10, 90, 24);
		menuPanel.add(Debit);

		Button help = new Button("Help");
		help.setBounds(792, 10, 56, 24);
		menuPanel.add(help);
		final CardLayout cardLayout = new CardLayout();
		 Cpanel = new JPanel(cardLayout);
		Cpanel.setBackground(new Color(255, 255, 255));
		Cpanel.setBounds(10, 56, 858, 673);
		contentPane.add(Cpanel);
		//////////////////////////////////////////////////////////////////////////////////////////
		JPanel homePage = new JPanel();
		homePage.setBackground(new Color(204, 204, 102));
		homePage.setBounds(10, 56, 1028, 813);
		homePage.setLayout(null);
		Cpanel.add(homePage,"HOME_PAGE");

		Panel homePanel = new Panel();
		homePanel.setBackground(new Color(204, 153, 102));
		homePanel.setBounds(10, 0, 838, 653);
		homePage.add(homePanel);
		homePanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Swellah Swellah");
		lblNewLabel.setForeground(new Color(153, 0, 255));
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD | Font.ITALIC, 90));
		lblNewLabel.setBounds(61, 175, 712, 188);
		homePanel.add(lblNewLabel);

		Label time = new Label("");
		time.setForeground(new Color(255, 255, 255));
		time.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 50));
		time.setBounds(156, 52, 634, 66);
		homePanel.add(time);

		 // create a dynamic date and time
	    Thread clock = new Thread(){

			public void run(){

			for(;;){

			Calendar cal = new GregorianCalendar();

			//creates year, month and date
			 mnth = cal.get(Calendar.MONTH);
			 yr = cal.get(Calendar.YEAR);
			 dy=cal.get(Calendar.DAY_OF_MONTH);
			dDate = yr + "/"+(mnth+1)+"/"+dy;

			 //create time in hours, minute and seconds
		 sec= cal.get(Calendar.SECOND);
		 min = cal.get(Calendar.MINUTE);
		 hr = cal.get(Calendar.HOUR);
		 dTime= hr+" :"+min+":"+sec;
		 dateAndTime= "			"+dDate+"						 "+"	"+dTime;

		//set the date and time to the time text fields time and time2
		 time.setText(dateAndTime);

			try {
				sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}}}
			};
			clock.start();//starts the thread


		//////////////////////////////////////////////////////////////////////////////////////////
		JPanel beg = new JPanel();
		beg.setBackground(new Color(204, 204, 102));
		beg.setBounds(10, 56, 1028, 813);
		 beg.setLayout(null);
		Cpanel.add(beg,"MAKE");

		JPanel newUserPanel = new JPanel();
		newUserPanel.setBackground(new Color(204, 153, 255));
		newUserPanel.setBounds(142, 65, 546, 538);
		beg.add(newUserPanel);
		newUserPanel.setLayout(null);

		JLabel nmelBle = new JLabel("Keeper's name  (in capital letters)");
		nmelBle.setForeground(new Color(128, 0, 0));
		nmelBle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		nmelBle.setBounds(76, 75, 379, 34);
		newUserPanel.add(nmelBle);

		JLabel pscdLbl = new JLabel("Password (should not be the keeper's name)");
		pscdLbl.setForeground(new Color(128, 0, 0));
		pscdLbl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pscdLbl.setBounds(76, 300, 436, 34);
		newUserPanel.add(pscdLbl);

		JLabel cdLbl = new JLabel("Identification Number(Four digits)");
		cdLbl.setForeground(new Color(128, 0, 0));
		cdLbl.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cdLbl.setBounds(76, 185, 384, 43);
		newUserPanel.add(cdLbl);

		nemFild = new JTextField();
		nemFild.setBackground(new Color(255, 255, 204));
		nemFild.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		nemFild.setBounds(66, 124, 417, 50);
		newUserPanel.add(nemFild);
		nemFild.setColumns(10);

		IDfild = new JTextField();
		IDfild.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		IDfild.setColumns(10);
		IDfild.setBackground(new Color(255, 255, 204));
		IDfild.setBounds(66, 225, 417, 50);
		newUserPanel.add(IDfild);

		userPasswordField = new JPasswordField();
		userPasswordField.setBackground(new Color(255, 255, 204));
		userPasswordField.setBounds(66, 338, 417, 50);
		newUserPanel.add(userPasswordField);

		Label accountLabel = new Label("Use the below fields to add a new account");
		accountLabel.setForeground(new Color(255, 255, 255));
		accountLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		accountLabel.setBounds(66, 10, 394, 34);
		newUserPanel.add(accountLabel);

		JLabel lblIdConfirmPassword = new JLabel("Confirm password(Should match the first password)");
		lblIdConfirmPassword.setForeground(new Color(128, 0, 0));
		lblIdConfirmPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblIdConfirmPassword.setBounds(71, 399, 425, 43);
		newUserPanel.add(lblIdConfirmPassword);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setForeground(new Color(255, 0, 255));
		horizontalGlue.setBounds(29, 48, 470, 16);
		newUserPanel.add(horizontalGlue);

		passconf = new JPasswordField();

		passconf.setBounds(66, 437, 417, 43);
		newUserPanel.add(passconf);
		    ///////////////////////////////////////////////////////////////////////////////////////////////////////


		JPanel viewKeepers = new JPanel();
		viewKeepers.setBackground(new Color(204, 204, 102));
		viewKeepers.setBounds(10, 56, 1028, 813);
		viewKeepers.setLayout(null);
		Cpanel.add(viewKeepers,"VIEW1");

		JScrollPane VKeepersPane = new JScrollPane();
		VKeepersPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		VKeepersPane.setBounds(23, 39, 825, 209);
		viewKeepers.add(VKeepersPane);
		vKeepersTable = new JTable();
		vKeepersTable.setFont(new Font("Verdana", Font.PLAIN, 16));
		vKeepersTable.setBorder(new CompoundBorder());
		vKeepersTable.setRowHeight(33);
		vKeepersTable.setShowHorizontalLines(false);
		VKeepersPane.setViewportView(vKeepersTable);
		keepersTableModel = new DefaultTableModel(new String [] {"Name","Identification Number"},0);
		vKeepersTable.setModel(keepersTableModel);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		items = new JPanel();
		items.setBackground(new Color(204, 204, 102));
		items.setBounds(10, 56, 1028, 780);
		items.setLayout(null);
		Cpanel.add(items,"ITEMS");

		itemPanel = new JPanel();
		itemPanel.setLayout(null);
		itemPanel.setBackground(new Color(204, 153, 153));
		itemPanel.setBounds(10, 59, 797, 603);
		items.add(itemPanel);

		Label codeLabel = new Label("Item Code");
		codeLabel.setForeground(Color.BLACK);
		codeLabel.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		codeLabel.setBackground(new Color(204, 153, 153));
		codeLabel.setBounds(10, 327, 134, 38);
		itemPanel.add(codeLabel);

		codeField = new JTextField();
		codeField.setForeground(new Color(204, 0, 0));
		codeField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		codeField.setColumns(10);
		codeField.setBounds(150, 327, 134, 38);
		itemPanel.add(codeField);

		btnEdit = new JButton("EDIT");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnEdit.setBackground(new Color(51, 0, 0));
		btnEdit.setBounds(680, 371, 107, 38);
		itemPanel.add(btnEdit);

		Label searchLbl = new Label("Search Item");
		itemPanel.add(searchLbl);

		searchItems = new JTextField();
		searchItems.setToolTipText("Search by Item description,item code or prices");
		searchItems.setForeground(new Color(204, 0, 0));
		searchItems.setFont(new Font("Times New Roman", Font.BOLD, 18));
		searchItems.setColumns(10);
		searchItems.setBounds(161, 24, 179, 38);
		itemPanel.add(searchItems);

		Label itmDeslbl = new Label("Description");
		itmDeslbl.setForeground(Color.BLACK);
		itmDeslbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		itmDeslbl.setBackground(new Color(204, 153, 153));
		itmDeslbl.setBounds(341, 327, 134, 38);
		itemPanel.add(itmDeslbl);

		derscriptionField = new JTextField();
		derscriptionField.setForeground(new Color(204, 0, 0));
		derscriptionField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		derscriptionField.setColumns(10);
		derscriptionField.setBounds(495, 327, 292, 38);
		itemPanel.add(derscriptionField);

		Label prcLbl = new Label("Item Price");
		prcLbl.setForeground(Color.BLACK);
		prcLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		prcLbl.setBackground(new Color(204, 153, 153));
		prcLbl.setBounds(10, 371, 134, 38);
		itemPanel.add(prcLbl);

		priceField = new JTextField();
		priceField.setForeground(new Color(204, 0, 0));
		priceField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		priceField.setColumns(10);
		priceField.setBounds(150, 371, 134, 38);
		itemPanel.add(priceField);

		Label qntityLbl = new Label("Item Quantity");
		qntityLbl.setForeground(Color.BLACK);
		qntityLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		qntityLbl.setBackground(new Color(204, 153, 153));
		qntityLbl.setBounds(341, 371, 134, 38);
		itemPanel.add(qntityLbl);

		qntyField = new JTextField();
		qntyField.setForeground(new Color(204, 0, 0));
		qntyField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		qntyField.setColumns(10);
		qntyField.setBounds(495, 371, 134, 38);
		itemPanel.add(qntyField);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 420, 777, 10);
		itemPanel.add(separator);

		Label editCodeLbl = new Label("Code");
		editCodeLbl.setForeground(Color.BLACK);
		editCodeLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		editCodeLbl.setBackground(new Color(204, 153, 153));
		editCodeLbl.setBounds(10, 480, 134, 38);
		itemPanel.add(editCodeLbl);

		Label label_2 = new Label("Price");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		label_2.setBackground(new Color(204, 153, 153));
		label_2.setBounds(10, 524, 134, 38);
		itemPanel.add(label_2);

		Label qntylbl = new Label("Quantity");
		qntylbl.setForeground(Color.BLACK);
		qntylbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		qntylbl.setBackground(new Color(204, 153, 153));
		qntylbl.setBounds(341, 524, 134, 38);
		itemPanel.add(qntylbl);

		codeAddtField = new JTextField();
		codeAddtField.setForeground(new Color(204, 0, 0));
		codeAddtField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		codeAddtField.setColumns(10);
		codeAddtField.setBounds(150, 480, 134, 38);
		itemPanel.add(codeAddtField);

		priceAddFld = new JTextField();
		priceAddFld.setForeground(new Color(204, 0, 0));
		priceAddFld.setFont(new Font("Times New Roman", Font.BOLD, 18));
		priceAddFld.setColumns(10);
		priceAddFld.setBounds(150, 524, 134, 38);
		itemPanel.add(priceAddFld);

		Label desAddlbl = new Label("Description");
		desAddlbl.setForeground(Color.BLACK);
		desAddlbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		desAddlbl.setBackground(new Color(204, 153, 153));
		desAddlbl.setBounds(341, 480, 134, 38);
		itemPanel.add(desAddlbl);

	    addDescrptnFld = new JTextField();
		addDescrptnFld.setForeground(new Color(204, 0, 0));
		addDescrptnFld.setFont(new Font("Times New Roman", Font.BOLD, 18));
		addDescrptnFld.setColumns(10);
		addDescrptnFld.setBounds(495, 480, 292, 38);
		itemPanel.add(addDescrptnFld);

		addQuantityFld = new JTextField();
		addQuantityFld.setForeground(new Color(204, 0, 0));
		addQuantityFld.setFont(new Font("Times New Roman", Font.BOLD, 18));
		addQuantityFld.setColumns(10);
		addQuantityFld.setBounds(495, 524, 134, 38);
		itemPanel.add(addQuantityFld);

		JPanel anotherPanel = new JPanel();
		anotherPanel.setLayout(null);
		anotherPanel.setBackground(new Color(0, 102, 102));
		anotherPanel.setBounds(10, 439, 777, 35);
		itemPanel.add(anotherPanel);

		Label addLbl = new Label("ADD ITEM");
		addLbl.setBounds(10, 10, 168, 28);
		anotherPanel.add(addLbl);
		addLbl.setForeground(Color.WHITE);
		addLbl.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		addLbl.setBackground(new Color(0, 102, 102));

		entryFailure = new Label("Item not successfully added, please check your entries");
		entryFailure.setForeground(new Color(255, 255, 255));
		entryFailure.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		entryFailure.setBackground(new Color(255, 0, 0));
		entryFailure.setBounds(10, 568, 448, 22);


		addSuccess = new Label("       Successfull!!!");
		addSuccess.setBackground(new Color(0, 204, 0));
		addSuccess.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addSuccess.setBounds(534, 568, 179, 22);


		editSucess = new Label("       Successfull!!!");
		editSucess.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		editSucess.setBackground(new Color(0, 204, 0));
		editSucess.setBounds(608, 10, 179, 22);
		//itemPanel.add(editSucess);

	   editFailure = new Label("Failed to keep changes....invalid entries");
		editFailure.setForeground(Color.WHITE);
		editFailure.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		editFailure.setBackground(Color.RED);
		editFailure.setBounds(457, 40, 330, 22);


		JScrollPane findSCLpane = new JScrollPane();
		findSCLpane.setBounds(10, 74, 777, 233);
		itemPanel.add(findSCLpane);

		findTable = new JTable();
		findTable.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
		findTable.setForeground(new Color(51, 0, 153));
		JTableHeader header = findTable.getTableHeader();
		header.setFont(new Font("Franklin Gothic Medium Cond", Font.ITALIC, 20));
		header.setForeground(new Color(204, 0, 0));
		  header.setBackground(Color.LIGHT_GRAY); ///color of the column header
		  findTable.setRowHeight(33);//set the height of the table row
		  findTable.setShowVerticalLines(false);
		  findTable.setShowHorizontalLines(true);
		findSCLpane.setViewportView(findTable);
		model2 = new DefaultTableModel(new String [] {"Code","Description","Quantity","Price"},0);
		findTable.setModel(model2);

		sslbl = new JLabel("Update successful..");
		sslbl.setForeground(new Color(255, 255, 255));
		sslbl.setBackground(new Color(0, 204, 51));
		sslbl.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		sslbl.setBounds(401, 47, 142, 23);

		JPanel pnl = new JPanel();
		pnl.setLayout(null);
		pnl.setBackground(new Color(0, 102, 102));
		pnl.setBounds(10, 11, 797, 48);
		items.add(pnl);

		Label lblAddAndUpdateStock = new Label("                       ADD AND UPDATE STOCK");
		lblAddAndUpdateStock.setForeground(Color.WHITE);
		lblAddAndUpdateStock.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblAddAndUpdateStock.setBackground(new Color(0, 102, 102));
		lblAddAndUpdateStock.setBounds(45, 10, 516, 36);
		pnl.add(lblAddAndUpdateStock);

		///////////////////////////////////////////////////////////////////////////////////////////////////////
		JPanel viewItems = new JPanel();
		viewItems.setBackground(new Color(204, 204, 102));
		viewItems.setBounds(10, 56, 1028, 1000);
		viewItems.setLayout(null);
		Cpanel.add(viewItems,"VIEW_ITEMS");

		JPanel bodyPanel = new JPanel();
		bodyPanel.setBackground(new Color(204, 153, 153));
		bodyPanel.setBounds(10, 70, 818, 591);
		viewItems.add(bodyPanel);
		bodyPanel.setLayout(null);

		viewingBtn = new Button("view stock");
		viewingBtn.setBackground(new Color(204, 153, 204));
		viewingBtn.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		viewingBtn.setBounds(10, 10, 129, 32);
		bodyPanel.add(viewingBtn);

		JScrollPane stockScrlPane = new JScrollPane();
		stockScrlPane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		stockScrlPane.setBounds(10, 48, 798, 532);
		bodyPanel.add(stockScrlPane);

		stocktable = new JTable();
		stocktable.setRowSelectionAllowed(false);
		stocktable.setFillsViewportHeight(true);
		stocktable.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
		stocktable.setShowGrid(false);
		stocktable.setForeground(new Color(51, 0, 153));
		stocktable.setShowVerticalLines(true);
		stockScrlPane.setViewportView(stocktable);

		//set alternate row colors in the table
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if(defaults.get("Table.alternateRowColor")==null)
			defaults.put("Table.alternateRowColor",Color.pink);

		//create the model for the column header
		stockModel = new DefaultTableModel(new String [] {"Code","Description","Price","Quantity Available"},0);
		stocktable.setModel(stockModel);//adds the model to the table
		JTableHeader stockHeader = stocktable.getTableHeader();
		stockHeader.setFont(new Font("Franklin Gothic Medium Cond", Font.CENTER_BASELINE, 25));
		stockHeader.setForeground(new Color(204, 0, 0));
		stockHeader.setBackground(Color.LIGHT_GRAY); ///color of the column header
		stocktable.setRowHeight(35);

		JPanel header1Panel = new JPanel();
		header1Panel.setLayout(null);
		header1Panel.setBackground(new Color(0, 102, 102));
		header1Panel.setBounds(10, 11, 818, 60);
		viewItems.add(header1Panel);

		Label headlbl = new Label("               STOCK");
		headlbl.setForeground(Color.WHITE);
		headlbl.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		headlbl.setBackground(new Color(0, 102, 102));
		headlbl.setBounds(45, 10, 516, 36);
		header1Panel.add(headlbl);

		//////////////////////////////////////////////////////////////////////////////////////////
		JPanel inAvailable = new JPanel();
		inAvailable.setBackground(new Color(204, 204, 102));
		inAvailable.setBounds(10, 56, 1028, 1000);
		inAvailable.setLayout(null);
		Cpanel.add(inAvailable,"ORUMO");

		JPanel notAvailablePanel = new JPanel();
		notAvailablePanel.setBackground(new Color(204, 153, 102));
		notAvailablePanel.setBounds(22, 11, 798, 265);
		inAvailable.add(notAvailablePanel);
		notAvailablePanel.setLayout(null);

		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(10, 11, 769, 243);
		notAvailablePanel.add(scrollPane3);

		tablenotAvailable = new JTable();
		scrollPane3.setViewportView(tablenotAvailable);

		JPanel dailySalesPanel = new JPanel();
		dailySalesPanel.setBackground(new Color(204, 153, 102));
		dailySalesPanel.setBounds(22, 287, 798, 348);
		inAvailable.add(dailySalesPanel);
		dailySalesPanel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 34, 772, 252);
		dailySalesPanel.add(scrollPane_1);

		Label salesLbl = new Label("Daily sales");
		salesLbl.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
		salesLbl.setForeground(new Color(102, 0, 0));
		salesLbl.setBounds(235, 6, 145, 22);
		dailySalesPanel.add(salesLbl);

		Label totalSales = new Label("Total amount");
		totalSales.setForeground(new Color(102, 0, 0));
		totalSales.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
		totalSales.setBounds(10, 305, 131, 22);
		dailySalesPanel.add(totalSales);

		TextField amntField = new TextField();
		amntField.setEditable(false);
		amntField.setForeground(new Color(0, 102, 0));
		amntField.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 17));
		amntField.setBounds(158, 303, 255, 35);
		dailySalesPanel.add(amntField);

		Label datelbl = new Label("Date");
		datelbl.setForeground(new Color(102, 0, 0));
		datelbl.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 18));
		datelbl.setBounds(452, 305, 57, 22);
		dailySalesPanel.add(datelbl);

		TextField dateField = new TextField();
		dateField.setEditable(false);
		dateField.setForeground(new Color(0, 102, 0));
		dateField.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 17));
		dateField.setBounds(516, 303, 255, 35);
		dailySalesPanel.add(dateField);
		///////////////////////////////////////////////////////////////////////////////////

		JPanel debit_1 = new JPanel();
		debit_1.setBackground(new Color(204, 204, 102));
		debit_1.setBounds(10, 56, 1028, 1000);
		debit_1.setLayout(null);
		Cpanel.add(debit_1,"DEBT");

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(204, 204, 102));
		panel_1.setBounds(0, 0, 858, 673);
		debit_1.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(0, 102, 102));
		panel_2.setBounds(10, 11, 838, 60);
		panel_1.add(panel_2);

		JLabel lblDebit = new JLabel("                      Debit");
		lblDebit.setForeground(Color.WHITE);
		lblDebit.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblDebit.setBounds(10, 11, 689, 42);
		panel_2.add(lblDebit);

		JPanel panelDbt = new JPanel();
		panelDbt.setLayout(null);
		panelDbt.setBackground(new Color(204, 255, 204));
		panelDbt.setBounds(20, 82, 838, 591);
		panel_1.add(panelDbt);

		JScrollPane	 debitPane = new JScrollPane();
		debitPane.setBounds(10, 11, 818, 398);
		panelDbt.add(debitPane);

		debitTable = new JTable();
		debitTable.setRowSelectionAllowed(false);
		debitTable.setFillsViewportHeight(true);
		debitTable.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
		debitTable.setShowGrid(false);
		debitTable.setForeground(new Color(51, 0, 153));
		debitTable.setShowVerticalLines(true);
		debitPane.setViewportView(debitTable);

		debitModel = new DefaultTableModel(new String [] {"ID","Customer_name","Update Date","service_provider","Amount"},0);
		debitTable.setModel(debitModel);//adds the model to the table

	    debitFld = new TextField();
		debitFld.setForeground(new Color(204, 0, 102));
		debitFld.setFont(new Font("Dialog", Font.PLAIN, 23));
		debitFld.setBounds(668, 415, 160, 28);
		panelDbt.add(debitFld);

		//////////////////////////////////////////////////////////////////////////////////
		JPanel unstld = new JPanel();
		unstld.setBackground(new Color(204, 204, 102));
		unstld.setBounds(10, 56, 1028, 1000);
		unstld.setLayout(null);
		Cpanel.add(unstld,"GOWI");

		JPanel hPanel = new JPanel();
		hPanel.setBackground(new Color(0, 102, 102));
		hPanel.setBounds(10, 11, 838, 60);
		unstld.add(hPanel);
		hPanel.setLayout(null);

		JLabel notYet = new JLabel("                      Creditors");
		notYet.setForeground(new Color(255, 255, 255));
		notYet.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		notYet.setBounds(10, 11, 689, 42);
		hPanel.add(notYet);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(20, 82, 838, 591);
		unstld.add(panel);
		panel.setLayout(null);

		JScrollPane unsettledScrollPane = new JScrollPane();
		unsettledScrollPane.setBounds(10, 11, 818, 414);
		panel.add(unsettledScrollPane);

		unsettledTable = new JTable();
		unsettledTable.setFillsViewportHeight(true);
		unsettledTable.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
		unsettledTable.setShowGrid(false);
		unsettledTable.setForeground(new Color(51, 0, 153));
		unsettledTable.setShowHorizontalLines(true);
		//create the model for the column header
		unsetldModel = new DefaultTableModel(new String [] {"Name","ID","Deficit","Served_by","Date"},0);
		unsettledTable.setRowHeight(33);
		unsettledTable.setModel(unsetldModel);//adds the model to the table
		unsettledTable.setBackground(new Color(204, 204, 255));
		unsettledScrollPane.setViewportView(unsettledTable);

		Label totlUnstldlbl = new Label("Total Amount");
		totlUnstldlbl.setBackground(new Color(0, 102, 102));
		totlUnstldlbl.setForeground(new Color(255, 255, 255));
		totlUnstldlbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		totlUnstldlbl.setBounds(22, 446, 168, 30);
		panel.add(totlUnstldlbl);

	    unsettledField = new TextField();
		unsettledField.setForeground(new Color(204, 0, 102));
		unsettledField.setFont(new Font("Dialog", Font.PLAIN, 23));
		unsettledField.setBounds(425, 444, 168, 49);
		panel.add(unsettledField);

		JSeparator unstldSprt = new JSeparator();
		unstldSprt.setBounds(10, 510, 806, 17);
		panel.add(unstldSprt);

	    refreshbutton = new Button("Reload");
		refreshbutton.setBackground(new Color(112, 128, 144));
		refreshbutton.setFont(new Font("Dialog", Font.PLAIN, 15));
		refreshbutton.setBounds(647, 446, 70, 22);
		panel.add(refreshbutton);


		newkeeper.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(Cpanel, "MAKE");
			}
		});
		availableItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(Cpanel, "VIEW_ITEMS");
			}
		});
		mngKeepers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(Cpanel, "VIEW1");
			}
		});
		mngItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				codeAddtField.requestFocus();
				cardLayout.show(Cpanel, "ITEMS");
			}
		});
		dailySls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(Cpanel, "ORUMO");
			}
		});
		unsettled.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(Cpanel, "GOWI");
				}
		});
		pointOfsale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(Cpanel, "SALES");
				codFld.requestFocus();
			}
		});
		Debit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String CtmName,svcProvider,date;
				int CstmID;
				double amount;
				while(debitModel.getRowCount() > 0){
					debitModel.removeRow(0);
				    }
				try {
					connection = DriverManager.getConnection( CONN_STRING, USERNAME, PASSWORD );
					Statement debitStmnt = connection.createStatement();
			    	String searchStrng ="SELECT * from debit";
					ResultSet rs = debitStmnt.executeQuery(searchStrng);

					while(rs.next()){
						CtmName= rs.getString("Customer_name");
						CstmID =rs.getInt("ID");
						svcProvider =rs.getString("service_provider");
						date =rs.getString("Date");
						amount =rs.getDouble("Amount");
						Object[]row={CstmID,CtmName,date,svcProvider,amount};
						debitModel.addRow(row);
						debitTable.setModel(debitModel);
					}}
				catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"There ere no records found");

				}
				double sum=0;
				//get the total amount you owe customers
				for(int i=0; i<debitTable.getRowCount();i++){
				  String ttlamount= debitTable.getValueAt(i,4).toString();
				  double start=Double.parseDouble(ttlamount);
				   sum+=start;
				   String toatlStr=Double.toString(sum);
				   debitFld.setText(toatlStr);
				cardLayout.show(Cpanel, "DEBT");

			}}

		});

	}

	private void point_of_sale() {
		JPanel slesPnl = new JPanel();
		slesPnl.setBackground(new Color(204, 204, 102));
		slesPnl.setBounds(10, 56, 1028, 1000);
		slesPnl.setLayout(null);
		Cpanel.add(slesPnl,"SALES");

		JPanel slsPanel = new JPanel();
		slsPanel.setBackground(new Color(204, 204, 153));
		slsPanel.setBounds(10, 22, 838, 651);
		slesPnl.add(slsPanel);
		slsPanel.setLayout(null);

		Label codeLbl = new Label("Item Code");
		codeLbl.setForeground(new Color(0, 102, 0));
		codeLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		codeLbl.setBackground(new Color(204, 204, 153));
		codeLbl.setBounds(20, 10, 131, 30);
		slsPanel.add(codeLbl);

		codFld = new TextField();
		codFld.setForeground(new Color(204, 0, 102));
		codFld.setFont(new Font("Dialog", Font.PLAIN, 19));
		codFld.setBounds(178, 8, 177, 32);
		slsPanel.add(codFld);


		Label labelQntntity = new Label("Item Quantity");
		labelQntntity.setForeground(new Color(0, 102, 0));
		labelQntntity.setFont(new Font("Dialog", Font.PLAIN, 21));
		labelQntntity.setBackground(new Color(204, 204, 153));
		labelQntntity.setBounds(406, 10, 131, 30);
		slsPanel.add(labelQntntity);

		qntityFld = new TextField();
		qntityFld.setForeground(new Color(204, 0, 102));
		qntityFld.setFont(new Font("Dialog", Font.PLAIN, 19));
		qntityFld.setBounds(559, 10, 177, 32);
		slsPanel.add(qntityFld);




		JScrollPane pOsScrollPn = new JScrollPane();
		pOsScrollPn.setBounds(10, 89, 818, 308);
		slsPanel.add(pOsScrollPn);

		poSaeTable = new JTable();
		poSaeTable.setFillsViewportHeight(true);
		poSaeTable.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 16));
		poSaeTable.setShowGrid(false);
		poSaeTable.setForeground(new Color(51, 0, 153));
		poSaeTable.setShowVerticalLines(true);
		pOsScrollPn.setViewportView(poSaeTable);
		//create the model for the column header
				model = new DefaultTableModel(new String [] {"Code","Description","Price","Quantity","Total"},0);
				poSaeTable.setModel(model);//adds the model to the table
		Label amntLbl = new Label("Total ");
		amntLbl.setForeground(new Color(0, 102, 0));
		amntLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		amntLbl.setBackground(new Color(204, 204, 153));
		amntLbl.setBounds(20, 427, 83, 30);
		slsPanel.add(amntLbl);

		totalPesa = new TextField();
		totalPesa.setEditable(false);
		totalPesa.setForeground(new Color(204, 0, 102));
		totalPesa.setFont(new Font("Dialog", Font.PLAIN, 19));
		totalPesa.setBounds(135, 425, 109, 32);
		slsPanel.add(totalPesa);

		Label pd = new Label("Amount");
		pd.setForeground(new Color(0, 102, 0));
		pd.setFont(new Font("Dialog", Font.PLAIN, 21));
		pd.setBackground(new Color(204, 204, 153));
		pd.setBounds(20, 500, 83, 30);
		slsPanel.add(pd);

		givenOut = new TextField();
		givenOut.setForeground(new Color(204, 0, 102));
		givenOut.setFont(new Font("Dialog", Font.PLAIN, 19));
		givenOut.setBounds(135, 498, 109, 32);
		slsPanel.add(givenOut);

		Label balanceLbl = new Label("Balance");
		balanceLbl.setForeground(new Color(0, 102, 0));
		balanceLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		balanceLbl.setBackground(new Color(204, 204, 153));
		balanceLbl.setBounds(20, 566, 83, 30);
		slsPanel.add(balanceLbl);

		balance = new TextField();
		balance.setEditable(false);
		balance.setForeground(new Color(204, 0, 102));
		balance.setFont(new Font("Dialog", Font.PLAIN, 19));
		balance.setBounds(135, 564, 109, 32);
		slsPanel.add(balance);

		Label lbleNm = new Label("Name");
		lbleNm.setForeground(new Color(0, 102, 0));
		lbleNm.setFont(new Font("Dialog", Font.PLAIN, 21));
		lbleNm.setBackground(new Color(204, 204, 153));
		lbleNm.setBounds(514, 427, 83, 30);
		slsPanel.add(lbleNm);

		Label ttleLbl = new Label("Amount");
		ttleLbl.setForeground(new Color(0, 102, 0));
		ttleLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		ttleLbl.setBackground(new Color(204, 204, 153));
		ttleLbl.setBounds(514, 500, 83, 30);
		slsPanel.add(ttleLbl);

		Label lblD = new Label("ID");
		lblD.setForeground(new Color(0, 102, 0));
		lblD.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblD.setBackground(new Color(204, 204, 153));
		lblD.setBounds(514, 566, 83, 30);
		slsPanel.add(lblD);

		nmnFld = new TextField();
		nmnFld.setEditable(false);
		nmnFld.setForeground(new Color(204, 0, 102));
		nmnFld.setFont(new Font("Dialog", Font.PLAIN, 19));
		nmnFld.setBounds(663, 427, 109, 32);
		slsPanel.add(nmnFld);

		 fieldAmount = new TextField();
		 fieldAmount.setEditable(false);
		fieldAmount.setForeground(new Color(204, 0, 102));
		fieldAmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		fieldAmount.setBounds(663, 500, 109, 32);
		slsPanel.add(fieldAmount);

		fieldID = new TextField();
		fieldID.setEditable(false);
		fieldID.setForeground(new Color(204, 0, 102));
		fieldID.setFont(new Font("Dialog", Font.PLAIN, 19));
		fieldID.setBounds(663, 564, 109, 32);
		slsPanel.add(fieldID);

		sezrchButton = new JButton("Find");
		sezrchButton.setForeground(Color.WHITE);
		sezrchButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		sezrchButton.setBackground(new Color(51, 0, 0));
		sezrchButton.setBounds(30, 48, 99, 30);
		slsPanel.add(sezrchButton);

		 addDebitor = new JButton("click here to add a debit");
		addDebitor.setBounds(152, 617, 187, 23);
		slsPanel.add(addDebitor);

	}
}