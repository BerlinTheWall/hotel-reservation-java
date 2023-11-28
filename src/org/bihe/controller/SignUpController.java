package org.bihe.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.bihe.bean.Address;
import org.bihe.bean.Guest;
import org.bihe.bean.Hotel;
import org.bihe.bean.HotelManager;
import org.bihe.bean.RequestForm;
import org.bihe.bean.Room;
import org.bihe.bean.Signing;
import org.bihe.dao.AddressDaoImpl;
import org.bihe.dao.AdminDaoImpl;
import org.bihe.dao.GuestDaoImpl;
import org.bihe.dao.HotelDaoImpl;
import org.bihe.dao.HotelManagerDaoImpl;
import org.bihe.dao.RequestFormDaoImpl;
import org.bihe.dao.SigningDaoImpl;
import org.bihe.exception.IsEmptyException;
import org.bihe.exception.duplicatedUserException;
import org.bihe.gui.LoginFrame;
import org.bihe.gui.SignUpFrame;
import org.bihe.gui.SignUpGuestPanel;
import org.bihe.gui.SignUpHotelRequestPanel;

public class SignUpController {
	private SignUpFrame signUpFrame;
	private SignUpGuestPanel signUpGuestPanel;
	private SignUpHotelRequestPanel signUphotelRequestPanel;

	// ---Constructor---

	public SignUpController(SignUpFrame signUpFrame) {

		this.signUpFrame = signUpFrame;
		this.signUpGuestPanel = signUpFrame.getSignUpGuestPanel();
		this.signUphotelRequestPanel = signUpFrame.getSignUpHotelRequestPanel();
		initSignUpController();
	}

	// ---Initialize of signUp Controller---

	private void initSignUpController() {
		signUpGuestPanel.getSignUpSubmitButton().addActionListener(e -> actionForSubmitButton());
		actionForBackButton(signUpGuestPanel.getBackButton(), signUpFrame);
		signUphotelRequestPanel.getSendRequestButton().addActionListener(e -> ActionForSentRequestButton());
		actionForBackButton(signUphotelRequestPanel.getBackButton(), signUpFrame);
	}

	/**
	 * Action for submit button for signing up a new guest
	 */
	public void actionForSubmitButton() {
		try {
			if (signUpGuestPanel.getFirstnameTextField().getText().isEmpty()
					|| signUpGuestPanel.getLastnameTextField().getText().isEmpty()
					|| signUpGuestPanel.getUsernameTextField().getText().isEmpty()
					|| signUpGuestPanel.getPasswordTextField().getText().isEmpty()
					|| signUpGuestPanel.getNationalIdTextField().getText().isEmpty()
					|| signUpGuestPanel.getPhoneNoTextField().getText().isEmpty()
					|| signUpGuestPanel.getCityTextField().getText().isEmpty()
					|| signUpGuestPanel.getAddressTextField().getText().isEmpty()) {
				IsEmptyException ime = new IsEmptyException("Please fill all the fields");
				throw ime;
			}
			if (!checkDuplicatedUser(signUpGuestPanel.getUsernameTextField().getText())) {
				duplicatedUserException e = new duplicatedUserException(
						"Your username is already taken\nPlease Choose another username");
				throw e;
			}
			String firstName = signUpGuestPanel.getFirstnameTextField().getText();
			String lastName = signUpGuestPanel.getLastnameTextField().getText();
			String username = signUpGuestPanel.getUsernameTextField().getText();
			String password = signUpGuestPanel.getPasswordTextField().getText();
			String nationalId = signUpGuestPanel.getNationalIdTextField().getText();
			String phoneNo = signUpGuestPanel.getPhoneNoTextField().getText();
			String city = signUpGuestPanel.getCityTextField().getText();
			String address = signUpGuestPanel.getAddressTextField().getText();
			Address ad = new Address(city, address);
			Guest guest = new Guest(firstName, lastName, nationalId, ad, phoneNo);
			Signing<Guest> signingGuest = new Signing<Guest>(username, password, guest);
			AddressDaoImpl.getInstance().addElement(ad);
			GuestDaoImpl.getInstance().addElement(guest);
			SigningDaoImpl.getInstance().addElement(signingGuest);
			JOptionPane.showMessageDialog(null, "Successful SignUp", "Congratulations", JOptionPane.PLAIN_MESSAGE);
		} catch (IsEmptyException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (duplicatedUserException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Action for send request button for creating a new hotel request form
	 */
	public void ActionForSentRequestButton() {
		try {
			if (signUphotelRequestPanel.getFirstnameTextField().getText().isEmpty()
					|| signUphotelRequestPanel.getLastnameTextField().getText().isEmpty()
					|| signUphotelRequestPanel.getHotelnameTextField().getText().isEmpty()
					|| signUphotelRequestPanel.getCityTextField().getText().isEmpty()
					|| signUphotelRequestPanel.getPhoneNoTextField().getText().isEmpty()
					|| signUphotelRequestPanel.getUsernameTextField().getText().isEmpty()
					|| signUphotelRequestPanel.getPasswordTextField().getText().isEmpty()
					|| signUphotelRequestPanel.getNationalIdTextField().getText().isEmpty()
					|| signUphotelRequestPanel.getAddressTextField().getText().isEmpty()) {
				IsEmptyException ime = new IsEmptyException("Please fill all the fields");
				throw ime;
			}
			if (!checkDuplicatedUser(signUphotelRequestPanel.getUsernameTextField().getText())) {
				duplicatedUserException e = new duplicatedUserException(
						"Your username is already taken\nPlease Choose another username");
				throw e;
			}
			String firstName = signUphotelRequestPanel.getFirstnameTextField().getText();
			String lastName = signUphotelRequestPanel.getLastnameTextField().getText();
			String hotelName = signUphotelRequestPanel.getHotelnameTextField().getText();
			String city = signUphotelRequestPanel.getCityTextField().getText();
			String phoneNo = signUphotelRequestPanel.getPhoneNoTextField().getText();
			String username = signUphotelRequestPanel.getUsernameTextField().getText();
			String password = signUphotelRequestPanel.getPasswordTextField().getText();
			String address = signUphotelRequestPanel.getAddressTextField().getText();
			String nationalId = signUphotelRequestPanel.getNationalIdTextField().getText();
			int roomNo = (Integer) signUphotelRequestPanel.getRoomNumberSpinner().getValue();
			int star = (Integer) signUphotelRequestPanel.getStarSpinner().getValue();
			boolean breakfast = signUphotelRequestPanel.getBreakfastCheckBox().isSelected();
			boolean lunch = signUphotelRequestPanel.getLunchCheckBox().isSelected();
			boolean dinner = signUphotelRequestPanel.getDinnerCheckBox().isSelected();
			boolean pool = signUphotelRequestPanel.getSwimmingPoolCheckBox().isSelected();
			boolean gym = signUphotelRequestPanel.getGymCheckBox().isSelected();
			boolean wifi = signUphotelRequestPanel.getWifiCheckBox().isSelected();
			Address ad = new Address(city, address);
			Hotel hotel = new Hotel(hotelName, roomNo, ad, phoneNo, star);
			HotelManager hotelManager = new HotelManager(hotel, firstName, lastName, nationalId, phoneNo);
			RequestForm requestForm = new RequestForm(breakfast, lunch, dinner, pool, gym, wifi, hotelManager);
			Signing<HotelManager> signingHotelManger = new Signing<HotelManager>(username, password, hotelManager);
			AddressDaoImpl.getInstance().addElement(ad);
			HotelManagerDaoImpl.getInstance().addElement(hotelManager);
			HotelDaoImpl.getInstance().addElement(hotel);
			RequestFormDaoImpl.getInstance().addElement(requestForm);
			SigningDaoImpl.getInstance().addElement(signingHotelManger);
			JOptionPane.showMessageDialog(null,
					"Your request was sent successfully\nPlease wait for the admin's response", "Congratulations",
					JOptionPane.PLAIN_MESSAGE);
		} catch (IsEmptyException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (duplicatedUserException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Action for back button
	 */
	public static void actionForBackButton(JButton button, JFrame frame) {
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				LoginController loginController = new LoginController(loginFrame);

			}
		});
	}

	/**
	 * method for checking duplicated usernames
	 */
	public boolean checkDuplicatedUser(String username) {
		for (Signing user : SigningDaoImpl.getInstance().getAllElements()) {
			if (user.getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}

}