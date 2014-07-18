import java.util.ArrayList;

/**
 * Derived class that represents a while statement in the SILLY language.
 *   @author Steven Warner
 *   @version 2/8/14
 */
public class While extends Statement {
    private Expression expr;
    private ArrayList<Statement> stmts;  
    
    /**
     * Reads in an while statement from the specified stream
     *   @param input the stream to be read from
     */
	public While(TokenStream input) throws Exception {
        Token keyword = input.next();
        if (!keyword.toString().equals("while")) {
	        throw new Exception("SYNTAX ERROR: Malformed while statement");
	    }

	    this.expr = new Expression(input);
        this.stmts = new ArrayList<Statement>();
        while (!input.lookAhead().toString().equals("end")) {
            this.stmts.add(Statement.getStatement(input));
        } 

        keyword = input.next();  
	}
    
    /**
	 * Executes the current while statement.
	 * 
	 */
	public void execute() throws Exception {
	    while (this.expr.evaluate().toBoolean()) {
	        for (Statement stmt : this.stmts) {
	            stmt.execute();
	        }
	    }
	}
	
	/**
	 * Converts the current assignment statement into a String.
	 *   @return the String representation of this statement
	 */
    public String toString() {
        String str = "while " + this.expr + "\n";
        for (Statement stmt : this.stmts) {
            str += "    " + stmt + "\n";
        }
        return str + "end";
    }
}
