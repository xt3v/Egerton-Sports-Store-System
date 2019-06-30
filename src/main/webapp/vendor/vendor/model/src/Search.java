package com.ShopApp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Search extends Duka1 {

	JFrame searchframe;
	public JTable searchTable;
	public DefaultTableModel resultModel;
	public TextField findField;
	public String fCode;
	public Search() {
		initialize1();

	}


	private void initialize1() {
		searchframe = new JFrame();
		searchframe.setBounds(100, 100, 750, 438);
		searchframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		searchTable.setShowVerticalLines(false);
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


				}
		   	}
		   });

		  findField.setForeground(new Color(204, 0, 102));
		  findField.setFont(new Font("Dialog", Font.PLAIN, 19));
		  findField.setBounds(10, 10, 177, 32);
		  searchPanel.add(findField);
		  searchTable.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){

					//get the selected row and display the selected data in various text_fields
					Duka1 d1 = new Duka1();
					d1.initialize();
					int ia = searchTable.getSelectedRow();
					 codFld.setText(resultModel.getValueAt(ia,0).toString());
					 codFld.requestFocus();
					 searchframe.dispose();

				}
			});
	}

}
