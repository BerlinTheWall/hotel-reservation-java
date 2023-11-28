package org.bihe.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ReserveFrame extends JFrame {

	private JTable roomstable;
	private JButton SubmitReserveButton;
	private JButton backButton;
	private JLabel dayLabel;
	private JButton searchButton;
	private JTextField daysTextField;
	private JLabel filterLabel;
	private JTextField filterTextField;
	private JComboBox sortComboBox;
	private JLabel sortLabel;

	// -----------------------------Accessor-----------------------

	public JTable getRoomstable() {
		return roomstable;
	}

	public JButton getSubmitReserveButton() {
		return SubmitReserveButton;
	}

	public JButton getBackButton() {
		return backButton;
	}
	
	public JComboBox getSortComboBox() {
		return sortComboBox;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JTextField getDaysTextField() {
		return daysTextField;
	}

	public JTextField getFilterTextField() {
		return filterTextField;
	}

	/**
	 * Create the panel.
	 */
	public ReserveFrame() {
		setResizable(false);
		setTitle("Reserve\r\n");
		setBounds(32, 88, 1011, 634);
		getContentPane().setBackground(Color.ORANGE);
		setBackground(Color.ORANGE);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(32, 88, 926, 388);
		getContentPane().add(scrollPane);

		roomstable = new JTable();
		scrollPane.setViewportView(roomstable);

		SubmitReserveButton = new JButton("Submit & Reserve");
		SubmitReserveButton.setFont(new Font("Tahoma", Font.BOLD, 19));
		SubmitReserveButton.setBounds(423, 485, 247, 71);
		getContentPane().add(SubmitReserveButton);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		backButton.setBounds(815, 524, 143, 46);
		getContentPane().add(backButton);
		
		daysTextField = new JTextField();
		daysTextField.setHorizontalAlignment(SwingConstants.CENTER);
		daysTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		daysTextField.setBounds(264, 501, 114, 38);
		getContentPane().add(daysTextField);
		daysTextField.setColumns(10);
		
		JLabel chooseRoomLabel = new JLabel("Choose your room:");
		chooseRoomLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		chooseRoomLabel.setBounds(10, 11, 185, 46);
		getContentPane().add(chooseRoomLabel);
		
		dayLabel = new JLabel("Number of days of staying: ");
		dayLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		dayLabel.setBounds(36, 501, 247, 38);
		getContentPane().add(dayLabel);
		
		filterLabel = new JLabel("Filter:");
		filterLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		filterLabel.setBounds(226, 39, 80, 38);
		getContentPane().add(filterLabel);
		
		filterTextField = new JTextField();
		filterTextField.setHorizontalAlignment(SwingConstants.CENTER);
		filterTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		filterTextField.setColumns(10);
		filterTextField.setBounds(302, 39, 176, 38);
		getContentPane().add(filterTextField);
		
		searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchButton.setBounds(509, 39, 154, 38);
		getContentPane().add(searchButton);
		
		sortComboBox = new JComboBox();
		sortComboBox.setModel(new DefaultComboBoxModel(new String[] {"Room Number", "Price"}));
		sortComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		sortComboBox.setBounds(815, 41, 139, 35);
		getContentPane().add(sortComboBox);
		
		sortLabel = new JLabel("Sort:");
		sortLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sortLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		sortLabel.setBounds(710, 40, 106, 39);
		getContentPane().add(sortLabel);

	}
}
