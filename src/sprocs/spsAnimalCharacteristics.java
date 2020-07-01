package sprocs;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import main.MainFrame;

public class spsAnimalCharacteristics {

	/*
	 * getSpeciesID
	 * PArameters: species name
	 * returns the ID number of that species name
	 */
	public static String getSpeciesID(MainFrame mainFrame, String species) {
        String speciesID = "";
        ResultSet rs = null;
        try {
        	mainFrame.getDBManager().executeQuery(
        			"SELECT sppSpeciesID FROM tblAnimalSpecies " + 
        			"WHERE sppSpecies LIKE '" + species + "'");
            
        	rs = mainFrame.getDBManager().getResults();

            while ( rs.next() ) {
                speciesID = rs.getString("sppSpeciesID");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return speciesID;
    }
	
	
	/*
	 * getAllSpecies
	 * returns all species in database
	 */
	 public static Map<String, Integer> getAllSpecies(MainFrame mainFrame) {
	        Map<String, Integer> speciesList = new TreeMap<>();
	        ResultSet rs = null;
	        try {
	        	mainFrame.getDBManager().executeQuery("exec spsAnimalSpeciesForValueLists");
	            rs = mainFrame.getDBManager().getResults();

	            while (rs.next()) {
	                String speciesName = rs.getString("Species");
	                int speciesID = rs.getInt("SpeciesID");

	                speciesList.put(speciesName, speciesID);
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return speciesList;
	    }
	 
	 
	 /*
	  * getBreedID
	  * Parameters: species name, breed name
	  * Returns the ID number of that breed name
	  */
	 public static String getBreedID(MainFrame mainFrame, String breed, String species) {
	        String breedID = "";
	        ResultSet rs = null;
	        try {
	        	mainFrame.getDBManager().executeQuery(
	        			"SELECT brdBreedID FROM tblAnimalBreeds " + 
	        			"WHERE brdBreed LIKE '" + breed + "' " + 
	        			"AND brdSpecies LIKE '" + species + "'");
	            
	        	rs = mainFrame.getDBManager().getResults();

	            while ( rs.next() ) {
	                breedID = rs.getString("brdBreedID");
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return breedID;
	    }
	 
	/*
	 * getAllBreeds
	 * Parameters: speciesID
	 * Returns all the breed names for that particular species 
	 */
	 public static Map<String, Integer> getAllBreeds(MainFrame mainFrame, int speciesID) {
	        Map<String, Integer>  breedList = new TreeMap<>();
	        ResultSet rs = null;
	        try {
	        	mainFrame.getDBManager().executeSproc(
	        			"exec spsAnimalBreedsForValueLists " +
	        			"@SpeciesID = " + speciesID);
	        	
	        	rs = mainFrame.getDBManager().getResults();

	            while (rs.next()) {
	                String breedName = rs.getString("Breed");
	                int breedID = rs.getInt("BreedID");

	                breedList.put(breedName, breedID);
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return breedList;
	    }
	
	 
	 /*
	  * getColourID
	  * Parameters: colour name, species name
	  * Returns ID number of the colour associated with the species
	  */
	 public static String getColourID(MainFrame mainFrame, String colour, String species) {
	        String colourID = "";
	        ResultSet rs = null;
	        try {
	        	mainFrame.getDBManager().executeQuery(
	        			"SELECT clrAnimalColourID FROM tblAnimalColours " +
	        			"WHERE clrColour LIKE '" + colour + "' " +
	                    "AND clrSpecies LIKE '" + species + "'");
	            
	        	rs = mainFrame.getDBManager().getResults();

	            while ( rs.next() ) {
	                colourID = rs.getString("clrAnimalColourID");
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return colourID;
	    }
	 
	 
	 /*
	  * getAllAnimalColours
	  * Parameters: speciesID
	  * Returns mapping of colour name and ID for that particular species
	  */
	   public static Map<String, Integer> getAnimalColours(MainFrame mainFrame, int speciesID) {
	        Map<String, Integer> colourList = new TreeMap<>();
	        CallableStatement clstmt = null;
	        ResultSet rs = null;
	        try {
	        	mainFrame.getDBManager().executeSproc(
	        			"exec spsAnimalColoursForValueLists " +
	        			"@SpeciesID = " + speciesID);
	        	
	        	rs = mainFrame.getDBManager().getResults();

	            while (rs.next()) {
	                String colour = rs.getString("Colour");
	                int colourID = rs.getInt("AnimalColourID");

	                colourList.put(colour, colourID);
	            }
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return colourList;
	    }
}
