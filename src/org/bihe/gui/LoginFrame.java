package org.bihe.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton signUpButton;
	private JButton exitButton;

	// --------------------------------Accessor--------------------------------


	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JButton getLoginButton() {
		return loginButton;
	}

	public JButton getSignUpButton() {
		return signUpButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setResizable(false);
		setTitle("Hotel Room");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 504);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		loginButton = new JButton("Login");
		loginButton.setBackground(new Color(153, 255, 102));
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginButton.setBounds(91, 350, 173, 50);
		contentPane.add(loginButton);

		signUpButton = new JButton("Sign up");
		signUpButton.setBackground(new Color(102, 255, 255));
		signUpButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		signUpButton.setBounds(319, 350, 173, 50);
		contentPane.add(signUpButton);

		exitButton = new JButton("Exit");
		exitButton.setBackground(new Color(255, 51, 0));
		exitButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		exitButton.setBounds(545, 350, 173, 50);
		contentPane.add(exitButton);

		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		usernameTextField.setBounds(343, 134, 238, 58);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordField.setBounds(343, 218, 238, 59);
		contentPane.add(passwordField);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setForeground(new Color(230, 230, 230));
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setBounds(155, 126, 189, 71);
		contentPane.add(usernameLabel);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(new Color(230, 230, 230));
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordLabel.setBounds(165, 208, 159, 69);
		contentPane.add(passwordLabel);
		
		JLabel welcomeLabel = new JLabel("Welcome to Hotel Room");
		welcomeLabel.setForeground(new Color(230, 230, 230));
		welcomeLabel.setBackground(new Color(0, 51, 255));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		welcomeLabel.setBounds(125, 26, 544, 58);
		contentPane.add(welcomeLabel);
	}
}
