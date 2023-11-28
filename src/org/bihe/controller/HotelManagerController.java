package org.bihe.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.bihe.Enum.RoomType;
import org.bihe.bean.Hotel;
import org.bihe.bean.HotelManager;
import org.bihe.bean.Reserve;
import org.bihe.bean.Room;
import org.bihe.dao.HotelDaoImpl;
import org.bihe.dao.ReserveDaoImpl;
import org.bihe.dao.RoomDaoImpl;
import org.bihe.gui.HotelManagerChangePanel;
import org.bihe.gui.HotelManagerFrame;
import org.bihe.gui.HotelManagerRoomsPanel;
import org.bihe.gui.LoginFrame;

public class HotelManagerController {
	private HotelManager hotelManager;
	private HotelManagerFrame hotelManagerFrame;
	private HotelManagerRoomsPanel hotelManagerRoomsPanel;
	private HotelManagerChangePanel hotelManagerChangePanel;
	private Set<Room> hotelManagerRooms = new HashSet<>();

	// ---Constructor---

	public HotelManagerController(HotelManagerFrame hotelManagerFrame, HotelManager hotelManager) {
		this.hotelManager = hotelManager;
		this.hotelManagerFrame = hotelManagerFrame;
		this.hotelManagerRoomsPanel = hotelManagerFrame.getHotelManagerRoomsPanel();
		this.hotelManagerChangePanel = hotelManagerFrame.getHotelManagerChangePanel();
		setRooms();
		initHotelManagerController();
		initializeComboBox();
	}

	// ---Initialize of hotelManager Controller---

	private void initHotelManagerController() {
		actionForBackButton(hotelManagerRoomsPanel.getBackButton(), hotelManagerFrame);
		actionForBackButton(hotelManagerChangePanel.getBackButton(), hotelManagerFrame);
		setRoomTableModel(hotelManagerRoomsPanel.getRoomTable(), new ArrayList<>(hotelManagerRooms));
		hotelManagerChangePanel.getApplyButton().addActionListener(e -> actionForApplyButton());
		hotelManagerRoomsPanel.getSearchButton()
				.addActionListener(e -> filter(hotelManagerRoomsPanel.getFilterTextField().getText(),
						hotelManagerRoomsPanel.getRoomsTable()));

	}

	// ---Initialize of Hotel rooms Combobox---
	public void initializeComboBox() {
		Vector<Integer> roomNumbers = new Vector<>();
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
		for (Room room : hotelManagerRooms) {
			roomNumbers.add(room.getRoomNo());
		}
		model.addAll(roomNumbers);
		hotelManagerChangePanel.getRoomNoComboBox().setModel(model);
	}
	
	/**
	 * Action for apply button for changing the information of the rooms 
	 */
	public void actionForApplyButton() {
		try {
		int roomNo = (int) hotelManagerChangePanel.getRoomNoComboBox().getSelectedItem();
		Room room = findRoomByRoomNumber(roomNo);
		boolean breakfast = hotelManagerChangePanel.getBreakfastCheckBox().isSelected();
		boolean lunch = hotelManagerChangePanel.getLunchCheckBox().isSelected();
		boolean dinner = hotelManagerChangePanel.getDinnerCheckBox().isSelected();
		boolean pool = hotelManagerChangePanel.getSwimmingPoolCheckBox().isSelected();
		boolean gym = hotelManagerChangePanel.getGymCheckBox().isSelected();
		boolean wifi = hotelManagerChangePanel.getWifiCheckBox().isSelected();
		double price = Double.parseDouble(hotelManagerChangePanel.getPriceTextField().getText());
		RoomType roomType = (RoomType) hotelManagerChangePanel.getRoomTypeComboBox().getSelectedItem();
		room.setBreakfast(breakfast);
		room.setLunch(lunch);
		room.setDinner(dinner);
		room.setPool(pool);
		room.setGym(gym);
		room.setWifi(wifi);
		room.setPrice(price);
		room.setRoomType(roomType);
		RoomDaoImpl.getInstance().updateElement(room);
		setRoomTableModel(hotelManagerRoomsPanel.getRoomTable(), new ArrayList<>(hotelManagerRooms));
		JOptionPane.showMessageDialog(null, "Room Number " + roomNo + " changed successfully", "", JOptionPane.INFORMATION_MESSAGE);
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Please enter a number!", "Error", JOptionPane.ERROR_MESSAGE);
		}catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Please select a room", "Error", JOptionPane.ERROR_MESSAGE);
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
	 * method for finding and returning the correct room of the hotelmanager's hotel
	 */
	public Room findRoomByRoomNumber(int roomNumber) {
		for (Room room : hotelManagerRooms) {
			if (room.getRoomNo() == roomNumber) {
				return room;

			}
		}
		return null;
	}
	/**
	 * method for adding all rooms of the hotelmanager's hotel to the 'rooms Hashset'
	 */
	public void setRooms() {
		for (Room room : RoomDaoImpl.getInstance().getAllElements()) {
			if (room.getHotel().getHotelName().equalsIgnoreCase(hotelManager.getHotel().getHotelName())) {
				hotelManagerRooms.add(room);
			}
		}
	}
	/**
	 * method for finding and returning name of the guests reservations
	 */

	public static String findGuestReservation(int roomNumber, String hotelName) {
		for (Reserve reserve : ReserveDaoImpl.getInstance().getAllElements()) {
			if ((reserve.getRoom().getRoomNo() == roomNumber)
					&& reserve.getHotel().getHotelName().equalsIgnoreCase(hotelName)) {
				return reserve.getGuest().getFirstName() + reserve.getGuest().getLastName() + "";
			}
		}
		return null;
	}
	/**
	 * method for finding and returning start date of the guests reservations
	 */
	public static String findGuestReservationStartDate(int roomNumber, String hotelName) {
		for (Reserve reserve : ReserveDaoImpl.getInstance().getAllElements()) {
			if ((reserve.getRoom().getRoomNo() == roomNumber)
					&& reserve.getHotel().getHotelName().equalsIgnoreCase(hotelName)) {
				return reserve.getStartReservation() + "";
			}
		}
		return null;
	}
	/**
	 * method for finding and returning end date of the guests reservations
	 */
	public static String findGuestReservationEndDate(int roomNumber, String hotelName) {
		for (Reserve reserve : ReserveDaoImpl.getInstance().getAllElements()) {
			if ((reserve.getRoom().getRoomNo() == roomNumber)
					&& reserve.getHotel().getHotelName().equalsIgnoreCase(hotelName)) {
				return reserve.getEndReservation() + "";
			}
		}
		return null;
	}

	// -------------------Filter By TextField
	public static void filter(String search, JTable table) {
		TableRowSorter<TableModel> sor = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sor);
		sor.setRowFilter(RowFilter.regexFilter(search));
	}

	// ---------------------Tables----------------------------------
	// ---------------------Rooms-----------------------------------

	public static void setRoomTableModel(JTable table, ArrayList<Room> rooms) {
		String[] column = { "Room Number", "Price", "Room Type", "Breakfast", "Lunch", "Dinner", "Swimming Pool", "Gym",
				"Wifi", "Reserve", "Start date", "End date" };
		String[][] data = new String[RoomDaoImpl.getInstance().getAllElements().size()][column.length];
		for (int i = 0; i < rooms.size(); i++) {
			for (int j = 0; j < column.length; j++) {
				switch (j) {
				case 0:
					data[i][j] = rooms.get(i).getRoomNo() + "";
					break;
				case 1:
					data[i][j] = rooms.get(i).getPrice() + "";
					break;
				case 2:
					data[i][j] = rooms.get(i).getRoomType() + "";
					break;
				case 3:
					if (rooms.get(i).isBreakfast()) {

						data[i][j] = "Yes";
						break;
					} else {
						data[i][j] = "No";
						break;
					}
				case 4:
					if (rooms.get(i).isLunch()) {

						data[i][j] = "Yes";
						break;
					} else {
						data[i][j] = "No";
						break;
					}
				case 5:
					if (rooms.get(i).isDinner()) {

						data[i][j] = "Yes";
						break;
					} else {
						data[i][j] = "No";
						break;
					}
				case 6:
					if (rooms.get(i).isPool()) {

						data[i][j] = "Yes";
						break;
					} else {
						data[i][j] = "No";
						break;
					}
				case 7:
					if (rooms.get(i).isGym()) {

						data[i][j] = "Yes";
						break;
					} else {
						data[i][j] = "No";
						break;
					}
				case 8:
					if (rooms.get(i).isWifi()) {

						data[i][j] = "Yes";
						break;
					} else {
						data[i][j] = "No";
						break;
					}
				case 9:
					if (rooms.get(i).isEmpty()) {
						data[i][j] = "No reservation";
						break;
					} else {
						data[i][j] = findGuestReservation(rooms.get(i).getRoomNo(),
								rooms.get(i).getHotel().getHotelName());
						break;
					}
				case 10:
					if (rooms.get(i).isEmpty()) {
						data[i][j] = "-";
						break;
					} else {
						data[i][j] = findGuestReservationStartDate(rooms.get(i).getRoomNo(),
								rooms.get(i).getHotel().getHotelName());
						break;
					}
				case 11:
					if (rooms.get(i).isEmpty()) {
						data[i][j] = "-";
						break;
					} else {
						data[i][j] = findGuestReservationEndDate(rooms.get(i).getRoomNo(),
								rooms.get(i).getHotel().getHotelName());
						break;
					}

				}
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(data, column);
		table.setModel(dtm);
	}

}
