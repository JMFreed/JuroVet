package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.MainFrame;
import searchforms.SearchFormAddress;
import searchforms.SearchFormCustomer;

class ActionManagerTest {
	
	MainFrame mainFrame = MainFrame.getInstance();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 * SHOW/HIDE SEARCH FORM TESTS
	 */
	
	@Test
	void testHideSearchFrameAction() {
		mainFrame.getSearchFrame().setSearchForm(mainFrame.getSearchFormCustomer());
		mainFrame.getSearchFrame().setVisible(true);
		mainFrame.getSearchFormCustomer().getCloseButton().doClick();
		if (!mainFrame.getSearchFrame().isVisible()) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testShowSearchCustomerFrameAction() {
		mainFrame.getSearchFrame().setSearchForm(mainFrame.getSearchFormCustomer());
		mainFrame.getMainMenuBar().getSearchCustomerItem().doClick();
		if (mainFrame.getSearchFrame().isVisible() &&
				mainFrame.getSearchFrame().getSearchForm() instanceof SearchFormCustomer) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testShowSearchAddressFrameAction() {
		mainFrame.getSearchFrame().setSearchForm(mainFrame.getSearchFormAddress());
		mainFrame.getMainMenuBar().getSearchAddressItem().doClick();
		if (mainFrame.getSearchFrame().isVisible() &&
				mainFrame.getSearchFrame().getSearchForm() instanceof SearchFormAddress) {
			return;
		}
		fail();
	}
	
	/*
	 * SEARCH ACTION TESTS
	 */
	
	@Test
	void testSearchCustomerByNameAction() {
		mainFrame.getSearchFormCustomer().clearFields();
		mainFrame.getSearchFrame().setSearchForm(mainFrame.getSearchFormCustomer());
		mainFrame.getSearchFormCustomer().getCusSurnameField().setText("freed");
		mainFrame.getSearchEventCustomer().updateInformation(mainFrame.getSearchFormCustomer());
		mainFrame.getSearchFormCustomer().getSearchButton().doClick();
		if (mainFrame.getListCustomer().getData().length == 8) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testSearchCustomerByAnimalAction() {
		mainFrame.getSearchFormCustomer().clearFields();
		mainFrame.getSearchFrame().setSearchForm(mainFrame.getSearchFormCustomer());
		mainFrame.getSearchFormCustomer().getCusSurnameField().setText("freed");
		mainFrame.getSearchFormCustomer().getCusAccountField().setText("woody");
		mainFrame.getSearchEventCustomer().updateInformation(mainFrame.getSearchFormCustomer());
		mainFrame.getSearchFormCustomer().getSearchButton().doClick();
		if (mainFrame.getListCustomer().getData().length == 1) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testSearchAddressByNameAction() {
		mainFrame.getSearchFormAddress().clearFields();
		mainFrame.getSearchFrame().setSearchForm(mainFrame.getSearchFormAddress());
		mainFrame.getSearchFormAddress().getAddressField().setText("fern cottage");
		mainFrame.getSearchEventAddress().updateInformation(mainFrame.getSearchFormAddress());
		mainFrame.getSearchFormAddress().getSearchButton().doClick();
		if (mainFrame.getListAddress().getData().length == 5) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testSearchAddressByPostcodeAction() {
		mainFrame.getSearchFormAddress().clearFields();
		mainFrame.getSearchFrame().setSearchForm(mainFrame.getSearchFormAddress());
		mainFrame.getSearchFormAddress().getPostcodeField().setText("me13 0bs");
		mainFrame.getSearchEventAddress().updateInformation(mainFrame.getSearchFormAddress());
		mainFrame.getSearchFormAddress().getSearchButton().doClick();
		if (mainFrame.getListAddress().getData().length == 11) {
			return;
		}
		fail();
	}
	
	
	/*
	 * SHOW/HIDE LIST ACTIONS
	 */
	
	@Test
	void testShowListCustomerAction() {
		mainFrame.getMainMenuBar().getSearchCustomerItem().doClick();
		if (mainFrame.getListCustomer().getFrame().isVisible()) {
			return;
		}
		fail();
	}
	
	@Test
	void testHideListCustomerAction() {
		mainFrame.getListCustomer().getCloseButton().doClick();
		if (!mainFrame.getListCustomer().getFrame().isVisible()) {
			return;
		}
		fail();
	}
	
	
	@Test
	void testShowListAddressAction() {
		mainFrame.getMainMenuBar().getSearchAddressItem().doClick();
		if (mainFrame.getListAddress().getFrame().isVisible()) {
			return;
		}
		fail();
	}
	
	@Test
	void testHideListAddressAction() {
		mainFrame.getListAddress().getCloseButton().doClick();
		if (!mainFrame.getListAddress().getFrame().isVisible()) {
			return;
		}
		fail();
	}

}
