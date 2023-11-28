package org.bihe.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.bihe.bean.Guest;
import org.bihe.bean.HotelManager;
import org.bihe.bean.Signing;
import org.bihe.dao.AdminDaoImpl;
import org.bihe.dao.GuestDaoImpl;
import org.bihe.dao.SigningDaoImpl;
import org.bihe.exception.IsEmptyException;
import org.bihe.exception.WrongInputException;
import org.bihe.gui.AdminFrame;
import org.bihe.gui.AdminGuestsPanel;
import org.bihe.gui.AdminHotelsPanel;
import org.bihe.gui.AdminRequestsPanel;
import org.bihe.gui.GuestFrame;
import org.bihe.gui.GuestHotelListPanel;
import org.bihe.gui.GuestHotelReservationsPanel;
import org.bihe.gui.HotelManagerChangePanel;
import org.bihe.gui.HotelManagerFrame;
import org.bihe.gui.HotelManagerRoomsPanel;
import org.bihe.gui.LoginFrame;
import org.bihe.gui.SignUpFrame;
import org.bihe.gui.SignUpGuestPanel;
import org.bihe.gui.SignUpHotelRequestPanel;

public class LoginController {
	private LoginFrame loginFrame;
	private HashMap<String, Guest> guestUserPass = new HashMap<String, Guest>();
	private HashMap<String, HotelManager> hotelManagerUserPass = new HashMap<String, HotelManager>();

	// ---Constructor---

	public LoginController(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
		initLoginController();
	}

	// ---Initialize of login Controller---

	private void initLoginController() {
		loginFrame.getLoginButton().addActionListener(e -> actionForLoginButton());
		actionForSignUpButton(loginFrame.getSignUpButton(), loginFrame);
		loginFrame.getExitButton().addActionListener(e -> actionForExitButton());
		initializeHashMaps();
	}

	// ---Initialize of guest & hotelManager hashMaps---

	public void initializeHashMaps() {
		for (Signing signing : SigningDaoImpl.getInstance().getAllElements()) {
			if (signing.getObject() instanceof Guest) {
				guestUserPass.put(signing.getUsername(), (Guest) signing.getObject());
			} else if (signing.getObject() instanceof HotelManager) {
				hotelManagerUserPass.put(signing.getUsername(), (HotelManager) signing.getObject());
			}
		}
	}

	/**
	 * Action for login button for logging into your account
	 */
	public void actionForLoginButton() {
		try {
			if (loginFrame.getUsernameTextField().getText().isEmpty()
					|| loginFrame.getPasswordField().getText().isEmpty()) {
				IsEmptyException ime = new IsEmptyException("Please fill all the fields");
				throw ime;
			}
			if (checkUserPass()) {
				if (guestUserPass.get(loginFrame.getUsernameTextField().getText()) != null) {
					Guest guest = guestUserPass.get(loginFrame.getUsernameTextField().getText());
					GuestHotelListPanel guestHotelListPanel = new GuestHotelListPanel();
					GuestHotelReservationsPanel guestHotelReservationsPanel = new GuestHotelReservationsPanel();
					loginFrame.dispose();
					GuestFrame guestFrame = new GuestFrame(guestHotelListPanel, guestHotelReservationsPanel);
					GuestController guestController = new GuestController(guestFrame, guest);
					guestFrame.setVisible(true);
				} else if (hotelManagerUserPass.get(loginFrame.getUsernameTextField().getText()) != null) {
					HotelManager hotelManager = hotelManagerUserPass.get(loginFrame.getUsernameTextField().getText());
					if (hotelManager.isAccepted()) {
						HotelManagerRoomsPanel hotelManagerRoomsPanel = new HotelManagerRoomsPanel();
						HotelManagerChangePanel hotelManagerChangePanel = new HotelManagerChangePanel();
						loginFrame.dispose();
						HotelManagerFrame hotelManagerFrame = new HotelManagerFrame(hotelManagerRoomsPanel,
								hotelManagerChangePanel);
						HotelManagerController hotelManagerController = new HotelManagerController(hotelManagerFrame,
								hotelManager);
						hotelManagerFrame.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "You are not accepted from admin.", "Not Accepted",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {
					AdminHotelsPanel adminHotelsPanel = new AdminHotelsPanel();
					AdminGuestsPanel adminGuestsPanel = new AdminGuestsPanel();
					AdminRequestsPanel adminRequestsPanel = new AdminRequestsPanel();
					loginFrame.dispose();
					AdminFrame adminFrame = new AdminFrame(adminHotelsPanel, adminRequestsPanel, adminGuestsPanel);
					AdminController adminController = new AdminController(adminFrame);
					adminFrame.setVisible(true);
				}

			} else {
				WrongInputException wie = new WrongInputException("Your username or password is wrong");
				throw wie;
			}
		} catch (WrongInputException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IsEmptyException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Action for signUp button for signing up a new account
	 */
	public void actionForSignUpButton(JButton button, JFrame frame) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SignUpHotelRequestPanel h = new SignUpHotelRequestPanel();
				SignUpGuestPanel g = new SignUpGuestPanel();
				frame.dispose();
				SignUpFrame signUpFrame = new SignUpFrame(g, h);
				signUpFrame.setVisible(true);
				SignUpController signUpController = new SignUpController(signUpFrame);
			}
		});

	}

	/**
	 * Action for exit button for exiting from program
	 */
	public void actionForExitButton() {
		System.exit(0);
	}

	/**
	 * method for finding and checking if the username & password are correct
	 */
	public boolean checkUserPass() {
		for (Signing signing : SigningDaoImpl.getInstance().getAllElements()) {
			if (signing.getUsername().equals(loginFrame.getUsernameTextField().getText())
					&& signing.getPassword().equals(loginFrame.getPasswordField().getText())) {
				return true;
			}

		}
		return false;
	}

}
