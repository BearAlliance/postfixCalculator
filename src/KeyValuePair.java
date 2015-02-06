// Nick Cacace
// Postfix Expression Calculator
// Prof. Lee
// Advanced Data Structures
// 2/5/2015

class KeyValuePair
{
	private String userVarName;
	private int userValue;

	public KeyValuePair(String varName, int value)
	{
		userVarName = varName;
		userValue = value;
	}

	public String getVarName()
	{
		return userVarName;
	}

	public int getValue()
	{
		return userValue;
	}
}
