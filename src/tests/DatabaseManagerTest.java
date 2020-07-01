package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.MainFrame;
import managers.DatabaseManager;

class DatabaseManagerTest {
	
	DatabaseManager dbManager = DatabaseManager.getInstance(MainFrame.getInstance());
	String query = "SELECT sppSpeciesID FROM tblAnimalSpecies WHERE sppSpecies LIKE 'dog'";
	String sproc = "exec spsEventTypes";

	@BeforeEach
	void setUp() throws Exception {
		dbManager.closeAll();
	}

	@AfterEach
	void tearDown() throws Exception {
		dbManager.closeAll();
	}

	@Test
	void testGetInstance() throws ClassNotFoundException, SQLException {
		if (dbManager.getConnection() == null &&
			dbManager.getStatement() == null &&
			dbManager.getCallableStatement() == null &&
			dbManager.getResults() == null) {
			return;
		}
		fail();
	}
	
	@Test
	void testInitConnection() throws ClassNotFoundException, SQLException {
		dbManager.initConnection(); 
		if (dbManager.getConnection() instanceof Connection &&
			dbManager.getStatement() == null &&
			dbManager.getCallableStatement() == null &&
			dbManager.getResults() == null) { 
			return; 
		}
		
		fail();
	}
	
	@Test
	void testExecuteQuery() throws ClassNotFoundException, SQLException {
		dbManager.initConnection(); 
		dbManager.executeQuery(query);
		if (dbManager.getConnection() instanceof Connection &&
			dbManager.getStatement() instanceof Statement &&
			dbManager.getCallableStatement() == null &&
			dbManager.getResults() instanceof ResultSet) {
			return;
		}
		fail();
	}
	
	@Test
	void testExecuteSproc() throws ClassNotFoundException, SQLException {
		dbManager.initConnection();
		dbManager.executeSproc(sproc);
		if (dbManager.getConnection() instanceof Connection &&
			dbManager.getStatement() == null &&
			dbManager.getCallableStatement() instanceof CallableStatement &&
			dbManager.getResults() instanceof ResultSet) {
			return;
		}
		fail();
	}
	
	@Test
	void testCloseAll() throws ClassNotFoundException, SQLException {
		dbManager.initConnection();
		dbManager.executeSproc(sproc);
		dbManager.closeAll();
		if (dbManager.getConnection() == null &&
			dbManager.getStatement() == null &&
			dbManager.getCallableStatement() == null &&
			dbManager.getResults() == null) {
			return;
		}
		fail();
	}

}
