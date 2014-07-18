import java.util.Stack;

/**
 * (Updated) class that models the stack partition of memory.
 *   @author Dave Reed
 *   @version 3/25/14
 */
public class MemoryStack {
	// Inner class for an activation record on the runtime stack.	
	private class ActivationRecord {
		// Inner class for pairing a variable name and value in a single object.
		private class Pair {
			private Token token;
			private DataValue value;
			
			public Pair(Token t, DataValue v) {
				this.token = t;
				this.value = v;
			}
			
			public DataValue getValue() {
				return this.value;
			}
			
			public void setValue(DataValue v) {
				this.value = v;
			}
			
			public boolean equals(Object other) {
				return this.token.equals(((Pair)other).token);
			}
		}
		
		///////////////////////////////////////////////////////////
		
		private Stack<Pair> memStack;
		
		public ActivationRecord() {
			this.memStack = new Stack<Pair>();
		}
		
		public boolean inScope(Token variable) {
			return this.memStack.contains(new Pair(variable, null));
		}
		
		public void store(Token variable, DataValue val) {
	    	int index = this.memStack.indexOf(new Pair(variable, null));
	    	if (index == -1) {
	    		this.memStack.push(new Pair(variable, val));    	
	    	}
	    	else {
	    	    this.memStack.get(index).setValue(val);
	    	}			
		}
		
	    public DataValue lookup(Token variable) throws Exception {
	    	int index = this.memStack.indexOf(new Pair(variable, null));
	    	if (index == -1) {
	    		throw new Exception("UNITIALIZED VARIABLE: " + variable);
	    	}
	    	return this.memStack.get(index).getValue();
	    }
	}
	
	/////////////////////////////////
	
    private Stack<ActivationRecord> runtimeStack;
    
    /**
     * Constructs a runtime stack with a single activation record (for main). 
     */
    public MemoryStack() {
    	this.runtimeStack = new Stack<ActivationRecord>();
    	this.beginScope();
    }
    
    /**
     * Begins a new scope by pushing an activation record on the runtime stack.
     */
    public void beginScope() {
    	this.runtimeStack.push(new ActivationRecord());
    }

    /**
     * Ends the current scope by popping an activation record off the runtime stack.
     */
    public void endScope() {
    	this.runtimeStack.pop();
    }
    
    /**
     * Stores a data value in either the current scope (if the variable is
     * declared in its activation record) or the global scope (if not).
     * @param variable the variable name the value is being stored under
     * @param val the data value (either an integer or a String)
     */
    public void store(Token variable, DataValue val) {
    	if (this.runtimeStack.peek().inScope(variable)) {
    		this.runtimeStack.peek().store(variable,  val);
    	}
    	else {
    		this.runtimeStack.get(0).store(variable, val);
    	}
    }
    
    /**
     * Looks up the value associated with a variable in the stack (accessing the 
     * scope first, then the global scope if not found).
     * @param variable the variable name being looked up
     * @return the data value for that variable (either an integer or a String)
     */
    public DataValue lookup(Token variable) throws Exception {
    	if (this.runtimeStack.peek().inScope(variable)) {
    		return this.runtimeStack.peek().lookup(variable);
    	}
    	else {
    		return this.runtimeStack.get(0).lookup(variable);
    	}
    }
    
    /**
     * Declares a local variable in the current scope (with initial value 0).
     * @param variable the variable name being declared
     */
    public void declareLocal(Token variable) {
    	this.runtimeStack.peek().store(variable, new IntegerValue(0));
    }
}
