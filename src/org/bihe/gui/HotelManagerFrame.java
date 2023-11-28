package org.bihe.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class HotelManagerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private HotelManagerRoomsPanel hotelManagerRoomsPanel;
	private HotelManagerChangePanel hotelManagerChangePanel;

    //--------------------------------Accessor--------------------------------

	public HotelManagerRoomsPanel getHotelManagerRoomsPanel() {
		return hotelManagerRoomsPanel;
	}

	public HotelManagerChangePanel getHotelManagerChangePanel() {
		return hotelManagerChangePanel;
	}

	/**
	 * Create the frame.
	 */
	public HotelManagerFrame(HotelManagerRoomsPanel hotelManagerRoomsPanel, HotelManagerChangePanel hotelManagerChangePanel) {
		setTitle("Hotel Manger");
		setResizable(false);
		this.hotelManagerChangePanel = hotelManagerChangePanel;
		this.hotelManagerRoomsPanel = hotelManagerRoomsPanel;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 961, 611);
		contentPane.add(tabbedPane);
		tabbedPane.add("Rooms",hotelManagerRoomsPanel);
		tabbedPane.add("Updating rooms",hotelManagerChangePanel);
		
	}

}
