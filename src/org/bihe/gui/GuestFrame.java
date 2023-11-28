package org.bihe.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class GuestFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private GuestHotelListPanel guestHotelListPanel;
	private GuestHotelReservationsPanel guestHotelReservationsPanel;
	
    //-----------------------------Accessor-----------------------

	public GuestHotelListPanel getGuestHotelListPanel() {
		return guestHotelListPanel;
	}


	public GuestHotelReservationsPanel getGuestHotelReservationsPanel() {
		return guestHotelReservationsPanel;
	}


	/**
	 * Create the frame.
	 */
	public GuestFrame(GuestHotelListPanel guestHotelListPanel, GuestHotelReservationsPanel guestHotelReservationsPanel) {
		setTitle("Guest");
		setResizable(false);
		this.guestHotelListPanel = guestHotelListPanel;
		this.guestHotelReservationsPanel = guestHotelReservationsPanel;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1013, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1006, 600);
		contentPane.add(tabbedPane);
		tabbedPane.add("Hotels",guestHotelListPanel);
		tabbedPane.add("My reservations",guestHotelReservationsPanel);
		
	}

}
