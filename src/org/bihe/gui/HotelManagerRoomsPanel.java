package org.bihe.gui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class HotelManagerRoomsPanel extends JPanel {
	private JTable roomsTable;
	private JButton backButton;
	private JLabel filterLabel;
	private JTextField filterTextField;
	private JButton searchButton;

	// -----------------------------Accessor-----------------------

	public JTable getRoomTable() {
		return roomsTable;
	}

	public JTable getRoomsTable() {
		return roomsTable;
	}

	public JTextField getFilterTextField() {
		return filterTextField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getBackButton() {
		return backButton;
	}

	/**
	 * Create the panel.
	 */
	public HotelManagerRoomsPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 75, 879, 393);
		add(scrollPane);

		roomsTable = new JTable();
		scrollPane.setViewportView(roomsTable);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		backButton.setBounds(756, 479, 133, 43);
		add(backButton);

		JLabel roomsLabel = new JLabel("Rooms:");
		roomsLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		roomsLabel.setBounds(10, 0, 86, 43);
		add(roomsLabel);

		filterLabel = new JLabel("Filter:");
		filterLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		filterLabel.setBounds(302, 21, 86, 43);
		add(filterLabel);

		filterTextField = new JTextField();
		filterTextField.setColumns(10);
		filterTextField.setBounds(378, 25, 170, 39);
		add(filterTextField);

		searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 19));
		searchButton.setBounds(586, 21, 170, 43);
		add(searchButton);

	}
}
