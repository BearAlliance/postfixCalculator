# postfixCalculator
A Java program that calculates postfix expressions from a file "in.dat"

I. The Output

   Output data and related information are sent to the screen.
   Your program should do the following for each expression:

   (1) printing the expression before it is evaluated;
   (2) prompting the user to enter a value for each variable;
   (3) reporting the result, i.e., the value of the expression, or
       an error message.


II. Conventions

   (1) The length of an expression is unknown.
   (2) The number of expressions in "in.dat" is unknown.
   (3) There are at least one space to separate tokens, i.e.,     
       constants, variables, operators, and end marks.
   (4) All information after the end mark $ are ignored.
   (5) All constants and variables are considered as integers.
   (6) Your program can ask the value of a variable only once even
       though a variable occurs more than once in an expression.
   (7) false = 0;  true = 1.
   (8) The following are valid characters and their meanings:

       +, -, *, /    integer arithmetic operators 
                     (/ is integer division)
       _             unary negation
       !             factorial
       #             square root (x # = the largest integer y
                                        such that y*y <= x)
       ^             exponentiation (a b ^ = a raised to the power b)
       <, <=, >, >=  relational operators
       ==, !=        equality operators
       &&, ||        logical operators
       0,1,...,9     characters for constants
       a,b,...,z     characters for variables
       $             the end mark 

       Note that the boolean data type is compatible with the
       integer date type. Therefore, truth values (0/1) can 
       participate in integer operations, and any integer value 
       can be regarded as a truth value (zero/nonzero).
