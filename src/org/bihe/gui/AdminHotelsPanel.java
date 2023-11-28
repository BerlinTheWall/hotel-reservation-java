package org.bihe.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;

public class AdminHotelsPanel extends JPanel {

	private JTable hoteltable;
	private JTextField hotelNameTextField;
	private JButton searchButton;
	private JComboBox sortComboBox;
	private JScrollPane scrollPane;
	private JButton backButton;
	private JLabel sortLabel;

	// -----------------------------Accessor-----------------------

	public JTable getHoteltable() {
		return hoteltable;
	}

	public JTextField getHotelNameTextField() {
		return hotelNameTextField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JComboBox getSortComboBox() {
		return sortComboBox;
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
	public AdminHotelsPanel() {
		setBackground(SystemColor.scrollbar);
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 107, 896, 412);
		add(scrollPane);
		
		hoteltable = new JTable();
		scrollPane.setViewportView(hoteltable);
		hoteltable.setBackground(Color.WHITE);
		hoteltable.setFont(new Font("Tahoma", Font.PLAIN, 11));

		searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 19));
		searchButton.setBounds(404, 57, 160, 39);
		add(searchButton);

		hotelNameTextField = new JTextField();
		hotelNameTextField.setBounds(214, 57, 170, 39);
		add(hotelNameTextField);
		hotelNameTextField.setColumns(10);

		sortComboBox = new JComboBox();
		sortComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		sortComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Hotel name", "City", "Star"}));
		sortComboBox.setBounds(732, 61, 139, 35);
		add(sortComboBox);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		backButton.setBounds(768, 530, 127, 39);
		add(backButton);

		JLabel hotelFilterLabel = new JLabel("Hotel filters:");
		hotelFilterLabel.setHorizontalAlignment(SwingConstants.LEFT);
		hotelFilterLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		hotelFilterLabel.setBounds(20, 11, 147, 39);
		add(hotelFilterLabel);

		JLabel hotelNameFilterLabel = new JLabel("Filter by name:");
		hotelNameFilterLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		hotelNameFilterLabel.setBounds(66, 57, 170, 39);
		add(hotelNameFilterLabel);
		
		sortLabel = new JLabel("Sort:");
		sortLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sortLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		sortLabel.setBounds(629, 59, 106, 39);
		add(sortLabel);
	}
}
