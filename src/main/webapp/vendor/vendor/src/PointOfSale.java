package com.ShopApp;

import javax.swing.*;
import java.awt.*;

public class PointOfSale extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JTable poSaeTable;

	/**
	 * Create the panel.
	 */
	public PointOfSale() {
		setBackground(new Color(255, 255, 204));
		setLayout(null);

		JPanel slsPanel = new JPanel();
		slsPanel.setBackground(new Color(204, 204, 153));
		slsPanel.setBounds(10, 11, 782, 629);
		add(slsPanel);
		slsPanel.setLayout(null);

		Label codeLbl = new Label("Item Code");
		codeLbl.setForeground(Color.WHITE);
		codeLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		codeLbl.setBackground(new Color(0, 102, 102));
		codeLbl.setBounds(20, 10, 131, 30);
		slsPanel.add(codeLbl);

		TextField codFld = new TextField();
		codFld.setForeground(new Color(204, 0, 102));
		codFld.setFont(new Font("Dialog", Font.PLAIN, 19));
		codFld.setBounds(178, 8, 177, 32);
		slsPanel.add(codFld);

		Label qntityLbl = new Label("Item Quantity");
		qntityLbl.setForeground(Color.WHITE);
		qntityLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		qntityLbl.setBackground(new Color(0, 102, 102));
		qntityLbl.setBounds(406, 10, 131, 30);
		slsPanel.add(qntityLbl);

		TextField qntityFld = new TextField();
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
		pOsScrollPn.setBounds(10, 117, 762, 280);
		slsPanel.add(pOsScrollPn);

		poSaeTable = new JTable();
		pOsScrollPn.setViewportView(poSaeTable);

		Label amntLbl = new Label("Total ");
		amntLbl.setForeground(Color.WHITE);
		amntLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		amntLbl.setBackground(new Color(0, 102, 102));
		amntLbl.setBounds(20, 427, 83, 30);
		slsPanel.add(amntLbl);

		TextField totalPesa = new TextField();
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

		TextField givenOut = new TextField();
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

		TextField balance = new TextField();
		balance.setForeground(new Color(204, 0, 102));
		balance.setFont(new Font("Dialog", Font.PLAIN, 19));
		balance.setBounds(135, 564, 109, 32);
		slsPanel.add(balance);

		Label lbleNm = new Label("Name");
		lbleNm.setForeground(Color.WHITE);
		lbleNm.setFont(new Font("Dialog", Font.PLAIN, 21));
		lbleNm.setBackground(new Color(0, 102, 102));
		lbleNm.setBounds(406, 427, 83, 30);
		slsPanel.add(lbleNm);

		Label ttleLbl = new Label("Amount");
		ttleLbl.setForeground(Color.WHITE);
		ttleLbl.setFont(new Font("Dialog", Font.PLAIN, 21));
		ttleLbl.setBackground(new Color(0, 102, 102));
		ttleLbl.setBounds(406, 500, 83, 30);
		slsPanel.add(ttleLbl);

		Label lblD = new Label("     ID");
		lblD.setForeground(Color.WHITE);
		lblD.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblD.setBackground(new Color(0, 102, 102));
		lblD.setBounds(406, 566, 83, 30);
		slsPanel.add(lblD);

		TextField nameField = new TextField();
		nameField.setForeground(new Color(204, 0, 102));
		nameField.setFont(new Font("Dialog", Font.PLAIN, 19));
		nameField.setBounds(569, 425, 109, 32);
		slsPanel.add(nameField);

		TextField fieldAmount = new TextField();
		fieldAmount.setForeground(new Color(204, 0, 102));
		fieldAmount.setFont(new Font("Dialog", Font.PLAIN, 19));
		fieldAmount.setBounds(569, 500, 109, 32);
		slsPanel.add(fieldAmount);

		TextField fieldID = new TextField();
		fieldID.setForeground(new Color(204, 0, 102));
		fieldID.setFont(new Font("Dialog", Font.PLAIN, 19));
		fieldID.setBounds(569, 564, 109, 32);
		slsPanel.add(fieldID);

	}
}