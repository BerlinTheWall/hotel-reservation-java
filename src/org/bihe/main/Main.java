package org.bihe.main;

import org.bihe.bean.Admin;
import org.bihe.bean.Signing;
import org.bihe.controller.LoginController;
import org.bihe.dao.AdminDaoImpl;
import org.bihe.dao.SigningDaoImpl;
import org.bihe.gui.LoginFrame;

public class Main {

	public static void main(String[] args) {
//		Admin admin = new Admin("Hooman", "Shahidi", "12345677", "09129475839");
//		Signing<Admin> signing = new Signing<Admin>("hoo", "1", admin);
//		AdminDaoImpl.getInstance().addElement(admin);
//		SigningDaoImpl.getInstance().addElement(signing);
		LoginFrame l = new LoginFrame();
		LoginController loginController = new LoginController(l);
		l.setVisible(true);
	}

}
