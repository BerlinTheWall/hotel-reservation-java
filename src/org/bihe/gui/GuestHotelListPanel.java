package org.bihe.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class GuestHotelListPanel extends JPanel {
	private JTable hotelstable;
	private JButton reserveButton;
	private JButton backButton;
	private JButton searchButton;
	private JLabel filterLabel;
	private JTextField filterTextField;
	private JComboBox sortComboBox;
	private JLabel sortLabel;

	// -----------------------------Accessor-----------------------

	public JTable getHotelstable() {
		return hotelstable;
	}

	public JButton getReserveButton() {
		return reserveButton;
	}

	public JComboBox getSortComboBox() {
		return sortComboBox;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JTextField getFilterTextField() {
		return filterTextField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	/**
	 * Create the panel.
	 */
	public GuestHotelListPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(34, 102, 944, 390);
		add(scrollPane);

		hotelstable = new JTable();
		scrollPane.setViewportView(hotelstable);

		reserveButton = new JButton("Reserve");
		reserveButton.setFont(new Font("Tahoma", Font.BOLD, 19));
		reserveButton.setBounds(34, 502, 205, 58);
		add(reserveButton);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		backButton.setBounds(839, 513, 139, 40);
		add(backButton);

		searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 19));
		searchButton.setBounds(519, 53, 180, 40);
		add(searchButton);

		JLabel hotelLabel = new JLabel("Hotels:");
		hotelLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		hotelLabel.setBounds(21, 11, 158, 49);
		add(hotelLabel);
		
		filterLabel = new JLabel("Filter:");
		filterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		filterLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		filterLabel.setBounds(243, 54, 86, 39);
		add(filterLabel);
		
		filterTextField = new JTextField();
		filterTextField.setColumns(10);
		filterTextField.setBounds(339, 54, 170, 39);
		add(filterTextField);
		
		sortComboBox = new JComboBox();
		sortComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Hotel name", "Star"}));
		sortComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		sortComboBox.setBounds(811, 58, 139, 35);
		add(sortComboBox);
		
		sortLabel = new JLabel("Sort:");
		sortLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sortLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		sortLabel.setBounds(718, 56, 106, 39);
		add(sortLabel);
	}
}
