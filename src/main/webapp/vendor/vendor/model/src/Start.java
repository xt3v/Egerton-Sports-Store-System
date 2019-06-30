package com.ShopApp;

import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JLabel;

public class Start extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<?> searchField;
    private JTable table,itemTable,tablenotAvailable,unsettledTable,poSaeTable;
	private JTextField idNumber,username,passwrd,nameField,addQuantityFld,codeField,textField
	,derscriptionField,priceField,qntyField,codeEditField,priceEditFld;
	private TextField codFld,qntityFld,totalPesa,balance,givenOut,fieldAmount,fieldID,nmnFld;
	public static String userpassword,comb,dTime,dDate,dateAndTime;
	public static int hr,mnth,dy,sec,min,yr;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public Start() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 0, 892, 900);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(204, 204, 153));
		menuPanel.setBounds(10, 11, 858, 44);
		contentPane.add(menuPanel);
		menuPanel.setLayout(null);

		Button newkeeper = new Button("New Keeper");
		newkeeper.setBounds(10, 10, 90, 24);
		menuPanel.add(newkeeper);

		Button availableItems = new Button("Available Items");
		availableItems.setBounds(114, 10, 90, 24);
		menuPanel.add(availableItems);

		Button mngKeepers = new Button("Manage Keepers");
		mngKeepers.setBounds(210, 10, 96, 24);
		menuPanel.add(mngKeepers);

		Button mngItems = new Button("Manage Items");
		mngItems.setBounds(312, 10, 90, 24);
		menuPanel.add(mngItems);

		Button dailySls = new Button("Daily Sales");
		dailySls.setBounds(408, 10, 90, 24);
		menuPanel.add(dailySls);

		Button unsettled = new Button("Unsettled ");
		unsettled.setBounds(504, 10, 90, 24);
		menuPanel.add(unsettled);

		Button pointOfsale = new Button("Point 0f Sale");
		pointOfsale.setBounds(600, 10, 90, 24);
		menuPanel.add(pointOfsale);

		Button calc = new Button("Calculator");
		calc.setBounds(696, 10, 90, 24);
		menuPanel.add(calc);

		Button help = new Button("Help");
		help.setBounds(792, 10, 56, 24);
		menuPanel.add(help);
		final CardLayout cardLayout = new CardLayout();
		final JPanel Cpanel = new JPanel(cardLayout);
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

		JLabel lblNewLabel = new JLabel("Karibu Umoja");
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
			dDate = dy+"-"+(mnth+1)+"-"+yr;

			 //create time in hours, minute and seconds
		 sec= cal.get(Calendar.SECOND);
		 min = cal.get(Calendar.MINUTE);
		 hr = cal.get(Calendar.HOUR);
//The PM added here is static therefore correct changes should be done for it to be dynamic depending on the 24Hr clock
		 dTime= hr+" :"+min+":"+sec +" PM";
		 dateAndTime= "			"+dDate+"						 "+"	      "+dTime;

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

		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(0, 102, 102));
		headerPanel.setBounds(10, 16, 838, 52);
		beg.add(headerPanel);

		Label label_1 = new Label("The below fields are used to create a new user");
		label_1.setBackground(new Color(0, 102, 102));
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		label_1.setForeground(new Color(255, 255, 255));
		headerPanel.add(label_1);

		JPanel createSubPanel = new JPanel();
		createSubPanel.setBackground(new Color(204, 153, 153));
		createSubPanel.setBounds(20, 79, 803, 383);
		createSubPanel.setLayout(null);
		beg.add(createSubPanel);


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
		username.setBounds(426, 25, 239, 50);
		createSubPanel.add(username);
		username.setColumns(10);

		idNumber = new JTextField();
		idNumber.setForeground(new Color(204, 0, 0));
		idNumber.setFont(new Font("Times New Roman", Font.BOLD, 17));
		idNumber.setColumns(10);
		idNumber.setBounds(426, 100, 239, 50);
		createSubPanel.add(idNumber);

//line 246 corrected from being a text field to password field
		passwrd = new JPasswordField();
		passwrd.setFont(new Font("Times New Roman", Font.BOLD, 17));
		passwrd.setForeground(new Color(204, 0, 0));
		passwrd.setColumns(10);
		passwrd.setBounds(426, 191, 239, 50);
		createSubPanel.add(passwrd);

		JButton btnNewButton = new JButton("CREATE");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(51, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(479, 275, 165, 46);
		createSubPanel.add(btnNewButton);
		    ///////////////////////////////////////////////////////////////////////////////////////////////////////


		JPanel viewKeepers = new JPanel();
		viewKeepers.setBackground(new Color(204, 204, 102));
		viewKeepers.setBounds(10, 56, 1028, 813);
		viewKeepers.setLayout(null);
		Cpanel.add(viewKeepers,"VIEW1");

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(51, 204, 0));
		mainPanel.setBounds(10, 11, 820, 507);
		viewKeepers.add(mainPanel);
		mainPanel.setLayout(null);

		JPanel viewSubPanel = new JPanel();
		viewSubPanel.setForeground(new Color(255, 255, 255));
		viewSubPanel.setBackground(new Color(204, 102, 51));
		viewSubPanel.setBounds(10, 21, 797, 468);
		mainPanel.add(viewSubPanel);
		viewSubPanel.setLayout(null);

		JPanel headerSubPanel = new JPanel();
		headerSubPanel.setBackground(new Color(0, 102, 102));
		headerSubPanel.setBounds(0, 0, 827, 60);
		viewSubPanel.add(headerSubPanel);
		headerSubPanel.setLayout(null);

		Label headLbl = new Label("     VIEW KEEPERS' DETAILS");
		headLbl.setForeground(new Color(255, 255, 255));
		headLbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 22));
		headLbl.setBackground(new Color(0, 102, 102));
		headLbl.setBounds(44, 10, 499, 32);
		headerSubPanel.add(headLbl);

		JPanel detailsPanel = new JPanel();
		detailsPanel.setBackground(new Color(204, 153, 153));
		detailsPanel.setBounds(45, 79, 696, 347);
		viewSubPanel.add(detailsPanel);
		detailsPanel.setLayout(null);

		Label nameLabel = new Label("     NAME");
		 nameLabel.setForeground(Color.BLACK);
		 nameLabel.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		 nameLabel.setBackground(new Color(204, 255, 255));
		 nameLabel.setBounds(10, 121, 216, 32);
		detailsPanel.add( nameLabel);

		Label lablId = new Label("     ID NUMBER");
		lablId.setForeground(Color.BLACK);
		lablId.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		lablId.setBackground(new Color(204, 255, 255));
		lablId.setBounds(10, 192, 216, 32);
		detailsPanel.add(lablId);

		idNumber = new JTextField();
		idNumber.setForeground(new Color(204, 0, 0));
		idNumber.setFont(new Font("Times New Roman", Font.BOLD, 18));
		idNumber.setColumns(10);
		idNumber.setBounds(290, 184, 265, 40);
		detailsPanel.add(idNumber);

		nameField = new JTextField();
		nameField.setForeground(new Color(204, 0, 0));
		nameField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		nameField.setColumns(10);
		nameField.setBounds(290, 113, 265, 40);
		detailsPanel.add(nameField);

		Label label_3 = new Label("  AUTHORIZED KEEPERS");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		label_3.setBackground(new Color(204, 255, 255));
		label_3.setBounds(10, 25, 257, 32);
		detailsPanel.add(label_3);

		searchField = new JComboBox();
		searchField.setForeground(new Color(204, 0, 0));
		searchField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		searchField.setBounds(300, 25, 240, 40);
		detailsPanel.add(searchField);

		JButton btnModify = new JButton("MODIFY");
		btnModify.setForeground(Color.WHITE);
		btnModify.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnModify.setBackground(new Color(51, 0, 0));
		btnModify.setBounds(38, 274, 165, 46);
		detailsPanel.add(btnModify);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnDelete.setBackground(new Color(51, 0, 0));
		btnDelete.setBounds(331, 274, 165, 46);
		detailsPanel.add(btnDelete);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JPanel items = new JPanel();
		items.setBackground(new Color(204, 204, 102));
		items.setBounds(10, 56, 1028, 780);
		items.setLayout(null);
		Cpanel.add(items,"ITEMS");

		JPanel itemPanel = new JPanel();
		itemPanel.setLayout(null);
		itemPanel.setBackground(new Color(204, 153, 153));
		itemPanel.setBounds(10, 59, 797, 603);
		items.add(itemPanel);

		Label codeLabel = new Label("Item Code");
		codeLabel.setForeground(Color.BLACK);
		codeLabel.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		codeLabel.setBackground(new Color(204, 255, 255));
		codeLabel.setBounds(10, 327, 134, 38);
		itemPanel.add(codeLabel);

		codeField = new JTextField();
		codeField.setForeground(new Color(204, 0, 0));
		codeField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		codeField.setColumns(10);
		codeField.setBounds(150, 327, 134, 38);
		itemPanel.add(codeField);

		JButton btnEdit = new JButton("EDIT");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnEdit.setBackground(new Color(51, 0, 0));
		btnEdit.setBounds(680, 371, 107, 38);
		itemPanel.add(btnEdit);

		Label searchLbl = new Label("Search Item");
		itemPanel.add(searchLbl);

		textField = new JTextField();
		textField.setToolTipText("Search by Item description,item code or prices");
		textField.setForeground(new Color(204, 0, 0));
		textField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textField.setColumns(10);
		textField.setBounds(161, 24, 179, 38);
		itemPanel.add(textField);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 777, 248);
		itemPanel.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

		Label itmDeslbl = new Label("Description");
		itmDeslbl.setForeground(Color.BLACK);
		itmDeslbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		itmDeslbl.setBackground(new Color(204, 255, 255));
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
		prcLbl.setBackground(new Color(204, 255, 255));
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
		qntityLbl.setBackground(new Color(204, 255, 255));
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
		editCodeLbl.setBackground(new Color(204, 255, 255));
		editCodeLbl.setBounds(10, 480, 134, 38);
		itemPanel.add(editCodeLbl);

		Label label_2 = new Label("Price");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		label_2.setBackground(new Color(204, 255, 255));
		label_2.setBounds(10, 524, 134, 38);
		itemPanel.add(label_2);

		Label qntylbl = new Label("Quantity");
		qntylbl.setForeground(Color.BLACK);
		qntylbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		qntylbl.setBackground(new Color(204, 255, 255));
		qntylbl.setBounds(341, 524, 134, 38);
		itemPanel.add(qntylbl);

		codeEditField = new JTextField();
		codeEditField.setForeground(new Color(204, 0, 0));
		codeEditField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		codeEditField.setColumns(10);
		codeEditField.setBounds(150, 480, 134, 38);
		itemPanel.add(codeEditField);

		priceEditFld = new JTextField();
		priceEditFld.setForeground(new Color(204, 0, 0));
		priceEditFld.setFont(new Font("Times New Roman", Font.BOLD, 18));
		priceEditFld.setColumns(10);
		priceEditFld.setBounds(150, 524, 134, 38);
		itemPanel.add(priceEditFld);

		Label desAddlbl = new Label("Description");
		desAddlbl.setForeground(Color.BLACK);
		desAddlbl.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 20));
		desAddlbl.setBackground(new Color(204, 255, 255));
		desAddlbl.setBounds(341, 480, 134, 38);
		itemPanel.add(desAddlbl);

		JTextField addDescrptnFld = new JTextField();
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

		JButton btnAdd = new JButton("ADD");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAdd.setBackground(new Color(51, 0, 0));
		btnAdd.setBounds(680, 524, 107, 38);
		itemPanel.add(btnAdd);

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

		Label entryFailure = new Label("Item not successfully added, please check your entries");
		entryFailure.setForeground(new Color(255, 255, 255));
		entryFailure.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		entryFailure.setBackground(new Color(255, 0, 0));
		entryFailure.setBounds(10, 568, 448, 22);
		itemPanel.add(entryFailure);

		Label addSuccess = new Label("       Successfull!!!");
		addSuccess.setBackground(new Color(0, 204, 0));
		addSuccess.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		addSuccess.setBounds(534, 568, 179, 22);
		itemPanel.add(addSuccess);

		Label editSucess = new Label("       Successfull!!!");
		editSucess.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		editSucess.setBackground(new Color(0, 204, 0));
		editSucess.setBounds(608, 10, 179, 22);
		itemPanel.add(editSucess);

		Label editFailure = new Label("Failed to keep changes....invalid entries");
		editFailure.setForeground(Color.WHITE);
		editFailure.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		editFailure.setBackground(Color.RED);
		editFailure.setBounds(457, 40, 330, 22);
		itemPanel.add(editFailure);

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

		Button button = new Button("View All");
		button.setBackground(new Color(204, 153, 204));
		button.setFont(new Font("Sitka Small", Font.PLAIN, 20));
		button.setBounds(10, 10, 180, 32);
		bodyPanel.add(button);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(10, 48, 792, 446);
		bodyPanel.add(scrollPane1);

		itemTable = new JTable();
		scrollPane.setViewportView(itemTable);

		JPanel header1Panel = new JPanel();
		header1Panel.setLayout(null);
		header1Panel.setBackground(new Color(0, 102, 102));
		header1Panel.setBounds(10, 11, 818, 60);
		viewItems.add(header1Panel);

		Label headlbl = new Label("               VIEW STOCK");
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

		JPanel slesPnl = new JPanel();
		slesPnl.setBackground(new Color(204, 204, 102));
		slesPnl.setBounds(10, 56, 1028, 1000);
		slesPnl.setLayout(null);
		Cpanel.add(slesPnl,"SALES");

		JPanel slsPanel = new JPanel();
		slsPanel.setBackground(new Color(204, 204, 153));
		slsPanel.setBounds(10, 11, 838, 651);
		slesPnl.add(slsPanel);
		slsPanel.setLayout(null);

		Label codeLbl = new Label("Item Code");
		codeLbl.setForeground(Color.WHITE);
		codeLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		codeLbl.setBackground(new Color(0, 102, 102));
		codeLbl.setBounds(20, 10, 131, 30);
		slsPanel.add(codeLbl);

		codFld = new TextField();
		codFld.setForeground(new Color(204, 0, 102));
		codFld.setFont(new Font("Dialog", Font.PLAIN, 19));
		codFld.setBounds(178, 8, 177, 32);
		slsPanel.add(codFld);

		Label labelQntntity = new Label("Item Quantity");
		labelQntntity.setForeground(Color.WHITE);
		labelQntntity.setFont(new Font("Dialog", Font.PLAIN, 21));
		labelQntntity.setBackground(new Color(0, 102, 102));
		labelQntntity.setBounds(406, 10, 131, 30);
		slsPanel.add(labelQntntity);

		qntityFld = new TextField();
		qntityFld.setForeground(new Color(204, 0, 102));
		qntityFld.setFont(new Font("Dialog", Font.PLAIN, 19));
		qntityFld.setBounds(559, 10, 177, 32);
		slsPanel.add(qntityFld);

		Label srchLbl = new Label("Search");
		srchLbl.setForeground(Color.WHITE);
		srchLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		srchLbl.setBackground(new Color(0, 102, 102));
		srchLbl.setBounds(20, 66, 109, 30);
		slsPanel.add(srchLbl);

		TextField sachFld = new TextField();
		sachFld.setForeground(new Color(204, 0, 102));
		sachFld.setFont(new Font("Dialog", Font.PLAIN, 19));
		sachFld.setBounds(178, 64, 177, 32);
		slsPanel.add(sachFld);

		JScrollPane pOsScrollPn = new JScrollPane();
		pOsScrollPn.setBounds(10, 117, 818, 280);
		slsPanel.add(pOsScrollPn);

		poSaeTable = new JTable();
		pOsScrollPn.setViewportView(poSaeTable);

		Label amntLbl = new Label("Total ");
		amntLbl.setForeground(Color.WHITE);
		amntLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		amntLbl.setBackground(new Color(0, 102, 102));
		amntLbl.setBounds(20, 427, 83, 30);
		slsPanel.add(amntLbl);

		totalPesa = new TextField();
		totalPesa.setForeground(new Color(204, 0, 102));
		totalPesa.setFont(new Font("Dialog", Font.PLAIN, 19));
		totalPesa.setBounds(135, 425, 109, 32);
		slsPanel.add(totalPesa);

		Label pd = new Label("Amount");
		pd.setForeground(Color.WHITE);
		pd.setFont(new Font("Dialog", Font.PLAIN, 21));
		pd.setBackground(new Color(0, 102, 102));
		pd.setBounds(20, 500, 83, 30);
		slsPanel.add(pd);

		givenOut = new TextField();
		givenOut.setForeground(new Color(204, 0, 102));
		givenOut.setFont(new Font("Dialog", Font.PLAIN, 19));
		givenOut.setBounds(135, 498, 109, 32);
		slsPanel.add(givenOut);

		Label balanceLbl = new Label("Balance");
		balanceLbl.setForeground(Color.WHITE);
		balanceLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		balanceLbl.setBackground(new Color(0, 102, 102));
		balanceLbl.setBounds(20, 566, 83, 30);
		slsPanel.add(balanceLbl);

		balance = new TextField();
		balance.setForeground(new Color(204, 0, 102));
		balance.setFont(new Font("Dialog", Font.PLAIN, 19));
		balance.setBounds(135, 564, 109, 32);
		slsPanel.add(balance);

		Label lbleNm = new Label("Name");
		lbleNm.setForeground(Color.WHITE);
		lbleNm.setFont(new Font("Dialog", Font.PLAIN, 21));
		lbleNm.setBackground(new Color(0, 102, 102));
		lbleNm.setBounds(514, 427, 83, 30);
		slsPanel.add(lbleNm);

		Label ttleLbl = new Label("Amount");
		ttleLbl.setForeground(Color.WHITE);
		ttleLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		ttleLbl.setBackground(new Color(0, 102, 102));
		ttleLbl.setBounds(514, 500, 83, 30);
		slsPanel.add(ttleLbl);

		Label lblD = new Label("     ID");
		lblD.setForeground(Color.WHITE);
		lblD.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblD.setBackground(new Color(0, 102, 102));
		lblD.setBounds(514, 566, 83, 30);
		slsPanel.add(lblD);

		nmnFld = new TextField();
		nmnFld.setForeground(new Color(204, 0, 102));
		nmnFld.setFont(new Font("Dialog", Font.PLAIN, 19));
		nmnFld.setBounds(663, 427, 109, 32);
		slsPanel.add(nmnFld);

		 fieldAmount = new TextField();
		fieldAmount.setForeground(new Color(204, 0, 102));
		fieldAmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		fieldAmount.setBounds(663, 500, 109, 32);
		slsPanel.add(fieldAmount);

		fieldID = new TextField();
		fieldID.setForeground(new Color(204, 0, 102));
		fieldID.setFont(new Font("Dialog", Font.PLAIN, 19));
		fieldID.setBounds(663, 564, 109, 32);
		slsPanel.add(fieldID);
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

		JLabel notYet = new JLabel("                      Unsettled");
		notYet.setForeground(new Color(255, 255, 255));
		notYet.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		notYet.setBounds(10, 11, 689, 42);
		hPanel.add(notYet);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 255, 204));
		panel.setBounds(10, 71, 838, 591);
		unstld.add(panel);
		panel.setLayout(null);

		JScrollPane unsettledScrollPane = new JScrollPane();
		unsettledScrollPane.setBounds(10, 11, 818, 358);
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
		unstldSprt.setBounds(10, 421, 806, 17);
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
		cashFld.setBounds(585, 489, 94, 32);
		panel.add(cashFld);

		Button moreBtn = new Button("MORE");
		moreBtn.setForeground(new Color(255, 255, 255));
		moreBtn.setBackground(new Color(102, 0, 0));
		moreBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		moreBtn.setBounds(723, 444, 78, 30);
		panel.add(moreBtn);

		Button stlBtn = new Button("PAY");
		stlBtn.setForeground(Color.WHITE);
		stlBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		stlBtn.setBackground(new Color(102, 0, 0));
		stlBtn.setBounds(723, 491, 78, 30);
		panel.add(stlBtn);


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

		stlBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Some of the information from this curly bracket is removed
			}
		});
		pointOfsale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(Cpanel, "SALES");
			}
		});


	}
}
