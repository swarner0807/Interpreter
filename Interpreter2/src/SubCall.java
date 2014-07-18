import java.util.ArrayList;


/**
 * Derived class that represents a sub call statement in the SILLY language.
 *   @author Steven Warner
 *   @version 3/29/14
 */
public class SubCall extends Statement {
	private ArrayList<String> paramCall;
	private Expression expr;

    /**
     * Reads in the subroutine from the SubDecl class
     *   @param input the stream to be read from
     */
	public SubCall(TokenStream input) throws Exception {
		this.paramCall = new ArrayList<String>();
		
        Token keyword = input.next();
        String keyword2 = input.next().toString();
        if (!keyword.toString().equals("call") || (!SubDecl.name.contains(keyword2))) {
	        throw new Exception("SYNTAX ERROR: Malformed sub call statement");
	    }
        
        /**
         * Reads in and evaluates the parameters from the input 
         */
        Interpreter.STACK.beginScope();
        if (input.next().toString().equals("(")) {
			while (!input.lookAhead().toString().equals(")")) {
				
				if (input.lookAhead().toString().equals("(")) {
	        		this.expr = new Expression(input);
	        		paramCall.add(expr.evaluate().toString());
	        	}
				
				else paramCall.add(input.next().toString());
			}
        }
        
        /**
         * Stores the parameter values
         */
        for (int i = 0; i < SubDecl.param.size(); i++) {
        	int x = Integer.parseInt(paramCall.get(i));
        	Interpreter.STACK.store(SubDecl.param.get(i), new IntegerValue(x));
        }
        
        keyword = input.next();
        
	}
	
  
    /**
	 * Executes the current subroutine statement.
	 */
	public void execute() throws Exception {
		
		for (Statement stmt : SubDecl.stmts) {
			stmt.execute();
		}
		
		Interpreter.STACK.endScope();
	}
	
	/**
	 * Converts the current assignment statement into a String.
	 *   @return the String representation of this statement
	 */
    public String toString() {
    	String str = "call";
    	for (Statement stmt : SubDecl.stmts) {
            str += "    " + stmt + "\n";
        }
        return str + "end";
    }
}
