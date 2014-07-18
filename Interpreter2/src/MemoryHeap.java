import java.util.ArrayList;

/**
 * Class that models the heap partition of memory.
 *   @author Dave Reed
 *   @version 2/8/14
 */
public class MemoryHeap {
    private ArrayList<String> memHeap;
    
    /**
     * Constructs an empty memory heap.
     */
    public MemoryHeap() {
    	this.memHeap = new ArrayList<String>();
    }
    
    /**
     * Stores a String value in the heap.
     * @param strValue the value being stored.
     * @return the address (index) where the value is stored in the heap.
     */
    public int store(String strValue) {
    	this.memHeap.add(strValue);
    	return this.memHeap.size()-1;
    }
    
    /**
     * Looks up a value stored in the heap.
     * @param index the address (index) of the desired value
     * @return the value stored at that index
     * @throws IndexOutOfBoundsException 
     */
    public String lookup(int index) throws IndexOutOfBoundsException {
    	if (index < 0 || index >= this.memHeap.size()) {
    		throw new IndexOutOfBoundsException("Illegal HEAP access");
    	}
		return this.memHeap.get(index);
    }
    
}
