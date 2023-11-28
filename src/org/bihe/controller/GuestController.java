package org.bihe.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import org.bihe.bean.Address;
import org.bihe.bean.Guest;
import org.bihe.bean.Hotel;
import org.bihe.bean.Reserve;
import org.bihe.bean.Room;
import org.bihe.dao.HotelDaoImpl;
import org.bihe.dao.MyDateDaoImpl;
import org.bihe.dao.ReserveDaoImpl;
import org.bihe.dao.RoomDaoImpl;
import org.bihe.gui.GuestFrame;
import org.bihe.gui.GuestHotelListPanel;
import org.bihe.gui.GuestHotelReservationsPanel;
import org.bihe.gui.LoginFrame;
import org.bihe.gui.ReserveFrame;

public class GuestController {
	private Guest guest;
	private GuestFrame guestFrame;
	private GuestHotelListPanel guestHotelListPanel;
	private GuestHotelReservationsPanel guestHotelReservationsPanel;
	private ArrayList<Reserve> reservations = new ArrayList<>();
	// ---Constructor---

	public GuestController(GuestFrame guestFrame, Guest guest) {
		this.guest = guest;
		this.guestFrame = guestFrame;
		this.guestHotelListPanel = guestFrame.getGuestHotelListPanel();
		this.guestHotelReservationsPanel = guestFrame.getGuestHotelReservationsPanel();
		initGuestController();
	}

	// ---Initialize of guest Controller---

	private void initGuestController() {
		actionForBackButton(guestHotelListPanel.getBackButton(), guestFrame);
		actionForBackButton(guestHotelReservationsPanel.getBackButton(), guestFrame);
		setHotelTableModel(guestHotelListPanel.getHotelstable(), new ArrayList<>(AdminController.acceptedHotels()));
		setReservationTableModel(guestHotelReservationsPanel.getReservationsTable(),
				new ArrayList<>(findGuestReservation()));
		guestHotelReservationsPanel.getCancelButton().addActionListener(e -> actionForCancelButton());
		guestHotelListPanel.getSearchButton().addActionListener(
				e -> filter(guestHotelListPanel.getFilterTextField().getText(), guestHotelListPanel.getHotelstable()));
		guestHotelListPanel.getSortComboBox().addActionListener(e -> actionForSortGuestComboBox());
		guestHotelListPanel.getReserveButton().addActionListener(e -> actionForReservationButton());
	}

	/**
	 * Action for cancel button for canceling the reservations
	 */
	public void actionForCancelButton() {
		try {
		int row = guestHotelReservationsPanel.getReservationsTable().getSelectedRow();
		Reserve reserve = findReservationByHotelnameCityDate(
				guestHotelReservationsPanel.getReservationsTable().getValueAt(row, 0).toString(),
				guestHotelReservationsPanel.getReservationsTable().getValueAt(row, 1).toString(),
				guestHotelReservationsPanel.getReservationsTable().getValueAt(row, 5).toString(),
				guestHotelReservationsPanel.getReservationsTable().getValueAt(row, 6).toString());
		Room room = reserve.getRoom();
		room.setEmpty(true);
		RoomDaoImpl.getInstance().updateElement(room);
		ReserveDaoImpl.getInstance().deleteElement(reserve);
		MyDateDaoImpl.getInstance().deleteElement(reserve.getStartReservation());
		MyDateDaoImpl.getInstance().deleteElement(reserve.getEndReservation());
		JOptionPane.showMessageDialog(null, "Reservation canceled successfully", "", JOptionPane.INFORMATION_MESSAGE);
		setReservationTableModel(guestHotelReservationsPanel.getReservationsTable(),
				new ArrayList<>(ReserveDaoImpl.getInstance().getAllElements()));
		}catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Please select a reservation", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Action for reservation button  
	 */
	public void actionForReservationButton() {
		try {
		int row = guestHotelListPanel.getHotelstable().getSelectedRow();
		Hotel hotel = findHotelByNameAndCity(guestHotelListPanel.getHotelstable().getValueAt(row, 0).toString(),
				guestHotelListPanel.getHotelstable().getValueAt(row, 1).toString());
		ReserveFrame rf = new ReserveFrame();
		ReserveController rc = new ReserveController(rf, hotel, guest);
		rf.setVisible(true);
		}catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Please select a hotel", "Error", JOptionPane.ERROR_MESSAGE);
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
	 * method for finding and returning the correct reservations
	 */
	public static Reserve findReservationByHotelnameCityDate(String hotelname, String city, String startDate,
			String endDate) {
		for (Reserve reserve : ReserveDaoImpl.getInstance().getAllElements()) {
			if (reserve.getHotel().getHotelName().equalsIgnoreCase(hotelname)
					&& reserve.getHotel().getAddress().getCity().equalsIgnoreCase(city)
					&& reserve.getStartReservation().toString().equalsIgnoreCase(startDate)
					&& reserve.getEndReservation().toString().equalsIgnoreCase(endDate)) {
				return reserve;
			}
		}
		return null;
	}

	/**
	 * method for finding and returning reservation of the guest
	 */
	public Set<Reserve> findGuestReservation() {
		HashSet<Reserve> reserves = new HashSet<>();
		for (Reserve reserve : ReserveDaoImpl.getInstance().getAllElements()) {
			if ((reserve.getGuest().equals(guest))) {
				reserves.add(reserve);
			}
		}
		return reserves;
	}

	/**
	 * method for adjusting different sort options from the guest ComboBox
	 */
	public void actionForSortGuestComboBox() {
		switch (guestHotelListPanel.getSortComboBox().getSelectedIndex()) {
		case 0:
			setHotelTableModel(guestHotelListPanel.getHotelstable(), new ArrayList<>(AdminController.acceptedHotels()));
			break;
		case 1:
			setHotelTableModel(guestHotelListPanel.getHotelstable(),
					new ArrayList<>(sortByHotelName(AdminController.acceptedHotels())));
			break;
		case 2:
			setHotelTableModel(guestHotelListPanel.getHotelstable(),
					new ArrayList<>(sortByHotelStar(AdminController.acceptedHotels())));
			break;
		}
	}

	/**
	 * method for finding and returning the selected hotel
	 */
	public static Hotel findHotelByNameAndCity(String hotelname, String city) {
		for (Hotel hotel : AdminController.acceptedHotels()) {
			if (hotelname.equalsIgnoreCase(hotel.getHotelName())
					&& hotel.getAddress().getCity().equalsIgnoreCase(city)) {
				return hotel;
			}
		}
		return null;
	}

	// -------------------Filter By TextField--------------------
	public static void filter(String search, JTable table) {
		TableRowSorter<TableModel> sor = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sor);
		sor.setRowFilter(RowFilter.regexFilter(search));
	}

	// -------------------Sort----------------------------
	// -------------------ShallowCopy---------------------
	public static TreeSet<Hotel> shallowCopy(Set<Hotel> hotels) {
		TreeSet<Hotel> hotelsCopy = new TreeSet<Hotel>();
		for (Hotel hotel : hotels) {
			Hotel hotelCopy = hotel;
			hotelsCopy.add(hotelCopy);
		}
		return hotelsCopy;
	}

	public static ArrayList<Hotel> shallowCopyHotel(ArrayList<Hotel> hotels) {
		ArrayList<Hotel> hotelsCopy = new ArrayList<Hotel>();
		for (Hotel hotel : hotels) {
			Hotel hotelCopy = hotel;
			hotelsCopy.add(hotelCopy);
		}
		return hotelsCopy;
	}

	// ---------------------Sort by HotelName & Stars for
	// guestsPanel----------------

	public static TreeSet<Hotel> sortByHotelName(Set<Hotel> hotels) {
		TreeSet<Hotel> sort = shallowCopy(hotels);
		return sort;
	}

	public static ArrayList<Hotel> sortByHotelStar(Set<Hotel> hotels) {
		ArrayList<Hotel> sort = shallowCopyHotel(new ArrayList<Hotel>(hotels));
		Collections.sort(sort, (o1, o2) -> Integer.valueOf(o2.getStar()).compareTo(Integer.valueOf(o1.getStar())));
		return sort;
	}

	// ---------------------Tables----------------------------------
	// ----------------------Hotels---------------------------------
	public static void setHotelTableModel(JTable table, ArrayList<Hotel> hotels) {
		String[] column = { "Hotel Name", "City", "Address", "Star", "Room Number", "Phone Number" };
		String[][] data = new String[AdminController.acceptedHotels().size()][column.length];
		for (int i = 0; i < hotels.size(); i++) {
			for (int j = 0; j < column.length; j++) {
				switch (j) {
				case 0:
					data[i][j] = hotels.get(i).getHotelName() + "";
					break;
				case 1:
					data[i][j] = hotels.get(i).getAddress().getCity() + "";
					break;
				case 2:
					data[i][j] = hotels.get(i).getAddress().getStreet() + "";
					break;
				case 3:
					data[i][j] = hotels.get(i).getStar() + "";
					break;
				case 4:
					data[i][j] = hotels.get(i).getRoomNo() + "";
					break;
				case 5:
					data[i][j] = hotels.get(i).getPhoneNo() + "";
					break;
				}
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(data, column);
		table.setModel(dtm);
	}

	// ----------------------Reservations---------------------------------
	public static void setReservationTableModel(JTable table, ArrayList<Reserve> reserves) {
		String[] column = { "Hotel Name", "City", "Address", "Star", "Price", "Start of Reserve", "End of Reserve",
				"Room Number", "Room Type", "Phone Number" };
		String[][] data = new String[ReserveDaoImpl.getInstance().getAllElements().size()][column.length];
		for (int i = 0; i < reserves.size(); i++) {
			for (int j = 0; j < column.length; j++) {
				switch (j) {
				case 0:
					data[i][j] = reserves.get(i).getHotel().getHotelName() + "";
					break;
				case 1:
					data[i][j] = reserves.get(i).getHotel().getAddress().getCity() + "";
					break;
				case 2:
					data[i][j] = reserves.get(i).getHotel().getAddress().getStreet() + "";
					break;
				case 3:
					data[i][j] = reserves.get(i).getHotel().getStar() + "";
					break;
				case 4:
					data[i][j] = reserves.get(i).getPrice() + "";
					break;
				case 5:
					data[i][j] = reserves.get(i).getStartReservation().toString();
					break;
				case 6:
					data[i][j] = reserves.get(i).getEndReservation().toString();
					break;
				case 7:
					data[i][j] = reserves.get(i).getRoom().getRoomNo() + "";
					break;
				case 8:
					data[i][j] = reserves.get(i).getRoom().getRoomType() + "";
					break;
				case 9:
					data[i][j] = reserves.get(i).getHotel().getPhoneNo() + "";
					break;
				}
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(data, column);
		table.setModel(dtm);
	}
}
