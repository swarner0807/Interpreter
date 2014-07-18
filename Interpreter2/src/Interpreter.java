
/**
 * Main method for the SILLY Interpreter. 
 * 
 * @author Dave Reed 
 * @version 2/8/14
 */
public class Interpreter {
	public static MemoryStack STACK = new MemoryStack();
	public static MemoryHeap HEAP = new MemoryHeap();
    
    /** 
     * Main method for starting the SILLY interpreter.
     */
    public static void main(String[] args) throws Exception {        
        TokenStream input;
        if (args.length == 0) {
            input = new TokenStream();
        }
        else {
            input = new TokenStream(args[0]);
        }
        
        Statement stmt;
        do {
            if (args.length == 0) {
                System.out.print(">>> ");
            }
            stmt = Statement.getStatement(input);
         
            stmt.execute();
        } while (!(stmt instanceof Quit));

    }
}
