import java.util.ArrayList;

/**
 * Derived class that represents a sub declaration statement in the SILLY language.
 *   @author Steven Warner
 *   @version 3/29/14
 */
public class SubDecl extends Statement {
	public static ArrayList<Statement> stmts;
	public static ArrayList<Token> param;
	public static String name;
	
    /**
     * Reads in an sub declaration statement from the specified stream
     *   @param input the stream to be read from
     */
	public SubDecl(TokenStream input) throws Exception {
        Token keyword = input.next();
        if (!keyword.toString().equals("sub")) {
	        throw new Exception("SYNTAX ERROR: Malformed sub declaration statement");
	    }
        
        /**
         * Stores the name and parameters of a sub declaration
         */
        SubDecl.stmts = new ArrayList<Statement>();
        SubDecl.param = new ArrayList<Token>();
        name = input.next().toString();
	        if (input.next().toString().equals("(")) {
				while (!input.lookAhead().toString().equals(")")) {
					Token paramToken = input.next();
					param.add(paramToken);
				}
			}
	        keyword = input.next(); 
	        
	        /**
	         * Declares local variables
	         */
        	if (input.lookAhead().toString().equals("local")) {
        		keyword = input.next();
        			if (input.next().toString().equals("(")) {
        				while (!input.lookAhead().toString().equals(")")) {
        					Interpreter.STACK.declareLocal(input.next());
        				}
        			}
        		keyword = input.next(); 
        	}
        	
        	/**
        	 * Stores statements until "end"
        	 */
        	
        	while (!input.lookAhead().toString().equals("end")) {
        		Interpreter.STACK.beginScope();
        		SubDecl.stmts.add(Statement.getStatement(input));
        	} 
        	
        
    	keyword = input.next();  
	}
     
    
    /**
	 * Executes the current sub declaration statement.
	 * 
	 */
	public void execute() throws Exception {
		
	}
	
	/**
	 * Converts the current assignment statement into a String.
	 *   @return the String representation of this statement
	 */
    public String toString() {
        return "end";
    }
}
