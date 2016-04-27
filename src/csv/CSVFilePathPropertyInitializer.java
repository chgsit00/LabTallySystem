package csv;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import exceptions.CSVFilePathNotFoundException;

public class CSVFilePathPropertyInitializer
{
	Properties prop = new Properties();
	InputStream input = null;

	public String GetCSVFilePath() throws CSVFilePathNotFoundException
	{
		try
		{
			input = getClass().getClassLoader().getResourceAsStream("config.properties");
			prop.load(input);
			String path = prop.getProperty("CSVFilesPath");
			if (path != null)
			{
				return path;
			} else
				throw new CSVFilePathNotFoundException();
		} catch (IOException ex)
		{
			ex.printStackTrace();
			return ex.getMessage();
		} finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
