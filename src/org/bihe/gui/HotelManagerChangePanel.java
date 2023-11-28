package org.bihe.gui;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import org.bihe.Enum.RoomType;

public class HotelManagerChangePanel extends JPanel {
	private JComboBox roomNoComboBox;
	private JTextField priceTextField;
	private JButton backButton;
	private JButton applyButton;
	private JCheckBox swimmingPoolCheckBox;
	private JCheckBox breakfastCheckBox;
	private JCheckBox lunchCheckBox;
	private JCheckBox dinnerCheckBox;
	private JCheckBox gymCheckBox;
	private JCheckBox wifiCheckBox;
	private JComboBox roomTypeComboBox;

    //-----------------------------Accessor-----------------------

	public JComboBox getRoomNoComboBox() {
		return roomNoComboBox;
	}

	public JTextField getPriceTextField() {
		return priceTextField;
	}

	public JComboBox getRoomTypeComboBox() {
		return roomTypeComboBox;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public JButton getApplyButton() {
		return applyButton;
	}

	public JCheckBox getSwimmingPoolCheckBox() {
		return swimmingPoolCheckBox;
	}

	public JCheckBox getBreakfastCheckBox() {
		return breakfastCheckBox;
	}

	public JCheckBox getLunchCheckBox() {
		return lunchCheckBox;
	}

	public JCheckBox getDinnerCheckBox() {
		return dinnerCheckBox;
	}

	public JCheckBox getGymCheckBox() {
		return gymCheckBox;
	}

	public JCheckBox getWifiCheckBox() {
		return wifiCheckBox;
	}

	/**
	 * Create the panel.
	 */
	public HotelManagerChangePanel() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);

		breakfastCheckBox = new JCheckBox("Breakfast");
		breakfastCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		breakfastCheckBox.setBounds(64, 204, 146, 44);
		add(breakfastCheckBox);

		lunchCheckBox = new JCheckBox("Lunch");
		lunchCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lunchCheckBox.setBounds(314, 204, 146, 44);
		add(lunchCheckBox);

		dinnerCheckBox = new JCheckBox("Dinner");
		dinnerCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		dinnerCheckBox.setBounds(576, 204, 146, 44);
		add(dinnerCheckBox);

		wifiCheckBox = new JCheckBox("WIFI");
		wifiCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		wifiCheckBox.setBounds(576, 278, 146, 44);
		add(wifiCheckBox);

		roomNoComboBox = new JComboBox();
		roomNoComboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		roomNoComboBox.setEditable(false);
		roomNoComboBox.setBounds(188, 75, 124, 44);
		add(roomNoComboBox);

		priceTextField = new JTextField();
		priceTextField.setHorizontalAlignment(SwingConstants.CENTER);
		priceTextField.setFont(new Font("Tahoma", Font.BOLD, 16));
		priceTextField.setColumns(10);
		priceTextField.setBounds(174, 367, 124, 44);
		add(priceTextField);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 153, 938, 2);
		add(separator);

		gymCheckBox = new JCheckBox("Gym");
		gymCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gymCheckBox.setBounds(314, 278, 146, 44);
		add(gymCheckBox);

		swimmingPoolCheckBox = new JCheckBox("Swimming pool");
		swimmingPoolCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		swimmingPoolCheckBox.setBounds(64, 278, 146, 44);
		add(swimmingPoolCheckBox);

		applyButton = new JButton("Apply ");
		applyButton.setFont(new Font("Tahoma", Font.BOLD, 19));
		applyButton.setBounds(44, 437, 268, 78);
		add(applyButton);

		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		backButton.setBounds(676, 471, 156, 44);
		add(backButton);

		JLabel titleLabel = new JLabel("Apply the changes:");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		titleLabel.setBounds(10, 11, 220, 53);
		add(titleLabel);

		JLabel roomTypeLabel = new JLabel("Room Type:");
		roomTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		roomTypeLabel.setBounds(382, 367, 124, 44);
		add(roomTypeLabel);

		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		priceLabel.setBounds(64, 367, 100, 44);
		add(priceLabel);

		JLabel roomNumberLabel = new JLabel("Room Number:");
		roomNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		roomNumberLabel.setBounds(44, 75, 135, 44);
		add(roomNumberLabel);
		
		roomTypeComboBox = new JComboBox();
		roomTypeComboBox.setFont(new Font("Tahoma", Font.BOLD, 16));
		roomTypeComboBox.setModel(new DefaultComboBoxModel(RoomType.values()));
		roomTypeComboBox.setBounds(515, 367, 161, 44);
		add(roomTypeComboBox);

	}
}
