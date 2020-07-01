package sprocs;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.MainFrame;

public class spsAccountStatus {

    public static int findCreditRatingByAccountStatus(MainFrame mainFrame, int accStatusID) {
        int creditRating = 0;
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeSproc("exec spsAccountStatusByPk @AccountStatusID = " + accStatusID);
            rs = mainFrame.getDBManager().getResults();

            while (rs.next()) {
                creditRating = rs.getInt("CreditRating");
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return creditRating;
    }
}
