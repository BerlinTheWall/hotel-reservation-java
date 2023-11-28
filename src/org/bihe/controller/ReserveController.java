package org.bihe.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.bihe.bean.Guest;
import org.bihe.bean.Hotel;
import org.bihe.bean.HotelManager;
import org.bihe.bean.MyDate;
import org.bihe.bean.Reserve;
import org.bihe.bean.Room;
import org.bihe.dao.HotelManagerDaoImpl;
import org.bihe.dao.MyDateDaoImpl;
import org.bihe.dao.ReserveDaoImpl;
import org.bihe.dao.RoomDaoImpl;
import org.bihe.exception.IsEmptyException;
import org.bihe.gui.GuestFrame;
import org.bihe.gui.GuestHotelListPanel;
import org.bihe.gui.GuestHotelReservationsPanel;
import org.bihe.gui.LoginFrame;
import org.bihe.gui.ReserveFrame;

public class ReserveController {
	private ReserveFrame reserveFrame;
	private Hotel hotel;
	private Guest guest;
	private Set<Room> rooms = new HashSet<>();

	// ---Constructor---

	public ReserveController(ReserveFrame reserveFrame, Hotel hotel, Guest guest) {
		this.hotel = hotel;
		this.reserveFrame = reserveFrame;
		this.guest = guest;
		setRooms();
		initReserveController();
	}

	// ---Initialize of reserve Controller---

	private void initReserveController() {
		actionForBackButton(reserveFrame.getBackButton(), reserveFrame);
		setRoomsTableModel(reserveFrame.getRoomstable(), new ArrayList<>(rooms));
		reserveFrame.getSubmitReserveButton().addActionListener(e -> actionForSubmitReserveButton());
		reserveFrame.getSearchButton().addActionListener(
				e -> filter(reserveFrame.getFilterTextField().getText(), reserveFrame.getRoomstable()));
		reserveFrame.getSortComboBox().addActionListener(e -> actionForSortComboBox());
	}
	/**
	 * Action for submit reserve button  
	 */
	public void actionForSubmitReserveButton() {
		try {
			if (reserveFrame.getDaysTextField().getText().isEmpty()) {
				IsEmptyException e = new IsEmptyException("Please fill text field!");
				throw e;
			}
			int row = reserveFrame.getRoomstable().getSelectedRow();
			Room room = findRoomByRoomNumber(
					Integer.parseInt(reserveFrame.getRoomstable().getValueAt(row, 0).toString()));
			room.setEmpty(false);
			RoomDaoImpl.getInstance().updateElement(room);
			MyDate startReservation = new MyDate(LocalDate.now());
			MyDateDaoImpl.getInstance().addElement(startReservation);
			int days = Integer.parseInt(reserveFrame.getDaysTextField().getText());
			LocalDate end = MyDate.convertMydatToLocalDate(startReservation).plusDays(days);
			MyDate endReservation = new MyDate(end);
			MyDateDaoImpl.getInstance().addElement(endReservation);
			Reserve reserve = new Reserve(hotel, room, guest, startReservation, endReservation);
			ReserveDaoImpl.getInstance().addElement(reserve);
			JOptionPane.showMessageDialog(null, "Room reserved successfully", "", JOptionPane.INFORMATION_MESSAGE);
		} catch (IsEmptyException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "please enter number!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ArrayIndexOutOfBoundsException e) {
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
			}
		});

	}
	
	/**
	 * method for adjusting different sort options from the room ComboBox
	 */
	public void actionForSortComboBox() {
		switch (reserveFrame.getSortComboBox().getSelectedIndex()) {
		case 0:
			setRoomsTableModel(reserveFrame.getRoomstable(), new ArrayList<>(sortByRoomNumber(rooms)));
			break;
		case 1: 
			setRoomsTableModel(reserveFrame.getRoomstable(), new ArrayList<>(sortByprice(rooms)));
			break;
		}
	}
	
	/**
	 * method for finding and returning correct room
	 */
	public Room findRoomByRoomNumber(int roomNumber) {
		for (Room room : rooms) {
			if (room.getRoomNo() == roomNumber) {
				return room;
			}
		}
		return null;
	}
	/**
	 * method for adding the room's of the selected hotel to the rooms HashSet
	 */
	private void setRooms() {
		for (Room room : RoomDaoImpl.getInstance().getAllElements()) {
			if (room.getHotel().equals(hotel)&&room.isEmpty()) {
				rooms.add(room);
			}
		}
	}
	

	// -------------------Filter By TextField----------------
	public static void filter(String search, JTable table) {
		TableRowSorter<TableModel> sor = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sor);
		sor.setRowFilter(RowFilter.regexFilter(search));
	}
	//-----------------------Sort----------------------------
	// -------------------ShallowCopy---------------------
	
	public static ArrayList<Room> shallowCopy(ArrayList<Room> rooms){
		ArrayList<Room> roomsCopy = new ArrayList<Room>();
		for (Room room : rooms) {
			Room roomCopy = room;
			roomsCopy.add(roomCopy);
		}
		return roomsCopy;
	}
	
	// ---------------------Sort by RoomNumber & price for reservePanel--------
	public static ArrayList<Room> sortByprice(Set<Room> rooms){
		ArrayList<Room> sort = shallowCopy(new ArrayList<Room>(rooms));
		Collections.sort(sort, (o1, o2) -> Double.valueOf(o2.getPrice()).compareTo(Double.valueOf(o1.getPrice())));
		return sort;
	}
	
	public static ArrayList<Room> sortByRoomNumber(Set<Room> rooms){
		ArrayList<Room> sort = shallowCopy(new ArrayList<Room>(rooms));
		Collections.sort(sort, (o1, o2) -> Integer.valueOf(o1.getRoomNo()).compareTo(Integer.valueOf(o2.getRoomNo())));
		return sort;
	}
	
	
	// ----------------------RoomsReservation---------------------------------
	public static void setRoomsTableModel(JTable table, ArrayList<Room> rooms) {
		String[] column = { "Room Number", "Room Type", "Price", "Breakfast", "Lunch", "Dinner", "Swimmimg Pool", "Gym",
				"Wifi" };
		String[][] data = new String[RoomDaoImpl.getInstance().getAllElements().size()][column.length];
		for (int i = 0; i < rooms.size(); i++) {
			for (int j = 0; j < column.length; j++) {
				switch (j) {
				case 0:
					data[i][j] = rooms.get(i).getRoomNo() + "";
					break;
				case 1:
					data[i][j] = rooms.get(i).getRoomType() + "";
					break;
				case 2:
					data[i][j] = rooms.get(i).getPrice() + "";
					break;
				case 3:
					if (rooms.get(i).isBreakfast()) {
						data[i][j] = "Yes";
					} else
						data[i][j] = "No";
					break;
				case 4:
					if (rooms.get(i).isLunch()) {
						data[i][j] = "Yes";
					} else
						data[i][j] = "No";
					break;
				case 5:
					if (rooms.get(i).isDinner()) {
						data[i][j] = "Yes";
					} else
						data[i][j] = "No";
					break;
				case 6:
					if (rooms.get(i).isPool()) {
						data[i][j] = "Yes";
					} else
						data[i][j] = "No";
					break;
				case 7:
					if (rooms.get(i).isGym()) {
						data[i][j] = "Yes";
					} else
						data[i][j] = "No";
					break;
				case 8:
					if (rooms.get(i).isWifi()) {
						data[i][j] = "Yes";
					} else
						data[i][j] = "No";
					break;
				}
			}
			DefaultTableModel dtm = new DefaultTableModel(data, column);
			table.setModel(dtm);
		}
	}
}
