public class StringValue implements DataValue {
    public int address;
    
	/**
	 * Constructs a String value.
	 *   @param str the String being stored
	 */
    public StringValue(String str) {
    	this.address = Interpreter.HEAP.store(str);
    }
    
	/**
	 * Accesses the stored String value.
	 *   @return the String value (as an Object)
	 */
    public Object getValue() {
        return Interpreter.HEAP.lookup(this.address);
    }
    
	/**
	 * Converts the String value to a boolean.
	 *   @return true if value is nonempty; else false
	 */
    public boolean toBoolean() {
        return !((String)this.getValue()).equals("");
    }
    
	/**
	 * Converts the String value to a String.
	 *   @return the stored String value
	 */
    public String toString() {
        return (String)this.getValue();
    }
}

