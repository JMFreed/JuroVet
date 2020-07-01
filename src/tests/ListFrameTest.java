package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lists.ListCustomer;
import lists.ListFrame;
import main.MainFrame;

class ListFrameTest {
	
	MainFrame mainFrame = MainFrame.getInstance();
	String[][] data = new String[ListCustomer.columnNames.length][0];
	ListCustomer listCustomer = new ListCustomer(mainFrame, data);
	ListFrame listFrame = new ListFrame(listCustomer, mainFrame);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void testListFrameInit() {
		ListFrame newListFrame = new ListFrame(listCustomer, mainFrame);
		if (newListFrame instanceof ListFrame) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testListFrameObjects() {
		if(listFrame.getList() instanceof ListCustomer &&
				listFrame.getMainFrame() instanceof MainFrame) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testListFrameSize() {
		if (listFrame.getWidth() == 800 &&
				listFrame.getHeight() == 300) {
			return;
		}
		fail();
	}
	

}
