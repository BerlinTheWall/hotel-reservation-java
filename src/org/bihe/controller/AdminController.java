package org.bihe.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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

import org.bihe.Enum.RoomType;
import org.bihe.bean.Guest;
import org.bihe.bean.Hotel;
import org.bihe.bean.HotelManager;
import org.bihe.bean.RequestForm;
import org.bihe.bean.Reserve;
import org.bihe.bean.Room;
import org.bihe.bean.Signing;
import org.bihe.dao.GuestDaoImpl;
import org.bihe.dao.HotelDaoImpl;
import org.bihe.dao.HotelManagerDaoImpl;
import org.bihe.dao.RequestFormDaoImpl;
import org.bihe.dao.RoomDaoImpl;
import org.bihe.dao.SigningDaoImpl;
import org.bihe.gui.AdminFrame;
import org.bihe.gui.AdminGuestsPanel;
import org.bihe.gui.AdminHotelsPanel;
import org.bihe.gui.AdminRequestsPanel;
import org.bihe.gui.LoginFrame;

public class AdminController {
	private AdminFrame adminFrame;
	private AdminHotelsPanel adminHotelsPanel;
	private AdminGuestsPanel adminGuestsPanel;
	private AdminRequestsPanel adminRequestsPanel;

	// ---Constructor---

	public AdminController(AdminFrame adminFrame) {
		this.adminFrame = adminFrame;
		this.adminGuestsPanel = adminFrame.getAdminGuestsPanel();
		this.adminHotelsPanel = adminFrame.getAdminHotelsPanel();
		this.adminRequestsPanel = adminFrame.getAdminRequestsPanel();
		initAdminController();
	}

	// ---Initialize of admin Controller---

	private void initAdminController() {
		actionForBackButton(adminHotelsPanel.getBackButton(), adminFrame);
		actionForBackButton(adminGuestsPanel.getBackButton(), adminFrame);
		actionForBackButton(adminRequestsPanel.getBackButton(), adminFrame);
		setHotelTableModel(adminHotelsPanel.getHoteltable(), new ArrayList<>(acceptedHotels()));
		setRequestTableModel(adminRequestsPanel.getRequestTable(),
				new ArrayList<>(RequestFormDaoImpl.getInstance().getAllElements()));
		setGuestTableModel(adminGuestsPanel.getGuestTable(),
				new ArrayList<>(GuestDaoImpl.getInstance().getAllElements()));
		adminHotelsPanel.getSearchButton().addActionListener(
				e -> filter(adminHotelsPanel.getHotelNameTextField().getText(), adminHotelsPanel.getHoteltable()));
		adminGuestsPanel.getSearchButton().addActionListener(
				e -> filter(adminGuestsPanel.getGuestNameTextField().getText(), adminGuestsPanel.getGuestTable()));
		adminRequestsPanel.getAcceptButton().addActionListener(e -> actionForAcceptButton());
		adminRequestsPanel.getRejectButton().addActionListener(e -> actionForRejectButton());
		adminHotelsPanel.getSortComboBox().addActionListener(e -> actionForSortComboBox());
		adminGuestsPanel.getHotelGuestComboBox().addActionListener(e -> actionForSortGuestComboBox());
	}

	/**
	 * Action for accept button for accepting a new hotel
	 */
	public void actionForAcceptButton() {
		try {
			int row = adminRequestsPanel.getRequestTable().getSelectedRow();
			int ownerId = Integer.parseInt(adminRequestsPanel.getRequestTable().getValueAt(row, 1).toString());
			int roomNo = Integer.parseInt(adminRequestsPanel.getRequestTable().getValueAt(row, 7).toString());
			HotelManager hotelManager = HotelManagerDaoImpl.getInstance().getElement(ownerId);
			hotelManager.setAccepted(true);
			HotelManagerDaoImpl.getInstance().updateElement(hotelManager);
			RequestForm requestForm = findRequestByOwnerId(ownerId);
			RequestFormDaoImpl.getInstance().deleteElement(requestForm);
			Hotel hotel = hotelManager.getHotel();
			for (int i = 1; i <= roomNo; i++) {
				Room room = new Room(hotel, i, requestForm.isBreakfast(), requestForm.isLunch(), requestForm.isDinner(),
						requestForm.isPool(), requestForm.isGym(), requestForm.isWifi(), RoomType.Single_bed);
				RoomDaoImpl.getInstance().addElement(room);
			}
			setRequestTableModel(adminRequestsPanel.getRequestTable(),
					new ArrayList<>(RequestFormDaoImpl.getInstance().getAllElements()));
			setHotelTableModel(adminHotelsPanel.getHoteltable(), new ArrayList<>(acceptedHotels()));
			JOptionPane.showMessageDialog(null, "Request form accepted successfully", "",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Please select a request form", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Action for reject button for rejecting a new hotel
	 */
	public void actionForRejectButton() {
		try {
			int row = adminRequestsPanel.getRequestTable().getSelectedRow();
			int ownerId = Integer.parseInt(adminRequestsPanel.getRequestTable().getValueAt(row, 1).toString());
			HotelManager hotelManager = HotelManagerDaoImpl.getInstance().getElement(ownerId);
			Hotel hotel = hotelManager.getHotel();
			RequestForm requestForm = findRequestByOwnerId(ownerId);
			Signing signing = findSigningByObjectId(ownerId);
			for (Room room : RoomDaoImpl.getInstance().getAllElements()) {
				if (room.getHotel().equals(hotel)) {
					RoomDaoImpl.getInstance().deleteElement(room);
				}
			}
			SigningDaoImpl.getInstance().deleteElement(signing);
			RequestFormDaoImpl.getInstance().deleteElement(requestForm);
			HotelManagerDaoImpl.getInstance().deleteElement(hotelManager);
			HotelDaoImpl.getInstance().deleteElement(hotel);
			setRequestTableModel(adminRequestsPanel.getRequestTable(),
					new ArrayList<>(RequestFormDaoImpl.getInstance().getAllElements()));
			JOptionPane.showMessageDialog(null, "Request form rejected successfully", "",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Please select a request form", "Error", JOptionPane.ERROR_MESSAGE);
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
	 * method for finding and returning the correct requestForm
	 */
	public static RequestForm findRequestByOwnerId(int ownerId) {
		for (RequestForm requestForm : RequestFormDaoImpl.getInstance().getAllElements()) {
			if (requestForm.getHotelManager().getId() == ownerId) {
				return requestForm;
			}
		}
		return null;
	}

	/**
	 * method for finding and returning the correct signing informations
	 */
	public static Signing findSigningByObjectId(int objectId) {
		for (Signing signing : SigningDaoImpl.getInstance().getAllElements()) {
			if (signing.getObject() instanceof HotelManager) {
				HotelManager hm = (HotelManager) signing.getObject();
				if (hm.getId() == objectId) {
					return signing;
				}
			}
		}
		return null;
	}

	/**
	 * method for adjusting different sort options from the hotel ComboBox
	 */
	public void actionForSortComboBox() {
		switch (adminHotelsPanel.getSortComboBox().getSelectedIndex()) {
		case 0:
			setHotelTableModel(adminHotelsPanel.getHoteltable(), new ArrayList<>(acceptedHotels()));
			break;
		case 1:
			setHotelTableModel(adminHotelsPanel.getHoteltable(), new ArrayList<>(sortByHotelName(acceptedHotels())));
			break;
		case 2:
			setHotelTableModel(adminHotelsPanel.getHoteltable(), new ArrayList<>(sortByHotelCity(acceptedHotels())));
			break;
		case 3:
			setHotelTableModel(adminHotelsPanel.getHoteltable(), new ArrayList<>(sortByHotelStar(acceptedHotels())));
		}
	}

	/**
	 * method for adjusting different sort options from the guest ComboBox
	 */
	public void actionForSortGuestComboBox() {
		switch (adminGuestsPanel.getHotelGuestComboBox().getSelectedIndex()) {
		case 0:
			setGuestTableModel(adminGuestsPanel.getGuestTable(),
					new ArrayList<>(GuestDaoImpl.getInstance().getAllElements()));
			break;
		case 1:
			setGuestTableModel(adminGuestsPanel.getGuestTable(),
					new ArrayList<>(sortByGuestLastName(GuestDaoImpl.getInstance().getAllElements())));
			break;
		case 2:
			setGuestTableModel(adminGuestsPanel.getGuestTable(),
					new ArrayList<>(sortByGuestCity(GuestDaoImpl.getInstance().getAllElements())));
			break;
		}

	}

	/**
	 * method for adding accepted hotels to the hotel's HashSet
	 */
	public static Set<Hotel> acceptedHotels() {
		HashSet<Hotel> hotels = new HashSet<>();
		for (HotelManager hotelManager : HotelManagerDaoImpl.getInstance().getAllElements()) {
			if (hotelManager.isAccepted()) {
				hotels.add(hotelManager.getHotel());
			}
		}
		return hotels;
	}

	/**
	 * method for adding accepted hotels managers to the hotelManager's HashSet
	 */
	public static Set<HotelManager> acceptedHotelManagers() {
		HashSet<HotelManager> hotelManagers = new HashSet<>();
		for (HotelManager hotelManager : HotelManagerDaoImpl.getInstance().getAllElements()) {
			if (hotelManager.isAccepted()) {
				hotelManagers.add(hotelManager);
			}
		}
		return hotelManagers;
	}

	// -------------------Filter By TextField----------------
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

	public static ArrayList<Guest> shallowCopyGuest(ArrayList<Guest> guests) {
		ArrayList<Guest> guestsCopy = new ArrayList<Guest>();
		for (Guest guest : guests) {
			Guest guestCopy = guest;
			guestsCopy.add(guestCopy);
		}
		return guestsCopy;
	}
	// ---------------------Sort by HotelName & Stars & City for
	// hotelsPanel----------------

	public static TreeSet<Hotel> sortByHotelName(Set<Hotel> hotels) {
		TreeSet<Hotel> sort = shallowCopy(hotels);
		return sort;
	}

	public static ArrayList<Hotel> sortByHotelStar(Set<Hotel> hotels) {
		ArrayList<Hotel> sort = shallowCopyHotel(new ArrayList<Hotel>(hotels));
		Collections.sort(sort, (o1, o2) -> Integer.valueOf(o2.getStar()).compareTo(Integer.valueOf(o1.getStar())));
		return sort;
	}

	public static ArrayList<Hotel> sortByHotelCity(Set<Hotel> hotels) {
		ArrayList<Hotel> sort = shallowCopyHotel(new ArrayList<Hotel>(hotels));
		Collections.sort(sort, (o1, o2) -> o1.getAddress().getCity().compareTo(o2.getAddress().getCity()));
		return sort;
	}
	// ---------------------Sort by LastName & City for guestsPanel----------------

	public static ArrayList<Guest> sortByGuestLastName(Set<Guest> guests) {
		ArrayList<Guest> sort = shallowCopyGuest(new ArrayList<Guest>(guests));
		Collections.sort(sort, (o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
		return sort;
	}

	public static ArrayList<Guest> sortByGuestCity(Set<Guest> guests) {
		ArrayList<Guest> sort = shallowCopyGuest(new ArrayList<Guest>(guests));
		Collections.sort(sort, (o1, o2) -> o1.getAddress().getCity().compareTo(o2.getAddress().getCity()));
		return sort;
	}

	// ---------------------Tables----------------------------------
	// ----------------------Hotels---------------------------------
	public static void setHotelTableModel(JTable table, ArrayList<Hotel> hotels) {
		String[] column = { "Hotel Name", "City", "Address", "Star", "Room Number", "Phone Number" };
		String[][] data = new String[acceptedHotels().size()][column.length];
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
	// ----------------------RequestForms---------------------------------

	public static void setRequestTableModel(JTable table, ArrayList<RequestForm> requestForms) {
		String[] column = { "Hotel Name", "Owner ID", "Owner Firstname", "Owner Lastname", "City", "Address", "Star",
				"Room Number", "Phone Number" };
		String[][] data = new String[RequestFormDaoImpl.getInstance().getAllElements().size()][column.length];
		for (int i = 0; i < requestForms.size(); i++) {
			for (int j = 0; j < column.length; j++) {
				switch (j) {
				case 0:
					data[i][j] = requestForms.get(i).getHotelManager().getHotel().getHotelName() + "";
					break;
				case 1:
					data[i][j] = requestForms.get(i).getHotelManager().getId() + "";
					break;
				case 2:
					data[i][j] = requestForms.get(i).getHotelManager().getFirstName() + "";
					break;
				case 3:
					data[i][j] = requestForms.get(i).getHotelManager().getLastName() + "";
					break;
				case 4:
					data[i][j] = requestForms.get(i).getHotelManager().getHotel().getAddress().getCity() + "";
					break;
				case 5:
					data[i][j] = requestForms.get(i).getHotelManager().getHotel().getAddress().getStreet() + "";
					break;
				case 6:
					data[i][j] = requestForms.get(i).getHotelManager().getHotel().getStar() + "";
					break;
				case 7:
					data[i][j] = requestForms.get(i).getHotelManager().getHotel().getRoomNo() + "";
					break;
				case 8:
					data[i][j] = requestForms.get(i).getHotelManager().getPhoneNo() + "";
					break;

				}
			}
		}
		DefaultTableModel dtm = new DefaultTableModel(data, column);
		table.setModel(dtm);
	}

	// ----------------------Guests---------------------------------

	public static void setGuestTableModel(JTable table, ArrayList<Guest> guests) {
		String[] column = { "Firstname", "Lastname", "National ID", "Phone Number", "City", "Address", "Hotel Name" };
		String[][] data = new String[GuestDaoImpl.getInstance().getAllElements().size()][column.length];
		for (int i = 0; i < guests.size(); i++) {
			for (int j = 0; j < column.length; j++) {
				switch (j) {
				case 0:
					data[i][j] = guests.get(i).getFirstName() + "";
					break;
				case 1:
					data[i][j] = guests.get(i).getLastName() + "";
					break;
				case 2:
					data[i][j] = guests.get(i).getNationalId() + "";
					break;
				case 3:
					data[i][j] = guests.get(i).getPhoneNo() + "";
					break;
				case 4:
					data[i][j] = guests.get(i).getAddress().getCity() + "";
					break;
				case 5:
					data[i][j] = guests.get(i).getAddress().getStreet() + "";
					break;
				case 6:
					Reserve reserve0 = null;
					LocalDate ld = LocalDate.now();
					for (Reserve reserve : guests.get(i).getReservations()) {
						if (reserve.getEndReservation().moreThan(ld)) {
							reserve0 = reserve;
							break;
						}
					}
					if (reserve0 == null) {
						data[i][j] = "No Reservation";
					} else
						data[i][j] = reserve0.getHotel().getHotelName();
					break;
				}

			}

		}
		DefaultTableModel dtm = new DefaultTableModel(data, column);
		table.setModel(dtm);
	}
}
