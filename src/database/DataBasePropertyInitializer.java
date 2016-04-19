package database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import exceptions.DataBasePathNotFoundException;

public class DataBasePropertyInitializer
{
	Properties prop = new Properties();
	InputStream input = null;
	
	public String GetDataBasePath() throws DataBasePathNotFoundException
	{
		try {
			input = getClass().getClassLoader().getResourceAsStream("config.properties");
			prop.load(input);
			String path = prop.getProperty("DataBasePath");
			if(path!=null){
				return path;
			}
			else throw new DataBasePathNotFoundException();
		}
		catch (IOException ex) {
		    ex.printStackTrace();
		    return ex.getMessage();
		} 
		finally {
		      if (input != null) {
		          try {
		              input.close();
		          } catch (IOException e) {
		              e.printStackTrace();
		          }
		      }
		}
	}
}
