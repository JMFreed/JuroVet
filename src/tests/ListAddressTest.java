package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lists.ListAddress;
import main.MainFrame;
import objects.ObjAddress;
import searchevents.SearchEventAddress;
import sprocs.spsAddress;

class ListAddressTest {
	
	MainFrame mainFrame = MainFrame.getInstance();
	String[][] data = new String[ListAddress.columnNames.length][0];
	ListAddress listAddress = new ListAddress(mainFrame, data);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testListAddressInit() {
		ListAddress testListCustomer = new ListAddress(mainFrame, data);
		if (testListCustomer instanceof ListAddress) {
			return;
		}
		fail();
	}
	
	@Test
	void testButtonLabels() {
		if (listAddress.getSearchButton() instanceof JButton &&
				listAddress.getCloseButton() instanceof JButton) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testColumnNames() {
		String[] columnNames = listAddress.getColumnNames();
		if (columnNames[0].contentEquals("AddressID") &&
				columnNames[1].contentEquals("Address") &&
				columnNames[2].contentEquals("Postcode")) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testUpdateAddressData() throws ClassNotFoundException, SQLException {
		ArrayList<ObjAddress> arrayListAddress = new ArrayList<ObjAddress>();
		SearchEventAddress event = new SearchEventAddress(this, "fern cottage", "me13 0bs");
		arrayListAddress = spsAddress.findAddressByName(mainFrame, event);
		mainFrame.getDBManager().closeAll();
		listAddress.updateAddressData(arrayListAddress);
		if(listAddress.getData().length == 5) {
			return;
		}
		fail();
		
	}

}
