// Nick Cacace
// Postfix Expression Calculator
// Prof. Lee
// Advanced Data Structures
// 2/5/2015

// This program calculates the value of a postfix expression from a file "in.dat" with 
// possible inclusion of unknown variables. There must be a whitespace between each operator, operand, and variable

import java.util.*;
import java.util.regex.*;
import java.math.*;
import java.io.*;

public class postfix
{
	public static KeyValuePair[] userVars = new KeyValuePair[1]; // Array of variable pair objects
	public static int userVarCounter = 0; // Records how many variables are currently stored

    public static void main(String[] args) 
    {
        System.out.println("");
        System.out.println("Postfix form expression calculator by N. Cacace");
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader("in.dat"));
            String line = inputStream.readLine(); 
            //end of file when next line is null, 
            //file is empty when first line is null
            while (line != null) {
                System.out.println("");
                System.out.println("The expression to be evaluated is: " + line);
                evaluate(new Scanner(line));
                line = inputStream.readLine(); //loads next line into line
            }
        inputStream.close();
        System.out.println("Finished!");
        }
        //handnle possible errors
        catch (FileNotFoundException e)
        {
            System.out.println("File does not exist");
        }
        catch (IOException e)
        {
            System.out.println("Error Reading From in.dat");
        }
    }

    public static void evaluate(Scanner input)
    {
        Stack<Integer> operands = new Stack<Integer>(); // Stored operands
        String next; // Current working token

        while (input.hasNext()) // if there is a next token
        {
            // if the token is an int
            if (input.hasNextInt()) { 
                //push it onto the stack
                operands.push(input.nextInt()); 
            }
            // if the token is a variable
            else if (input.hasNext(VARIABLE)) {
                operands.push(defineVariable(input.findInLine(VARIABLE)));
            }
            // if the token is an operator
            else if (input.hasNext(OPERATOR)) 
            { 
                next = input.findInLine(OPERATOR).trim(); // Set next equal to it, trim whitespaces
                if (next.equals("+")) {//addition
                    operands.push(operands.pop() + operands.pop());
                }
                else if (next.equals("-")) { //subtraction
                    int tempSub = operands.pop();
                    operands.push(operands.pop() - tempSub);
                }
                else if (next.equals("*")) {//multiplication
                   operands.push(operands.pop() * operands.pop());
                }
                else if (next.equals("/")) {//division
                    int tempDiv = operands.pop();
                    operands.push(operands.pop() / tempDiv);
                }
                else if (next.equals("^")) {//exponent
                    double tempExp = new Double(operands.pop());
                    operands.push((int)Math.pow(operands.pop().doubleValue(), tempExp));
                }
                else if (next.equals("#")) { //square root
                    operands.push((int)Math.sqrt(operands.pop()));
                }
                else if (next.equals("_")) { //unary negation
                    operands.push(-operands.pop());
                }
                else if (next.equals("!")) { //factorial
                        operands.push(factorial(operands.pop()));
                }
                else if (next.equals("<")) { //less than
                    if (operands.pop() > operands.pop())
                        operands.push(1);
                    else
                        operands.push(0);
                }
                else if (next.equals("<=")) { //less than or equal to
                    if (operands.pop() >= operands.pop())
                        operands.push(1);
                    else
                        operands.push(0);
                }
                else if (next.equals("==")) { //equal to
                    if (operands.pop() == operands.pop())
                        operands.push(1);
                    else
                        operands.push(0);
                }
                else if (next.equals(">")) { //greater than
                    if (operands.pop() < operands.pop())
                        operands.push(1);
                    else
                        operands.push(0);
                }
                else if (next.equals(">=")) { //greater than or equal to
                    if (operands.pop() <= operands.pop())
                        operands.push(1);
                    else
                        operands.push(0);
                }
                else if (next.equals("!=")) { //not equal to
                    if (operands.pop() != operands.pop())
                        operands.push(1);
                    else
                    operands.push(0);
                }
                else if (next.equals("&&")) { //and
                    if (operands.pop() > 0 && operands.pop() > 0)
                        operands.push(1);
                    else
                       operands.push(0);
                }
                else if (next.equals("||")) { //or
                        if (operands.pop() >= 0 || operands.pop() >= 0)
                            operands.push(1);
                        else
                            operands.push(0);
                }
                else if (next.equals("$")) { //end
                	for (int i = 0; i <= userVarCounter; i++) // Erase existing stored variables
                		userVars[i] = null;
                	userVarCounter = 0; // Reset variable counter
                    System.out.println("The value of this expression is " + operands.pop()); // Print Result
                    return; // Return to main
                }
                else {
                    // If there is an urecognized token, it will continue on to the next one,
                    // and disregard the most recent operand
                    System.out.println("There is an unrecognized operator");
                    System.out.println("Ignoring most recent operand");
                    operands.pop();
                }
            }
        }
    }

    // Calculates the factorial of a postivie number
    public static int factorial(int n)
    {
        if (n < 1) {
            System.out.println("Factorial cannot be taken from negative or zero");
            System.out.println("Using zero as result");
            return n;
        }
        else if (n <= 1)
            return 1;
        else
            return n * factorial(n-1);
    }

    // Prompts the user for the value of an unknown variable
    public static int defineVariable(String inputVar)
    {
    	String userTemp; // Placeholder until type int can be verified
    	int userVar; // Verified user input

    	if (alreadyExists(inputVar)) // If the variable value has already been given
    		return getExisting(inputVar); // Return the stored user value
    	else {
            ensureCapacity(); // Make sure the variable array is big enough
        	while(true) {
            	System.out.print("Enter the value of " + inputVar + " > "); // Propt user for value
            	Scanner userIn = new Scanner(System.in);
            	userTemp = userIn.nextLine(); // Get the input from the user
            	if (userTemp.matches("\\d+|-\\d+")) { // If the input is an accpetable value
            		userVar = Integer.parseInt(userTemp); // Make it in integer
            		userVars[userVarCounter] = new KeyValuePair(inputVar, userVar); // Store it
            		userVarCounter++; // Increment the counter
                	return userVar; // Return the value
                }
           		else { // If input is not acceptable
            	    System.out.println("That was not a valad input"); // Error Message
        	        System.out.println("");
        	    }
    		}
        }
    }

    // Determines if the given variable is already stored
    public static boolean alreadyExists(String inputVar)
    {
        if (userVars[0] == null) // If there are no stored variables
            return false;
    	for (int i = 0; i < userVarCounter; i++) {
    		if (userVars[i].getVarName().equals(inputVar)) // If there is a variable that matches the input
    			return true;
    	}
    	return false;
    }

    // Returns the value of the variable matching the string inputVar
    public static int getExisting(String inputVar)
    {
    	int i;
    	for (i = 0; i <= userVarCounter; i++) {
    		if (userVars[i].getVarName().equals(inputVar))
    			return userVars[i].getValue();
    	}
        // Unlikely
        System.out.println("Could not find stored variable");
        return 0;
    }

    // Ensures that the vairiable object array has enough slots to continue
    public static void ensureCapacity()
    {
        if (userVarCounter >= userVars.length) {
            KeyValuePair[] temp = new KeyValuePair[userVarCounter+2];
            for (int i = 0; i < userVarCounter; i++)
                temp[i] = userVars[i];
            userVars = temp;
        }
        return;
    }

    // Pattern assignment
    public static final Pattern VARIABLE = 
     Pattern.compile("value|[a-z]");
    public static final Pattern OPERATOR = 
     Pattern.compile("[^\\w^\\s]{1,2}|_|\\^");
}
