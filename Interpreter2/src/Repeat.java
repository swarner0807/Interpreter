import java.util.ArrayList;

/**
 * Derived class that represents a repeat statement in the SILLY language.
 *   @author Steven Warner
 *   @version 2/8/14
 */
public class Repeat extends Statement {
    private Expression expr;
    private ArrayList<Statement> stmts;  
    
    /**
     * Reads in an while statement from the specified stream
     *   @param input the stream to be read from
     */
	public Repeat(TokenStream input) throws Exception {
        Token keyword = input.next();
        if (!keyword.toString().equals("repeat")) {
	        throw new Exception("SYNTAX ERROR: Malformed repeat statement");
	    }

	    this.expr = new Expression(input);
        this.stmts = new ArrayList<Statement>();
        
        while (!input.lookAhead().toString().equals("end")) {
            this.stmts.add(Statement.getStatement(input));
        } 

        keyword = input.next();  
	}
    
    /**
	 * Executes the current repeat statement.
     * @param input 
	 * 
	 */
	public void execute() throws Exception { 
		DataValue value = this.expr.evaluate();
		/**
		 * Takes the length of the string and repeats the statement by that many
		 */
		if (value instanceof StringValue) {
			String stringLength = (String)value.getValue();
			int stringRepeat = (stringLength.length() - 2);
			for (int i = 0; (i < stringRepeat); i++) {
	        	for (Statement stmt : this.stmts) {
		            stmt.execute();
		        }
	        	
				 }
		}
		/**
		 * Repeats the statement by the expression value
		 * 
		 */
			else{
		int timesToRepeat = (int) this.expr.evaluate().getValue();
	        for (int i = 0; (i < timesToRepeat); i++) {
	        	for (Statement stmt : this.stmts) {
		            stmt.execute();
		        }
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