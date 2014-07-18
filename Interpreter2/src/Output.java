/**
 * Derived class that represents an output statement in the SILLY language.
 *   @author Dave Reed
 *   @version 2/8/14
 */
public class Output extends Statement {
	private Expression expr;
	
    /**
     * Reads in an output statement from the specified TokenStream
     *   @param input the stream to be read from
     */
	public Output(TokenStream input) throws Exception {
	    Token op = input.next();
	    this.expr = new Expression(input);
	    
	    if (!op.toString().equals("output")) {
	        throw new Exception("SYNTAX ERROR: Malformed output statement");
	    }
	}
	
    /**
	 * Executes the current assignment statement.
	 */
	public void execute() throws Exception {
	    System.out.println(this.expr.evaluate().getValue());    
	}
	
	/**
	 * Converts the current output statement into a String.
	 *   @return the String representation of this statement
	 */
	public String toString() {
	    return "output " + this.expr;    
	}
}
