package org.bihe.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class SignUpFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private SignUpHotelRequestPanel signUpHotelRequestPanel;
	private SignUpGuestPanel signUpGuestPanel;
	
    //-----------------------------Accessor-----------------------

	public SignUpHotelRequestPanel getSignUpHotelRequestPanel() {
		return signUpHotelRequestPanel;
	}

	public SignUpGuestPanel getSignUpGuestPanel() {
		return signUpGuestPanel;
	}

	/**
	 * Create the frame.
	 */
	public SignUpFrame(SignUpGuestPanel guestSignUp, SignUpHotelRequestPanel hotelRequestForm) {
		setTitle("SignUp");
		setResizable(false);
		this.signUpGuestPanel = guestSignUp;
		this.signUpHotelRequestPanel = hotelRequestForm;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 965, 634);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 949, 595);
		contentPane.add(tabbedPane);
		tabbedPane.add("Guest SignUp", guestSignUp);
		tabbedPane.add("Hotel Request Form", hotelRequestForm);
	}
}
