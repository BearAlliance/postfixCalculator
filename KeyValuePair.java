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
