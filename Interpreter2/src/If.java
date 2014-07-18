import java.util.ArrayList;

/**
 * Derived class that represents an if statement in the SILLY language.
 *   @author Dave Reed
 *   @author Steven Warner
 *   @version 2/8/14
 */
public class If extends Statement {
    private Expression expr;
    private ArrayList<Statement> stmts;  
    private ArrayList<Statement> stmts2;
    
    /**
     * Reads in an if statement from the specified stream
     *   @param input the stream to be read from
     */
	public If(TokenStream input) throws Exception {
        Token keyword = input.next();
        if (!keyword.toString().equals("if")) {
	        throw new Exception("SYNTAX ERROR: Malformed if statement");
	    }

	    this.expr = new Expression(input);
        this.stmts = new ArrayList<Statement>();
        this.stmts2 = new ArrayList<Statement>();
        
       /**
        *  Adds if statement to stmts and the else statement, if there is one, to stmts2
        */
      
        while (!input.lookAhead().toString().equals("end") && (!input.lookAhead().toString().equals("else"))) {
            this.stmts.add(Statement.getStatement(input));
        }
        if (!input.lookAhead().toString().equals("end") && (input.next().toString().equals("else"))) {
        	while (!input.lookAhead().toString().equals("end")){ 
        		this.stmts2.add(Statement.getStatement(input));
        	}
        
        }
        
       

        keyword = input.next();  
	}
    
    /**
	 * Executes the current if statement.
	 */
	public void execute() throws Exception {
	    if (this.expr.evaluate().toBoolean()) {
	        for (Statement stmt : this.stmts) {
	            stmt.execute();
	            }
	     
	    }
	    else {
	    	for (Statement stmt2 : this.stmts2) {
	    		stmt2.execute();
	    	}
	    }
	}
	
	/**
	 * Converts the current assignment statement into a String.
	 *   @return the String representation of this statement
	 */
    public String toString() {
        String str = "if " + this.expr + "\n";
        for (Statement stmt : this.stmts) {
            str += "    " + stmt + "\n";
        }
        
        return str + "end";
    }
}
