package org.bihe.gui;

import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class AdminGuestsPanel extends JPanel {
	private JTextField guestNameTextField;
	private JComboBox hotelGuestComboBox;
	private JButton searchButton;
	private JTable guestTable;
	private JButton backButton;

	// -----------------------------Accessor-----------------------

	public JTextField getGuestNameTextField() {
		return guestNameTextField;
	}


	public JComboBox getHotelGuestComboBox() {
		return hotelGuestComboBox;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JTable getGuestTable() {
		return guestTable;
	}

	public JButton getBackButton() {
		return backButton;
	}

	/**
	 * Create the panel.
	 */
	public AdminGuestsPanel() {
		setBackground(SystemColor.inactiveCaption);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(27, 121, 902, 404);
		add(scrollPane);

		guestTable = new JTable();
		scrollPane.setViewportView(guestTable);

		guestNameTextField = new JTextField();
		guestNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		guestNameTextField.setColumns(10);
		guestNameTextField.setBounds(219, 71, 170, 39);
		add(guestNameTextField);

		hotelGuestComboBox = new JComboBox();
		hotelGuestComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Last name", "City"}));
		hotelGuestComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		hotelGuestComboBox.setBounds(737, 71, 156, 39);
		add(hotelGuestComboBox);

		searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 19));
		searchButton.setBounds(397, 71, 175, 39);
		add(searchButton);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		backButton.setBounds(769, 536, 124, 39);
		add(backButton);

		JLabel guestFilterLabel = new JLabel("Guest filters:");
		guestFilterLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		guestFilterLabel.setBounds(27, 11, 198, 51);
		add(guestFilterLabel);

		JLabel guestNameFilterLabel = new JLabel("Filter by name:");
		guestNameFilterLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		guestNameFilterLabel.setBounds(77, 71, 149, 39);
		add(guestNameFilterLabel);

		JLabel sortLabel = new JLabel("Sort:");
		sortLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sortLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		sortLabel.setBounds(621, 71, 106, 39);
		add(sortLabel);

	}
}
