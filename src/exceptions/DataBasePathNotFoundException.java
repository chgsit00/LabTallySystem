package exceptions;

public class DataBasePathNotFoundException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String Message = "Database not found !";
	
	public String GetMessage(){
		return Message;
	}
	
	public DataBasePathNotFoundException()
	{
		// TODO Auto-generated constructor stub		
	}

	public DataBasePathNotFoundException(String arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DataBasePathNotFoundException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DataBasePathNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataBasePathNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
