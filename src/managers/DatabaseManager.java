package managers;

import java.sql.*;

import main.MainFrame;

public class DatabaseManager {
	
	/*
	 * Handles connection to the database
	 * Handles the execution of queries and stored procedures
	 * Create connection, execute statement, get result set
	 * Close in following order: result set, statement, connection
	 */
	
	private static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DEFAULT_URL = "jdbc:sqlserver://localhost:1433;databaseName=SqlVet";
    private static final String DEFAULT_USERNAME = "sa";
    private static final String DEFAULT_PASSWORD = "*********";
	
	private MainFrame mainFrame;
    private static DatabaseManager instance = null;
	private Connection connection;
	private Statement statement;
	private CallableStatement callableStatement;
	private ResultSet resultSet;
	
	protected DatabaseManager(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.connection = null;
		this.statement = null;
		this.callableStatement = null;
		this.resultSet = null;
	}
	
	public static DatabaseManager getInstance(MainFrame mainFrame) {
	    if (instance == null) {
	      instance = new DatabaseManager(mainFrame);
	    }
	    return instance;
	}
	
	// Close result set, then statements, then connection
	public void closeAll() throws SQLException {
		try {
			if (getResults() instanceof ResultSet) { 
				getResults().close(); 
				this.resultSet = null; 
			}
			if (getCallableStatement() instanceof CallableStatement) { 
				getCallableStatement().close(); 
				this.callableStatement = null; 
			}
			if (getStatement() instanceof Statement) { 
				getStatement().close(); 
				this.statement = null; 
			}
			if (getConnection() instanceof Connection) { 
				getConnection().close(); 
				this.connection = null; 
			}
		}
		catch (SQLException e) { 
			e.printStackTrace(); 
		}
	}
	
	// ACCESSORS
	
	public Connection getConnection() { 
		if (this.connection != null) { 
			return this.connection; 
		} 
		return null; 
	}
	
	public Statement getStatement() { 
		if (this.statement != null) { 
			return this.statement; 
		} 
		return null; 
	}
	
	public CallableStatement getCallableStatement() { 
		if (this.callableStatement != null) { 
			return this.callableStatement; 
		} 
		return null; 
	}
	
	public ResultSet getResults() { 
		if (this.resultSet != null) { 
			return this.resultSet; 
		} 
		return null; 
	}
	
	
	// Initialise connection to the database
	public void initConnection() throws SQLException, ClassNotFoundException {
		try {
			Class.forName(DEFAULT_DRIVER_CLASS);
			this.connection = DriverManager.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
		}
		catch (SQLException | ClassNotFoundException e) { 
			e.printStackTrace();
		}
	}
	
	
	public void executeQuery(String query) throws SQLException, ClassNotFoundException {
		try {
			initConnection();
			this.statement = getConnection().createStatement();
			getStatement().execute(query);
			this.resultSet = getStatement().getResultSet();
		}
		catch (SQLException | ClassNotFoundException e) { 
			e.printStackTrace(); 
		}
	}
	
	
	public void executeSproc(String sproc) throws SQLException, ClassNotFoundException {
		try {
			initConnection();
			this.callableStatement = getConnection().prepareCall(sproc);
			getCallableStatement().execute();
			this.resultSet = getCallableStatement().getResultSet();
		}
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}