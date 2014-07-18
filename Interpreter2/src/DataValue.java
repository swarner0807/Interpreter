/**
 * Interface that defines the data type operations for the SILLY language. 
 * @author Dave Reed
 * @version 2/8/14
 */
public interface DataValue {   
    public Object getValue();
    public boolean toBoolean();
    public String toString();
}