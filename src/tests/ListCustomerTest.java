package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lists.ListCustomer;
import main.MainFrame;
import objects.ObjCustomer;
import searchevents.SearchEventCustomer;
import sprocs.spsCustomer;

class ListCustomerTest {
	
	MainFrame mainFrame = MainFrame.getInstance();
	String[][] data = new String[ListCustomer.columnNames.length][0];
	ListCustomer listCustomer = new ListCustomer(mainFrame, data);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testListCustomerInit() {
		ListCustomer testListCustomer = new ListCustomer(mainFrame, data);
		if (testListCustomer instanceof ListCustomer) {
			return;
		}
		fail();
	}
	
	@Test
	void testButtonLabels() {
		if (listCustomer.getSearchButton() instanceof JButton &&
				listCustomer.getCloseButton() instanceof JButton) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testColumnNames() {
		String[] columnNames = listCustomer.getColumnNames();
		if (columnNames[0].contentEquals("CustomerID") &&
				columnNames[1].contentEquals("Customer") &&
				columnNames[2].contentEquals("Address") &&
				columnNames[3].contentEquals("Postcode") &&
				columnNames[4].contentEquals("Telephone") &&
				columnNames[5].contentEquals("Animal") &&
				columnNames[6].contentEquals("Balance") &&
				columnNames[7].contentEquals("AccountGUID")) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testUpdateCustomerData() throws ClassNotFoundException, SQLException {
		ArrayList<ObjCustomer> arrayListCustomer = new ArrayList<ObjCustomer>();
		SearchEventCustomer event = new SearchEventCustomer(this, "freed", "fern cottage", "", "woody");
		arrayListCustomer = spsCustomer.findCustomerBySearch(mainFrame, event);
		listCustomer.updateCustomerData(arrayListCustomer);
		if(listCustomer.getData().length == 1) {
			ObjCustomer customer = arrayListCustomer.get(0);
			System.out.println(
					customer.getCustomer() + ", " + 
					customer.getAddress() + ", " +
					customer.getHomePhone() + ", " +
					customer.getAnimal()
			);
			return;
		}
		fail();
		
	}

}
