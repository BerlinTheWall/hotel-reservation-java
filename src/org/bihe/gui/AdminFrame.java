package org.bihe.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class AdminFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private AdminHotelsPanel adminHotelsPanel;
	private AdminRequestsPanel adminRequestsPanel;
	private AdminGuestsPanel adminGuestsPanel;
	
	/**
	 * Create the frame.
	 */
	public AdminFrame(AdminHotelsPanel adminHotelsPanel, AdminRequestsPanel adminRequestsPanel, AdminGuestsPanel adminGuestsPanel) {
		setTitle("Admin");
		setResizable(false);
		this.adminHotelsPanel = adminHotelsPanel;
		this.adminRequestsPanel = adminRequestsPanel;
		this.adminGuestsPanel = adminGuestsPanel;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 977, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 961, 611);
		contentPane.add(tabbedPane);
		tabbedPane.add("Hotels",adminHotelsPanel);
		tabbedPane.add("Requests",adminRequestsPanel);
		tabbedPane.add("Guests",adminGuestsPanel);
		
	}
    //--------------------------------Accessor--------------------------------

	public AdminHotelsPanel getAdminHotelsPanel() {
		return adminHotelsPanel;
	}

	public AdminRequestsPanel getAdminRequestsPanel() {
		return adminRequestsPanel;
	}

	public AdminGuestsPanel getAdminGuestsPanel() {
		return adminGuestsPanel;
	}
	
	
}
