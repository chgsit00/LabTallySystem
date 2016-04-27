package exceptions;

public class CSVFilePathNotFoundException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String Message = "CSV File not found !";

	public String GetMessage()
	{
		return Message;
	}

	public CSVFilePathNotFoundException()
	{
		// TODO Auto-generated constructor stub
	}

	public CSVFilePathNotFoundException(String arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public CSVFilePathNotFoundException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CSVFilePathNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CSVFilePathNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
