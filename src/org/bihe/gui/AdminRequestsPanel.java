package org.bihe.gui;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class AdminRequestsPanel extends JPanel {
	private JButton acceptButton;
	private JButton rejectButton;
	private JTable requestTable;
	private JScrollPane scrollPane;
	private JButton backButton;

	// -----------------------------Accessor-----------------------

	public JButton getAcceptButton() {
		return acceptButton;
	}

	public JButton getRejectButton() {
		return rejectButton;
	}

	public JTable getRequestTable() {
		return requestTable;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JButton getBackButton() {
		return backButton;
	}

	/**
	 * Create the panel.
	 */
	public AdminRequestsPanel() {
		setBackground(SystemColor.textHighlight);
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(54, 59, 823, 437);
		add(scrollPane);

		requestTable = new JTable();
		scrollPane.setViewportView(requestTable);

		acceptButton = new JButton("Accept");
		acceptButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		acceptButton.setBounds(54, 507, 184, 52);
		add(acceptButton);

		rejectButton = new JButton("Reject");
		rejectButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		rejectButton.setBounds(277, 507, 184, 52);
		add(rejectButton);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		backButton.setBounds(726, 514, 151, 40);
		add(backButton);

		JLabel requestLabel = new JLabel("Request forms: ");
		requestLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		requestLabel.setBounds(30, 11, 211, 52);
		add(requestLabel);

	}

}
