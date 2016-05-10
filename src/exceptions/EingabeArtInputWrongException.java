package exceptions;

public class EingabeArtInputWrongException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String Message = "Input of EingabeArt is not correct!";

	public String GetMessage()
	{
		return Message;
	}

	public EingabeArtInputWrongException()
	{
		// TODO Auto-generated constructor stub
	}

	public EingabeArtInputWrongException(String arg0)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public EingabeArtInputWrongException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public EingabeArtInputWrongException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EingabeArtInputWrongException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
