import java.util.ArrayList;

/**
 * Derived class that represents a comment in the SILLY language.
 *   @author Steven Warner
 *   @version 2/8/14
 */
public class Comment extends Statement {
    private ArrayList<Statement> stmts;  
    
    /**
     * Reads in a comment from the specified stream
     *   @param input the stream to be read from
     */
	public Comment(TokenStream input) throws Exception {
        Token keyword = input.next();
        if (!keyword.toString().equals("//")) {
	        throw new Exception("SYNTAX ERROR: Malformed comment");
	    }

        keyword = input.next();  
	}
    
    /**
	 * Executes the current comment.
	 * 
	 */
	public void execute() throws Exception {
	  
	}
	
	/**
	 * Converts the current assignment statement into a String.
	 *   @return the String representation of this statement
	 */
    public String toString() {
        String str = "// ";
        for (Statement stmt : this.stmts) {
            str += "    " + stmt + "\n";
        }
        return str;
    }
}
