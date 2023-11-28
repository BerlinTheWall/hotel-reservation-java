package org.bihe.gui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;

public class GuestHotelReservationsPanel extends JPanel {
	private JTable reservationsTable;
	private JButton backButton;
	private JButton cancelButton;

	// -----------------------------Accessor-----------------------

	public JTable getReservationsTable() {
		return reservationsTable;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	/**
	 * Create the panel.
	 */
	public GuestHotelReservationsPanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 56, 977, 429);
		add(scrollPane);

		reservationsTable = new JTable();
		scrollPane.setViewportView(reservationsTable);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		backButton.setBounds(821, 503, 152, 45);
		add(backButton);

		cancelButton = new JButton("Cancel Reservation");
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 19));
		cancelButton.setBounds(20, 496, 232, 55);
		add(cancelButton);

		JLabel reservationsLabel = new JLabel("Your reservations:");
		reservationsLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		reservationsLabel.setBounds(10, 11, 191, 45);
		add(reservationsLabel);
	}
}
