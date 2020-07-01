package dateconverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {

    public static boolean yyyyMMddValidDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
        	sdf.parse(strDate);
        }
        catch (ParseException e) {
        	return false;
        }
        return true;
    }

    public static String yyyyMMddToddMMyyyy(String strDate) throws ParseException {
        String formattedDate = "N/A";
        if (yyyyMMddValidDate(strDate)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            formattedDate = dateFormat.format(new SimpleDateFormat("yyyy-MM-dd").parse(strDate.trim()));
        }
        return formattedDate;
    }

    public static boolean ddMMyyyyValidDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
        	sdf.parse(strDate);
        }
        catch (ParseException e) {
        	return false;
        }
        return true;
    }

    public static String ddMMyyyyToyyyyMMdd(String strDate) throws ParseException {
        String date = "N/A";
        if (ddMMyyyyValidDate(strDate)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.format(new SimpleDateFormat("dd-MM-yyyy").parse(strDate.trim()));
        }
        return date;
    }
}
